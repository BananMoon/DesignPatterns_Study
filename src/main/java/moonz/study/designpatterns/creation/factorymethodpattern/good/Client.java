package moonz.study.designpatterns.creation.factorymethodpattern.good;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Client {

    public static void main(String[] args) {
        ShipFactorySelector shipFactorySelector = new ShipFactorySelector();
        Ship ship = shipFactorySelector.pickShipFactory(ShipColorType.WHITE).orderShip("My First Ship", "moon@a.com");
        Ship ship2 = shipFactorySelector.pickShipFactory(ShipColorType.BLACK).orderShip("My Second Ship", "moon2@a.com");

        log.info("ship = {}", ship.toString());
        log.info("ship2 = {}", ship2.toString());
    }

}
