package backend.academy.fractal.flame.model;

public record Rect(double x, double y, double width, double height) {
    public boolean contains(Point point) {
        return point.x() >= x && point.x() <= width && point.y() >= y && point.y() <= height;
    }
}
