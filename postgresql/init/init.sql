-- 文字コードの設定
SET client_encoding = 'UTF8';
-- テーブルの作成
CREATE TABLE book_tbl (
  book_id INTEGER PRIMARY KEY NOT NULL,
  title VARCHAR(100) NOT NULL,
  author VARCHAR(100) NOT NULL,
  genre VARCHAR(50),
  status BOOLEAN NOT NULL DEFAULT TRUE,
  publication_year INT,
  ISBN VARCHAR,
  publisher VARCHAR
);
CREATE TABLE user_tbl (
  user_id INTEGER PRIMARY KEY NOT NULL,
  pass VARCHAR(50) NOT NULL,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(50) NOT NULL,
  address VARCHAR(255),
  grade INTEGER NOT NULL
);
CREATE TABLE track_tbl (
  track_id INTEGER PRIMARY KEY NOT NULL,
  book_id INTEGER NOT NULL REFERENCES book_tbl(book_id),
  user_id INTEGER NOT NULL REFERENCES user_tbl(user_id),
  track_status VARCHAR(10) NOT NULL
);
CREATE TABLE due_tbl (
  track_id INTEGER PRIMARY KEY NOT NULL REFERENCES track_tbl(track_id),
  borrow_date DATE NOT NULL,
  return_due_date DATE NOT NULL
);
CREATE TABLE done_tbl (
  track_id INTEGER PRIMARY KEY NOT NULL REFERENCES track_tbl(track_id),
  done_date DATE NOT NULL
);
CREATE TABLE reservation_tbl (
  track_id INTEGER PRIMARY KEY NOT NULL,
  RESERVATION_DATE DATE NOT NULL
);
-- ダミーデータの入力
INSERT INTO book_tbl (book_id, title, author, genre, status, publication_year, ISBN, publisher) 
VALUES (1, 'Book Title 1', 'Author 1', 'Genre 1', TRUE, 2022, '123-4567890123', 'Publisher 1');

INSERT INTO user_tbl (user_id, pass, first_name, last_name, address, grade) 
VALUES (1, 'hashedpassword', 'First', 'Last', '123 Main St', 5);

INSERT INTO track_tbl (track_id, book_id, user_id, track_status) 
VALUES (1, 1, 1, '貸出');

INSERT INTO due_tbl (track_id, borrow_date, return_due_date) 
VALUES (1, '2023-05-01', '2023-05-31');

INSERT INTO done_tbl (track_id, done_date) 
VALUES (1, '2023-05-31');

INSERT INTO reservation_tbl (RESERVATION_ID, track_id, RESERVATION_DATE) 
VALUES (1, '2023-04-01');
