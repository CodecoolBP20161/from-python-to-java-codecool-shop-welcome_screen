DROP TABLE IF EXISTS todos;

CREATE TABLE supplier
(
sup_id INTEGER PRIMARY KEY,
name varchar(50),
description varchar(100)
);

CREATE TABLE productcategory
(
cat_id INTEGER PRIMARY KEY,
name varchar(50),
description varchar(100)
);

CREATE TABLE products
(
id INTEGER  PRIMARY KEY,
name varchar(50),
description varchar(100),
defPrice INTEGER,
currency VARCHAR(10),
sup_id INTEGER,
cat_id INTEGER,
FOREIGN KEY (sup_id) REFERENCES supplier(sup_id),
FOREIGN KEY(cat_id) REFERENCES productcategory(cat_id)
);



INSERT INTO supplier(sup_id, name, description) VALUES(1, 'Apple', 'Rich&Famous alias The Gold& The Beautiful');
INSERT INTO supplier(sup_id, name, description) VALUES(2, 'Amazon', 'oh yeah');
INSERT INTO supplier(sup_id, name, description) VALUES(3, 'Lenovo', 'Le No Vo');
INSERT INTO supplier(sup_id, name, description) VALUES(4, 'Codeshop', 'Best ever');

INSERT INTO productcategory(cat_id, name, description) VALUES(1, 'Tablets', 'A tablet computer');
INSERT INTO productcategory(cat_id, name, description) VALUES(2, 'Phones', 'Smartphones');
INSERT INTO productcategory(cat_id, name, description) VALUES(3, 'Watches', 'Smartwatches');
INSERT INTO productcategory(cat_id, name, description) VALUES(4, 'Others', 'Best stuffs ever');

INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(1,'Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49,'USD',2,1);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(2,'Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479,'USD',3,1);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(3,'IWatch', 'Instant get...or not?', 389,'USD',1,3);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(4,'HTC', ' best phone ever', 115,'USD',2,2);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(5,'Sound Bowl', 'Ring-Ring', 59,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(6,'White Board', 'for brainstorming', 129,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(7,'White Board Maxx 290', 'for drawing tables and classes', 39,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(8,'White Board Eraser', 'for "drop" table', 115,'USD',4,4);
INSERT INTO products(id, name, description, defPrice, currency, sup_id, cat_id) VALUES(9,'Iphone', 'simple the best', 999,'USD',1,2);


