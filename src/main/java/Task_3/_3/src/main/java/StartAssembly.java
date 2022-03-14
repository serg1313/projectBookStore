package Task_3;

public class StartAssembly {
    public static void main(String[] args) {
        CarAssemblyLine car = new CarAssemblyLine(new ChassisLineStep(), new EngineLineStep(), new BodyLineStep());
        car.assembleProduct(new Car());
        System.out.println("автомобиль собран");

    }
}
