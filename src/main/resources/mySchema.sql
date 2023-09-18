CREATE TABLE TB_CLIENT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR (100),
    CPF VARCHAR (11)
);

CREATE TABLE TB_PRODUCT (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    DESCRIPTION VARCHAR (150),
    UNITY_PRICE NUMERIC(20,2)
);

CREATE TABLE TB_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    CLIENT_ID INTEGER REFERENCES TB_CLIENT(ID),
    ORDER_DATE TIMESTAMP,
    STATUS VARCHAR (20),
    TOTAL NUMERIC(25, 2)
);

CREATE TABLE ITEM_ORDER (
    ID INTEGER PRIMARY KEY AUTO_INCREMENT,
    ORDER_ID INTEGER REFERENCES TB_ORDER (ID),
    PRODUCT_ID INTEGER REFERENCES TB_PRODUCT (ID),
    QUANTITY INTEGER
);