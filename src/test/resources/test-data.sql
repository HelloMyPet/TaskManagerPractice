INSERT INTO task
(`id`, `description`, `is_reminder_set`, `is_task_open`, `created_on`, `priority`)
VALUES (111, 'first test todo', false, false, CURRENT_TIME(), 'MINOR');

INSERT INTO task
(`id`, `description`, `is_reminder_set`, `is_task_open`, `created_on`, `priority`)
VALUES (112, 'second test todo', true, false, CURRENT_TIME(), 'NORMAL');

INSERT INTO task
(`id`, `description`, `is_reminder_set`, `is_task_open`, `created_on`, `priority`)
VALUES (113, 'third test todo', true, true, CURRENT_TIME(), 'CRITICAL');