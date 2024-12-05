package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;

public class HeartTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double sqrt = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double newX = sqrt * Math.sin(sqrt * Math.atan(point.y() / point.x()));
        double newY = -sqrt * Math.cos(sqrt * Math.atan(point.y() / point.x()));
        return new Point(newX, newY);
    }
}
