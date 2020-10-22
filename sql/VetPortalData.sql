BEGIN TRANSACTION;
INSERT INTO "clients" ("client_id","first_name","last_name","phone","email") VALUES (1,'john','doe','555-321-6543','johndoe@mail.com');
INSERT INTO "pets" ("pet_id","name","species","gender","dob","owner") VALUES (1,'Lily','cat','FS','2006-11-01',1);

INSERT INTO "appointments" ("date","time","client","pet","reason") VALUES ('2020-05-03','2020-05-03 11:00:00',1,1,'Annual Exam');
INSERT INTO "users" ("user_id","username","password") VALUES (1,'bob','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');
COMMIT;

/*
### INSERT statements used to generate the data above: ###

INSERT INTO users (username, password) VALUES ('bob', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8');

INSERT INTO  clients (first_name, last_name, phone, email) VALUES('john', 'doe', '555-321-6543', 'johndoe@mail.com');

INSERT INTO pets (name, species, gender, dob, owner) VALUES ('Isis', 'cat', 'FS', date('2006-11-01'), (SELECT client_id FROM clients WHERE first_name='john' AND last_name='doe'));

INSERT INTO appointments(date, time, client, pet, reason) VALUES (date('2020-05-03'), datetime('2020-05-03 11:00'), (SELECT owner FROM pets WHERE name='Isis'), (SELECT pet_id FROM pets WHERE name='Isis'), 'Annual Exam');
*/
