package moonz.study.designpatterns.creation.factorymethodpattern.good;

public class BlackShip extends Ship{

    public BlackShip(String name) {
        this.name = name;
        this.color = ShipColorType.BLACK;
        this.logo = "\uD83D\uDEE5️";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getLogo() {
        return this.logo;
    }

    @Override
    public ShipColorType getColor() {
        return this.color;
    }

    public String toString() {
        return String.format("name: %s, color: %s, logo: %s", this.name, this.color.getName(), this.logo);
    }
}
