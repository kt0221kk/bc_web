# TOMCATの環境構築
https://hub.docker.com/_/tomcat
## 環境変数の設定
    CATALINA_BASE:   /usr/local/tomcat
    CATALINA_HOME:   /usr/local/tomcat
    CATALINA_TMPDIR: /usr/local/tomcat/temp
    JRE_HOME:        /usr
    CLASSPATH:       /usr/local/tomcat/bin/bootstrap.jar:/usr/local/tomcat/bin/tomcat-juli.jar

    # WEBアプリケーション with java
## はじめに
    docker-compose build
    docker-compose up -d
## Webサイトへのアクセス
    http://localhost:8080/library_management_system_bc/
## データベースへのアクセス方法
### その１
    docker-compose exec db psql -U bc -W web_java
### その２
    docker-compose ps -q db
    docker exec -it <container_id> psql -U bc -W web_java
## javaの実行
    docker-compose exec java bash
    cd library_management_system_bc/WEB-INF/src/
    javac -d ../classes AccessLibraryData.java 
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
    export CLASSPATH=/usr/local/tomcat/lib/servlet-api.jar:$CLASSPATH
    export CLASSPATH=/usr/local/tomcat/webapps/library_management_system_bc/WEB-INF/src/postgresql-42.6.0.jar:$CLASSPATH
### 方法２
    java -cp ./:postgresql-42.6.0.jar  Main
    docker-compose down --rmi all --volumes 