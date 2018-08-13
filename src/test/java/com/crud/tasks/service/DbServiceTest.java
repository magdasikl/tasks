package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DbServiceTest {

    @Mock
    private DbService dbService;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> listTask = new ArrayList<>();
        listTask.add(new Task(1L,"Task1","comment"));
        //when
        when(dbService.getAllTasks()).thenReturn(listTask);
        //then
        assertEquals("Task1",dbService.getAllTasks().get(0).getTitle());

    }

    @Test
    public void testGetTask() {
        //Given
        Optional<Task> task = Optional.of(new Task(1L, "Task1", "comment"));
        //when
        when(dbService.getTask(1L)).thenReturn(task);
        //then
        assertEquals("Task1",dbService.getTask(1L).get().getTitle());

    }

    @Test
    public void saveTask() {
        //Given
        Task task = new Task(1L, "Task1", "comment");
        //when
        when(dbService.saveTask(task)).thenReturn(task);
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
        when(dbService.getTasksByTitle(tesk)).thenReturn(listTask);
        //then
        assertEquals("Task1",dbService.getTasksByTitle(tesk).get(0).getTitle());

    }
}