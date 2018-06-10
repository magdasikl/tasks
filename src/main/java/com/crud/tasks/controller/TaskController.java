package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/task")
public class TaskController {

    @RequestMapping(method = RequestMethod.GET,value = "getTasks")
    public List<TaskDao> getTasks() {
        return new ArrayList<>();
    }

    @RequestMapping(method = RequestMethod.GET,value = "getTask")
    public TaskDao getTask(Long taskId) {
        return new TaskDao(1L,"test title","test_content");
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteTask")
    public void deleteTask(Long taskId){
    }

    @RequestMapping(method = RequestMethod.PUT,value = "updateTask")
    public TaskDao updateTask(TaskDao taskDao) {
        return new TaskDao(1L,"Edited test title", "Test_content");
    }

    @RequestMapping(method = RequestMethod.POST,value = "createTask")
    public void createTask(TaskDao taskDao) {
    }
}
