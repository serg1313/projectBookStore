package Task_3._2;

public class testMain {
    public static void main(String[] args) {
        Bouquet b1 = new Bouquet(new Tulip(Color.GREEN, 23),
                new Violet(Color.YELLOW, 39),
                new Tulip(Color.YELLOW, 33));

        System.out.println(b1);
        Rose r = new Rose(Color.YELLOW, 500);
        System.out.println(r);

        System.out.println("цена букета равна " + b1.getPrice());
    }
}
