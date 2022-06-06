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
     * @param id объекта
     */
    public BaseEntity(final long id) {
        this.id = id;
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
     * @param id объекта
     */
    public void setId(final long id) {
        this.id = id;
    }
}


