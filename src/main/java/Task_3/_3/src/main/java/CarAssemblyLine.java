package Task_3;

public class CarAssemblyLine implements IAssemblyLine {
    private ILineStep firstStep;
    private ILineStep secondStep;
    private ILineStep thirdStep;

    public CarAssemblyLine(ChassisLineStep firstStep, EngineLineStep secondStep, BodyLineStep thirdStep) {
        this.firstStep = firstStep;
        this.secondStep = secondStep;
        this.thirdStep = thirdStep;
    }

    @Override
    public IProduct assembleProduct(IProduct product) {
        IProductPart chassisPart = firstStep.buildProductPart();
        product.installFirstPart(chassisPart);

        IProductPart enginePart = secondStep.buildProductPart();
        product.installSecondPart(enginePart);

        IProductPart bodyCar = thirdStep.buildProductPart();
        product.installThirdPart(bodyCar);
        return product;
    }
}
