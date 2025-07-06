package com.example.todolist.repository;

import com.example.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
    
    List<Task> findByPriority(Task.Priority priority);
    
    @Query("SELECT t FROM Task t WHERE t.dueDate < CURRENT_TIMESTAMP AND t.completed = false")
    List<Task> findOverdueTasks();
    
    List<Task> findByTitleContainingIgnoreCase(String keyword);
    List<Task> findByDueDateBetweenAndCompletedFalse(LocalDateTime start, LocalDateTime end);
}