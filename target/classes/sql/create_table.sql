DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS supplier;
DROP TABLE IF EXISTS productcategory;


CREATE TABLE supplier
(
supplier_id SERIAL PRIMARY KEY ,
name varchar(50),
description varchar(100)
);

CREATE TABLE productcategory
(
productcategory_id SERIAL PRIMARY KEY ,
name varchar(50),
department VARCHAR(100),
description varchar(50)
);

CREATE TABLE products
(
product_id SERIAL PRIMARY KEY ,
name varchar(50),
description varchar(100),
defPrice FLOAT,
currency VARCHAR(10),
supplier INTEGER,
prodcat INTEGER,
FOREIGN KEY (supplier) REFERENCES supplier(supplier_id),
FOREIGN KEY(prodcat) REFERENCES productcategory(productcategory_id)
);



INSERT INTO supplier(name, description) VALUES('Apple', 'Rich&Famous alias The Gold& The Beautiful');
INSERT INTO supplier(name, description) VALUES('Amazon', 'oh yeah');
INSERT INTO supplier( name, description) VALUES('Lenovo', 'Le No Vo');
INSERT INTO supplier( name, description) VALUES('Codeshop', 'Best ever');

INSERT INTO productcategory(name,description, department) VALUES( 'Tablets', 'A tablet computer','Hardware');
INSERT INTO productcategory(name,description, department) VALUES('Phones','Smartphones','Hardware');
INSERT INTO productcategory( name,description, department) VALUES( 'Watches', 'Smartwatches','Hardware');
INSERT INTO productcategory( name,description, department) VALUES('Others', 'Best stuffs ever','Hardware');

INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('Amazon Fire', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 49,'USD',2,1);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('Lenovo IdeaPad Miix 700', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 479,'USD',3,1);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('Amazon HD8', 'Best ever ever', 89,'USD',2,1);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('IWatch', 'Instant get...or not?', 389,'USD',1,3);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('HTC', ' best phone ever', 115,'USD',2,2);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('Sound Bowl', 'Ring-Ring', 59,'USD',4,4);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('White Board', 'for brainstorming', 129,'USD',4,4);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('White Board Maxx 290', 'for drawing tables and classes', 39,'USD',4,4);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('White Board Eraser', 'for "drop" table', 115,'USD',4,4);
INSERT INTO products( name, description, defPrice, currency, supplier, prodcat) VALUES('Iphone', 'simple the best', 999,'USD',1,2);


