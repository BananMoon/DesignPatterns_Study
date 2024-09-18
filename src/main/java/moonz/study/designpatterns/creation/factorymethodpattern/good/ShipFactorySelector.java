package moonz.study.designpatterns.creation.factorymethodpattern.good;

public class ShipFactorySelector {

    public ShipFactory pickShipFactory(ShipColorType shipType) {
        switch (shipType) {
            case WHITE:
                return new WhiteShipFactory();
            case BLACK:
            return new BlackShipFactory();
            default:
                throw new IllegalArgumentException();
        }
    }
}
