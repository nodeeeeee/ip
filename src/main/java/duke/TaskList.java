package duke;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import duke.senpaiexception.SenpaiException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;

/**
 * Manages the task list and related operations.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Loads tasks from saved strings.
     *
     * @param loadStr Array of saved strings.
     */
    public TaskList(String[] loadStr) {
        assert loadStr != null : "loadStr must not be null";
        tasks = new ArrayList<>();
        int idx = 1;
        for (int i = 0; i < loadStr.length; i++) {
            String[] taskComponents = loadStr[i].split(" \\| ", 4);
            try {
                if (taskComponents[0].equals("T")) {
                    addWithoutResponse(new TodoTask(taskComponents[2]));
                } else if (taskComponents[0].equals("D")) {
                    addWithoutResponse(new DeadlineTask(taskComponents[2] + " " + taskComponents[3]));
                } else if (taskComponents[0].equals("E")) {
                    addWithoutResponse(new EventTask(taskComponents[2] + " " + taskComponents[3]));
                } else {
                    throw new SenpaiException("loading error...");
                }
                if (taskComponents[1].equals("1")) {
                    mark(idx);
                }
                idx++;
            } catch (DateTimeParseException e) {
                System.out.println("Date time error");
                continue;
            } catch (SenpaiException e) {
                System.out.println(e.getResponse());
                continue;
            }
        }
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Saves the current list to file.
     */
    public void saveList() {
        try {
            writeToFile("savedTasks.txt", formatSave());
        } catch (IOException e) {
            System.out.println("cannot find file.");
        }
    }


    /**
     * Adds a task and prints the response.
     *
     * @param task Task to add.
     */
    public String addWithResponse(Task task) {
        addTask(task);
        //        ResponseBlock response = new ResponseBlock("Got it. I've added this task:\n"
        //                + task.getRep() + "\nNow you have " + getChatSize() + " tasks in the list.");
        //        response.print();
        return "Got it. I've added this task:\n"
                + task.getRep() + "\nNow you have " + getChatSize() + " tasks in the list.";
    }

    /**
     * Adds a task without printing the response.
     *
     * @param task Task to add.
     */
    public void addWithoutResponse(Task task) {
        addTask(task);
    }

    //Reused from https://nus-cs2103-ay2526-s2.github.io/website/schedule/week3/topics.html
    private void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        assert task != null : "task must not be null";
        tasks.add(task);
    }

    /**
     * Deletes a task in 1-based manner.
     *
     * @param idx 1-based index of task to delete.
     */
    public String deleteTask(int idx) {
        if (idx < 1 || idx > tasks.size()) {
            throw new SenpaiException("OOPS!!! The task index is out of range.");
        }
        String taskRep = tasks.get(idx - 1).getRep();
        tasks.remove(idx - 1);
        //        ResponseBlock response = new ResponseBlock("Deleted task successfully:\n"
        //                + taskRep + "\nNow you have " + getChatSize() + " tasks in the list.");
        //        response.print();
        return "Deleted task successfully:\n"
                + taskRep + "\nNow you have " + getChatSize() + " tasks in the list.";
    }

    /**
     * Get the task string in 1-based index.
     *
     * @param index 1-based index of task to get.
     * @return Task to string.
     */
    public String getTask(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new SenpaiException("OOPS!!! The task index is out of range.");
        }
        return tasks.get(index - 1).getRep();
    }

    /**
     * Mark a task as done.
     *
     * @param index 1-based index of task to mark.
     */
    public String mark(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new SenpaiException("OOPS!!! The task index is out of range.");
        }
        tasks.get(index - 1).mark();
        return "marked";
    }

    /**
     * Mark a task as not done.
     *
     * @param index 1-based index of task to unmark.
     */
    public String unmark(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new SenpaiException("OOPS!!! The task index is out of range.");
        }
        tasks.get(index - 1).unmark();
        return "unmarked";
    }

    /**
     * Returns how many tasks are in the list.
     *
     * @return Number of tasks.
     */
    public int getChatSize() {
        return tasks.size();
    }

    /**
     * Prints the list of tasks.
     */
    public String list() {
        //        ResponseBlock response = new ResponseBlock("Here are the tasks in your list:\n" + getAllTasks());
        //        response.print();
        return "Here are the tasks in your list:\n" + getAllTasks();
    }

    /**
     * Find tasks that match a keyword and print them.
     *
     * @param keyword Keyword to search for.
     */
    public String find(String ...keyword) {
        //        ResponseBlock response = new ResponseBlock("Here are the matching tasks in your list:\n"
        //                + getMatchingTasks(keyword));
        //        response.print();

        return "Here are the matching tasks in your list:\n"
                + getMatchingTasks(keyword);
    }

    /**
     * Gets the string of all tasks.
     *
     * @return String of tasks.
     */
    public String getAllTasks() {
        return IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).getRep())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Get the string of matched tasks.
     *
     * @param keywords Keyword to search for.
     * @return String of matched tasks.
     */
    public String getMatchingTasks(String ...keywords) {
        if (keywords == null || keywords.length == 0) {
            return "";
        }
        return IntStream.range(0, tasks.size())
                .filter(i -> {
                    Task task = tasks.get(i);
                    for (String keyword : keywords) {
                        if (task.getTaskName().contains(keyword)) {
                            return true;
                        }
                    }
                    return false;
                })
                .mapToObj(i -> (i + 1) + ". " + tasks.get(i).getRep())
                .collect(Collectors.joining("\n"));
    }

    /**
     * Formats tasks to save to file.
     *
     * @return formatted string.
     */
    public String formatSave() {
        return tasks.stream()
                .map(Task::getRep)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Finds the nearest day with a free slot of at least the requested minutes.
     *
     * @param requiredMinutes Required free minutes (1-1440).
     * @param currentTime User-provided current time.
     * @return Response string.
     */
    public String findNearestFreeDay(int requiredMinutes, LocalDateTime currentTime) {
        if (requiredMinutes <= 0 || requiredMinutes > 1440) {
            throw new SenpaiException("Free duration must be between 1 and 1440 minutes.");
        }
        if (currentTime == null) {
            throw new SenpaiException("Must specify current time!");
        }
        LocalDate date = currentTime.toLocalDate();
        LocalDate end = date.plusDays(365);
        while (!date.isAfter(end)) {
            int earliestStart = date.equals(currentTime.toLocalDate())
                    ? currentTime.getHour() * 60 + currentTime.getMinute()
                    : 0;
            int startMinute = findFreeSlotStart(date, requiredMinutes, earliestStart);
            if (startMinute >= 0 && startMinute <= 1439) {
                String formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                String timeStr = LocalTime.of(startMinute / 60, startMinute % 60)
                        .format(DateTimeFormatter.ofPattern("HH:mm"));
                return "Nearest day with at least " + formatDuration(requiredMinutes)
                        + " free slot: " + formattedDate + " starting at " + timeStr;
            }
            date = date.plusDays(1);
        }
        return "No free slot found within the next 365 days.";
    }

    /**
     * Finds the earliest start minute within the given day that can fit a free slot.
     *
     * <p>Busy intervals are derived from EventTask timings that overlap the day and
     * DeadlineTask occurrences on the same date (treated as blocking the entire day).
     *
     * @param date Day to search.
     * @param requiredMinutes Required free minutes.
     * @param earliestStart Earliest allowed start minute from day start (0-1439).
     * @return Start minute from day start if a slot is available, or -1 if none fits.
     */
    private int findFreeSlotStart(LocalDate date, int requiredMinutes, int earliestStart) {
        LocalDateTime dayStart = date.atStartOfDay();
        LocalDateTime dayEnd = date.atTime(23, 59);
        List<Interval> intervals = new ArrayList<>();
        for (Task task : tasks) {
            if (task instanceof EventTask event) {
                LocalDateTime from = event.getFrom();
                LocalDateTime to = event.getTo();
                if (to.isBefore(dayStart) || from.isAfter(dayEnd)) {
                    continue;
                }
                LocalDateTime busyStart = from.isAfter(dayStart) ? from : dayStart;
                LocalDateTime busyEnd = to.isBefore(dayEnd) ? to : dayEnd;
                int startMin = (int) Duration.between(dayStart, busyStart).toMinutes();
                int endMin = (int) Duration.between(dayStart, busyEnd).toMinutes();
                int endExclusive = Math.min(1440, endMin + 1);
                intervals.add(new Interval(startMin, endExclusive));
            } else if (task instanceof DeadlineTask ddl) {
                if (date.isEqual(ddl.getDue())) {
                    intervals.add(new Interval(0, 1440));
                }
            }
        }
        if (intervals.isEmpty()) {
            return 0;
        }
        intervals.sort(Comparator.comparingInt(i -> i.start));
        int current = Math.max(0, earliestStart);
        if (current >= 1440) {
            return -1;
        }
        for (Interval interval : intervals) {
            if (interval.start - current >= requiredMinutes) {
                return current;
            }
            current = Math.max(current, interval.end);
            if (current >= 1440) {
                return -1;
            }
        }
        return (1440 - current >= requiredMinutes) ? current : -1;
    }

    private String formatDuration(int minutes) {
        int hours = minutes / 60;
        int mins = minutes % 60;
        if (mins == 0) {
            return hours + " hour" + (hours == 1 ? "" : "s");
        }
        return hours + "h " + mins + "m";
    }

    private static class Interval {
        private final int start;
        private final int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
