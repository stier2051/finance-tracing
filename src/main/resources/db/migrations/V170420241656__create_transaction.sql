CREATE TABLE IF NOT EXISTS transaction
(
    id                BIGINT PRIMARY KEY,
    date              DATE,
    amount            FLOAT8,
    transaction_type  TEXT,
    detail            TEXT
);

CREATE SEQUENCE transaction_seq START 1;