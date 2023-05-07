DROP TABLE city CASCADE CONSTRAINTS;
DROP TABLE country CASCADE CONSTRAINTS;
DROP TABLE town CASCADE CONSTRAINTS;

CREATE TABLE city (
    town_id NUMBER CONSTRAINT city_town_id_pk PRIMARY KEY,
    country_id NUMBER NOT NULL CONSTRAINT city_country_id_fk REFERENCES country(country_id),
    city_name VARCHAR(100) NOT NULL
);

CREATE TABLE country (
    country_id NUMBER CONSTRAINT country_country_id_pk PRIMARY KEY,
    country_name VARCHAR2(40) NOT NULL
);

DELETE FROM city;
commit;

ALTER TABLE member
MODIFY town_id CONSTRAINT member_town_id_fk REFERENCES city(town_id) ON DELETE CASCADE;

DROP TABLE town CASCADE CONSTRAINTS;

UPDATE member
SET town_id = 020101000; 

UPDATE retail
SET town_id = 020101000;

ALTER TABLE retail
MODIFY town_id CONSTRAINT retail_town_id_fk REFERENCES city(town_id);
