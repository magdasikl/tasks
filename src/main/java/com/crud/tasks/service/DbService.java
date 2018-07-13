package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.LongLiteral;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

@Service
public class DbService {
    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTaskById(Long id){
        repository.delete(id);
    }
    public List<Task> getTasksByTitle(){
        return repository.findTasksByTitle("test3");
    }
}
