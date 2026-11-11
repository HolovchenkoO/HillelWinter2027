//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //part1
        Employee emp = new Employee();
        Employee emp2 = new Employee("Max Gun", "CEO", "CEO@gmail.com", "050-125-67-89", 50);
        System.out.println(emp.toString());
        System.out.println(emp2.toString());

        //part2
        hw4_1.SameName firstSameName = new hw4_1.SameName();
        hw4_2.SameName secondSameName = new hw4_2.SameName();

        firstSameName.print();
        secondSameName.print();

        //part2
        Car car = new Car();
        car.start();
    }
   }
