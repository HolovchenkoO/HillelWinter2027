//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    Cat catMurzik = new Cat("Murzik");
    Cat catBarsik = new Cat("Barsik");
    Cat catPusha = new Cat("Pusha");

    Dog dogZhuzha = new Dog("Zhuzha");
    Dog dogDozor = new Dog("Dozor");

    //Перевірка бігу
        catMurzik.run(120);
        catBarsik.run(-10);
        catPusha.run(800);

        dogZhuzha.run(490);
        dogDozor.run(900);

    //Перевірка плавання
        catBarsik.swim(20);

        dogZhuzha.swim(50);
        dogDozor.swim(10);

        //Підрахунок кількості
        System.out.println(Cat.getCatCount() + " - кількість створених котів");
        System.out.println(Dog.getDogCount() + " - кількість створених собак");
        System.out.println(Animal.getAnimalCount() + " - кількість всіх створених тварин");


    }
}