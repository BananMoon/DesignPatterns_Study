package moonz.study.designpatterns.creation.factorymethodpattern.good;

public class WhiteShipFactory implements ShipFactory {
    @Override
    public Ship createShip(String name) {
        return new WhiteShip(name);
    }
}
