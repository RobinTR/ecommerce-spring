CREATE TABLE IF NOT EXISTS eco_refresh_token
(
    id         UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    user_id    UUID         NOT NULL,
    session_id VARCHAR(255) NOT NULL,
    token_hash VARCHAR(256) NOT NULL,
    revoked    BOOLEAN      NOT NULL DEFAULT FALSE,
    expires_at TIMESTAMP(0) NOT NULL,
    created_at TIMESTAMP(0) NOT NULL,
    updated_at TIMESTAMP(0),
    created_by VARCHAR(255) NOT NULL DEFAULT 'ECO',
    updated_by VARCHAR(255),
    CONSTRAINT fk_eco_refresh_token_user_id FOREIGN KEY (user_id) REFERENCES eco_user (id)
);