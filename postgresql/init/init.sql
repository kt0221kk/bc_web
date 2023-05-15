-- 文字コードの設定
SET client_encoding = 'UTF8';

-- テーブルの作成
CREATE TABLE users (
  id INTEGER PRIMARY KEY,
  password VARCHAR(10) NOT NULL,
  name VARCHAR(40) NOT NULL
);

-- データの挿入
INSERT INTO users(id, password, name) VALUES 
  (0, 'password', 'admin');
