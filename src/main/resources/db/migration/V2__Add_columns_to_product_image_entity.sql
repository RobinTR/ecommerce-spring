ALTER TABLE eco_product_image
    ADD COLUMN public_id VARCHAR(255) NOT NULL,
    ADD COLUMN format VARCHAR(50),
    ADD COLUMN width INTEGER,
    ADD COLUMN height INTEGER,
    ADD COLUMN size_bytes BIGINT;