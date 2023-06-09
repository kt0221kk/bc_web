package library_management_class;

import java.util.ArrayList;

import library_management_class.ConnectionManager;
import library_management_class.Book;
import java.util.Date;

import java.sql.*;
public class BookDao {
    private Connection connection;
    public BookDao(Connection connection) {
        super();
        this.connection = connection;
    }
    public int insert(Book book) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "INSERT INTO book_tbl (title, author, genre, status, publication_year, ISBN, publisher) VALUES (?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getStatus());
            preparedStatement.setInt(5, book.getPublicationYear());
            preparedStatement.setString(6, book.getIsbn());
            preparedStatement.setString(7, book.getPublisher());
            int result = preparedStatement.executeUpdate();

            return result;
        }catch(SQLException e){
            throw new RuntimeException("book_tblのINSERTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報登録時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public boolean update(Book book) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "UPDATE book_tbl SET title = ?, author = ?, genre = ?, status = ?, publication_year = ?, ISBN = ?, publisher = ? WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getStatus());
            preparedStatement.setInt(5, book.getPublicationYear());
            preparedStatement.setString(6, book.getIsbn());
            preparedStatement.setString(7, book.getPublisher());
            preparedStatement.setInt(8, book.getBookId());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            throw new RuntimeException("book_tblのUPDATEに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報更新時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public boolean delete(Book book) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "DELETE FROM book_tbl WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, book.getBookId());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            throw new RuntimeException("book_tblのDELETEに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報削除時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public Book selectById(int bookId) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM book_tbl WHERE book_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Book book = new Book();
            while(resultSet.next()){
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setStatus(resultSet.getString("status"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setIsbn(resultSet.getString("ISBN"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCreatedAt(resultSet.getTimestamp("created_at"));
            }
            return book;
        }catch(SQLException e){
            throw new RuntimeException("book_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public ArrayList<Book> selectByTitle(String title){
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM book_tbl WHERE title LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + title + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> bookList = new ArrayList<Book>();
            while(resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setStatus(resultSet.getString("status"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setIsbn(resultSet.getString("ISBN"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCreatedAt(resultSet.getTimestamp("created_at"));
                bookList.add(book);
            }
            return bookList;
        }catch(SQLException e){
            throw new RuntimeException("book_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public ArrayList<Book> selectByStatus(String status){
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM book_tbl WHERE status = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> bookList = new ArrayList<Book>();
            while(resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setStatus(resultSet.getString("status"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setIsbn(resultSet.getString("ISBN"));
                book.setPublisher(resultSet.getString("publisher"));
                bookList.add(book);
            }
            return bookList;
        }catch(SQLException e){
            throw new RuntimeException("book_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public ArrayList<Book> selectAll() {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM book_tbl";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> bookList = new ArrayList<Book>();
            while(resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setStatus(resultSet.getString("status"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setIsbn(resultSet.getString("ISBN"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCreatedAt(resultSet.getDate("created_at"));
                bookList.add(book);
            }
            return bookList;
        }catch(SQLException e){
            throw new RuntimeException("book_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public ArrayList<Book> selectByCreatedAt(Date start_date, Date end_date) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM book_tbl WHERE created_at BETWEEN ? AND ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1,new java.sql.Date(start_date.getTime()));
            preparedStatement.setDate(2,new java.sql.Date(end_date.getTime()));
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Book> bookList = new ArrayList<Book>();
            while(resultSet.next()){
                Book book = new Book();
                book.setBookId(resultSet.getInt("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setGenre(resultSet.getString("genre"));
                book.setStatus(resultSet.getString("status"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setIsbn(resultSet.getString("ISBN"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setCreatedAt(resultSet.getDate("created_at"));
                bookList.add(book);
            }
            return bookList;
        }catch(SQLException e){
            throw new RuntimeException("book_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }
}