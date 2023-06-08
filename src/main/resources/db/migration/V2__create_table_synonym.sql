CREATE TABLE synonym (
        synonym_id   BIGINT AUTO_INCREMENT NOT NULL,
        question_id  BIGINT              NOT NULL,
        synonym_word VARCHAR(255)       NOT NULL,
        CONSTRAINT pk_synonym PRIMARY KEY (synonym_id)
    );