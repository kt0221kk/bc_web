# WEBアプリケーション with java
## データベースへのアクセス方法
### その１
    docker-compose ps -q db
    docker exec -it <container_id> psql -U your_user -W your_database
