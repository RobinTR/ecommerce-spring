-- For Testing and Demo purposes only
-- Password is '12345678Ro.'
INSERT INTO eco_user (id, email, password, user_status, created_at, created_by)
VALUES (gen_random_uuid(), 'admin@eco.com', '$2a$10$1bWf9h2JNuR1KMGP3gAcdO45nYRuwHzzDHeaH4UHR/XKZ8gjofAYe',
        'ACTIVE',
        CURRENT_TIMESTAMP, 'ECO');

DO
$$
    DECLARE
        user_id UUID;
    BEGIN
        SELECT id INTO user_id FROM eco_user WHERE email = 'admin@eco.com';

        INSERT INTO eco_user_role (user_id, role_id)
        VALUES (user_id, (SELECT id FROM eco_role WHERE name = 'ROLE_SELLER'));

        INSERT INTO eco_user_role (user_id, role_id)
        VALUES (user_id, (SELECT id FROM eco_role WHERE name = 'ROLE_USER'));

        INSERT INTO eco_seller (id, store_name, mersis_number, contact_number, seller_type, seller_status)
        VALUES (user_id, 'Ecommerce Official Store', '0123456789000015', '05554442222', 'PLATFORM', 'APPROVED');
    END
$$;