package moonz.study.designpatterns.creation.good;

public abstract class Ship {
    protected String name;
    protected String logo;
    protected ShipColorType color;
    abstract String getName();
    abstract String getLogo();
    abstract ShipColorType getColor();
}
