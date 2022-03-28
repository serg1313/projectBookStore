package my_Book_Store.myBookStore.model;

public class Request extends BaseEntity {
    private static long id = 1;

    private long idBook;
    private RequestStatus requestStatus;

    public Request(long idBook, RequestStatus requestStatus) {
        super(id++);
        this.idBook = idBook;
        this.requestStatus = requestStatus;
    }

    public long getIdBook() {
        return idBook;
    }

    public void setIdBook(long idBook) {
        this.idBook = idBook;
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
        return "Request{" +
                " id = " + getId() +
                " idBook = " + idBook +
                ", requestStatus = " + requestStatus +
                '}';
    }
}


