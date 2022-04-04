package myBookStore.service;

import myBookStore.model.Book;
import myBookStore.repository.BookRepository;
import myBookStore.repository.BookRepositoryImpl;
import com.opencsv.CSVWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {
    private static final Logger log = LogManager.getLogger(BookServiceImpl.class.getName());

    private BookRepository bookRepository;
    private RequestService requestService;

    public BookServiceImpl(BookRepository bookRepository, RequestService requestService) {
        log.info("the constructor is called ");
        this.bookRepository = bookRepository;
        this.requestService = requestService;
    }

    /**
     * списать книгу со склада и перевести в статус отсутствует
     *
     * @param id
     */
    @Override
    public void writeOffBook(long id) {
        log.info("Method call writeOffBook with a parameter {} ", +id);

        Book book = bookRepository.getBookById(id);
        if (book != null) {
            if (book.getStatusBook()) {
                book.setStatusBook(false);
                System.out.println("Книга списана");
            }
        } else {
            System.out.println(" Данная книга отсутствует");
        }
        log.info("method writeOffBook completed");
    }

    /**
     * добавить книгу на склад
     *
     * @param id
     */
    @Override
    public void addBook(long id) {
        log.info("calling a method addBook");
        log.info("добавление книги");
        Book book = bookRepository.getBookById(id);
        if (bookRepository.getBookById(id) == null) {
            System.out.println("книга не найдена");
            log.info("книга не найдена ");
        } else if (bookRepository.getBookById(id).getStatusBook() != true) {
            System.out.println("книга на складе имеется");
            log.info("книга на складе имеется");
        } else {
            requestService.canceledRequestByBookId(id);
            bookRepository.addBook(book);
            System.out.println("книга " + book.getNameBook() + " добавлена");
        }
        log.info("method addBook completed");
    }

    /**
     * узнать наличие книги на складе
     *
     * @param idBook id книги
     * @return true - книга есть на складе, false - книги нет
     */
    @Override
    public boolean getStatusBook(long idBook) {
        log.info("calling a method getStatusBook");
        for (Book b : bookRepository.getBooks()) {
            if (b.getId() == idBook) {
                if (b.getStatusBook()) {
                    System.out.println("книга " + b.getNameBook() + " имеется на складе ");
                    log.info("книга с указанным id имеется на складе");
                    break;
                } else {
                    System.out.println("книга " + b.getNameBook() + " отсутствует на складе ");
                    log.info("книга с указанным id остутствует на складе");
                    break;
                }
            }
            log.info("книга с указанным id остутствует на складе");
        }
        log.info("method getStatusBook completed");
        return false;
    }

    /**
     * сортировка книг по названию
     *
     * @param bookList
     */
    @Override
    public void sortingBookByName(List<Book> bookList) {
        log.info("calling a method sortingBookByName");
        Collections.sort(bookList);
        for (Book book : bookList)
            System.out.println(book);
        log.info("сортировка проведена");
        log.info("method sortingBookByName completed");
    }

    /**
     * сортировка книг по дате издания
     *
     * @param bookList
     */
    @Override
    public void sortingBookByYearOfPublic(List<Book> bookList) {
        log.info("calling a method sortingBookByYearOfPublic");
        bookList.sort((o1, o2) -> o1.getYearOfPublic() - o2.getYearOfPublic());
        for (Book book : bookList)
            System.out.println(book);
        log.info("сортировка проведена");
        log.info("method sortingBookByYearOfPublic completed");
    }

    /**
     * Сортировка книг по стоимости
     *
     * @param bookList
     */
    @Override
    public void sortingBookByPrice(List<Book> bookList) {
        log.info("calling a method sortingBookByPrice");
        bookList.sort((Book o1, Book o2) -> (int) (o1.getPrice() - o2.getPrice()));
        for (Book book : bookList)
            System.out.println(book);
        log.info("сортировка проведена");
        log.info("method sortingBookByPrice completed");
    }

    /**
     * сортировка книг по наличию на складе
     *
     * @param status
     */
    @Override
    public void sortBooksByStockAvailability(boolean status) {
        log.info("calling a method sortBooksByStockAvailability");
        if (status) {
            for (Book book : bookRepository.getBooks()) {
                if (book.getStatusBook()) {
                    System.out.println(book);
                }
            }
        } else {
            for (Book book : bookRepository.getBooks()) {
                if (book.getStatusBook() == status)
                    System.out.println(book);
            }
        }
        log.info("method sortBooksByStockAvailability completed");
    }

    /**
     * Возвращает книгу по ID
     *
     * @param b
     * @return
     */
    @Override
    public Book getBookById(long b) {
        log.info("calling a method getBookById");
        Book current = null;
        for (Book book : bookRepository.getBooks()) {
            if (book.getId() == b) {
                current = book;
                break;
            }
        }
        log.info("книги с указанным id - " + b + " нет");
        log.info("method getBookById completed");
        return current;
    }

    @Override
    public int getCountBookByRepository() {
        log.info("calling a method getCountBookByRepository");
        int count = 0;
        for (Book book : bookRepository.getBooks()) {
            if (book.getStatusBook() == true) {
                count++;
            }
        }
        log.info("method getCountBookByRepository completed");
        return count;
    }

    @Override
    public void sortingBookById(List<Book> bookList) {
        log.info("calling a method sortingBookById");
        bookList.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        for (Book book : bookList) {
            System.out.println(book);
        }
        log.info("method sortingBookById completed");
    }

    @Override
    public List<Book> sortByPriceListOfStaleBooksNotSold(List<Book> bookList) {
        log.info("calling a method sortByPriceListOfStaleBooksNotSold");
        bookList = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook() == true)
                .filter(book -> book.getDateDelivery().plusMonths(6).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        bookList.sort((o1, o2) -> (int) (o1.getPrice() - o2.getPrice()));
        log.info("method sortByPriceListOfStaleBooksNotSold completed");
        return bookList;
    }

    @Override
    public List<Book> sortByNameListOfStaleBooksNotSold(List<Book> bookList) {
        log.info("calling a method sortByNameListOfStaleBooksNotSold");
        bookList = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook() == true)
                .filter(book -> book.getDateDelivery().plusMonths(6).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        bookList.sort((o1, o2) -> o1.getNameBook().compareTo(o2.getNameBook()));
        log.info("method sortByNameListOfStaleBooksNotSold completed");
        return bookList;
    }

    @Override
    public List<Book> sortByDateDeliveryOfStaleBooksNotSold(List<Book> books) {
        log.info("calling a method sortByDateDeliveryOfStaleBooksNotSold");
        books = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook() == true)
                .filter(book -> book.getDateDelivery().plusMonths(6).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        books.sort((o1, o2) -> o1.getDateDelivery().compareTo(o2.getDateDelivery()));
        log.info("method sortByDateDeliveryOfStaleBooksNotSold completed");
        return books;
    }

    @Override
    public void sortBookByDateDelivery(List<Book> bookList) {
        log.info("calling a method sortBookByDateDelivery");
        bookList.sort(Comparator.comparing(Book::getDateDelivery));
        for (Book book : bookList) {
            System.out.println(book);
        }
        log.info("method sortBookByDateDelivery completed");
    }

    @Override
    public void getDescriptionBook(long id) {
        log.info("calling a method getDescriptionBook");
        Book book = bookRepository.getBookById(id);
        System.out.println("Автор книги - " + book.getAuthorBook() + ". Наименование книги - " + book.getNameBook() +
                ". Цена книги - " + book.getPrice() + ". Дата издание книги - " + book.getYearOfPublic());
        log.info("method getDescriptionBook completed");
    }

    @Override
    /**
     * создает csv файл и заполняет его книгами из хранилища
     *
     * @param nameFile
     * @throws IOException
     */
    public void writeFileCsvBook() {
        log.info("calling a method writeFileCsvBook");
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
                String[] csvNewFile = new String[7];
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
            log.error("Error message ", e);
        }
        log.info("method writeFileCsvBook completed");
    }

    @Override
    public void addNewBookToRepository(Book book) {
        log.info("calling a method addNewBookToRepository");
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
            log.error("Error message ", e);
        }
        log.info("method addNewBookToRepository completed");
    }

    @Override
    public void readCsvBook() {
        log.info("calling a method readCsvBook");
        BookRepository bookRepository = BookRepositoryImpl.getBookRepository();
        List<Book> books = bookRepository.getBooks();
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
                        log.error("Error message", e);
                    }
                }
            } catch (IOException e) {
                log.error("Error message", e);
            }
        }
        log.info("method readCsvBook completed");
    }


}
