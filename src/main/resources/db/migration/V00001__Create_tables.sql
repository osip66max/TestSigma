CREATE TABLE car (
    id     INTEGER       NOT NULL AUTO_INCREMENT,
    number VARCHAR(9)   NOT NULL,
    name   VARCHAR(255) NOT NULL,
    CONSTRAINT car_primary_key PRIMARY KEY (id)
);

CREATE TABLE parking (
    id        INTEGER    NOT NULL AUTO_INCREMENT,
    number    INTEGER(4) NOT NULL,
    occupancy BOOLEAN    NOT NULL,
    CONSTRAINT parking_primary_key PRIMARY KEY (id)
);

CREATE TABLE time_price (
    id         INTEGER  NOT NULL AUTO_INCREMENT,
    time       INTEGER NOT NULL,
    price      INTEGER NOT NULL,
    car_id     INTEGER  NOT NULL,
    parking_id INTEGER  NOT NULL,
    CONSTRAINT time_primary_key PRIMARY KEY (id),
    CONSTRAINT car_foreign_key FOREIGN KEY (car_id) REFERENCES car (id),
    CONSTRAINT parking_foreign_key FOREIGN KEY (parking_id) REFERENCES parking (id)
);