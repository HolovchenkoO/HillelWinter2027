public abstract class Animal {

    private static int animalCount = 0;

    public String animalName;

    public Animal(String animalName) {
        this.animalName = animalName;
        animalCount++;
    }

    public abstract void run(int distance);

    public abstract void swim(int distance);

    public static int getAnimalCount() {
        return animalCount;
    }
}
