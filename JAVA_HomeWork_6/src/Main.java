import hw6.*;

public class Main {
    public static void main(String[] args) {
        // Геометричні фігури
        GeometricFigure[] geometricFigures = {
                new Circle(5.2),
                new Triangle(8, 10),
                new Square(7),
                new Square(10)
        };

        System.out.println("Площі геометричних фігур:");

        for (GeometricFigure figure : geometricFigures) {
            System.out.printf("Площа фігури - %.2f%n", figure.getArea());
        }

        System.out.printf("Загальна площа геометричних фігур: %.2f%n",
                calculateTotalArea(geometricFigures));
        //Учасники та перешкоди

        Member [] members = {
                new Human("Олексій", 1000, 2.0),
                new Cat("Барсік", 50, 2.5),
                new Robot("Вертер", 200, 1.0),
                new Human("Микола", 800, 1.8),
                new Cat("Пуша", 42, 2.2),
                new Robot("Дрон", 500, 3.0)
        };
        Obstacle[] obstacles = {
                new Racetrack(40),
                new Wall(1.5),
                new Racetrack(100),
                new Wall(2.2)
        };
        System.out.println("\n -- Змагання учасників: ");

        for (Member Member : members) {

            System.out.println("\nУчасник: " + Member.getName());

            boolean active = true;

            for (Obstacle obstacle : obstacles) {

                if (!active) {
                    break;
                }

                active = obstacle.overcome(Member);
            }

            if (active) {
                System.out.println(
                        Member.getName() + " пройшов усі перешкоди!"
                );
            } else {
                System.out.println(
                        Member.getName() + " вибув зі змагання."
                );
            }
        }
    }

   // Метод обчислення загальної площі всіх фігур
    public static double calculateTotalArea(GeometricFigure[] geometricFigures) {
        double totalArea = 0;

        for (GeometricFigure figure : geometricFigures) {
            totalArea += figure.getArea();
        }

        return totalArea;
    }
}