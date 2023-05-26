package library_management_class;
public class Book {
    private int book_id;
    private String title;
    private String author;
    private String genre;
    private boolean status;
    private int publication_year;
    private String ISBN;
    private String publisher;

    // Getters
    public int getBookId() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isStatus() {
        return status;
    }

    public int getPublicationYear() {
        return publication_year;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    // Setters
    public void setBookId(int book_id) {
        this.book_id = book_id;
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

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPublicationYear(int publication_year) {
        this.publication_year = publication_year;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
