package com.example.demo;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/taskManager")
@CrossOrigin
public class TaskController {
    
    @Autowired
    private TaskService service;

    // @GetMapping(value="/tasks", produces={"application/json","application/xml"})
    // public Collection<Task> getTasks() { 
    //     return service.getTasks(); }

    @GetMapping(value="/tarefas", produces={"application/json","application/xml"})
	public ResponseEntity<Collection<Task>> getTasks() {
		Collection<Task> result = service.getTasks();
		return ResponseEntity.ok().body(result);
	}

    // @GetMapping(value="/task/{id}", produces={"application/json","application/xml"})
	// public Task getTask(@PathVariable int id) {
	// 	return service.getTask(id);
	// }

	@GetMapping(value="/tarefas/{id}", produces={"application/json","application/xml"})
	public ResponseEntity<Task> getItem(@PathVariable int id) {
		Task result = service.getTask(id);
		if (service.getTask(id) == null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok().body(result);
	}

	// @PostMapping(value="/task",
	// 		     consumes={"application/json","application/xml"},
	// 		     produces={"application/json","application/xml"})
	// @ResponseStatus(HttpStatus.CREATED)
	// public Task addTask(@RequestBody Task task) {
	// 	service.insert(task);
	// 	return task;
	// }

	@PostMapping(value="/tarefas",
			consumes={"application/json","application/xml"},
			produces={"application/json","application/xml"})
	public ResponseEntity<Task> addTask(@RequestBody Task task) {
		service.insert(task);
		URI uri = URI.create("/task/" + task.getId());
		return ResponseEntity.created(uri).body(task);
	}

	// @PutMapping(value="/task/{id}", consumes={"application/json","application/xml"})
	// public void modifyItem(@PathVariable int id, @RequestBody Task task) {
	// 	System.out.println("Modifying item to " + task);
	// 	service.update(task);
	// }    

	@PutMapping(value="/tarefas/{id}", consumes={"application/json","application/xml"})
	public ResponseEntity modifyTask(@PathVariable int id, @RequestBody Task task) {
		if (service.getTask(id) == null)
			return ResponseEntity.notFound().build();
		else {
			service.update(task);
			return ResponseEntity.ok().build();
		}
	}

    // @DeleteMapping("/task/{id}")
	// public void deleteItem(@PathVariable int id) {
	// 	service.delete(id);
	// }

    @DeleteMapping("/tarefas/{id}")
	public ResponseEntity deleteTask(@PathVariable int id) {
		if (service.getTask(id) == null)
			return ResponseEntity.notFound().build();
		else {
			service.delete(id);
			return ResponseEntity.ok().build();
		}
	}
}
