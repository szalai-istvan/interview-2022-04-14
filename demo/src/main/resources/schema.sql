CREATE TABLE IF NOT EXISTS SHIP(
ID INT PRIMARY KEY,
NAME VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS LOCATION_DATA(
    ID INT PRIMARY KEY,
    SHIP_ID INT FOREIGN KEY,
    LATITUDE FLOAT,
    LONGITUDE FLOAT,
    DAY DATE,
    MINUTE TIME,
    SPEED FLOAT
);