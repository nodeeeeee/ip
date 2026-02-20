# YajuSenpai User Guide

YajuSenpai is a lightweight task manager with a chat-style UI. Use simple commands to add, list, and manage tasks.

## Quick start

1. Run the app.
1. Type a command and press Enter or click `Send`.

## Command format


- Dates: `yyyy-MM-dd` or `MMM dd yyyy`.
- Times: `HH:mm` (24-hour).
- Indexes are 1-based from `list`.

## Commands

### Add a todo

`todo DESCRIPTION`

Example:

```text
todo read chapter 3
```

### Add a deadline

`deadline DESCRIPTION /by DATE`

Example:

```text
deadline submit report /by 2026-03-01
```

### Add an event

`event DESCRIPTION /from START /to END`

Examples:

```text
event project sprint /from 2026-03-01 /to 2026-03-05
event meeting /from 2026-03-01 14:00 /to 2026-03-01 16:00
```

### List tasks

`list`

### Mark or unmark

`mark INDEX`  
`unmark INDEX`

### Delete

`delete INDEX`

### Find

`find KEYWORDS`

### Find nearest free slot

`free DURATION /at CURRENT_TIME`

Example:

```text
free 2:30 /at 2026-03-01 09:00
```

### Exit

`bye`

## Data storage

Tasks are saved to `savedTasks.txt` in the project root.
