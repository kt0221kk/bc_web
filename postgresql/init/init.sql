CREATE TABLE book_tbl (
  book_id SERIAL PRIMARY KEY,
  title VARCHAR(255),
  author VARCHAR(255),
  genre VARCHAR(255),
  status VARCHAR(255),
  publication_year INTEGER,
  isbn VARCHAR(255),
  publisher VARCHAR(255)
);

CREATE TABLE user_tbl (
  user_id SERIAL PRIMARY KEY,
  password VARCHAR(255),
  first_name VARCHAR(255),
  last_name VARCHAR(255),
  address VARCHAR(255)
);

CREATE TABLE track_tbl (
  track_id SERIAL PRIMARY KEY,
  book_id INTEGER REFERENCES book_tbl(book_id),
  user_id INTEGER REFERENCES user_tbl(user_id),
  track_time TIMESTAMP,
  track_status VARCHAR(255) CHECK (track_status IN ('貸出', '返却', '予約', '予約取消'))
);

CREATE TABLE due_tbl (
  track_id INTEGER PRIMARY KEY REFERENCES track_tbl(track_id),
  borrow_date DATE,
  return_due_date DATE
);

CREATE TABLE reservation_tbl (
  track_id INTEGER PRIMARY KEY REFERENCES track_tbl(track_id),
  reservation_start_date DATE,
  reservation_end_date DATE,
  is_active BOOLEAN
);

-- ダミーデータの挿入
-- Insert into book_tbl
INSERT INTO book_tbl(title, author, genre, status, publication_year, isbn, publisher) 
VALUES ('The Great Book', 'John Doe', 'Fiction', 'Available', 2022, '123-456789-0', 'Big Publisher');

INSERT INTO book_tbl(title, author, genre, status, publication_year, isbn, publisher) 
VALUES ('Another Great Book', 'Jane Doe', 'Non-Fiction', 'Reserved', 2021, '123-456789-1', 'Another Big Publisher');

-- Insert into user_tbl
INSERT INTO user_tbl(password, first_name, last_name, address) 
VALUES ('password1', 'Alice', 'Smith', '123 Fake St');

INSERT INTO user_tbl(password, first_name, last_name, address) 
VALUES ('password2', 'Bob', 'Johnson', '456 Real St');

-- Insert into track_tbl
INSERT INTO track_tbl(book_id, user_id, track_time, track_status) 
VALUES (1, 1, CURRENT_TIMESTAMP, '貸出');

INSERT INTO track_tbl(book_id, user_id, track_time, track_status) 
VALUES (2, 2, CURRENT_TIMESTAMP, '予約');

-- Insert into due_tbl
INSERT INTO due_tbl(track_id, borrow_date, return_due_date) 
VALUES (1, CURRENT_DATE, CURRENT_DATE + INTERVAL '7 days');

-- Insert into reservation_tbl
INSERT INTO reservation_tbl(track_id, reservation_start_date, reservation_end_date, is_active) 
VALUES (2, CURRENT_DATE, CURRENT_DATE + INTERVAL '14 days', true);
