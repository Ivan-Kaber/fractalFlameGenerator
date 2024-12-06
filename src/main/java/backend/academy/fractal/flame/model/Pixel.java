package backend.academy.fractal.flame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pixel {
    private Point point;
    private Rgb rgb;
    private Integer hitCount;

    public Pixel(Point point) {
        this.point = point;
        this.rgb = new Rgb(0, 0, 0);
        this.hitCount = 0;
    }

    public void increaseHitCount() {
        hitCount++;
    }
}
