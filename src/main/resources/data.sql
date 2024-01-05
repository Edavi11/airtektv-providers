-- insert initial data providers table --
INSERT INTO providers (provider_description, version, next_version, demo_version , is_active, created_at, updated_at, deleted_at) VALUES ('Proveedor A', '1.0', '2.0', '2.1', true, NOW(), NOW(), null);
INSERT INTO providers (provider_description, version, next_version, demo_version , is_active, created_at, updated_at, deleted_at) VALUES ('Proveedor B', '1.1', '2.1', '2.2', true, NOW(), NOW(), null);

-- insert initial data categories table --
INSERT INTO categories (category_description) VALUES ('Nacionales') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Entretenimiento') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Deportes') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Infantil') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Educativo') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Telenovelas') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Musica') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Lifestyle') ON CONFLICT (category_description) DO NOTHING;
INSERT INTO categories (category_description) VALUES ('Religion') ON CONFLICT (category_description) DO NOTHING;

-- insert initial data channels table --

