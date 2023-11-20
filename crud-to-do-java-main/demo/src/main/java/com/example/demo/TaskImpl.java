package com.example.demo;

import java.util.Collection;
import java.util.Map;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class TaskImpl implements TaskService {
    
    private static Map<Integer, Task> tasks = new HashMap<Integer, Task>(); 

    private int nextId = 1;

    {
        insert(new Task("Varrer a casa", true));
        insert(new Task("Estudar sobre matéria X", false));
        insert(new Task("fazer almoço", true));
        insert(new Task("Fazer a atividade x", false));
    }

    @Override
    public Task getTask(int id) { return tasks.get(id); }

    @Override
    public Collection<Task> getTasks() { return tasks.values(); }

    @Override
    public void insert(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    @Override
	public void update(Task task) {
		int id = task.getId();
		if (tasks.containsKey(id)) {
			tasks.put(id, task);
		}
	}

    @Override
	public void delete(int id) {
		Task task = tasks.get(id);
		if (task != null) {
			tasks.remove(id);
		}
	}
}
