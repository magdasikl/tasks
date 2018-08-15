package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)

public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void testEmptyGetTask() throws Exception {
        //Given
        List<TaskDto> taskListDto = new ArrayList<>();
        List<Task> taskList = new ArrayList<>();
        when(service.getAllTasks()).thenReturn(taskList);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200)) //or isOk()
                .andExpect(jsonPath("$",hasSize(0)));
    }

    @Test
    public void testTaskGetLists () throws Exception {
        //Given
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(new TaskDto(1L,"Test List","content" ));
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L, "Test List", "content"));

        when(service.getAllTasks()).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);

        //When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id",is( 1)))
                .andExpect(jsonPath("$[0].title",is("Test List")))
                .andExpect(jsonPath("$[0].content",is("content")));

        }

        @Test
    public void testCreateTask () throws Exception {
        //Given
            TaskDto taskDto = new TaskDto(
                    1L,
                    "title",
                    "content");
            Task  task = new Task(1L, "title", "content");

            when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
            when(service.saveTask(task)).thenReturn(task);
            when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
            Gson gson = new Gson();
            String jsonContent = gson.toJson(taskDto);
            //When & Then
            mockMvc.perform(post("/v1/task/createTask")
                    .contentType(MediaType.APPLICATION_JSON)
                    .characterEncoding("UTF-8")
                    .content(jsonContent))
                        .andExpect(jsonPath("$.id",is(1)))
                        .andExpect(jsonPath("$.title",is("title")))
                        .andExpect(jsonPath("$.content", is("content")));

        }

        @Test
    public void testGetTask () throws Exception {
        //Given
            Optional<Task>  task = Optional.of(new Task(1L, "title", "content"));
            TaskDto taskDto = new TaskDto(
                    1L,
                    "title",
                    "content");
            when(service.getTask(1L)).thenReturn(task);
            when(taskMapper.mapToTaskDto(task.get())).thenReturn(taskDto);
            mockMvc.perform(get("/v1/task/getTask").contentType(MediaType.APPLICATION_JSON).param("taskId","1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id",is( 1)))
                    .andExpect(jsonPath("$.title",is("title")))
                    .andExpect(jsonPath("$.content",is("content")));
        }

    @Test
    public void testUpdateTask() throws Exception{
        //Given
        TaskDto taskDto = new TaskDto(
                1L,
                "title",
                "content");
        Task  task = new Task(1L, "title", "content");

        when(taskMapper.mapToTask(ArgumentMatchers.any(TaskDto.class))).thenReturn(task);
        when(service.saveTask(task)).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(taskDto);
        //When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.title",is("title")))
                .andExpect(jsonPath("$.content", is("content")));
    }

    @Test
    public void testGetTasksByTitle() throws Exception{
        //Given
        List<TaskDto> taskListDto = new ArrayList<>();
        taskListDto.add(new TaskDto(1L,"title","content" ));

        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(1L,"title", "content" ));

        when(service.getTasksByTitle("title")).thenReturn(taskList);
        when(taskMapper.mapToTaskDtoList(taskList)).thenReturn(taskListDto);
        //When & Then
        mockMvc.perform(get("/v1/task/getTasksByTitle")
                .contentType(MediaType.APPLICATION_JSON).param("title","title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id",is( 1)))
                .andExpect(jsonPath("$[0].title",is("title")))
                .andExpect(jsonPath("$[0].content",is("content")));

    }
}