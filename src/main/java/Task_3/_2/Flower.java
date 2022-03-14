package Task_3._2;

public abstract class Flower {
    private String name;
    private Color color;
    private int price;

    public Flower(String name, Color color, int price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Букет состоит из - " +
                name + '\'' +
                ", цвет " + color +
                ",цена цветка " + price;
    }
}
