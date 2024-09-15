package moonz.study.designpatterns.creation.factorymethodpattern;

import moonz.study.designpatterns.creation.factorymethodpattern.good.BlackShip;
import moonz.study.designpatterns.creation.factorymethodpattern.good.Ship;
import moonz.study.designpatterns.creation.factorymethodpattern.good.WhiteShip;

/**
 * 단순한 팩토리 패턴. (Simple Factory Pattern)
 * 하나의 ConcreteCreator에서 다양한 ConcreteProduct (Product의 구현체)들을 반환한다.
 */
public class SimpleFactory {

    /**
     * 구현체 Product를 하나의 팩토리 메서드에서 처리한다.
     * @param name 구현체 반환 기준
     * @return Ship 구현체 (ConcreteProduct)
     */
    public Ship createProduct(String name) {
        if (name.equals("white")) {
            return new WhiteShip(name);
        } else if (name.equals("black")) {
            return new BlackShip(name);
        }
        throw new IllegalArgumentException();
    }
}
