BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "clients" (
	"client_id"	INTEGER,
	"first_name"	VARCHAR(255) NOT NULL,
	"last_name"	VARCHAR(255) NOT NULL,
	"phone"	VARCHAR(255) NOT NULL UNIQUE,
	"email"	VARCHAR(255) NOT NULL,
	PRIMARY KEY("client_id")
);
CREATE TABLE IF NOT EXISTS "pets" (
	"pet_id"	INTEGER,
	"name"	VARCHAR(255) NOT NULL,
	"species"	VARCHAR(255) NOT NULL,
	"gender"	CHARACTER(2) NOT NULL,
	"dob"	DATE NOT NULL,
	"owner"	INTEGER NOT NULL,
	PRIMARY KEY("pet_id"),
	CONSTRAINT "fkpet_client" FOREIGN KEY("owner") REFERENCES "clients"("client_id") ON DELETE CASCADE
);
CREATE TABLE IF NOT EXISTS "appointments" (
	"date"	DATE NOT NULL,
	"time"	DATETIME NOT NULL,
	"client"	INTEGER NOT NULL,
	"pet"	INTEGER NOT NULL,
	"reason"	VARCHAR(255) NOT NULL,
	CONSTRAINT "fkapt_client" FOREIGN KEY("client") REFERENCES "clients"("client_id") ON DELETE CASCADE,
	CONSTRAINT "fkapt_pet" FOREIGN KEY("pet") REFERENCES "pets"("pet_id") ON DELETE CASCADE,
	PRIMARY KEY("date","time")
);
CREATE TABLE IF NOT EXISTS "users" (
	"user_id"	INTEGER,
	"username"	VARCHAR(255) NOT NULL,
	"password"	VARCHAR(255) NOT NULL,
	PRIMARY KEY("user_id")
);
COMMIT;
