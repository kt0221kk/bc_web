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
## クラスパスを通す
    export CLASSPATH=postgresql-42.6.0.jar:$CLASSPATH
    #これはできないのはなぜ？？
    export CLASSPATH=postgresql-42.6.0.jar:$CLASSPATH