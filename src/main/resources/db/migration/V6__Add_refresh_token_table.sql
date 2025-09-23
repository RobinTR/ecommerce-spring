CREATE TABLE IF NOT EXISTS eco_refresh_token
(
    id         UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    user_id    UUID         NOT NULL,
    token      VARCHAR(256) NOT NULL,
    device_id  VARCHAR(255) NOT NULL,
    revoked    BOOLEAN      NOT NULL DEFAULT FALSE,
    expires_at TIMESTAMP(0) NOT NULL,
    created_at TIMESTAMP(0) NOT NULL,
    updated_at TIMESTAMP(0),
    created_by VARCHAR(255) NOT NULL DEFAULT 'ECO',
    updated_by VARCHAR(255),
    CONSTRAINT fk_eco_refresh_token_user_id FOREIGN KEY (user_id) REFERENCES eco_user (id),
    CONSTRAINT uq_eco_refresh_token_token UNIQUE (token)
);

CREATE INDEX idx_eco_refresh_token_user_id ON eco_refresh_token (user_id);
CREATE INDEX idx_eco_refresh_token_token ON eco_refresh_token (token);