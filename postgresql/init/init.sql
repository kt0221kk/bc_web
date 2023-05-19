-- 文字コードの設定
SET client_encoding = 'UTF8';

-- テーブルの作成
CREATE TABLE users (
  ID INTEGER PRIMARY KEY,
  PASSWORD VARCHAR(10) NOT NULL,
  NAME VARCHAR(40) NOT NULL
);
CREATE TABLE T_BOOK (
  ID SERIAL PRIMARY KEY,
  TITLE VARCHAR(100) NOT NULL,
  AUTHOR VARCHAR(40) NOT NULL,
  STATUS BOOLEAN DEFAULT TRUE,
  START_DATE DATE,
  END_DATE DATE
);

-- データの挿入
INSERT INTO users(ID, PASSWORD, name) VALUES 
  (0, 'password', 'admin');
INSERT INTO T_BOOK (TITLE, AUTHOR, STATUS, START_DATE, END_DATE) VALUES
('Moby Dick', 'Herman Melville', TRUE, '2023-01-01', '2023-01-31'),
('To Kill a Mockingbird', 'Harper Lee', FALSE, '2023-02-01', '2023-02-28'),
('1984', 'George Orwell', TRUE, '2023-03-01', '2023-03-31');
INSERT INTO T_BOOK (TITLE, AUTHOR, START_DATE, END_DATE)
VALUES 
('カートゥーン小説 ヘンな生き物研究', 'Author1', '2023-01-01', '2023-12-31'),
('きみとチェンジ!? 入れかわり中に', 'Author2', '2023-01-02', '2023-12-31'),
('いきいき！保育士', 'Author3', '2023-01-03', '2023-12-31'),
('いみちぇん！（３） ねらわれた主さま', 'Author4', '2023-01-04', '2023-12-31'),
('ドラゴンボール超 スーパーヒーロー', 'Author5', '2023-01-05', '2023-12-31'),
('少年探偵 響（３） 夜の学校で七不', 'Author6', '2023-01-06', '2023-12-31'),
('生き残りゲーム ラストサバイバル', 'Author7', '2023-01-07', '2023-12-31'),
('恋する和パティシエール １ 夢みる', 'Author8', '2023-01-08', '2023-12-31'),
('恐怖チャンネル 不幸をまねくコトリ', 'Author9', '2023-01-09', '2023-12-31'),
('理花のおかしな実験室（６） 波乱だ', 'Author10', '2023-01-10', '2023-12-31'),
('動物と話せる少女リリアーネ（９）', 'Author11', '2023-01-11', '2023-12-31'),
('ドリトル先生月へゆく', 'Author12', '2023-01-12', '2023-12-31'),
('いみちぇん！（１） 今日からひみつ', 'Author13', '2023-01-13', '2023-12-31'),
('アンデルセン童話集 １', 'Author14', '2023-01-14', '2023-12-31'),
('動物と話せる少女リリアーネ（７）', 'Author15', '2023-01-15', '2023-12-31');