CREATE TABLE IF NOT EXISTS PLAYERS (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    codename VARCHAR(255) NOT NULL,
    codename_group VARCHAR(255) NOT NULL,
    CONSTRAINT unique_codename_group UNIQUE (codename, codename_group)
    );

