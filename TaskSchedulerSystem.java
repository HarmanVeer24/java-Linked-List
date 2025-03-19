public class TaskSchedulerSystem {
    public static void main(String[] args) {
        TaskSchedulerSystem scheduler = new TaskSchedulerSystem();
        scheduler.addTaskAtBeginning(1, "Project Meeting", 1, "2025-03-20");
        scheduler.addTaskAtPosition(2, "Code Review", 2, "2025-03-21", 1);
        scheduler.addTaskAtEnd(3, "Submit Report", 1, "2025-03-22");

        System.out.println("Viewing all tasks:");
        scheduler.viewTasks();

        System.out.println("\nSearching for tasks with priority 1:");
        scheduler.searchByPriority(1);

        System.out.println("\nRemoving Task with ID 2:");
        scheduler.removeTask(2);

        System.out.println("\nViewing all tasks:");
        scheduler.viewTasks();
    }
    TaskNode head = null;

    static class TaskNode {
        int taskId;
        String taskName;
        int priority;
        String dueDate;
        TaskNode next;

        TaskNode(int taskId, String taskName, int priority, String dueDate) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.dueDate = dueDate;
            this.next = null;
        }
    }

    public void addTaskAtEnd(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
        }
    }

    public void addTaskAtBeginning(int taskId, String taskName, int priority, String dueDate) {
        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null) {
            head = newTask;
            head.next = head;
        } else {
            TaskNode temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newTask;
            newTask.next = head;
            head = newTask;
        }
    }

    public void addTaskAtPosition(int taskId, String taskName, int priority, String dueDate, int position) {
        TaskNode newTask = new TaskNode(taskId, taskName, priority, dueDate);
        if (head == null || position <= 1) {
            addTaskAtBeginning(taskId, taskName, priority, dueDate);
            return;
        }
        TaskNode temp = head;
        int count = 1;
        while (temp.next != head && count < position - 1) {
            temp = temp.next;
            count++;
        }
        newTask.next = temp.next;
        temp.next = newTask;
    }


    public void removeTask(int taskId) {
        if (head == null) return;
        TaskNode temp = head, prev = null;
        while (temp.taskId != taskId) {
            if (temp.next == head) return;
            prev = temp;
            temp = temp.next;
        }
        if (temp == head) {
            TaskNode last = head;
            while (last.next != head) {
                last = last.next;
            }
            if (head == head.next) {
                head = null;
            } else {
                head = head.next;
                last.next = head;
            }
        } else {
            prev.next = temp.next;
        }
    }

    public void viewTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head;
        do {
            System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Priority: " + temp.priority + ", Due Date: " + temp.dueDate);
            temp = temp.next;
        } while (temp != head);
    }

    public void searchByPriority(int priority) {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head;
        boolean found = false;
        do {
            if (temp.priority == priority) {
                System.out.println("Task ID: " + temp.taskId + ", Name: " + temp.taskName + ", Due Date: " + temp.dueDate);
                found = true;
            }
            temp = temp.next;
        } while (temp != head);
        if (!found) {
            System.out.println("No tasks found with priority " + priority);
        }
    }
}
//output
// Viewing all tasks:
//Task ID: 2, Name: Code Review, Priority: 2, Due Date: 2025-03-21
//Task ID: 1, Name: Project Meeting, Priority: 1, Due Date: 2025-03-20
//Task ID: 3, Name: Submit Report, Priority: 1, Due Date: 2025-03-22
//
//Searching for tasks with priority 1:
//Task ID: 1, Name: Project Meeting, Due Date: 2025-03-20
//Task ID: 3, Name: Submit Report, Due Date: 2025-03-22
//
//Removing Task with ID 2:
//
//Viewing all tasks:
//Task ID: 1, Name: Project Meeting, Priority: 1, Due Date: 2025-03-20
//Task ID: 3, Name: Submit Report, Priority: 1, Due Date: 2025-03-22
