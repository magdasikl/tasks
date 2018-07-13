package com.crud.tasks.repository;

import com.crud.tasks.controller.TaskNotFoundException;
import com.crud.tasks.domain.Task;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    @Override
    Task findOne(Long aLong);

//     Optional<Task> findById(Long id);

    List<Task> findByTitle(String title);

    default Optional<Task> findById(Long id) {
//        Task maybeFound = findOne(id);
//        Optional.of(new Task());
//
//        String title = Optional.ofNullable(maybeFound)
//                .filter(task -> task.getId() > 1)
//                .map(task -> task.getTitle())
//               .orElse("brak");
////                .orElseThrow(RuntimeException::new);
//
//

        return Optional.ofNullable(findOne(id));
    }

    @Override
    Task save(Task task);

    void delete(Long id);

//    default Optional<Task> deleteTaskById(Long id) {
//        Optional<Task> task = findById(id);
//        task.ifPresent(this::delete);
//        return task;
//    }

    List<Task> findTasksByTitle(String nazwa);


    @Override
    Iterable<Task> findAll(Iterable<Long> longs);
}

