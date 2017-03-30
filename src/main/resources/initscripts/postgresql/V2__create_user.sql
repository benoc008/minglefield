CREATE TABLE IF NOT EXISTS MINGLE_USER (
    ID         NUMERIC PRIMARY KEY,
    FIRST_NAME VARCHAR(50) NOT NULL,
    LAST_NAME  VARCHAR(50) NOT NULL,
    EMAIL      VARCHAR(50) NOT NULL,
    PASSWORD   VARCHAR(50) NOT NULL,

    CONSTRAINT UNIQUE_EMAIL UNIQUE (EMAIL)
)