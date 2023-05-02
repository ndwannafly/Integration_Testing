package taskFunction;

import com.opencsv.CSVWriter;
import logarithmic.CommonLogarithm;
import logarithmic.NaturalLogarithm;
import trigonometric.Cos;
import trigonometric.Sec;
import trigonometric.Sin;
import trigonometric.Tan;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
public class TaskFunction {
    CommonLogarithm log;
    NaturalLogarithm ln;
    Sec sec;
    Cos cos;
    Sin sin;
    Tan tan;
    public TaskFunction() {
        sec = new Sec();
        cos = new Cos();
        sin = new Sin();
        tan = new Tan();
        log = new CommonLogarithm();
        ln = new NaturalLogarithm();
    }

    public TaskFunction(Sec sec, Cos cos, Sin sin, Tan tan, CommonLogarithm log, NaturalLogarithm ln){
        this.sec = sec;
        this.cos = cos;
        this.sin = sin;
        this.tan = tan;
        this.log = log;
        this.ln = ln;
    }
//    x <= 0 : (((((cos(x) * sec(x)) + sin(x)) * cos(x)) / tan(x)) ^ 3)
//    x > 0 : (((((ln(x) + log_2(x)) ^ 3) ^ 2) / (((log_3(x) ^ 2) / (log_10(x) + log_3(x))) ^ 3)) + log_2(x))
    public double calculate(double x, double eps){
        double res;
        if (x < 0  || x < eps ){
            System.out.println("tan: " + tan.calculate(x, eps));
            res = Math.pow(((((cos.calculate(x, eps) * sec.calculate(x, eps)) + sin.calculate(x, eps)) * cos.calculate(x, eps)) / tan.calculate(x, eps)),3);
        } else {
            res = Math.pow((Math.pow((ln.ln(x, eps) + log.log(2, x, eps)), 3)), 2)
                    / Math.pow((log.log(3, Math.pow(x, 2), eps) / (log.log(10, x, eps) + log.log(3, x, eps))), 3)
                    + log.log(2, x, eps);
        }
        return res;
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Lab2");

        TaskFunction function = new TaskFunction();

        double res = function.calculate(0.523599, 0.0001);
        System.out.println(res);

        // function
        // csv graph data
        Writer out = new FileWriter("src/main/resources/Outputs/FunctionOut.csv");
        CSVWriter writer = new CSVWriter(out, ',', CSVWriter.NO_QUOTE_CHARACTER);
        double leftX, rightX, step;
        leftX = -5;
        rightX = 5;
        step = 0.001;
        while (leftX < rightX) {
            leftX += step;
            double cal = function.calculate(leftX, 0.0001);
            if (Math.abs(cal) < 500) {
                String[] data1 = {String.valueOf(leftX), String.valueOf(cal)};
                writer.writeNext(data1);
            }
        }
    }
}
