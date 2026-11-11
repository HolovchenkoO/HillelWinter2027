package coffee.order;

public class Order {
    private int num;
    private String name;

    public Order(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return  num + " | " + name;
    }
}
