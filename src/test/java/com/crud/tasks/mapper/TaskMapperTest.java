package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskMapperTest {
    @Autowired
    private TaskMapper taskMapper;

    @Test
    public void mapToTask() {
        //Given
        TaskDto taskDto = new TaskDto(1L,"name", "description");
        //When
        Task  task = taskMapper.mapToTask(taskDto);
        //Then
        assertEquals(task.getTitle(),taskDto.getTitle());
    }

    @Test
    public void mapToTaskDto() {
        //Given
        Task task = new Task(1L,"name", "description");
        //When
        TaskDto  taskDto = taskMapper.mapToTaskDto(task);
        //Then
        assertEquals(taskDto.getContent(),task.getContent());
    }

    @Test
    public void mapToTaskDtoList() {
        //Given
        List<Task> task = new ArrayList<>();
        task.add(new Task(1L, "name1", "desc.1"));
        task.add(new Task(2L, "name2", "desc.2"));
        //When
        List<TaskDto> taskDto = taskMapper.mapToTaskDtoList(task);
        //Then
        assertEquals(task.size(),taskDto.size());

    }
}