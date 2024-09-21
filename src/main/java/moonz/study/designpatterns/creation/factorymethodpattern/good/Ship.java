package moonz.study.designpatterns.creation.factorymethodpattern.good;

import moonz.study.designpatterns.creation.abstractfactorypattern.bad.WhiteAnchor;
import moonz.study.designpatterns.creation.abstractfactorypattern.bad.WhiteWheel;

public abstract class Ship {
    protected String name;
    protected String logo;
    protected ShipColorType color;
    abstract String getName();
    abstract String getLogo();
    abstract ShipColorType getColor();

    // bad : 구현체에 의존
    public void setAnchor(WhiteAnchor whiteAnchor) {

    }
    // bad : 구현체에 의존
    public void setWheel(WhiteWheel whiteWheel) {

    }

}
