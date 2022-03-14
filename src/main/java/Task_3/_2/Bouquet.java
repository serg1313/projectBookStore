package Task_3._2;

import java.util.Arrays;

public class Bouquet {
    private int price;
    private Flower[] flowers;

    public Bouquet(Flower... flowers) {
        this.flowers = flowers;
        priceBouquet();
    }

    @Override
    public String toString() {
        return "Bouquet{" +
                "price=" + price +
                ", flowers=" + Arrays.toString(flowers) +
                '}';
    }

    private void priceBouquet() {
        for (Flower f : flowers) {
            price += f.getPrice();
        }
    }

    public int getPrice() {
        return price;
    }
}


