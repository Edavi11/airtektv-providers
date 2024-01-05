CREATE TABLE IF NOT EXISTS PROVIDERS (
    id SERIAL PRIMARY KEY,
    provider_description VARCHAR(35) NOT NULL,
    version VARCHAR(15) NOT NULL,
    next_version VARCHAR(15) NOT NULL,
    demo_version VARCHAR(15) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    category_description VARCHAR(35) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP,
    CONSTRAINT unique_category_description UNIQUE (category_description)
);