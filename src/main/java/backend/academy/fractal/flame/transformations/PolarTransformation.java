package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;

public class PolarTransformation implements Transformation {
    @Override
    public Point apply(Point point) {
        double newX = Math.atan(point.y() / point.x()) / Math.PI;
        double newY = Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2)) - 1;
        return new Point(newX, newY);
    }
}
