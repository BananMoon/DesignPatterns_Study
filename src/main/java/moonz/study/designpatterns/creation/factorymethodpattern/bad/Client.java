package moonz.study.designpatterns.creation.factorymethodpattern.bad;

public class Client {

    public static void main(String[] args) {
        Ship whiteship = ShipFactory.orderShip("whiteship", "moon@a.com");
        System.out.println(whiteship);

        Ship blackship = ShipFactory.orderShip("blackship", "moon2@a.com");
        System.out.println(blackship);
    }

}
