CREATE TABLE IF NOT EXISTS eco_parameter
(
    id         BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name       VARCHAR(500)  NOT NULL,
    definition VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP(0)  NOT NULL,
    updated_at TIMESTAMP(0),
    created_by VARCHAR(255)  NOT NULL DEFAULT 'eco',
    updated_by VARCHAR(255)
);

INSERT INTO eco_parameter (name, definition, created_at)
VALUES ('ECO', 'ECO', CURRENT_TIMESTAMP),
       ('AUTH_ACCESS_TOKEN_EXPIRE_MINUTE', '180', CURRENT_TIMESTAMP),
       ('AUTH_REFRESH_TOKEN_EXPIRE_DAY', '1', CURRENT_TIMESTAMP);