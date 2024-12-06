package backend.academy.fractal.flame.model;

public record Rgb(int red, int green, int blue) {

    public Rgb sum(Rgb newRgb) {
        int red = this.red + newRgb.red();
        int green = this.green + newRgb.green();
        int blue = this.blue + newRgb.blue();
        return new Rgb(red, green, blue);
    }

    public Rgb divideByTwo() {
        return new Rgb(
            this.red / 2,
            this.green / 2,
            this.blue / 2);
    }
}
