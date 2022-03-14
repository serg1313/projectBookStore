package Task_3._4.service;

import Task_3._4.model.Book;
import Task_3._4.repository.BookRepository;

import java.util.Collections;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private RequestService requestService;

    public BookServiceImpl(BookRepository bookRepository, RequestService requestService) {
        this.bookRepository = bookRepository;
        this.requestService = requestService;
    }

    /**
     * списать книгу сос клада и перевести в статус отсутствует
     *
     * @param id
     */
    @Override
    public void writeOffBook(long id) {
        Book book = bookRepository.getBookById(id);
        if (book.getStatusBook()) {
            book.setStatusBook(false);
            System.out.println("Книга списана");
        } else {
            System.out.println(" Данная книга отсутствует");
        }
    }

    /**
     * добавить книгу на склад
     *
     * @param id
     */
    @Override
    public void addBook(long id) {
        Book book = bookRepository.getBookById(id);
        if (bookRepository.getBookById(id) == null) {
            System.out.println("книга не найдена");
        } else {
            requestService.canceledRequestByBookId(id);
            bookRepository.addBook(book);
            System.out.println("книга " + book.getNameBook() + " добавлена");
        }
    }

    /**
     * узнать наличие книги на складе
     *
     * @param idBook id книги
     * @return true - книга есть на складе, false - книги нет
     */
    @Override
    public boolean getStatusBook(long idBook) {
        for (Book b : bookRepository.getBooks()) {
            if (b.getId() == idBook) {
                if (b.getStatusBook()) {
                    System.out.println("книга " + b.getNameBook() + " имеется на складе ");
                    break;
                } else {
                    System.out.println("книга " + b.getNameBook() + " отсутствует на складе ");
                    break;
                }
            }
        }
        return false;
    }

    /**
     * сортировка книг по названию
     *
     * @param bookList
     */
    @Override
    public void sortingBookByName(List<Book> bookList) {
        Collections.sort(bookList);
        for (Book book : bookList)
            System.out.println(book);
    }

    /**
     * сортировка книг по дате издания
     *
     * @param bookList
     */
    @Override
    public void sortingBookByYearOfPublic(List<Book> bookList) {
        bookList.sort((o1, o2) -> o1.getYearOfPublic() - o2.getYearOfPublic());
        for (Book book : bookList)
            System.out.println(book);
    }

    /**
     * Сортировка книг по стоимости
     *
     * @param bookList
     */
    @Override
    public void sortingBookByPrice(List<Book> bookList) {
        bookList.sort((Book o1, Book o2) -> (int) (o1.getPrice() - o2.getPrice()));
        for (Book book : bookList)
            System.out.println(book);
    }

    /**
     * сортировка книг по наличию на складе
     *
     * @param status
     */
    @Override
    public void sortBooksByStockAvailability(boolean status) {
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
    }

    /**
     * Возвращает книгу по ID
     *
     * @param b
     * @return
     */
    @Override
    public Book getBookById(long b) {
        Book current = null;
        for (Book book : bookRepository.getBooks()) {
            if (book.getId() == b) {
                current = book;
                break;
            }
        }
        return current;
    }

    @Override
    public void addNewBookInRepository(Book book) {
        Book b = book;
        for (Book book1 : bookRepository.getBooks()) {
            if (book1.getNameBook() != b.getNameBook() && book1.getAuthorBook() != b.getAuthorBook() && book1.getYearOfPublic() != b.getYearOfPublic() && b.getStatusBook() == false) {
                bookRepository.addBook(b);
            }
        }
    }
}
