package moonz.study.designpatterns.creation.factorymethodpattern.good;

public class BlackShipFactory implements ShipFactory {
    @Override
    public Ship createShip(String name) {
        return new BlackShip(name);
    }
}
