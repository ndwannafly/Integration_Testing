package trigonometric;

public class Tan implements Trigonometry{
    Sin sin = new Sin();
    Cos cos = new Cos();

    public double calculate(double x, double eps) {
        System.out.println("here calculating...");
        double result;
        double cosValue = cos.calculate(x, eps);
        System.out.println("cosValue: " + cosValue);
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY || cosValue == 0){
            return Double.NaN;
        }
        double sinValue = sin.calculate(x, eps);
        System.out.println("cosValue: " + cosValue + " sinValue: " + sinValue);
        result = sinValue/cosValue;
        System.out.println("result: " + result);
        return result;
    }
}
