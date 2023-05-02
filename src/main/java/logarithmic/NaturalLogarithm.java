package logarithmic;

public class NaturalLogarithm {

    public double ln(double x, double eps) {
        if (Double.isNaN(x) || x < (double) 0) {
            return Double.NaN;
        } else if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        } else if (x == 0.0) {
            return Double.NEGATIVE_INFINITY;
        }
        double res = 0;
        int step = 1;
        double addition = (x - 1) / (x + 1);
        double c = Math.pow(x - 1, 2) / Math.pow(x + 1, 2);
        while (Math.abs(addition) > eps / 2) {
            res += addition;
            addition = (2 * step - 1) * addition * c / (2 * step + 1);
            step++;
        }
        res *= 2;
        return res;
    }
}
