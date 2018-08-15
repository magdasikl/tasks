package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest

public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> listTask = new ArrayList<>();
        listTask.add(new Task(1L,"Task1","comment"));
        //when
        when(repository.findAll()).thenReturn(listTask);
        List<Task> tasks = dbService.getAllTasks();
        //then
        assertEquals("Task1",tasks.get(0).getTitle());

    }

    @Test
    public void testGetTask() {
        //Given
        Optional<Task> task = Optional.of(new Task(1L, "Task1", "comment"));
        //when
        when(repository.findById(1L)).thenReturn(task);
        //then
        assertEquals("Task1",dbService.getTask(1L).get().getTitle());

    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1L, "Task1", "comment");
        //when
        when(repository.save(task)).thenReturn(task);
        //then
        assertEquals("Task1",dbService.saveTask(task).getTitle());

    }

    @Test
    public void testGetTasksByTitle() {
        //Given
        List<Task> listTask = new ArrayList<>();
        listTask.add(new Task(1L,"Task1","comment"));
        String tesk = "Task1";
        //when
        when(repository.findTasksByTitle(tesk)).thenReturn(listTask);
        //then
        assertEquals("Task1",dbService.getTasksByTitle(tesk).get(0).getTitle());

    }
}