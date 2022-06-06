package mybookstore.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * класс для создания объекта книга.
 */
public class Book extends BaseEntity implements Comparable<Book>, Serializable {
    /**
     * поле id.
     */
    private static long id = 1;
    /**
     * поле nameBook.
     */
    private String nameBook;
    /**
     * поле authorBook.
     */
    private String authorBook;
    /**
     * поле yearOfPublic.
     */
    private int yearOfPublic;
    /**
     * поле price.
     */
    private double price;
    /**
     * поле statusBook.
     */
    private boolean statusBook;
    /**
     * поле dateDelivery.
     */
    private LocalDate dateDelivery;
    /**
     * поле список запросов.
     */
    private List<Request> requests = new ArrayList<>();

    /**
     * Конструктор - создание нового объекта.
     * @param book book
     */
    public Book(final Book book) {
        super(id++);
        this.nameBook = book.getNameBook();
        this.authorBook = book.getAuthorBook();
        this.yearOfPublic = book.getYearOfPublic();
        this.price = book.getPrice();
        this.statusBook = true;
        this.dateDelivery = book.getDateDelivery();
    }

    /**
     * Конструктор - создание нового объекта.
     * @param name name
     * @param author author
     * @param year year
     * @param price price
     * @param status status
     * @param date date
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public Book(final String name, final String author, final int year, final double price, final boolean status, final LocalDate date) {
        super(id++);
        this.nameBook = name;
        this.authorBook = author;
        this.yearOfPublic = year;
        this.price = price;
        this.statusBook = status;
        this.dateDelivery = date;
    }

    /**
     * метод получения id.
     * @param idBook idBook
     */
    public Book(final long idBook) {
        super(id);
    }

    /**
     * метод по статусу книги.
     * @return если книги нет возварщает false.
     */
    public boolean getStatusBook() {
        return statusBook;
    }

    /**
     * метод устанавливает статус книги указанный в параметре.
     * @param status true или false
     */
    public void setStatusBook(final boolean status) {
        this.statusBook = status;
    }

    /**
     * метод возвращает id книги.
     * @return id id книги
     */
    public static long getIdBook() {
        return id;
    }

    /**
     * Метод возвращает имя книги.
     * @return nameBook имя книги
     */
    public String getNameBook() {
        return nameBook;
    }

    /**
     * Метод записывает имя книги из параметра.
     * @param name имя книги
     */
    public void setNameBook(final String name) {
        this.nameBook = name;
    }

    /**
     * метод возвращает автора книги.
     * @return authorBook автора книги
     */
    public String getAuthorBook() {
        return authorBook;
    }

    /**
     * установить автора книги.
     * @param authorBook автора книги
     */
    public void setAuthorBook(final String authorBook) {
        this.authorBook = authorBook;
    }

    /**
     * вернуть год публикации.
     * @return yearOfPublic год публикации
     */
    public int getYearOfPublic() {
        return yearOfPublic;
    }

    /**
     * установить год публиации.
     * @param yearOfPublic год публиации
     */
    public void setYearOfPublic(final int yearOfPublic) {
        this.yearOfPublic = yearOfPublic;
    }

    /**
     * вернуть стоимость книги.
     * @return price стоимость книги
     */
    public double getPrice() {
        return price;
    }

    /**
     * установить стоимость книги.
     * @param price стоимость книги
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /**
     * вернуть дату доставки книги.
     * @return dateDelivery дату доставки книги
     */
    public LocalDate getDateDelivery() {
        return dateDelivery;
    }

    /**
     * установить дату доставки книги.
     * @param dateDelivery дату доставки книги
     */
    public void setDateDelivery(final LocalDate dateDelivery) {
        this.dateDelivery = dateDelivery;
    }

    /**
     * вернуть список запросов.
     * @return requests список запросов
     */
    public List<Request> getRequests() {
        return requests;
    }

    /**
     * внести запрос в список запросов.
     * @param requests список запросов
     */
    public void setRequests(final Request requests) {
        this.requests.add(requests);
    }


    /**
     * переопределенный метод compareTo для класса Book.
     * @param o типа данных Book
     * @return книги сравниваемые по названию
     */
    @Override
    public int compareTo(final Book o) {
        return this.nameBook.compareTo(o.nameBook);
    }

    /**
     * переопределенный метод toString для класса Book.
     * @return переопределенный метод toString для класса Book
     */
    @Override
    public String toString() {
        return "Book{" + "id = " + getId()
                + " nameBook='" + nameBook + '\''
                + ", authorBook='" + authorBook + '\''
                + ", yearOfPublic=" + yearOfPublic
                + ", price=" + price
                + ", statusBook=" + statusBook
                + ", dateDelivery=" + dateDelivery
                + ", количество запросов на книгу=" + getRequests().size()
                + '}';
    }

    /**
     * переопределенный метод equals для класса Book.
     * @param o
     * @return true если книги одинаковы по названию и автору, falseесли не одинаковы
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book book = (Book) o;
        return nameBook.equals(book.nameBook) && authorBook.equals(book.authorBook);
    }

    /**
     * переопределенный метод hashCode для класса Book.
     * @return числовой представление объекта книга по названию и автору
     */
    @Override
    public int hashCode() {
        return Objects.hash(nameBook, authorBook);
    }
}

