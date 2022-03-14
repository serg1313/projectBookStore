package Task_3._4.model;

import java.time.LocalDate;

public class Book extends BaseEntity implements Comparable<Book> {
    private static long id = 0;
    private String nameBook;
    private String authorBook;
    private int yearOfPublic;
    private double price;
    private boolean statusBook;
    private LocalDate dateDelivery;

    public Book(Book book) {
        super(id++);
        this.nameBook=book.getNameBook();
        this.authorBook =book.getAuthorBook();
        this.yearOfPublic = book.getYearOfPublic();
        this.price = book.getPrice();
        this.statusBook = true;
        this.dateDelivery=book.getDateDelivery();
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

    @Override
    public int compareTo(Book o) {

        return this.nameBook.compareTo(o.nameBook);
    }

    @Override
    public String toString() {
        return "Book{" + "id = "+getId()+
                " nameBook='" + nameBook + '\'' +
                ", authorBook='" + authorBook + '\'' +
                ", yearOfPublic=" + yearOfPublic +
                ", price=" + price +
                ", statusBook=" + statusBook +
                '}';
    }
}

