public class Cat extends Animal {

    private static int catCount = 0;

    private static final int maxRUN = 200;

    public Cat(String animalName) {
        super(animalName);
        catCount++;
    }

    @Override
    public void run(int distance) {
        if (distance < 0) {
            System.out.println(animalName + ": відстань не може бути від'ємною");
        } else if (distance <= maxRUN) {
            System.out.println(animalName + " пробіг " + distance + " м");
        } else {
            System.out.println(animalName + " не може пробігти " + distance
                    + " м. Максимум: " + maxRUN + " м");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(animalName + " не вміє плавати!");
    }

    public static int getCatCount() {
        return catCount;
    }
}
