DELETE FROM recipe_ingredients;
DELETE FROM recipes;
DELETE FROM ingredients;
DELETE FROM users;
DELETE FROM categories;
DELETE FROM uploads;



INSERT INTO users (id, first_name, last_name, username, password, email, role) VALUES
                                                                                   (1, 'Admin', 'Kook', 'admin', '$2a$10$WoafFSPD5obU55Y6bSqnM.2367Y.OqiftmokwE7jNk6jIcUgDKcai', 'admin@kookassistent.nl', 'ADMIN'),
                                                                                   (2, 'Regular', 'Gebruiker', 'user', '$2a$10$yaEGna8kgRY6bU9YO4x9Ne5vAAextVqqIzIRaSQcqtrm1DX/jCnki', 'user@kookassistent.nl', 'USER');


INSERT INTO categories (id, name) VALUES
                                      (1, 'Hoofdgerechten'),
                                      (2, 'Soepen'),
                                      (3, 'Vleesgerechten'),
                                      (4, 'Pastagerechten'),
                                      (5, 'Salades');


INSERT INTO ingredients (id, name, allergies, category) VALUES
                                                            (1, 'Bloem', 'Gluten', 'Droogwaren'),
                                                            (2, 'Suiker', NULL, 'Droogwaren'),
                                                            (3, 'Ei', 'Eieren', 'Zuivel'),
                                                            (4, 'Melk', 'Lactose', 'Zuivel'),
                                                            (5, 'Zout', NULL, 'Kruiden'),
                                                            (6, 'Boter', 'Lactose', 'Zuivel'),
                                                            (7, 'Kipfilet', NULL, 'Vlees'),
                                                            (8, 'Rijst', NULL, 'Granen'),
                                                            (9, 'Groentemix', NULL, 'Groenten'),
                                                            (10, 'Sojasaus', 'Soja', 'Sauzen'),
                                                            (11, 'Knoflook', NULL, 'Groenten'),
                                                            (12, 'Gember', NULL, 'Groenten'),
                                                            (13, 'Rundergehakt', NULL, 'Vlees'),
                                                            (14, 'Tomaten', NULL, 'Groenten'),
                                                            (15, 'Ui', NULL, 'Groenten'),
                                                            (16, 'Pasta', 'Gluten', 'Granen'),
                                                            (17, 'Parmezaanse kaas', 'Lactose', 'Zuivel'),
                                                            (18, 'Rundvlees', NULL, 'Vlees'),
                                                            (19, 'Wortelen', NULL, 'Groenten'),
                                                            (20, 'Selderij', NULL, 'Groenten'),
                                                            (21, 'Varkenshaas', NULL, 'Vlees'),
                                                            (22, 'Brood', 'Gluten', 'Brood'),
                                                            (23, 'Kaas', 'Lactose', 'Zuivel'),
                                                            (24, 'Champignons', NULL, 'Groenten'),
                                                            (25, 'Linzen', NULL, 'Peulvruchten'),
                                                            (26, 'Paprika', NULL, 'Groenten'),
                                                            (27, 'Courgette', NULL, 'Groenten'),
                                                            (28, 'Tofu', 'Soja', 'Vleesvervanger'),
                                                            (29, 'Kikkererwten', NULL, 'Peulvruchten'),
                                                            (30, 'Kruiden', NULL, 'Kruiden'),
                                                            (31, 'Olijfolie', NULL, 'Olie'),
                                                            (32, 'Bouillon', NULL, 'Kruiden');


INSERT INTO recipes (id, title, description, servings, user_id, category_id) VALUES
                                                                                 (1, 'Kip Curry', 'Pittige kipcurry met rijst', 4, 1, 1),
                                                                                 (2, 'Gegrilde Kip', 'Malse gegrilde kipfilet met groenten', 2, 2, 1),
                                                                                 (3, 'Kip Pasta', 'Romige kippasta met champignons', 3, 1, 4),
                                                                                 (4, 'Spaghetti Bolognese', 'Klassieke Italiaanse pastasaus met gehakt', 4, 2, 4),
                                                                                 (5, 'Beef Stir-fry', 'Roerbakschotel met rundvlees en groenten', 3, 1, 1),
                                                                                 (6, 'Runderstoofpot', 'Traditionele stoofpot met rundvlees', 4, 2, 3),
                                                                                 (7, 'Carbonara', 'Romige pasta met ei, kaas en spek', 2, 1, 4),
                                                                                 (8, 'Pesto Pasta', 'Verse pasta met zelfgemaakte pesto', 3, 2, 4),
                                                                                 (9, 'Lasagne', 'Ovenschotel met lagen pasta, saus en kaas', 6, 1, 4),
                                                                                 (10, 'Tomatensoep', 'Huisgemaakte tomatensoep met basilicum', 4, 2, 2),
                                                                                 (11, 'Groentesoep', 'Gevarieerde soep met seizoensgroenten', 4, 1, 2),
                                                                                 (12, 'Linzensoep', 'Hartige soep met linzen en groenten', 4, 2, 2),
                                                                                 (13, 'Varkenshaas Medaillons', 'Malse varkenshaas met roomsaus', 2, 1, 3),
                                                                                 (14, 'Speklapjes uit de oven', 'Krokante speklapjes met kruiden', 4, 2, 3),
                                                                                 (15, 'Varkensragout', 'Stoofschotel met varkensvlees en groenten', 4, 1, 3),
                                                                                 (16, 'Club Sandwich', 'Gevuld brood met kip, spek en ei', 2, 2, 5),
                                                                                 (17, 'Quiche Lorraine', 'Franse taart met spek en kaas', 6, 1, 5),
                                                                                 (18, 'Salade Nicoise', 'Frans geïnspireerde salade met tonijn', 2, 2, 5),
                                                                                 (19, 'Ratatouille', 'Provençaalse groentenschotel', 4, 1, 1),
                                                                                 (20, 'Chana Masala', 'Indiase kikkererwtencurry', 4, 2, 1),
                                                                                 (21, 'Tofu Roerbak', 'Aziatische roerbak met tofu en groenten', 3, 1, 1);

INSERT INTO uploads (id, url, public_id, resource_type, format, recipe_id) VALUES
                                                                               (1, '/uploads/kip_curry_1.jpg', 'kip_curry_1.jpg', 'image/jpeg', 'jpg', 1),
                                                                               (2, '/uploads/gegrilde_kip_1.jpg', 'gegrilde_kip_1.jpg', 'image/jpeg', 'jpg', 2),
                                                                               (3, '/uploads/kip_pasta_1.jpg', 'kip_pasta_1.jpg', 'image/jpeg', 'jpg', 3),
                                                                               (4, '/uploads/bolognese_1.jpg', 'bolognese_1.jpg', 'image/jpeg', 'jpg', 4),
                                                                               (5, '/uploads/beef_stir_fry_1.jpg', 'beef_stir_fry_1.jpg', 'image/jpeg', 'jpg', 5);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (1, 7, 500, 'g'), (1, 8, 250, 'g'), (1, 10, 2, 'el'), (1, 11, 2, 'teentjes'), (1, 12, 1, 'cm'), (1, 26, 1, 'st');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (2, 7, 2, 'st'), (2, 31, 2, 'el'), (2, 30, 1, 'tl'), (2, 19, 1, 'st'), (2, 26, 1, 'st');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (3, 7, 300, 'g'), (3, 16, 250, 'g'), (3, 24, 200, 'g'), (3, 4, 100, 'ml'), (3, 17, 50, 'g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (4, 13, 400, 'g'), (4, 14, 2, 'st'), (4, 15, 1, 'st'), (4, 16, 400, 'g'), (4, 17, 50, 'g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (5, 18, 300, 'g'), (5, 9, 300, 'g'), (5, 26, 1, 'st'), (5, 27, 1, 'st'), (5, 10, 3, 'el');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (6, 18, 500, 'g'), (6, 19, 2, 'st'), (6, 20, 1, 'stengel'), (6, 15, 1, 'st'), (6, 32, 500, 'ml');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (7, 16, 200, 'g'), (7, 3, 2, 'st'), (7, 17, 50, 'g'), (7, 21, 100, 'g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (8, 16, 300, 'g'), (8, 14, 1, 'st'), (8, 31, 50, 'ml'), (8, 11, 2, 'teentjes');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (9, 16, 12, 'vellen'), (9, 13, 500, 'g'), (9, 14, 3, 'st'), (9, 15, 1, 'st'), (9, 17, 100, 'g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (10, 14, 5, 'st'), (10, 15, 1, 'st'), (10, 31, 2, 'el'), (10, 32, 500, 'ml');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (11, 9, 1, 'zak'), (11, 19, 2, 'st'), (11, 20, 1, 'stengel'), (11, 27, 1, 'st'), (11, 32, 1, 'l');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (12, 25, 250, 'g'), (12, 15, 1, 'st'), (12, 19, 2, 'st'), (12, 32, 1, 'l');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (13, 21, 2, 'st'), (13, 4, 100, 'ml'), (13, 17, 50, 'g'), (13, 30, 1, 'tl');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (14, 21, 4, 'st'), (14, 30, 1, 'el'), (14, 31, 2, 'el'), (14, 5, 1, 'snuf');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (15, 21, 500, 'g'), (15, 19, 1, 'st'), (15, 20, 1, 'stengel'), (15, 15, 1, 'st'), (15, 32, 500, 'ml');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (16, 22, 3, 'plakken'), (16, 7, 1, 'filet'), (16, 21, 2, 'repen'), (16, 3, 1, 'st'), (16, 14, 2, 'plakjes');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (17, 3, 3, 'st'), (17, 4, 200, 'ml'), (17, 21, 100, 'g'), (17, 23, 100, 'g'), (17, 22, 1, 'vel');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (18, 3, 2, 'st'), (18, 14, 1, 'st'), (18, 26, 1, 'st'), (18, 31, 2, 'el'), (18, 29, 100, 'g');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (19, 14, 1, 'st'), (19, 26, 1, 'st'), (19, 27, 1, 'st'), (19, 15, 1, 'st'), (19, 31, 3, 'el');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (20, 29, 400, 'g'), (20, 14, 2, 'st'), (20, 15, 1, 'st'), (20, 11, 2, 'teentjes'), (20, 10, 3, 'el');
INSERT INTO recipe_ingredients (recipe_id, ingredient_id, quantity, unit) VALUES
                                                                              (21, 28, 300, 'g'), (21, 26, 1, 'st'), (21, 27, 1, 'st'), (21, 11, 2, 'teentjes'), (21, 10, 4, 'el');


SELECT setval('users_id_seq', (SELECT MAX(id) FROM users), true);
SELECT setval('ingredients_id_seq', (SELECT MAX(id) FROM ingredients), true);
SELECT setval('recipes_id_seq', (SELECT MAX(id) FROM recipes), true);
SELECT setval('categories_id_seq', (SELECT MAX(id) FROM categories), true);
SELECT setval('uploads_id_seq', (SELECT MAX(id) FROM uploads), true);

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Snijd kip in blokjes\n2. Fruit ui, knoflook en gember\n3. Voeg kip toe en bak mee\n4. Doe currypoeder en kokosmelk erbij\n5. Laat 20 minuten sudderen\n6. Serveer met rijst' WHERE id = 1;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Marineer kip met olie en kruiden\n2. Verhit grillpan\n3. Grill kip 6-8 minuten per kant\n4. Serveer met gegrilde paprika en courgette' WHERE id = 2;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Kook pasta volgens verpakking\n2. Bak kip en champignons\n3. Voeg room en kaas toe\n4. Meng pasta door de saus\n5. Bestrooi met peterselie' WHERE id = 3;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Bak gehakt met ui en knoflook\n2. Voeg tomatenblokjes en puree toe\n3. Laat 30 minuten pruttelen\n4. Kook spaghetti\n5. Serveer saus over pasta met kaas' WHERE id = 4;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Snijd rundvlees in reepjes\n2. Roerbak met groenten op hoog vuur\n3. Voeg sojasaus en gember toe\n4. Dien op met noedels of rijst' WHERE id = 5;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Braad rundvlees aan\n2. Voeg wortel, ui en selderij toe\n3. Schenk bouillon en rode wijn bij\n4. Laat 2 uur zachtjes stoven\n5. Serveer met aardappelpuree' WHERE id = 6;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Kook pasta\n2. Bak spekjes knapperig\n3. Klop eieren met kaas\n4. Meng alles snel door elkaar\n5. Voeg versgemalen peper toe' WHERE id = 7;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Maal basilicum, knoflook en pijnboompitten\n2. Voeg olijfolie toe tot pesto\n3. Kook pasta\n4. Meng pesto door pasta\n5. Bestrooi met pijnboompitten' WHERE id = 8;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Maak bolognesesaus\n2. Maak bechamelsaus\n3. Bouw lasagne op in ovenschaal\n4. Bak 40 minuten op 180°C\n5. Laat 10 minuten rusten voor serveren' WHERE id = 9;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Fruit ui en knoflook\n2. Voeg tomaten en bouillon toe\n3. Kook 20 minuten\n4. Pureer soep fijn\n5. Serveer met basilicum en croutons' WHERE id = 10;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Snijd alle groenten in blokjes\n2. Fruit ui en knoflook\n3. Voeg groenten en bouillon toe\n4. Kook 25 minuten\n5. Breng op smaak met kruiden' WHERE id = 11;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Week linzen 30 minuten\n2. Bak ui, wortel en selderij\n3. Voeg linzen en bouillon toe\n4. Laat 45 minuten zachtjes koken\n5. Pureer half voor romige textuur' WHERE id = 12;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Snijd varkenshaas in medaillons\n2. Bak aan in hete pan\n3. Blus met room\n4. Voeg mosterd en tijm toe\n5. Laat saus indikken' WHERE id = 13;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Marineer speklapjes met kruiden\n2. Leg op bakplaat met bakpapier\n3. Bak 25 min op 200°C\n4. Keer halverwege\n5. Serveer met appelmoes' WHERE id = 14;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Snijd vlees in blokjes\n2. Fruit ui en knoflook\n3. Voeg vlees toe en bak rondom bruin\n4. Doe groenten en bouillon erbij\n5. Stoof 1,5 uur op laag vuur' WHERE id = 15;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Rooster brood\n2. Beleg met kip, spek en ei\n3. Voeg sla en mayonaise toe\n4. Snijd diagonaal door\n5. Steek met prikkers vast' WHERE id = 16;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Rol deeg uit in vorm\n2. Klop eieren met room en kaas\n3. Bak spekjes knapperig\n4. Giet mengsel over deeg\n5. Bak 35 min op 180°C' WHERE id = 17;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Kook eieren en aardappelen\n2. Maak sladressing\n3. Rangschik sla, tonijn en groenten\n4. Garneer met olijven en ansjovis\n5. Besprenkel met dressing' WHERE id = 18;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Snijd groenten in plakjes\n2. Rangschik in ovenschaal\n3. Besprenkel met olijfolie en kruiden\n4. Bak 40 min op 190°C\n5. Serveer met vers basilicum' WHERE id = 19;

UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Bak ui, knoflook en gember\n2. Voeg kerriepoeder toe\n3. Doe kikkererwten en tomaten erbij\n4. Laat 20 minuten sudderen\n5. Serveer met rijst of naan' WHERE id = 20;


UPDATE recipes SET description = description || E'\n\nInstructies:\n1. Pers tofu uit en snijd in blokjes\n2. Bak tofu knapperig\n3. Roerbak groenten op hoog vuur\n4. Voeg sojasaus en sesamolie toe\n5. Meng tofu erdoor en serveer' WHERE id = 21;