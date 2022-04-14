package mybookstore.repository;

import mybookstore.model.Request;

import java.util.List;


public interface RequestRepository {

    List<Request> getRequest();
}
