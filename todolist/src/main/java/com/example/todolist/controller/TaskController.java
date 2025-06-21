package com.example.todolist.controller;

import com.example.todolist.exception.TaskNotFoundException;
import com.example.todolist.model.Task;
import com.example.todolist.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @ModelAttribute("newTask")
    public Task newTask() {
        return new Task();
    }

    private List<Task> getTodayTasks() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfDay = now.toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = now.toLocalDate().atTime(23, 59, 59);
        
        return taskService.getAllTasks().stream()
            .filter(task -> task.getDueDate() != null && 
                          !task.isCompleted() &&
                          task.getDueDate().isAfter(startOfDay) &&
                          task.getDueDate().isBefore(endOfDay))
            .sorted(Comparator.comparing(Task::getDueDate))
            .collect(Collectors.toList());
    }

    @GetMapping
    public String getAllTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("todayTasks", getTodayTasks());
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("pendingTasksCount", taskService.getPendingTasks().size());
        model.addAttribute("completedTasksCount", taskService.getCompletedTasks().size());
        model.addAttribute("filter", "all");
        return "tasks";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task, RedirectAttributes redirectAttributes) {
        try {
            taskService.createTask(task);
            redirectAttributes.addFlashAttribute("successMessage", "Task berhasil dibuat!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Gagal membuat task: " + e.getMessage());
        }
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/toggle")
    public String toggleTaskCompletion(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            taskService.toggleTaskCompletion(id);
            redirectAttributes.addFlashAttribute("successMessage", "Status task berhasil diubah!");
        } catch (TaskNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            taskService.deleteTask(id);
            redirectAttributes.addFlashAttribute("successMessage", "Task berhasil dihapus!");
        } catch (TaskNotFoundException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/tasks";
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks().stream()
            .sorted(Comparator.comparing(
                task -> task.getDueDate() == null ? LocalDateTime.MAX : task.getDueDate(),
                Comparator.nullsLast(Comparator.naturalOrder())
            ))
            .collect(Collectors.toList());
            
        model.addAttribute("tasks", completedTasks);
        model.addAttribute("todayTasks", getTodayTasks());
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("pendingTasksCount", taskService.getPendingTasks().size());
        model.addAttribute("completedTasksCount", completedTasks.size());
        model.addAttribute("filter", "completed");
        return "tasks";
    }

    @GetMapping("/pending")
    public String getPendingTasks(Model model) {
        List<Task> pendingTasks = taskService.getPendingTasks().stream()
            .sorted(Comparator.comparing(
                task -> task.getDueDate() == null ? LocalDateTime.MAX : task.getDueDate(),
                Comparator.nullsLast(Comparator.naturalOrder())
            ))
            .collect(Collectors.toList());
            
        long overdueCount = pendingTasks.stream()
            .filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now()))
            .count();
            
        long dueTodayCount = pendingTasks.stream()
            .filter(task -> task.getDueDate() != null && 
                          task.getDueDate().isAfter(LocalDateTime.now()) && 
                          task.getDueDate().isBefore(LocalDateTime.now().plusDays(1)))
            .count();

        model.addAttribute("tasks", pendingTasks);
        model.addAttribute("todayTasks", getTodayTasks());
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("pendingTasksCount", pendingTasks.size());
        model.addAttribute("completedTasksCount", taskService.getCompletedTasks().size());
        model.addAttribute("overdueCount", overdueCount);
        model.addAttribute("dueTodayCount", dueTodayCount);
        model.addAttribute("filter", "pending");
        return "tasks";
    }

    @GetMapping("/priority/{priority}")
    public String getTasksByPriority(@PathVariable String priority, Model model) {
        try {
            Task.Priority priorityEnum = Task.Priority.valueOf(priority.toUpperCase());
            List<Task> filteredTasks = taskService.getTasksByPriority(priorityEnum).stream()
                .sorted(Comparator.comparing(
                    task -> task.getDueDate() == null ? LocalDateTime.MAX : task.getDueDate(),
                    Comparator.nullsLast(Comparator.naturalOrder())
                ))
                .collect(Collectors.toList());
            
            long pendingCount = filteredTasks.stream()
                .filter(task -> !task.isCompleted())
                .count();
                
            long completedCount = filteredTasks.stream()
                .filter(Task::isCompleted)
                .count();
                
            long overdueCount = filteredTasks.stream()
                .filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now()))
                .count();
                
            long dueTodayCount = filteredTasks.stream()
                .filter(task -> task.getDueDate() != null && 
                              task.getDueDate().isAfter(LocalDateTime.now()) && 
                              task.getDueDate().isBefore(LocalDateTime.now().plusDays(1)))
                .count();

            model.addAttribute("tasks", filteredTasks);
            model.addAttribute("todayTasks", getTodayTasks());
            model.addAttribute("currentTime", LocalDateTime.now());
            model.addAttribute("pendingTasksCount", pendingCount);
            model.addAttribute("completedTasksCount", completedCount);
            model.addAttribute("overdueCount", overdueCount);
            model.addAttribute("dueTodayCount", dueTodayCount);
            model.addAttribute("filter", priorityEnum.name());
            return "tasks";
        } catch (IllegalArgumentException e) {
            return "redirect:/tasks";
        }
    }

    @GetMapping("/search")
    public String searchTasks(@RequestParam String keyword, Model model) {
        List<Task> searchResults = taskService.searchTasks(keyword).stream()
            .sorted(Comparator.comparing(
                task -> task.getDueDate() == null ? LocalDateTime.MAX : task.getDueDate(),
                Comparator.nullsLast(Comparator.naturalOrder())
            ))
            .collect(Collectors.toList());
            
        long overdueCount = searchResults.stream()
            .filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now()))
            .count();
            
        long dueTodayCount = searchResults.stream()
            .filter(task -> task.getDueDate() != null && 
                          task.getDueDate().isAfter(LocalDateTime.now()) && 
                          task.getDueDate().isBefore(LocalDateTime.now().plusDays(1)))
            .count();

        model.addAttribute("tasks", searchResults);
        model.addAttribute("todayTasks", getTodayTasks());
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("pendingTasksCount", taskService.getPendingTasks().size());
        model.addAttribute("completedTasksCount", taskService.getCompletedTasks().size());
        model.addAttribute("overdueCount", overdueCount);
        model.addAttribute("dueTodayCount", dueTodayCount);
        model.addAttribute("filter", "search");
        model.addAttribute("searchKeyword", keyword);
        return "tasks";
    }

    @GetMapping("/urgent")
    public String getUrgentTasks(Model model) {
        List<Task> urgentTasks = taskService.getAllTasks().stream()
            .filter(task -> task.getDueDate() != null && 
                          (task.getDueDate().isBefore(LocalDateTime.now().plusDays(1)) || 
                           task.getPriority() == Task.Priority.HIGH))
            .sorted(Comparator.comparing(
                task -> task.getDueDate() == null ? LocalDateTime.MAX : task.getDueDate(),
                Comparator.nullsLast(Comparator.naturalOrder())
            ))
            .collect(Collectors.toList());
            
        long overdueCount = urgentTasks.stream()
            .filter(task -> task.getDueDate() != null && task.getDueDate().isBefore(LocalDateTime.now()))
            .count();
            
        long dueTodayCount = urgentTasks.stream()
            .filter(task -> task.getDueDate() != null && 
                          task.getDueDate().isAfter(LocalDateTime.now()) && 
                          task.getDueDate().isBefore(LocalDateTime.now().plusDays(1)))
            .count();

        model.addAttribute("tasks", urgentTasks);
        model.addAttribute("todayTasks", getTodayTasks());
        model.addAttribute("currentTime", LocalDateTime.now());
        model.addAttribute("pendingTasksCount", urgentTasks.stream().filter(task -> !task.isCompleted()).count());
        model.addAttribute("completedTasksCount", urgentTasks.stream().filter(Task::isCompleted).count());
        model.addAttribute("overdueCount", overdueCount);
        model.addAttribute("dueTodayCount", dueTodayCount);
        model.addAttribute("filter", "urgent");
        return "tasks";
    }

    // Endpoint untuk testing exception handling
    @GetMapping("/test/notfound/{id}")
    public String testNotFoundException(@PathVariable Long id) {
        taskService.getTaskById(id); // Akan melempar TaskNotFoundException jika tidak ditemukan
        return "tasks";
    }

    @GetMapping("/test/generic-error")
    public String testGenericException() {
        throw new RuntimeException("Ini adalah exception generic untuk testing");
    }
}