/**package myBookStore.
 * @author Sergey Kostenko
 */
package mybookstore.model;

/**Абстрактный класс базовой сущности.
 *
 */
public abstract class BaseEntity {
    /**
     * поле id.
     */
    private long id;

    /**
     * Конструктор - создание нового объекта.
     * @param id1
     */
    public BaseEntity(final long id1) {
        this.id = id1;
    }

    /**
     * метод получения значения поля.
     * @return значение поля id
     */
    public long getId() {
        return id;
    }

    /**
     * метод установки id.
     * @param id1
     */
    public void setId(final long id1) {
        this.id = id1;
    }
}


