# WEBアプリケーション with java
## はじめに
    docker-compose build
    docker-compose up -d
## データベースへのアクセス方法
### その１
    docker-compose exec db psql -U bc -W web_java
### その２
    docker-compose ps -q db
    docker exec -it <container_id> psql -U bc -W web_java
## javaの実行
    docker-compose exec java bash
# その他（忘備録用）
## ビルドができない場合
### 事例１　　ディレクトリをマウントする権限が足りてない
1. Docker Desktopを開く
2. Settingを開く
3. Resourcesを開く
4. File sharingを開く
5. +でマウントする場所を追加
他にもchmodコマンドでdockerの権限をつよつよにする方法もある
## クラスパスを通す(環境ファイルに記述済みのため今は不要)
### 方法１
    export CLASSPATH=postgresql-42.6.0.jar:$CLASSPATH
### 方法２
    java -cp ./:postgresql-42.6.0.jar  Main