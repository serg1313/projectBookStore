package mybookstore.service;

import com.opencsv.CSVWriter;
import mybookstore.model.Book;
import mybookstore.repository.BookRepository;
import mybookstore.repository.BookRepositoryImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс для работы с объектами классов BookRepository.
 */
public class BookServiceImpl implements BookService {
    /**
     * поле logger.
     */
    private static final Logger LOG = LogManager.getLogger(BookServiceImpl.class.getName());
    /**
     * Поле класса BookRepository для операциями с объектами данного класса.
     */
    private BookRepository bookRepository;
    /**
     * Поле класса RequestService для операциями с объектами данного класса.
     */
    private RequestService requestService;


    public BookServiceImpl(final BookRepository bookRepository, final RequestService requestService) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " the constructor is called ");
        this.bookRepository = bookRepository;
        this.requestService = requestService;
    }

    /**
     * Принимает id книги. Получает id книг из списка. Если книга с указанным id
     * имеет стаус true, то меняет статус на false и выводит на консоль что книга списана.
     * Если книга имеет статус false, то выводит на консоль что книга отуствует.
     * @param id
     */
    @Override
    public void writeOffBook(final long id) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " Method call writeOffBook with a parameter {} ", +id);

        Book book = bookRepository.getBookById(id);
        if (book != null) {
            if (book.getStatusBook()) {
                book.setStatusBook(false);
                System.out.println("Книга списана");
            }
        } else {
            System.out.println(" Данная книга отсутствует");
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method writeOffBook completed");
    }

    /**
     * Принимает id книги. Получает книгу из списка по указаному id.
     * Если книги с указанным id нет в списке, то выводит в консоль что книга не найдена.
     * Если книга с указанным id имеется, проверяет статус книги, сели статус true, выводит в консоль что книга имеется.
     * Если книга с указанным id имеет статус false, то добавляет книгу в список с помощью метода addBook.
     * @param id
     */
    @Override
    public void addBook(final long id) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method addBook");
        LOG.info(BookServiceImpl.class.getSimpleName() + " добавление книги");
        Book book = bookRepository.getBookById(id);
        if (bookRepository.getBookById(id) == null) {
            System.out.println("книга не найдена");
            LOG.info(BookServiceImpl.class.getSimpleName() + " книга не найдена ");
        } else if (!bookRepository.getBookById(id).getStatusBook()) {
            System.out.println("книга на складе имеется");
            LOG.info(BookServiceImpl.class.getSimpleName() + " книга на складе имеется");
        } else {
            requestService.canceledRequestByBookId(id);
            bookRepository.addBook(book);
            System.out.println("книга " + book.getNameBook() + " добавлена");
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method addBook completed");
    }

    /**
     * Принимает id книги.
     * Получете список книг и проводит сравнение на id  из параметра.
     * Если книга с указанным id найдена, выводит информацию о статусе книги (true или false).
     * @param idBook id книги
     * @return true - книга есть на складе, false - книги нет
     */
    @Override
    public boolean getStatusBook(final long idBook) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method getStatusBook");
        for (Book b : bookRepository.getBooks()) {
            if (b.getId() == idBook) {
                if (b.getStatusBook()) {
                    System.out.println("книга " + b.getNameBook() + " имеется на складе ");
                    LOG.info(BookServiceImpl.class.getSimpleName() + " книга с указанным id имеется на складе");
                    break;
                } else {
                    System.out.println("книга " + b.getNameBook() + " отсутствует на складе ");
                    LOG.info(BookServiceImpl.class.getSimpleName() + " книга с указанным id остутствует на складе");
                    break;
                }
            }
            LOG.info(BookServiceImpl.class.getSimpleName() + " книга с указанным id остутствует на складе");
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method getStatusBook completed");
        return false;
    }

    /**
     * Принимает список книг и производит сортировку книг названию книг.
     * @param bookList
     */
    @Override
    public void sortingBookByName(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortingBookByName");
        Collections.sort(bookList);
        for (Book book : bookList) {
            System.out.println(book);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " сортировка проведена");
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortingBookByName completed");
    }

    /**
     * Получает список книг и производит сортировку книг по дате издания.
     * @param bookList
     */
    @Override
    public void sortingBookByYearOfPublic(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortingBookByYearOfPublic");
        bookList.sort((o1, o2) -> o1.getYearOfPublic() - o2.getYearOfPublic());
        for (Book book : bookList) {
            System.out.println(book);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " сортировка проведена");
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortingBookByYearOfPublic completed");
    }

    /**
     * Метод принимает список книг и производит сортировку книг по стоимости от низкой к дорогой.
     * @param bookList принимает список книг
     */
    @Override
    public void sortingBookByPrice(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortingBookByPrice");
        bookList.sort((Book o1, Book o2) -> (int) (o1.getPrice() - o2.getPrice()));
        for (Book book : bookList) {
            System.out.println(book);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " сортировка проведена");
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortingBookByPrice completed");
    }

    /**
     * Принимает статус книги (true или false) и производит сортировку книг по наличию на складе.
     * @param status кни
     */
    @Override
    public void sortBooksByStockAvailability(final boolean status) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortBooksByStockAvailability");
        if (status) {
            for (Book book : bookRepository.getBooks()) {
                if (book.getStatusBook()) {
                    System.out.println(book);
                }
            }
        } else {
            for (Book book : bookRepository.getBooks()) {
                if (book.getStatusBook() == status) {
                    System.out.println(book);
                }
            }
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortBooksByStockAvailability completed");
    }

    /**
     * Принимает id книги и возвращает книгу по id если книга найдена.
     * @param b
     * @return
     */
    @Override
    public Book getBookById(final long b) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method getBookById");
        Book current = null;
        for (Book book : bookRepository.getBooks()) {
            if (book.getId() == b) {
                current = book;
                break;
            }
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " книги с указанным id - " + b + " нет");
        LOG.info(BookServiceImpl.class.getSimpleName() + " method getBookById completed");
        return current;
    }

    /**
     * Возвращает общее количество книг в списке книг у которых статус true.
     * @return
     */
    @Override
    public int getCountBookByRepository() {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method getCountBookByRepository");
        int count = 0;
        for (Book book : bookRepository.getBooks()) {
            if (book.getStatusBook()) {
                count++;
            }
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method getCountBookByRepository completed");
        return count;
    }

    /**
     * Принимает список книг и производит сортировку книг из списка по id.
     * @param bookList
     */
    @Override
    public void sortingBookById(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortingBookById");
        bookList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        for (Book book : bookList) {
            System.out.println(book);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortingBookById completed");
    }

    /**
     * Принимает список книг и производит сотрировку не проданных книг больше 6 мес по цене.
     * @param bookList
     * @return
     */
    @Override
    public List<Book> sortByPriceListOfStaleBooksNotSold(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortByPriceListOfStaleBooksNotSold");
        final long month = 6;
        List<Book>list = bookList;
        list = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook())
                .filter(book -> book.getDateDelivery().plusMonths(month).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        bookList.sort((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortByPriceListOfStaleBooksNotSold completed");
        return bookList;
    }

    /**
     * Принимает список книг и производит сотрировку не проданных книг больше 6 мес по названию книги.
     * @param bookList
     * @return список книг по названию
     */
    @Override
    public List<Book> sortByNameListOfStaleBooksNotSold(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortByNameListOfStaleBooksNotSold");
        final long month = 6;
        List<Book> list = bookList;
        list = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook())
                .filter(book -> book.getDateDelivery().plusMonths(month).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        bookList.sort((o1, o2) -> o1.getNameBook().compareTo(o2.getNameBook()));
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortByNameListOfStaleBooksNotSold completed");
        return bookList;
    }

    /**
     * Принимает список книг и производит сотрировку не проданных книг больше 6 мес по дате доставки книги.
     * @param books
     * @return
     */
    @Override
    public List<Book> sortByDateDeliveryOfStaleBooksNotSold(final List<Book> books) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortByDateDeliveryOfStaleBooksNotSold");
        final long month = 6;
        List<Book> list = books;
        list = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook())
                .filter(book -> book.getDateDelivery().plusMonths(month).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        books.sort((o1, o2) -> o1.getDateDelivery().compareTo(o2.getDateDelivery()));
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortByDateDeliveryOfStaleBooksNotSold completed");
        return books;
    }

    /**
     * Принимает список книг и сотрирует их по дате доставки.
     * @param bookList
     */
    @Override
    public void sortBookByDateDelivery(final List<Book> bookList) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method sortBookByDateDelivery");
        List<Book> list = bookList;
        list.sort(Comparator.comparing(Book::getDateDelivery));
        for (Book book : bookList) {
            System.out.println(book);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method sortBookByDateDelivery completed");
    }

    /**
     * Принимает id книги и выводит н консоль информацию о книге.
     * @param id
     */
    @Override
    public void getDescriptionBook(final long id) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method getDescriptionBook");
        Book book = bookRepository.getBookById(id);
        System.out.println("Автор книги - " + book.getAuthorBook() + ". Наименование книги - " + book.getNameBook()
                + ". Цена книги - " + book.getPrice() + ". Дата издание книги - " + book.getYearOfPublic());
        LOG.info(BookServiceImpl.class.getSimpleName() + " method getDescriptionBook completed");
    }


    /**
     * создает csv файл и заполняет его книгами из списка книг.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void writeFileCsvBook() {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method writeFileCsvBook");
        String csvFile = "bookRepository.csv";
        Path path = Paths.get(csvFile);
        File file = new File(csvFile);
        List<Book> bookList = bookRepository.getBooks();
        try (CSVWriter writer = new CSVWriter(new FileWriter(csvFile),
                CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END)) {
            for (Book b : bookList) {
                final int lengthArray = 7;
                String[] csvNewFile = new String[lengthArray];
                csvNewFile[0] = String.valueOf(b.getId());
                csvNewFile[1] = b.getNameBook();
                csvNewFile[2] = b.getAuthorBook();
                csvNewFile[3] = String.valueOf(b.getYearOfPublic());
                csvNewFile[4] = String.valueOf(b.getPrice());
                csvNewFile[5] = String.valueOf(b.getStatusBook());
                csvNewFile[6] = String.valueOf(b.getDateDelivery());
                writer.writeNext(csvNewFile);
            }
        } catch (IOException e) {
            LOG.error(BookServiceImpl.class.getSimpleName() + " Error message ", e);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method writeFileCsvBook completed");
    }

    /**
     * Принимает книгу из параметра, проверяет - существует ли такая книга в списке, если нет, то
     * записывает ее в CSV файл.
     * @param book
     */
    @SuppressWarnings({"checkstyle:MagicNumber"})
    @Override
    public void addNewBookToRepository(final Book book) {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method addNewBookToRepository");
        String csvFile = "bookRepository.csv";
        File file = new File(csvFile);
        if (!file.exists() && !file.isDirectory()) {
            writeFileCsvBook();
        }
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(csvFile, true),
                    CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_QUOTE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            Book b = book;
            String[] record = new String[7];
            for (Book book1 : bookRepository.getBooks()) {
                if (!book.equals(book1)) {
                    record[0] = String.valueOf(b.getId());
                    record[1] = String.valueOf(b.getNameBook());
                    record[2] = String.valueOf(b.getAuthorBook());
                    record[3] = String.valueOf(b.getYearOfPublic());
                    record[4] = String.valueOf(b.getPrice());
                    record[5] = String.valueOf(b.getStatusBook());
                    record[6] = String.valueOf(b.getDateDelivery());
                }
            }
            writer.writeNext(record);
            writer.close();
        } catch (IOException e) {
            LOG.error(BookServiceImpl.class.getSimpleName() + " Error message ", e);
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method addNewBookToRepository completed");
    }

    /**
     * Проверяет на заполненность список книг. Если список книг пустой, то считывает CSV файл и вносит
     * считанную информацию о книгах в список.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    @Override
    public void readCsvBook() {
        LOG.info(BookServiceImpl.class.getSimpleName() + " calling a method readCsvBook");
        BookRepository bookRepository1 = BookRepositoryImpl.getBookRepository();
        List<Book> books = bookRepository1.getBooks();
        if (books.size() == 0) {
            String csv = "bookRepository.csv";
            try (BufferedReader reader = new BufferedReader(new FileReader(csv));) {
                String line = null;
                while ((line = reader.readLine()) != null) {
                    String[] items = line.split(",");
                    try {
                        Long id = Long.parseLong(items[0]);
                        String name = items[1];
                        String autor = items[2];
                        int year = Integer.parseInt(items[3]);
                        double price = Double.parseDouble(items[4]);
                        boolean status = Boolean.parseBoolean(items[5]);
                        LocalDate date = LocalDate.parse(items[6]);
                        Book book = new Book(name, autor, year, price, status, date);
                        books.add(book);
                    } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
                        LOG.error(BookServiceImpl.class.getSimpleName() + " Error message", e);
                    }
                }
            } catch (IOException e) {
                LOG.error(BookServiceImpl.class.getSimpleName() + " Error message", e);
            }
        }
        LOG.info(BookServiceImpl.class.getSimpleName() + " method readCsvBook completed");
    }
}
