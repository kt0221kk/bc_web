# 図書管理サイト作成プロジェクト

Docker Hubの[Tomcat公式イメージ](https://hub.docker.com/_/tomcat)を使用しています。

## 開始手順

1. コンテナをビルドします：
    ```bash
    docker-compose build
    ```
2. コンテナを起動します：
    ```bash
    docker-compose up -d
    ```

## Webサイトへのアクセス

次のURLにアクセスします：
    http://localhost:8080/library_management_system_bc/

## データベースへのアクセス方法

### 方法1
以下のコマンドで直接アクセスします：
    ```bash
    docker-compose exec db psql -U bc -W web_java
    ```

### 方法2
まず、DBのコンテナIDを取得します：
    ```bash
    docker-compose ps -q db
    ```
その後、取得したコンテナIDを使ってデータベースにアクセスします：
    ```bash
    docker exec -it <container_id> psql -U bc -W web_java
    ```

## Javaの実行方法

1. Javaコンテナのbashを起動します：
    ```bash
    docker-compose exec java bash
    ```
2. Javaのソースコードがあるディレクトリに移動します：
    ```bash
    cd library_management_system_bc/WEB-INF/src/
    ```
3. Javaファイルをコンパイルします：
    ```bash
    javac -d ../classes AccessLibraryData.java
    ```

# トラブルシューティング

## ビルドができない場合

### 事例1：ディレクトリをマウントする権限が足りていない
1. Docker Desktopを開きます。
2. "Settings"を開きます。
3. "Resources"を開きます。
4. "File sharing"を開きます。
5. "+"ボタンをクリックして、マウントする場所を追加します。

    注： `chmod` コマンドを使用してDockerの権限を強化する方法もあります。

## クラスパスを通す

クラスパスは環境ファイルに記述済みなので、現在は不要です。

### 方法1
    ```bash
    export CLASSPATH=/usr/local/tomcat/lib/servlet-api.jar:$CLASSPATH
    export CLASSPATH=/usr/local/tomcat/webapps/library_management_system_bc/WEB-INF/src/postgresql-42.6.0.jar:$CLASSPATH
    ```

### 方法2
    ```bash
    java -cp ./:postgresql-42.6.0.jar  Main
    docker-compose down --rmi all --volumes
    ```
