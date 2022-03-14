package Task_4.service;



import Task_4.model.Book;
import Task_4.repository.BookRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        } else if (bookRepository.getBookById(id).getStatusBook() != true) {
            System.out.println("книга на складе имеется");
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
    public int getCountBookByRepository() {
        int count = 0;
        for (Book book : bookRepository.getBooks()) {
            if (book.getStatusBook() == true) {
                count++;
            }
        }
        return count;
    }

    @Override
    public void sortingBookById(List<Book> bookList) {
        bookList.sort((o1, o2) -> (int) (o1.getId()-o2.getId()));
        for (Book book : bookList) {
            System.out.println(book);
        }
    }

    @Override
    public List<Book> sortByPriceListOfStaleBooksNotSold(List<Book> bookList) {
        bookList=bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook()==true)
                .filter(book -> book.getDateDelivery().plusMonths(6).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        bookList.sort((o1, o2) -> (int) (o1.getPrice()-o2.getPrice()));
        return bookList;
    }

    @Override
    public List<Book> sortByNameListOfStaleBooksNotSold(List<Book> bookList, long periodOfMonths) {
        bookList = bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook()==true)
                .filter(book -> book.getDateDelivery().plusMonths(periodOfMonths).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
            bookList.sort((o1, o2) -> o1.getNameBook().compareTo(o2.getNameBook()));
        return bookList;
    }

    @Override
    public List<Book> sortByDateDeliveryOfStaleBooksNotSold(List<Book> books) {
        books=bookRepository.getBooks().stream()
                .filter(book -> book.getStatusBook()==true)
                .filter(book -> book.getDateDelivery().plusMonths(6).isBefore(LocalDate.now()))
                .collect(Collectors.toList());
        books.sort((o1, o2) -> o1.getDateDelivery().compareTo(o2.getDateDelivery()));
        return books;
    }

    @Override
    public void sortBookByDateDelivery(List<Book> bookList) {
        bookList.sort(Comparator.comparing(Book::getDateDelivery));
        for (Book book:bookList){
            System.out.println(book);
        }
    }

    @Override
    public void getDescriptionBook(long id){
        Book book = bookRepository.getBookById(id);
        System.out.println("Автор книги - "+book.getAuthorBook()+ ". Наименование книги - "+book.getNameBook()+
                ". Цена книги - "+ book.getPrice()+". Дата издание книги - "+book.getYearOfPublic());

    }

}
