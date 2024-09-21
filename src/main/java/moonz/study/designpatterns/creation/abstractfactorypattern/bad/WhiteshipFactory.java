package moonz.study.designpatterns.creation.abstractfactorypattern.bad;

import moonz.study.designpatterns.creation.factorymethodpattern.good.Ship;
import moonz.study.designpatterns.creation.factorymethodpattern.good.ShipFactory;
import moonz.study.designpatterns.creation.factorymethodpattern.good.WhiteShip;

/**
 * Client code.
 * anchor, wheel 부품이 바뀔 때마다 코드 수정이 발생하는 문제 존재
 */
public class WhiteshipFactory implements ShipFactory {
    @Override
    public Ship createShip(String name) {
        Ship ship = new WhiteShip();
        ship.setAnchor(new WhiteAnchor());
        ship.setWheel(new WhiteWheel());
        return ship;
    }
}
