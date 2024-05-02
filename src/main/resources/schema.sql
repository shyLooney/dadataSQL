CREATE TABLE IF NOT EXISTS country
(
    id               serial PRIMARY KEY,
    postal_code      integer      NOT NULL,
    country          varchar(120) NOT NULL,
    country_iso_code varchar(2)   NOT NULL,
    federal_district varchar(20)  NOT NULL
);

CREATE TABLE IF NOT EXISTS region
(
    id               serial PRIMARY KEY,
    region_fias_id   uuid         NOT NULL,
    region_kladr_id  char(19)     NOT NULL,
    region_iso_code  varchar(6)   NOT NULL,
    region_with_type varchar(131) NOT NULL,
    region_type      varchar(10)  NOT NULL,
    region_type_full varchar(50)  NOT NULL,
    region           varchar(120) NOT NULL,
    country_id       integer      NOT NULL references country (id)
);

CREATE TABLE IF NOT EXISTS area
(
    id             serial PRIMARY KEY,
    area_fias_id   uuid,
    area_kladr_id  char(19),
    area_with_type varchar(131),
    area_type      varchar(10),
    area_type_full varchar(50),
    area           varchar(120),
    region_id      integer NOT NULL references region (id)
);

CREATE TABLE IF NOT EXISTS city
(
    id             serial PRIMARY KEY,
    city_fias_id   uuid,
    city_kladr_id  char(19),
    city_with_type varchar(131),
    city_type      varchar(10),
    city_type_full varchar(50),
    city           varchar(120),
    region_id      integer NOT NULL references region (id)
);

CREATE TABLE IF NOT EXISTS city_district
(
    id                      serial PRIMARY KEY,
    city_area               varchar(120),
    city_district_fias_id   uuid,
    city_district_kladr_id  char(19),
    city_district_with_type varchar(131),
    city_district_type      varchar(10),
    city_district_type_full varchar(50),
    city_district           varchar(120),
    city_id                 integer NOT NULL references city (id)
);

CREATE TABLE IF NOT EXISTS settlement
(
    id                   serial PRIMARY KEY,
    settlement_fias_id   uuid,
    settlement_kladr_id  char(19),
    settlement_with_type varchar(131),
    settlement_type      varchar(10),
    settlement_type_full varchar(50),
    settlement           varchar(120),
    area_id              integer NOT NULL references area (id),
    city_district_id     integer references city_district (id)
);

CREATE TABLE IF NOT EXISTS street
(
    id               serial PRIMARY KEY,
    street_fias_id   uuid,
    street_kladr_id  char(19),
    street_with_type varchar(131),
    street_type      varchar(10),
    street_type_full varchar(50),
    street           varchar(120),
    settlement_id    integer references settlement (id),
    city_id          integer references city (id)
);

CREATE TABLE IF NOT EXISTS house
(
    id              serial PRIMARY KEY,
    house_fias_id   uuid,
    house_kladr_id  char(19),
    house_cadnum    varchar(50),
    house_type      varchar(10),
    house_type_full varchar(50),
    house           varchar(50),
    block_type      varchar(10),
    block_type_full varchar(50),
    block           varchar(50),
    street_id       integer references street (id),
    settlement_id   integer references settlement (id)
);

CREATE TABLE IF NOT EXISTS flat
(
    id                 serial PRIMARY KEY,
    flat_fias_id       uuid,
    flat_cadnum        varchar(50),
    flat_type          varchar(10),
    flat_type_full     varchar(50),
    flat               integer,
    flat_area          real,
    square_meter_price numeric,
    flat_price         numeric,
    postal_box         varchar(50),
    house_id           integer NOT NULL references house (id)
);

CREATE TABLE IF NOT EXISTS geo_result
(
    id               serial PRIMARY KEY,
    timezone         varchar(50),
    geo              point,
    beltway_hit      varchar(8),
    beltway_distance integer,
    qc_geo           integer,
    qc_complete      integer,
    qc_house         integer,
    qc               integer,
    history_values   varchar(120)[]
);

CREATE TABLE IF NOT EXISTS common_result
(
    id                   serial PRIMARY KEY,
    full_address         varchar(120),
    fias_id              uuid           NOT NULL,
    fias_level           integer,
    fias_actuality_state integer,
    kladr_id             varchar(19),
    capital_marker       integer,
    okato                varchar(11)    NOT NULL,
    oktmo                varchar(11)    NOT NULL,
    tax_office           varchar(4),
    tax_office_legal     varchar(4),
    geo_result_id        integer UNIQUE NOT NULL references geo_result (id)
);

CREATE TABLE IF NOT EXISTS metro
(
    id       serial PRIMARY KEY,
    name     varchar(100),
    line     varchar(100),
    distance real
);

CREATE TABLE IF NOT EXISTS geo_result_metro
(
    id            serial PRIMARY KEY,
    geo_result_id integer NOT NULL references geo_result (id),
    metro_id      integer NOT NULL references metro (id)
);
