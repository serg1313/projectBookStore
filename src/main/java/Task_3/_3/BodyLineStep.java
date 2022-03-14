package Task_3._3;

public class BodyLineStep implements ILineStep {

    public IProductPart buildProductPart() {
        return new BodyPart();
    }
}
