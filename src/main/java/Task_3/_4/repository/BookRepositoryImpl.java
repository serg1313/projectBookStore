package Task_3._4.repository;

import Task_3._4.model.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private List<Book> books = new ArrayList<>();

    public BookRepositoryImpl() {
        initData();
    }

    private void initData() {
        books.add(new Book("Время - деньги!", "Бенджамин Франклин", 2019, 500, true, LocalDate.of(2021, 1, 5)));
        books.add(new Book("Тайна Богов", "Бернард Вербер", 2020, 450, true, LocalDate.of(2021, 2, 5)));
        books.add(new Book("Квадрант денежного потока", "Роберт Кийосаки", 2018, 600, true, LocalDate.of(2021, 3, 5)));
        books.add(new Book("Гений", "Теодор Драйзер", 2018, 850, false, LocalDate.of(2021, 4, 5)));
        books.add(new Book("Фальшивомонетчики", "Андре Жид", 2015, 451, true, LocalDate.of(2021, 5, 5)));
        books.add(new Book("Магазин древностей", "Чарльз Диккенс", 2020, 742, false, LocalDate.of(2021, 6, 5)));
        books.add(new Book("Чингис Хан", "Василий Ян", 2010, 544, true, LocalDate.of(2021, 7, 5)));
        books.add(new Book("Мастер и Маргарита", "Михаил Булгагов", 2013, 855, true, LocalDate.of(2021, 8, 5)));
        books.add(new Book("Похититель Вечности", "Клайв Баркер", 2005, 456, true, LocalDate.of(2021, 9, 5)));
        books.add(new Book("Гарри Поттер", "Джоан Роулинг", 2017, 770, false, LocalDate.of(2021, 10, 5)));
        books.add(new Book("Код", "Изабелла Мальдонадо", 2019, 350, true, LocalDate.of(2021, 11, 5)));
        books.add(new Book("Маленькая черная ложь", "Шарон Болтон", 2017, 449, true, LocalDate.of(2021, 12, 5)));
        books.add(new Book("Железная вдова", "Сиран Джей Чжао", 2020, 450, true, LocalDate.of(2021, 1, 5)));
        books.add(new Book("Последний день", "Тео Лоуренс", 2020, 541, true, LocalDate.of(2021, 10, 5)));
        books.add(new Book("Рыцарь", "Карен Линч", 2016, 654, true, LocalDate.of(2021, 11, 5)));
        books.add(new Book("Глубина", "Алма Катсу", 2015, 740, true, LocalDate.of(2021, 12, 5)));
        books.add(new Book("Союз молодежи", "Андрей Сальников", 2014, 500, true, LocalDate.of(2021, 12, 5)));
        books.add(new Book("Призраки Гарварда", "Франческа Серрителла", 2013, 666, true, LocalDate.of(2021, 1, 5)));
        books.add(new Book("Игра с огнем", "Наталья Тимошенко", 2019, 313, true, LocalDate.of(2021, 2, 5)));
        books.add(new Book("Сад нашей памяти", "Мэри Эллен Тейлор", 2013, 722, true, LocalDate.of(2021, 3, 5)));
        books.add(new Book("Лагерь", "Эл К. Розен", 2010, 250, true, LocalDate.of(2022, 1, 5)));
    }

    /**
     * возвращает список книг на складе
     *
     * @return
     */
    @Override
    public List<Book> getBooks() {
        return books;
    }

    /**
     * поиск книги по ее id
     *
     * @param id
     * @return
     */
    @Override
    public Book getBookById(long id) {
        for (Book book : getBooks()) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    /**
     * добавляет книгу на склад
     *
     * @param book
     */
    @Override
    public void addBook(Book book) {
        if (book.getStatusBook() == false) {
            book.setStatusBook(true);
            books.add(book);
        }
    }

    public void addNewBookInRepository(Book book) {
        for (Book book1 : books) {
            if (book1.getNameBook() != book.getNameBook() && book1.getAuthorBook() != book.getAuthorBook() && book1.getYearOfPublic() != book.getYearOfPublic() && book.getStatusBook() == false) {
                books.add(new Book(book));
            }
        }
    }
}
