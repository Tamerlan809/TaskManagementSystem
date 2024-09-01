package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.entity.Task;
import com.example.taskmanagementsystem.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> findAllTask(){
        return taskRepository.findAll();
    }

    public void deleteTaskById(Long id){
        taskRepository.deleteById(id);
    }

    public Task findTaskById(Long id){
        return taskRepository.findById(id).orElse(null);
    }

    public void updateTask(Long id, Task updatedTask){
        Task existingTask = taskRepository.findById(id).orElse(null);
        if (existingTask != null){
            existingTask.setTitle(updatedTask.getTitle());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setDueDate(updatedTask.getDueDate());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setUser(updatedTask.getUser());
            taskRepository.save(existingTask);
        }
    }
}
