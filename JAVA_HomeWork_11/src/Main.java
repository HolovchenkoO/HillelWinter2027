import coffee.order.CoffeeOrderBoard;
import coffee.order.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CoffeeOrderBoard board = new CoffeeOrderBoard();
        // Додаємо замовлення
        board.add(new Order("Alen"));
        board.add(new Order("Yoda"));
        board.add(new Order("Obi-van"));
        board.add(new Order("John Snow"));
        board.add(new Order("Luke Skywalker"));

        System.out.println("Початкова черга:");
        board.draw();

        // Видаємо перше замовлення у черзі (Alen)
        System.out.println("\nВидаємо поточне замовлення: " + board.deliver().getName());

        // Видаємо замовлення поза чергою (John Snow, номер 4)
        System.out.println("Видаємо замовлення №4: " + board.deliver(4).getName());

        System.out.println("\nЧерга після двох видач:");
        board.draw();

        // Додаємо нове замовлення
        board.add(new Order("Dart Vader"));
        System.out.println("\nДодали нове замовлення:");
        board.draw();

    }
}