CREATE TABLE member
(
    member_id  BIGINT AUTO_INCREMENT NOT NULL,
    created_at datetime              NULL,
    age        INT                   NOT NULL,
    CONSTRAINT pk_member PRIMARY KEY (member_id)
);

CREATE TABLE question
(
    question_id BIGINT       NOT NULL,
    image_url   VARCHAR(255) NOT NULL,
    word        VARCHAR(255) NOT NULL,
    CONSTRAINT pk_question PRIMARY KEY (question_id)
);

CREATE TABLE answer
(
    answer_id             BIGINT AUTO_INCREMENT NOT NULL,
    question_id           BIGINT                NOT NULL,
    member_id             BIGINT                NOT NULL,
    answer_status         VARCHAR(10)           NOT NULL,
    audio_file_object_key VARCHAR(64)           NOT NULL,
    CONSTRAINT pk_answer PRIMARY KEY (answer_id)
);

ALTER TABLE answer
    ADD CONSTRAINT unique_column_in_answer UNIQUE (audio_file_object_key);

CREATE INDEX idx_member ON answer (member_id);
