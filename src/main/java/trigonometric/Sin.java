package trigonometric;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Sin implements Trigonometry{
    public double calculate(double x, double eps){
        if(x == Double.POSITIVE_INFINITY || x == Double.NEGATIVE_INFINITY){
            return Double.NaN;
        }
        double fact = 1, result_l = 1, result = 0;
        int sign = 1, i = 1;

        if (x >= 0) {
            while (x > Math.PI * 2) {
                x -= Math.PI * 2;
            }
        } else if (x < 0) {
            while (x < Math.PI * 2) {
                x += Math.PI * 2;
            }
        }

        while (Math.abs(result_l - result) > eps) {
            fact = fact / i;
            result_l = result;
            result += sign * Math.pow(x, i) * fact;
            sign = -sign;
            fact = fact / (i + 1);
            i += 2;
        }
        if (Math.abs(result) > 1) return Double.NaN;
        if (Math.abs(result) < eps) return 0;
        return result;
    }

    public void getPoints(double leftX, double rightX, double step) throws IOException {
        Writer out = new FileWriter("src/main/resources/Outputs/SinOut.csv");
        CSVWriter writer = new CSVWriter(out, ',', CSVWriter.NO_QUOTE_CHARACTER);
        while (leftX <  rightX){
            leftX += step;
            String[] data1 = {String.valueOf(leftX), String.valueOf(calculate(leftX, step))};
            writer.writeNext(data1);
        }
    }
}
