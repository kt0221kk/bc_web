package library_management_class;

import java.util.ArrayList;
import library_management_class.Book;
import library_management_class.ConnectionManager;
import library_management_class.Book;
import library_management_class.BookDao;
import java.sql.Connection;
public class OperateBook {
    private Connection connection;
    public OperateBook(Connection connection) {
        super();
        this.connection = connection;
    }
    public ArrayList<Book> searchAllBooks() {
        BookDao bookDao = new BookDao(connection);
        ArrayList<Book> bookList = bookDao.selectAll();
        return bookList;
    }

    public ArrayList<Book> searchBook(String bookTitle) {
        BookDao bookDao = new BookDao(connection);
        ArrayList<Book> bookList = bookDao.selectByTitle(bookTitle);
        return bookList;
    }

    public ArrayList<Book> getBorrowingRecords(String status) {
        BookDao bookDao = new BookDao(connection);
        ArrayList<Book> bookList = bookDao.selectByStatus(status);
        return bookList;
    }

    public boolean registerBook(Book book) {
        BookDao bookDao = new BookDao(connection);
        int result = bookDao.insert(book);
        if (result == 1){
            return true;
        }else{
            return false;
        }
    }
}
