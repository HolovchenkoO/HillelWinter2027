package coffee.order;

import java.util.LinkedList;
import java.util.List;

public class CoffeeOrderBoard {
    private List<Order> orders = new LinkedList<>();
    private int lastOrderNumber = 0;

    public void add(Order order){
        lastOrderNumber++;
        order.setNum(lastOrderNumber);
        orders.add(order);
    }
public Order deliver() {
        if(!orders.isEmpty()){
            return orders.remove(0);
        }
        else System.out.println("Черга порожня, замовлення відсутні.");
        return null;
}
    public Order deliver(int orderNum){
        for(int i =0; i <= orders.size(); i++) {
            if (orders.get(i).getNum() == orderNum) {
                return orders.remove(i);
            }
        }
        System.out.println("Замовлення з номером: " + orderNum + " не знайдено!");
        return null;
    }

    public void draw(){
        System.out.println("=============");
        System.out.println("Num | Name");
        for(Order order : orders){
            System.out.println(order.toString());
        }
    }
}
