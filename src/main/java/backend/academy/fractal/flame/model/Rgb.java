package backend.academy.fractal.flame.model;

public record Rgb(int red, int green, int blue) {

    public Rgb sum(Rgb newRgb) {
        int newRed = this.red + newRgb.red();
        int newGreen = this.green + newRgb.green();
        int newBlue = this.blue + newRgb.blue();
        return new Rgb(newRed, newGreen, newBlue);
    }

    public Rgb divideByTwo() {
        return new Rgb(
            this.red / 2,
            this.green / 2,
            this.blue / 2);
    }
}
