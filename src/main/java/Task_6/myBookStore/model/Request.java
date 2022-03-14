package Task_6.myBookStore.model;
public class Request extends BaseEntity {
    private static long id = 1;
    private Book book;
    private RequestStatus requestStatus;

    public Request(Book book, RequestStatus requestStatus) {
        super(id++);
        this.book = book;
        this.requestStatus = requestStatus;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public static long getIdRequest() {
        return id;
    }

    @Override
    public String toString() {
        return "Request " + getId() + "{" +
                "book=" + book +
                ", requestStatus=" + requestStatus +
                '}';
    }
}


