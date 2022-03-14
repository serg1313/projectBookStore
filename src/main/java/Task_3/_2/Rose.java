package Task_3._2;

public class Rose extends Flower {

    public Rose(Color color, int price) {
        super("Роза", color, price);
    }

    @Override
    public String toString() {
        return "Rose{" +
                "name='" + getName() + '\'' +
                '}';
    }
}
