package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;

public class DiskTransformation implements Transformation {

    @Override
    public Point apply(Point point) {
        double piSqrt = Math.PI * Math.sqrt(Math.pow(point.x(), 2) + Math.pow(point.y(), 2));
        double piAtan = (1 / Math.PI) * Math.atan(point.y() / point.x());
        double newX = piAtan * Math.sin(piSqrt);
        double newY = piAtan * Math.cos(piSqrt);
        return new Point(newX, newY);
    }
}
