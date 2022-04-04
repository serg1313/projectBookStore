package myBookStore.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Book extends BaseEntity implements Comparable<Book>, Serializable {
    private static final Logger log = LogManager.getLogger(Book.class.getName());
    private static long id = 1;
    private String nameBook;
    private String authorBook;
    private int yearOfPublic;
    private double price;
    private boolean statusBook;
    private LocalDate dateDelivery;

    private List<Request> requests = new ArrayList<>();

    public Book(Book book) {
        super(id++);
        this.nameBook = book.getNameBook();
        this.authorBook = book.getAuthorBook();
        this.yearOfPublic = book.getYearOfPublic();
        this.price = book.getPrice();
        this.statusBook = true;
        this.dateDelivery = book.getDateDelivery();
    }

    public Book(String nameBook, String authorBook, int yearOfPublic, double price, boolean statusBook, LocalDate dateDelivery) {
        super(id++);
        this.nameBook = nameBook;
        this.authorBook = authorBook;
        this.yearOfPublic = yearOfPublic;
        this.price = price;
        this.statusBook = statusBook;
        this.dateDelivery = dateDelivery;
    }

    public Book(long id) {
        super(id);
    }

    public boolean getStatusBook() {
        return statusBook;
    }

    public void setStatusBook(boolean statusBook) {
        this.statusBook = statusBook;
    }

    public static long getIdBook() {
        return id;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(String authorBook) {
        this.authorBook = authorBook;
    }

    public int getYearOfPublic() {
        return yearOfPublic;
    }

    public void setYearOfPublic(int yearOfPublic) {
        this.yearOfPublic = yearOfPublic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    public void setDateDelivery(LocalDate dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(Request requests) {
        this.requests.add(requests);
    }

    @Override
    public int compareTo(Book o) {
        return this.nameBook.compareTo(o.nameBook);
    }

    @Override
    public String toString() {
        return "Book{" + "id = " + getId() +
                " nameBook='" + nameBook + '\'' +
                ", authorBook='" + authorBook + '\'' +
                ", yearOfPublic=" + yearOfPublic +
                ", price=" + price +
                ", statusBook=" + statusBook +
                ", dateDelivery=" + dateDelivery +
                ", количество запросов на книгу=" + getRequests().size() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return nameBook.equals(book.nameBook) && authorBook.equals(book.authorBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameBook, authorBook);
    }
}

