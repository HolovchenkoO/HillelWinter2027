public class Dog extends Animal {


    private static int dogCount = 0;

    private static final int maxRun = 500;
    private static final int maxSwim = 10;

    public Dog(String animalName) {
        super(animalName);
        dogCount++;
    }

    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println(animalName + ": відстань не може бути від'ємною!");
        } else if (distance <= maxRun) {
            System.out.println(animalName + " пробіг " + distance + " м.");
        } else {
            System.out.println(animalName + " не може пробігти " + distance
                    + " м. Максимум: " + maxRun + " м.");
        }
    }

    @Override
    public void swim(int distance) {
        if (distance < 0) {
            System.out.println(animalName + ": відстань не може бути від'ємною!");
        } else if (distance <= maxSwim) {
            System.out.println(animalName + " проплив " + distance + " м.");
        } else {
            System.out.println(animalName + " не може проплисти " + distance
                    + " м. Максимум: " + maxSwim + " м.");
        }
    }

    public static int getDogCount() {
        return dogCount;
    }
}