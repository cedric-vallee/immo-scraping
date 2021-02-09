DROP TABLE property IF EXISTS;

CREATE TABLE property  (
    property_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    address VARCHAR(100),
    price BIGINT,
    description VARCHAR(500),
    nb_room BIGINT,
    nb_bedroom BIGINT,
    size DOUBLE,
    detail_url VARCHAR(500),
    provider VARCHAR(100),
    provider_id VARCHAR(100)
);