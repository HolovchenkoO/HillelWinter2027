public class Car {
public void start() {
    startElectricity();
    startCommand();
    startFuelSystem();
    System.out.println("Машина почала рух!");
}
    private void startElectricity() {
    System.out.println("Машина увімкнена.");
}

    private void startCommand() {
        System.out.println("Команда 'старт' виконана.");
    }

    private void startFuelSystem() {
        System.out.println("Паливна система запущена.");
    }

}
