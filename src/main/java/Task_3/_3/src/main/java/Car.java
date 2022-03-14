package Task_3;

public class Car implements IProduct {
    private IProductPart chassisPart;
    private IProductPart enginePart;
    private IProductPart bodyPart;

    public Car() {
        System.out.println("Создается автомобиль");
    }

    @Override
    public void installFirstPart(IProductPart productPart) {
        this.chassisPart = productPart;
        System.out.println("Автомобилю установлено шасси");
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        this.bodyPart = productPart;
        System.out.println("Автомобилю установлен кузов");
    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        this.enginePart = productPart;
        System.out.println("Автомобилю установлен двигатель");
    }
}
