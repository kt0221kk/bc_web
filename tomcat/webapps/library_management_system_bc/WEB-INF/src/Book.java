package library_management_class;

public class Book {

    private int bookId;
    private String title;
    private String author;
    private String genre;
    private String status;
    private int publicationYear;
    private String isbn;
    private String publisher;

    // getters
    public int getBookId() {
        return this.bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getGenre() {
        return this.genre;
    }

    public String getStatus() {
        return this.status;
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getPublisher() {
        return this.publisher;
    }

    // setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
