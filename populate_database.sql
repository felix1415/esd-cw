/* Delete members from Members table */
DELETE FROM Members WHERE id IS NOT NULL;

/* Delete users from users table */
DELETE FROM users WHERE id IS NOT NULL;

/* Delete users from users table */
DELETE FROM payments WHERE id IS NOT NULL;

/* Delete users from users table */
DELETE FROM Claims WHERE id IS NOT NULL;

/* Add members to the Members table */
INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('a-ayala','Aidan Ayala','32 The Pasture, Bradley Stoke, Bristol, Avon, BS32 4FT', '1991/11/20', '2016/11/21','UNPAID', 0, 2);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('alex','Alex Gray','10 Maywood, Horfield, BS12 4TR', '1992-11-18', '2015-10-01','UNPAID', 0, 2);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('felix','Felix Gray','Flat 10, 34 Hallow Road, GL2 0FR', '1993-11-17', '2016-11-14','UNPAID', 0, 2);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('garylee','Gary Lee','10 Gallow Road, Bristol, BS17 4FY', '1991-04-09', '2011-11-04','UNPAID', 0, 2);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('me-aydin','Mehmet Aydin','148 Station Rd, London, N3 2SG', '1968-10-20', '2015-01-26','PAID', 0, 0);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('s-lee','Shaun Lee','54 Hert Drive, Gangstill, Hull, HU43 5GY', '1991-11-14', '2016-11-09','UNPAID', 0, 2);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('stephenturner','Stephen Turner','15 Helts Way, Cheltenham, GL54 4ED', '1995-11-08', '2016-11-15','UNPAID', 0, 2);

INSERT INTO Members (id, name, address, dob, dor, status, balance, claims_remaining)
VALUES ('troy','Troy Ong','164 Queens Road, Hotwells, BS18 5GH', '1994-11-22', '2016-11-21','UNPAID', 0, 2);

/* Add users to the users table */
INSERT INTO users (id, password, status, is_admin)
VALUES ('a-ayala','password', 'UNPAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('admin','1234', 'UNPAID', true);

INSERT INTO users (id, password, status, is_admin)
VALUES ('alex','gray', 'UNPAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('felix','1234', 'UNPAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('garylee','1234', 'UNPAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('me-aydin','201068', 'PAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('s-lee','1234', 'UNPAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('stephenturner','1234', 'UNPAID', false);

INSERT INTO users (id, password, status, is_admin)
VALUES ('troy','1234', 'UNPAID', false);

/* Add payment info to the payments table */
INSERT INTO payments (id, mem_id, type_of_payment, amount, date)
VALUES ('1','me-aydin', 'Membership', 500, '2015-11-27');

/* Add claims info to the Claims table */
INSERT INTO Claims (id, mem_id, date, rationale, status, amount)
VALUES ('1','me-aydin', '2016-11-27', 'Replace front bumper and bonnet', 'APPROVED', 400);
INSERT INTO Claims (id, mem_id, date, rationale, status, amount)
VALUES ('2','me-aydin', '2016-11-27', 'Front tyre and wheel replacements', 'APPROVED', 300);