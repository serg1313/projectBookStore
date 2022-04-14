package mybookstore.repository;

import mybookstore.model.Book;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для создания списка книг.
 */
public class BookRepositoryImpl implements BookRepository {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(BookRepositoryImpl.class.getName());
    /**
     * статичное поле типа BookRepository.
     */
    private static BookRepository bookRepository;
    /**
     * поле список книг.
     */
    private List<Book> books = new ArrayList<>();

    public BookRepositoryImpl() {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " the constructor is called BookRepositoryImpl");
        initData();
    }

    public static BookRepository getBookRepository() {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method getBookRepository");
        if (bookRepository == null) {
            bookRepository = new BookRepositoryImpl();
        }
        return bookRepository;
    }

    private void initData() {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method initData");
//        books.add(new Book("Время - деньги!", "Бенджамин Франклин", 2019, 500, true, LocalDate.of(2021, 1, 5)));
//        books.add(new Book("Тайна Богов", "Бернард Вербер", 2020, 450, true, LocalDate.of(2021, 2, 5)));
//        books.add(new Book("Квадрант денежного потока", "Роберт Кийосаки", 2018, 600, true, LocalDate.of(2021, 3, 5)));
//        books.add(new Book("Гений", "Теодор Драйзер", 2018, 850, false, LocalDate.of(2021, 4, 5)));
//        books.add(new Book("Фальшивомонетчики", "Андре Жид", 2015, 451, true, LocalDate.of(2021, 5, 5)));
//        books.add(new Book("Магазин древностей", "Чарльз Диккенс", 2020, 742, false, LocalDate.of(2021, 6, 5)));
//        books.add(new Book("Чингис Хан", "Василий Ян", 2010, 544, true, LocalDate.of(2021, 7, 5)));
//        books.add(new Book("Мастер и Маргарита", "Михаил Булгагов", 2013, 855, true, LocalDate.of(2021, 1, 1)));
//        books.add(new Book("Похититель Вечности", "Клайв Баркер", 2005, 456, true, LocalDate.of(2021, 9, 5)));
//        books.add(new Book("Гарри Поттер", "Джоан Роулинг", 2017, 770, false, LocalDate.of(2021, 10, 5)));
//        books.add(new Book("Код", "Изабелла Мальдонадо", 2019, 350, true, LocalDate.of(2021, 11, 5)));
//        books.add(new Book("Маленькая черная ложь", "Шарон Болтон", 2017, 449, true, LocalDate.of(2021, 12, 5)));
//        books.add(new Book("Железная вдова", "Сиран Джей Чжао", 2020, 450, true, LocalDate.of(2021, 1, 5)));
//        books.add(new Book("Последний день", "Тео Лоуренс", 2020, 541, true, LocalDate.of(2021, 10, 5)));
//        books.add(new Book("Рыцарь", "Карен Линч", 2016, 654, true, LocalDate.of(2021, 11, 5)));
//        books.add(new Book("Глубина", "Алма Катсу", 2015, 740, true, LocalDate.of(2021, 12, 5)));
//        books.add(new Book("Союз молодежи", "Андрей Сальников", 2014, 500, true, LocalDate.of(2021, 12, 5)));
//        books.add(new Book("Призраки Гарварда", "Франческа Серрителла", 2013, 666, true, LocalDate.of(2021, 1, 5)));
//        books.add(new Book("Игра с огнем", "Наталья Тимошенко", 2019, 313, true, LocalDate.of(2021, 2, 5)));
//        books.add(new Book("Сад нашей памяти", "Мэри Эллен Тейлор", 2013, 722, true, LocalDate.of(2021, 3, 5)));
//        books.add(new Book("Лагерь", "Эл К. Розен", 2010, 250, true, LocalDate.of(2022, 1, 5)));
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " method initData completed");
    }

    /**
     * метод возвращает список книг.
     * @return list book
     */
    @Override
    public List<Book> getBooks() {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method getBooks");
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " method getBooks completed");
        return books;
    }

    /**
     * поиск книги по ее id указаному в параметре.
     * @param id
     * @return book
     */
    @Override
    public Book getBookById(final long id) {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method getBookById");
        for (Book book : getBooks()) {
            if (book.getId() == id) {
                return book;
            }
        }
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " method getBookById completed");
        return null;
    }

    /**
     * добавляет книгу, указанному в параметре, в список книг, если статус книги false.
     * @param book
     */
    @Override
    public void addBook(final Book book) {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method addBook");
        if (!book.getStatusBook()) {
            book.setStatusBook(true);
            books.add(book);
        }
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " method addBook completed");
    }

    /**
     * Проверяет книгу которая передана в параметре с id книг из списка. Если
     * указанной книги нет, то добавляет новую книгу в список книг.
     * @param book
     */
    @Override
    public void addNewBookInRepository(final Book book) {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method addNewBookInRepository");
        List<Book> bookList = getBookRepository().getBooks();
        for (Book book1 : bookList) {
            if (book.getId() == book1.getId()) {
                book1 = book;
            }
        }
        books.add(book);
        if (!(bookList.contains(book))) {
            bookList.add(book);
        }
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " method addNewBookInRepository completed");
    }

    /**
     * Проверяет книгу которая передана в параметре с списком книг. Если
     * указанной книги нет, то добавляет новую книгу в список книг.
     * @param book
     */
    @Override
    public void addNewBook(final Book book) {
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " calling a method addNewBook");
        List<Book> list = bookRepository.getBooks();
        if (!list.contains(book)) {
            books.add(book);
        } else {
            System.out.println("книга существует");
        }
        LOG.info(BookRepositoryImpl.class.getSimpleName() + " method addNewBook completed");
    }
}



