// Update real-time clock, today's tasks, and check deadlines
function updateClockTasksAndDeadlines() {
  const now = new Date();

  // Format time (HH:MM)
  const hours = now.getHours().toString().padStart(2, "0");
  const minutes = now.getMinutes().toString().padStart(2, "0");
  const timeString = `${hours}:${minutes}`;

  // Format date (Weekday, Month Day)
  const options = { weekday: "long", month: "long", day: "numeric" };
  const dateString = now.toLocaleDateString(undefined, options);

  // Update DOM
  document.getElementById("currentTime").textContent = timeString;
  document.getElementById("currentDate").textContent = dateString;

  // Check and update today's tasks
  updateTodayTasks(now);

  // Check for urgent tasks
  checkUrgentTasks();
}

function updateTodayTasks(now) {
  const todayTaskItems = document.querySelectorAll(".today-task-item");

  todayTaskItems.forEach((item) => {
    const timeText = item.querySelector(".task-time").textContent;
    const [hours, minutes] = timeText.split(":").map(Number);
    const taskTime = new Date();
    taskTime.setHours(hours, minutes, 0, 0);

    const diffInHours = (taskTime - now) / (1000 * 60 * 60);

    // Reset classes
    item.classList.remove("urgent", "imminent", "overdue");

    // Check if task is overdue (time passed but still not completed)
    if (taskTime < now) {
      item.classList.add("overdue");
    }
    // Task due within 1 hour
    else if (diffInHours > 0 && diffInHours < 1) {
      item.classList.add("urgent");
    }
    // Task due within 3 hours
    else if (diffInHours > 0 && diffInHours < 3) {
      item.classList.add("imminent");
    }
  });
}

function checkUrgentTasks() {
  // Check urgent tasks in main list
  const urgentTasks = document.querySelectorAll(
    ".deadline-overdue, .deadline-due-today"
  );

  // Check urgent tasks in today's list
  const urgentTodayTasks = document.querySelectorAll(
    ".today-task-item.urgent, .today-task-item.overdue"
  );

  const totalUrgentTasks = urgentTasks.length + urgentTodayTasks.length;

  if (totalUrgentTasks > 0) {
    showNotification(
      `You have ${totalUrgentTasks} urgent task(s) needing attention!`
    );

    // Blink effect for urgent tasks
    urgentTasks.forEach((task) => {
      task.style.animation = "blink 1s 3";
    });

    urgentTodayTasks.forEach((task) => {
      task.style.animation = "blink 1s 3";
    });
  }
}

function showNotification(message) {
  if (!("Notification" in window)) {
    console.log("Browser doesn't support notifications");
    return;
  }

  if (Notification.permission === "granted") {
    new Notification("Task Reminder", {
      body: message,
      icon: "/images/task-icon.png", // Sesuaikan dengan path icon Anda
    });
  } else if (Notification.permission !== "denied") {
    Notification.requestPermission().then((permission) => {
      if (permission === "granted") {
        new Notification("Task Reminder", { body: message });
      }
    });
  }
}

// Initial setup
document.addEventListener("DOMContentLoaded", function () {
  // Request notification permission
  if ("Notification" in window) {
    Notification.requestPermission();
  }

  // Animate task cards
  const taskCards = document.querySelectorAll(".task-card");
  taskCards.forEach((card, index) => {
    setTimeout(() => {
      card.style.opacity = "1";
      card.style.transform = "translateY(0)";
    }, index * 100);
  });

  // Delete confirmation
  document.querySelectorAll(".btn-delete").forEach((button) => {
    button.addEventListener("click", function (e) {
      if (!confirm("Are you sure you want to delete this task?")) {
        e.preventDefault();
      }
    });
  });

  // Priority indicator for new task form
  const prioritySelect = document.getElementById("priority");
  if (prioritySelect) {
    prioritySelect.addEventListener("change", function () {
      const form = this.closest("form");
      form.classList.remove("priority-high", "priority-medium", "priority-low");

      if (this.value === "HIGH") {
        form.classList.add("priority-high");
      } else if (this.value === "MEDIUM") {
        form.classList.add("priority-medium");
      } else if (this.value === "LOW") {
        form.classList.add("priority-low");
      }
    });
  }

  // Initialize clock and task updates
  updateClockTasksAndDeadlines();
  setInterval(updateClockTasksAndDeadlines, 60000); // Update every minute

  // Add click event to today's tasks to scroll to corresponding task
  document.querySelectorAll(".today-task-item").forEach((item) => {
    item.addEventListener("click", function () {
      const taskId = this.getAttribute("data-task-id");
      if (taskId) {
        const taskElement = document.querySelector(
          `.task-card[data-task-id="${taskId}"]`
        );
        if (taskElement) {
          taskElement.scrollIntoView({ behavior: "smooth", block: "center" });
          // Highlight briefly
          taskElement.style.animation = "highlight 2s";
        }
      }
    });
  });
});
