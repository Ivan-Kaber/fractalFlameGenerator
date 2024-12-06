package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;

public class SphereTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = point.x() / (Math.pow(point.x(), 2) + (Math.pow(point.y(), 2)));
        double newY = point.y() / (Math.pow(point.x(), 2) + (Math.pow(point.y(), 2)));
        return new Point(newX, newY);
    }
}
