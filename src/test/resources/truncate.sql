TRUNCATE TABLE member;
ALTER TABLE  member  ALTER COLUMN  member_id RESTART WITH 1;

TRUNCATE TABLE question;

TRUNCATE TABLE answer;
ALTER TABLE  answer  ALTER COLUMN  answer_id RESTART WITH 1;
