CREATE TABLE IF NOT EXISTS correspondent
(
    id         BIGINT PRIMARY KEY,
    name       TEXT,
    category   TEXT
);

CREATE SEQUENCE correspondent_seq START 1;