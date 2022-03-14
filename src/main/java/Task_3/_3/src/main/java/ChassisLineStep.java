package Task_3;

public class ChassisLineStep implements ILineStep {
    @Override
    public IProductPart buildProductPart() {
        return new ChassisPart();
    }
}
