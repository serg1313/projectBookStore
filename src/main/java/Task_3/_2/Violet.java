package Task_3._2;

public class Violet extends Flower {

    public Violet(Color color, int price) {
        super("Фиалка", color, price);
    }

    @Override
    public String toString() {
        return "Violet{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
