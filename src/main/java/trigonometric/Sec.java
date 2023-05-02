package trigonometric;

public class Sec implements Trigonometry{
    private Cos cos;

    public Sec(Cos cos) {
        this.cos = cos;
        System.out.println("duc " + cos.calculate(0, 0.00000001));
    }

    public Sec() {
        this.cos = new Cos();
    }

    public double calculate(double x, double eps) {
        Cos cos = new Cos();
        double result;
        System.out.println("calculating...");
        double cosValue = cos.calculate(x, eps);
        System.out.println("x: " + x + " cosValue: " + cosValue);
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || cosValue == 0){
            return Double.NaN;
        }
        result = 1 / cosValue;
        return result;
    }

}
