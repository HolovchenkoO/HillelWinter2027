package hw6;

public class Wall implements Obstacle{
    private final double height;

    public Wall(double height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Member member) {
        boolean success = member.jump(height);
        if(success) {
            System.out.println("Учасник " + member.getName() + " пройшов перешкоду " +
                    "Стіна на висоті " + height + " м.");
        }
        else {
            System.out.println("Учасник " + member.getName() + " не пройшов перешкоду " +
                    "Стіна на висоті " + height + " м." +
                    "Подолено "+ member.getMaxJumpHeight() + " м.");
        }
        return success;
    }
}
