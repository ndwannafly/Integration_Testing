package trigonometric;

public class Cos implements Trigonometry{
    private final Sin sin;

    public Cos(Sin sin) {
        this.sin = sin;
    }

    public Cos() {
        this.sin = new Sin();
    }


    public double calculate(double x, double eps) {
        System.out.println("cos calculate x: " + x);
        double x_init = x;
        x %= Math.PI * 2;
        //Sin sin = new Sin();
        double result = 0;
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY){
            return Double.NaN;
        }
        if (x >= 0) {
            while (x > Math.PI) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < -Math.PI) {
                x += Math.PI * 2;
            }
        }
        //correcting sign
        if (x > Math.PI / 2 || x < -Math.PI / 2) {
            result = -1 * Math.sqrt(1 - sin.calculate(x_init, eps) * sin.calculate(x_init, eps));
        } else {
            result = Math.sqrt(1 - sin.calculate(x_init, eps) * sin.calculate(x_init, eps));
        }
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) <= eps) return 0;
        return result;
    }
}
