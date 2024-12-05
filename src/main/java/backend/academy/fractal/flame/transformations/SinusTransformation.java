package backend.academy.fractal.flame.transformations;

import backend.academy.fractal.flame.model.Point;

public class SinusTransformation implements Transformation{

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.x()), Math.sin(point.y()));
    }
}
