package hw6;

public interface Member {
    String getName();

    double getMaxRunDistance();

    double getMaxJumpHeight();

    boolean run(double distance);

    boolean jump(double height);

}
