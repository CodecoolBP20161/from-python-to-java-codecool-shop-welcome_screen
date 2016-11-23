DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS productcategory;
DROP TABLE IF EXISTS products;

CREATE TABLE supplier
(
id INTEGER PRIMARY KEY,
name varchar(50),
description varchar(100)
);

CREATE TABLE productcategory
(
id INT PRIMARY KEY ,
name varchar(50),
description VARCHAR(100),
department varchar(50)
);

CREATE TABLE products
(
id INTEGER  PRIMARY KEY,
name varchar(50),
description varchar(100),
defPrice INTEGER,
currency VARCHAR(10),
supplier INTEGER,
prodcat INTEGER,
FOREIGN KEY (supplier) REFERENCES supplier(id),
FOREIGN KEY(prodcat) REFERENCES productcategory(id)
);



INSERT INTO supplier(id, name, description) VALUES(1, 'Apple', 'Rich&Famous alias The Gold& The Beautiful');
INSERT INTO supplier(id, name, description) VALUES(2, 'Amazon', 'oh yeah');
INSERT INTO supplier(id, name, description) VALUES(3, 'Lenovo', 'Le No Vo');
INSERT INTO supplier(id, name, description) VALUES(4, 'Codeshop', 'Best ever');

INSERT INTO productcategory(id, name,description, department) VALUES(1, 'Tablets', 'A tablet computer','Hardware');
INSERT INTO productcategory(id, name,description, department) VALUES(2, 'Phones','Smartphones','Hardware');
INSERT INTO productcategory(id, name,description, department) VALUES(3, 'Watches', 'Smartwatches','Hardware');
INSERT INTO productcategory(id, name,description, department) VALUES(4, 'Others', 'Best stuffs ever','Hardware');

INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(1,'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49,'USD',2,1);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(2,'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479,'USD',3,1);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(3,'IWatch', 'Instant get...or not?', 389,'USD',1,3);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(4,'HTC', ' best phone ever', 115,'USD',2,2);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(5,'Sound Bowl', 'Ring-Ring', 59,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(6,'White Board', 'for brainstorming', 129,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(7,'White Board Maxx 290', 'for drawing tables and classes', 39,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(8,'White Board Eraser', 'for "drop" table', 115,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, supplier, prodcat) VALUES(9,'Iphone', 'simple the best', 999,'USD',1,2);


