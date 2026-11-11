package hw6;

public class Human implements Member{

    private final String name;
    private final double maxRunDistance;
    private final double maxJumpHeight;

    public Human(String name, double maxRunDistance, double maxJumpHeight) {
        this.name = name;
        this.maxRunDistance = maxRunDistance;
        this.maxJumpHeight = maxJumpHeight;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getMaxRunDistance() {
        return maxRunDistance;
    }

    @Override
    public double getMaxJumpHeight() {
        return maxJumpHeight;
    }

    @Override
    public boolean run(double distance) {
        System.out.println(name + " біжить " + distance + " м.");
        return distance <= maxRunDistance;
    }

    @Override
    public boolean jump(double height) {
        System.out.println(name + " стрибає на " + height + " м.");
        return height <= maxJumpHeight;
    }
}
