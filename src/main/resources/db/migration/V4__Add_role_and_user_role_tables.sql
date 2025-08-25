CREATE TABLE IF NOT EXISTS eco_role
(
    id         UUID PRIMARY KEY      DEFAULT gen_random_uuid(),
    name       VARCHAR(200) NOT NULL,
    created_at TIMESTAMP(0) NOT NULL,
    updated_at TIMESTAMP(0),
    created_by VARCHAR(255) NOT NULL DEFAULT 'eco',
    updated_by VARCHAR(255),
    CONSTRAINT uq_eco_role_name UNIQUE (name)
);

CREATE TABLE IF NOT EXISTS eco_user_role
(
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_eco_user_role_user_id FOREIGN KEY (user_id) REFERENCES eco_user (id),
    CONSTRAINT fk_eco_user_role_role_id FOREIGN KEY (role_id) REFERENCES eco_role (id)
);

INSERT INTO eco_role (name, created_at)
VALUES ('ROLE_USER', CURRENT_TIMESTAMP),
       ('ROLE_CUSTOMER', CURRENT_TIMESTAMP),
       ('ROLE_SELLER', CURRENT_TIMESTAMP),
       ('ROLE_ADMIN', CURRENT_TIMESTAMP);