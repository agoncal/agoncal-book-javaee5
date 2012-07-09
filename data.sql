-- Delete
DELETE FROM T_ORDER_ORDER_LINE;
DELETE FROM T_ORDER_LINE;
DELETE FROM T_ORDER;
DELETE FROM T_ITEM;
DELETE FROM T_PRODUCT;
DELETE FROM T_CATEGORY;
DELETE FROM T_CUSTOMER;
DELETE FROM T_ADDRESS;

-- Load
INSERT INTO T_CATEGORY (id, name, description) VALUES (1, 'Fish', 'Any of numerous cold-blooded aquatic vertebrates characteristically having fins, gills, and a streamlined body' );
INSERT INTO T_CATEGORY (id, name, description) VALUES (2, 'Dogs', 'A domesticated carnivorous mammal related to the foxes and wolves and raised in a wide variety of breeds' );
INSERT INTO T_CATEGORY (id, name, description) VALUES (3, 'Reptiles', 'Any of various cold-blooded, usually egg-laying vertebrates, such as a snake, lizard, crocodile, turtle' );
INSERT INTO T_CATEGORY (id, name, description) VALUES (4, 'Cats', ' Small carnivorous mammal domesticated since early times as a catcher of rats and mice and as a pet and existing in several distinctive breeds and varieties' );
INSERT INTO T_CATEGORY (id, name, description) VALUES (5, 'Birds', 'Any of the class Aves of warm-blooded, egg-laying, feathered vertebrates with forelimbs modified to form wings' );

INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (1, 'Angelfish', 'Saltwater fish from Australia', 1);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (2, 'Tiger Shark', 'Saltwater fish from Australia', 1);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (3, 'Koi', 'Freshwater fish from Japan', 1);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (4, 'Goldfish', 'Freshwater fish from China', 1);

INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (5, 'Bulldog', 'Friendly dog from England', 2);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (6, 'Poodle', 'Cute dog from France', 2);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (7, 'Dalmation', 'Great dog for a fire station', 2);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (8, 'Golden Retriever', 'Great family dog', 2);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (9, 'Labrador Retriever', 'Great hunting dog', 2);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (10, 'Chihuahua', 'Great companion dog', 2);

INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (11, 'Rattlesnake', 'Doubles as a watch dog', 3);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (12, 'Iguana', 'Friendly green friend', 3);

INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (13, 'Manx', 'Great for reducing mouse populations', 4);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (14, 'Persian', 'Friendly house cat, doubles as a princess', 4);

INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (15, 'Amazon Parrot', 'Great companion for up to 75 years', 5);
INSERT INTO T_PRODUCT (id, name, description, category_fk) VALUES (16, 'Finch', 'Great stress reliever', 5);

INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (1, 'Large', 10.00, 'fish1.jpg', 1);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (2, 'Thootless', 10.00, 'fish1.jpg', 1);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (3, 'Spotted', 12.00, 'fish4.jpg', 2);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (4, 'Spotless', 12.00, 'fish4.jpg', 2);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (5, 'Male Adult', 12.00, 'fish3.jpg', 3);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (6, 'Female Adult', 12.00, 'fish3.jpg', 3);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (7, 'Male Puppy', 12.00, 'fish2.jpg', 4);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (8, 'Female Puppy', 12.00, 'fish2.jpg', 4);

INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (9, 'Spotless Male Puppy', 22.00, 'dog1.jpg', 5);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (10, 'Spotless Female Puppy', 22.00, 'dog1.jpg', 5);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (11, 'Spotted Male Puppy', 32.00, 'dog2.jpg', 6);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (12, 'Spotted Female Puppy', 32.00, 'dog2.jpg', 6);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (13, 'Tailed', 62.00, 'dog3.jpg', 7);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (14, 'Tailless', 62.00, 'dog3.jpg', 7);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (15, 'Tailed', 82.00, 'dog4.jpg', 8);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (16, 'Tailless', 82.00, 'dog4.jpg', 8);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (17, 'Tailed', 100.00, 'dog5.jpg', 9);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (18, 'Tailless', 100.00, 'dog5.jpg', 9);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (19, 'Female Adult', 100.00, 'dog6.jpg', 10);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (20, 'Female Adult', 100.00, 'dog6.jpg', 10);

INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (21, 'Female Adult', 20.00, 'reptile1.jpg', 11);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (22, 'Male Adult', 20.00, 'reptile1.jpg', 11);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (23, 'Female Adult', 150.00, 'lizard1.jpg', 12);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (24, 'Male Adult', 160.00, 'lizard1.jpg', 12);

INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (25, 'Male Adult', 120.00, 'cat1.jpg',13);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (26, 'Female Adult', 120.00, 'cat1.jpg', 13);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (27, 'Male Adult', 70.00, 'cat2.jpg',14);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (28, 'Female Adult', 90.00, 'cat2.jpg', 14);

INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (29, 'Male Adult', 120.00, 'bird2.jpg', 15);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (30, 'Female Adult', 120.00, 'bird2.jpg', 15);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (31, 'Male Adult', 75.00, 'bird1.jpg', 16);
INSERT INTO T_ITEM (id, name, unit_cost, image_path, product_fk) VALUES (32, 'Female Adult', 80.00, 'bird1.jpg', 16);

INSERT INTO T_ADDRESS (id, street1, street2, city, state, zip_code, country) VALUES (1, '65 Ritherdon Road', '', 'Los Angeles', 'LA', '56421', 'USA');
INSERT INTO T_CUSTOMER (id, login, password, firstname, lastname, telephone, email, date_of_birth, address_fk) VALUES (1, 'marc', 'marc', 'Marc', 'Fleury', '545 123 45', 'marc@jboss.org', '1971-05-29', 1);

INSERT INTO T_ADDRESS (id, street1, street2, city, state, zip_code, country) VALUES (2, '27 West Side', 'Story', 'Alhabama', 'TX', '8401', 'USA');
INSERT INTO T_CUSTOMER (id, login, password, firstname, lastname, telephone, email, date_of_birth, address_fk) VALUES (2, 'bill', 'bill', 'Bill', 'Gates', '654 046 12', 'bill.gates@microsoft.com', '1965-02-16', 2);

INSERT INTO T_ADDRESS (id, street1, street2, city, state, zip_code, country) VALUES (3, '154 Star Boulevard', '', 'San Francisco', 'WC', '5455', 'USA');
INSERT INTO T_CUSTOMER (id, login, password, firstname, lastname, telephone, email, date_of_birth, address_fk) VALUES (3, 'job', 'job', 'Steve', 'Jobs', '548 157 15', 'steve.jobs@apple.com', '1964-11-30', 3);

INSERT INTO T_ADDRESS (id, street1, street2, city, state, zip_code, country) VALUES (4, '42 Glass Street', '', 'Oakland', 'LA', '8765', 'USA');
INSERT INTO T_CUSTOMER (id, login, password, firstname, lastname, telephone, email, date_of_birth, address_fk) VALUES (4, 'antoncal', 'antoncal', 'Antonio', 'Goncalves', '487 487 21', 'antonio.goncalves@gmail.com', '1971-05-29', 4);

INSERT INTO T_ORDER (id, order_date, credit_card_type, credit_card_number, credit_card_expiry_date, address_fk, customer_fk) VALUES (1, '2006-05-05', 'Visa', '1234 5648 7890', '09/09', 3, 3);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (1, 1, 3);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (2, 1, 4);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (3, 5, 8);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (4, 10, 12);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (1, 1);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (2, 1);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (3, 1);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (4, 1);

INSERT INTO T_ORDER (id, order_date, credit_card_type, credit_card_number, credit_card_expiry_date, address_fk, customer_fk) VALUES (2, '2006-10-31', 'American Express', '1234 7890 5648', '08/08', 1, 1);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (5, 4, 3);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (6, 4, 4);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (5, 2);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (6, 2);

INSERT INTO T_ORDER (id, order_date, credit_card_type, credit_card_number, credit_card_expiry_date, address_fk, customer_fk) VALUES (3, '2006-08-28', 'Master Card', '5648 7890 1234', '09/10', 2, 2);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (7, 1, 7);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (8, 1, 2);
INSERT INTO T_ORDER_LINE (id, quantity, item_fk) VALUES (9, 10, 11);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (7, 3);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (8, 3);
INSERT INTO T_ORDER_ORDER_LINE (order_line_fk, order_fk) VALUES (9, 3);

-- Mise à jour de la séquence
UPDATE SEQUENCE SET seq_count=100 WHERE seq_name='SEQ_GEN';