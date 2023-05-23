# データベース構成
![データベースのER図](./ER%E5%9B%B3.PNG)
## book_tbl
| Field             | Type          | Constraints           | Default | Description                                  |
|-------------------|---------------|-----------------------|---------|----------------------------------------------|
| book_id           | INTEGER       | PRIMARY KEY, NOT NULL | None    | 書籍の一意のID                               |
| title             | VARCHAR(100)  | NOT NULL              | None    | 書籍のタイトル                               |
| author            | VARCHAR(100)  | NOT NULL              | None    | 書籍の著者                                  |
| genre             | VARCHAR(50)   | None                  | None    | 書籍のジャンル                               |
| status            | BOOLEAN       | NOT NULL              | TRUE    | 書籍の利用可能状態（TRUE:利用可能、FALSE:利用不可能）|
| publication_year  | INT           | None                  | None    | 出版年                                       |
| ISBN              | VARCHAR       | None                  | None    | ISBN番号                                     |
| publisher         | VARCHAR       | None                  | None    | 出版社                                       |
## user_tbl
| Field       | Type         | Constraints           | Default | Description                             |
|-------------|--------------|-----------------------|---------|-----------------------------------------|
| user_id     | INTEGER      | PRIMARY KEY, NOT NULL | None    | ユーザの一意のID                        |
| pass        | VARCHAR(50)  | NOT NULL              | None    | ユーザのパスワード（ハッシュ化されて保存されるべき）|
| first_name  | VARCHAR(50)  | NOT NULL              | None    | ユーザの名前                            |
| last_name   | VARCHAR(50)  | NOT NULL              | None    | ユーザの姓                              |
| address     | VARCHAR(255) | None                  | None    | ユーザの住所                            |
| grade       | INTEGER      | NOT NULL              | None    | ユーザの成績                            |
## track_tbl
| Field        | Type          | Constraints                     | Default | Description                   |
|--------------|---------------|---------------------------------|---------|-------------------------------|
| track_id     | INTEGER       | PRIMARY KEY, NOT NULL           | None    | 取引ログの一意のID            |
| book_id      | INTEGER       | NOT NULL, FOREIGN KEY           | None    | 書籍の一意のID                |
| user_id      | INTEGER       | NOT NULL, FOREIGN KEY           | None    | ユーザの一意のID              |
| track_status | VARCHAR(10)   | NOT NULL                        | None    | 取引の種別を貸出、返却、予約を区別  |

## due_tbl
| Field            | Type     | Constraints                         | Default | Description         |
|------------------|----------|-------------------------------------|---------|---------------------|
| track_id         | INTEGER  | PRIMARY KEY, NOT NULL, FOREIGN KEY  | None    | 取引ログの一意のID  |
| borrow_date      | DATE     | NOT NULL                            | None    | 書籍の貸出日        |
| return_due_date  | DATE     | NOT NULL                            | None    | 書籍の返却期限日    |
## done_tbl
| Field     | Type     | Constraints                         | Default | Description         |
|-----------|----------|-------------------------------------|---------|---------------------|
| track_id  | INTEGER  | PRIMARY KEY, NOT NULL, FOREIGN KEY  | None    | 取引ログの一意のID  |
| done_date | DATE     | NOT NULL                            | None    | 書籍の返却日        |
## reservation_tbl
| Field           | Type    | Constraints                      | Default | Description       |
|-----------------|---------|----------------------------------|---------|-------------------|
| resurvation_id  | INTEGER | PRIMARY KEY, NOT NULL            | None    | 予約ログの一意のID |
| track_id        | INTEGER | NOT NULL, FOREIGN KEY            | None    | 取引ログの一意のID |
| resutvation_date| DATE    | NOT NULL                         | None    | 予約日            |



