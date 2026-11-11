package hw6;

public class Racetrack implements Obstacle{
    private final double length;

    public Racetrack(double length) {
        this.length = length;
    }

    @Override
    public boolean overcome(Member member) {
        boolean success = member.run(length);
        if(success) {
            System.out.println("Учасник " + member.getName() + " пройшов перешкоду " +
                    "Бігова Доріжка на дистанцїї " + length + " м.");
        }
        else {
            System.out.println("Учасник " + member.getName() + " не пройшов перешкоду " +
                    "Бігова Доріжка на дистанцїї " + length + " м." +
            "Пройдено "+ member.getMaxRunDistance() + " м.");
        }
        return success;
    }
}
