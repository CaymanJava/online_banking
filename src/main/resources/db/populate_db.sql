DELETE FROM users;
DELETE FROM accounts;
DELETE FROM messages;
DELETE FROM account_histories;

ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (login, password, first_name, last_name, email, enabled, role) VALUES
  ('login', '$2a$10$6VQmTi9OFIlafm4ggjyxS.vdbPfxE9gD0K8LcS9f2Ckolt5hcgFZ2', 'Dmitriy', 'Bosenko', 'bosenkodmitriy@gmail.ru', true, 'ROLE_SUPER_ADMIN'),
  ('admin', '$2a$10$jmz0mFynjuMtSo1lZW.JRuf8OJeA1u..ceaIzyywUKNab4EDBorsC', 'Admin', 'Adminow', 'admi@mail.ru', true, 'ROLE_ADMIN'),
  ('shapka', '$2a$10$sbrFSPcWL2cuqr4vLu95s.y5/PUJ0YVv.P..qF8vR2UclwmhGrBU2', 'Vitya', 'Yanukovich', 'mejigorje@mail.ru', true, 'ROLE_USER'),
  ('bimba', '$2a$10$avUz.Vzw.ovVnVNvHV489.VIRn2o2qsBYoivwZgxBdh2FmmDSfccu', 'Kolya', 'Azarov', 'azirov@mail.ru', true, 'ROLE_USER'),
  ('poroh', '$2a$10$ZjbhPnXJoLM3P60rWle09eWVJd0y3gDLUCyfFZPsa4qgXZBh9qMcG', 'Petya', 'Poroshenko', 'sweet@mail.ru', true, 'ROLE_USER'),
  ('bugsbunny', '$2a$10$zwuvKg6GV0JnHJwcCgBwMOBy3ha4hjnKwYM0dgsp2SxIaKTz1kMb6', 'Senya', 'Yacenuk', 'arsenushka@mail.ru', true, 'ROLE_USER'),
  ('wheel', '$2a$10$VES6iKUOhOhj1Ef/xH/CJO90DR1Pdyrmn1jzBZCNPzdXRQ8caau66', 'Yulya', 'Timoshenko', 'sizo@mail.ru', true, 'ROLE_USER'),
  ('skm', '$2a$10$ccL1njbvJPFEIrlSGIzPXO2G6gkLKV/XHv1rshegCXWApWkm.2GIu', 'Rinat', 'TotKotoryjSamyjBednij', '16billion_dollars@mail.ru', true, 'ROLE_USER'),
  ('painkiller', '$2a$10$BbYO7AfQQ8NhXkmeYLajce.KMoNrO6Oq0TtFu3xlrXvUame/BAPZW', 'Виталя', 'Кличко', 'superpower@mail.ru', true, 'ROLE_USER');

  INSERT INTO accounts(name, account_number, currency, balance, user_id) VALUES
  ('USD_account', '20200000000000000011', 'USD', 0, 1),
  ('PLN_account', '40400000000000000012', 'PLN', 0, 1),
  ('UAH_account', '10100000000000000013', 'UAH', 0, 1),
  ('EUR_account', '30300000000000000014', 'EUR', 0, 1),
  ('RUB_account', '50500000000000000015', 'RUB', 0, 1),
  ('доллары', '20200000000000000423', 'USD', 986784.89, 4),
  ('для побега в европу', '30300000000000000421', 'EUR', 56000000.00, 4),
  ('гривна', '10100000000000000425', 'UAH', 1678000000.00, 4),
  ('для продажи Roshen', '20200000000000000529', 'USD', 0, 5),
  ('для откатов', '10100000000000000530', 'UAH', 56000000.00, 5),
  ('на всякий случай', '40400000000000000532', 'PLN', 100000000.00, 5),
  ('hidden account', '30300000000000000527', 'EUR', 4600000000.00, 5),
  ('2', '20200000000000000842', 'USD', 9999999999.00, 8),
  ('3', '30300000000000000844', 'EUR', 999999999999.00, 8),
  ('4', '20200000000000000846', 'USD', 9999999999999.00, 8),
  ('5', '20200000000000000848', 'USD', 99999999999999.00, 8),
  ('1', '30300000000000000840', 'EUR', 10000000000000000.00, 8),
  ('на пиво', '10100000000000000850', 'UAH', 100000000000000000.00, 8),
  ('доларики', '20200000000000000635', 'USD', 1000000.00, 6),
  ('гривночка', '10100000000000000636', 'UAH', 5469933.00, 6),
  ('еврики', '30300000000000000634', 'EUR', 96000000.00, 6),
  ('актуальный счет', '50500000000000000320', 'RUB', 0.00, 3),
  ('на каждый день', '30300000000000000316', 'EUR', 10000000.00, 3),
  ('на черный день', '20200000000000000314', 'USD', 20000000.00, 3),
  ('гривневый счет', '10100000000000000318', 'UAH', 15000000000.00, 3),
  ('Мой новый счет', '30300000000000000752', 'EUR', 7858758959858.00, 7),
  ('Мой новый счет', '30300000000000000549', 'EUR', 200.00, 9),
  ('Мой новый счет', '20200000000000000559', 'USD', 550.00, 9);
