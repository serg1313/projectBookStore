/**
 * общая сущность с id по моделям.
 */
package myBookStore.model;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BaseEntity {
    private static final Logger log = LogManager.getLogger(BaseEntity.class.getName());
    private long id;

    public BaseEntity(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

