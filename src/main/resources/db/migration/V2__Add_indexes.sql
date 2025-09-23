CREATE INDEX idx_address_city ON eco_address (city);
CREATE INDEX idx_address_city_district ON eco_address (city, district);
CREATE INDEX idx_address_city_district_neighborhood ON eco_address (city, district, neighborhood);

CREATE INDEX idx_inventory_lookup ON eco_inventory (product_id, warehouse_id, stock_type);