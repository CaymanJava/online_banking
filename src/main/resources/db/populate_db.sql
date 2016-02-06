DELETE FROM users;
DELETE FROM accounts;
DELETE FROM account_histories;
DELETE FROM roles;
ALTER SEQUENCE global_seq RESTART WITH 1;

INSERT INTO users (login, password, first_name, last_name, email) VALUES
  ('adminLogin', 'password', 'Dima', 'Bosenko', 'dima_boss@mail.ru'),
  ('userLogin1', 'password2', 'Nastya', 'Bosenko', 'nastya_boss@mail.ru'),
  ('userLogin2', 'password3', 'Vasya', 'Pupkin', '2e14s@mail.ru');

INSERT INTO accounts(name, account_number, currency, balance, user_id) VALUES
  ('USD_account', '20200000000000000011', 'USD', 0, 1),
  ('PLN_account', '40400000000000000012', 'PLN', 0, 1),
  ('UAH_account', '10100000000000000013', 'UAH', 0, 1),
  ('EUR_account', '30300000000000000014', 'EUR', 0, 1),
  ('RUB_account', '50500000000000000015', 'RUB', 0, 1),
  ('RUB', '50500000000000000026', 'RUB', 0, 2),
  ('UAH', '10100000000000000027', 'UAH', 0, 2),
  ('USD', '20200000000000000038', 'USD', 0, 3),
  ('EUR', '30300000000000000039', 'EUR', 0, 3);

INSERT INTO roles (user_id, role) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_ADMIN');
