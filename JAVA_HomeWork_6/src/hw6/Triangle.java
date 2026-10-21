package hw6;

public class Triangle implements GeometricFigure{
   private final double base;
   private final double height;
    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }
    @Override
    public double getArea() {
        return base * height / 2;
    }
}
