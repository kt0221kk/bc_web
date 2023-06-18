DROP TABLE IF EXISTS reservation_tbl CASCADE;
DROP TABLE IF EXISTS due_tbl CASCADE;
DROP TABLE IF EXISTS track_tbl CASCADE;
DROP TABLE IF EXISTS user_tbl CASCADE;
DROP TABLE IF EXISTS book_tbl CASCADE;
CREATE TABLE book_tbl (
  book_id SERIAL PRIMARY KEY,
  title VARCHAR(255),
  author VARCHAR(255),
  genre VARCHAR(255),
  status VARCHAR(255) CHECK (status IN ('貸出可能', '貸出中','予約中','閲覧のみ可能','その他')),
  publication_year INTEGER,
  ISBN VARCHAR(255),
  publisher VARCHAR(255),
  -- 登録時に自動で現在時刻を入れる
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE user_tbl (
  user_id SERIAL PRIMARY KEY,
  password VARCHAR(255),
  user_name VARCHAR(255),
  address VARCHAR(255)
);
CREATE TABLE track_tbl (
  track_id SERIAL PRIMARY KEY,
  book_id INTEGER REFERENCES book_tbl(book_id),
  user_id INTEGER REFERENCES user_tbl(user_id),
  track_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  track_status VARCHAR(255) CHECK (track_status IN ('貸出', '返却', '予約', '予約取消','書籍登録','ユーザ情報更新'))
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
  is_active BOOLEAN DEFAULT true
);

CREATE UNIQUE INDEX idx_unique_reservations
ON reservation_tbl (reservation_start_date, reservation_end_date)
WHERE is_active = true;

-- ダミーデータの挿入
-- Insert into book_tbl
-- INSERT INTO book_tbl(title, author, genre, status, publication_year, isbn, publisher) 
-- VALUES ('The Great Book', 'John Doe', 'Fiction', '貸出中', 2022, '123-456789-0', 'Big Publisher');

-- Insert into user_tbl
INSERT INTO user_tbl(password, user_name, address)
VALUES ('password', 'John Doe', 'Tokyo'), ('abcd1234', 'Takuma', 'Osaka'),('test', 'test', 'test');

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
