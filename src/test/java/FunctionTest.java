import logarithmic.CommonLogarithm;
import logarithmic.NaturalLogarithm;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;
import taskFunction.TaskFunction;
import trigonometric.Cos;
import trigonometric.Sec;
import trigonometric.Sin;
import trigonometric.Tan;
import utils.CsvLog;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FunctionTest {
    static Sec secMock;
    static Cos cosMock;
    static Sin sinMock;
    static Tan tanMock;
    static NaturalLogarithm lnMock;
    static CommonLogarithm logMock;

    static Reader cosIn;
    static Reader secIn;
    static Reader sinIn;
    static Reader tanIn;
    static Reader lnIn;
    static Reader log2In;
    static Reader log3In;
    static Reader log10In;
    static double eps = 0.000000000001;
    static double feps = 0.0001;

    private final CsvLog csvLog = new CsvLog();

    @BeforeAll
    public static void initTest() throws IOException {
        cosMock = Mockito.mock(Cos.class);
        secMock = Mockito.mock(Sec.class);
        sinMock = Mockito.mock(Sin.class);
        tanMock = Mockito.mock(Tan.class);
        logMock = Mockito.mock(CommonLogarithm.class);
        lnMock = Mockito.mock(NaturalLogarithm.class);
        try {
            cosIn = new FileReader("src/main/resources/Inputs/CosIn.csv");
            secIn = new FileReader("src/main/resources/Inputs/SecIn.csv");
            sinIn = new FileReader("src/main/resources/Inputs/SinIn.csv");
            tanIn = new FileReader("src/main/resources/Inputs/TanIn.csv");
            lnIn = new FileReader("src/main/resources/Inputs/LnIn.csv");
            log2In = new FileReader("src/main/resources/Inputs/Log2In.csv");
            log3In = new FileReader("src/main/resources/Inputs/Log3In.csv");
            log10In = new FileReader("src/main/resources/Inputs/Log10In.csv");

            Iterable<CSVRecord> records;
            records = CSVFormat.DEFAULT.parse(cosIn);
            for (CSVRecord record: records){
                Mockito.when(cosMock.calculate(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
                System.out.println("call " + Double.parseDouble(record.get(0)) + "return " + Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(secIn);
            for (CSVRecord record : records) {
                Mockito.when(secMock.calculate(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(sinIn);
            for (CSVRecord record : records) {
                Mockito.when(sinMock.calculate(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(tanIn);
            for (CSVRecord record : records) {
                Mockito.when(tanMock.calculate(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(lnIn);
            for (CSVRecord record : records) {
                Mockito.when(lnMock.ln(Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log2In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(2, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log3In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(3, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
            records = CSVFormat.DEFAULT.parse(log10In);
            for (CSVRecord record : records) {
                Mockito.when(logMock.log(10, Double.parseDouble(record.get(0)), eps)).thenReturn(Double.valueOf(record.get(1)));
            }
        } catch (IOException x) {
            System.out.println("IO ERROR!");
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/CosIn.csv")
    void testCos(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        Cos cos = new Cos();
        Assertions.assertEquals(expected, cos.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/SinIn.csv")
    void testSin(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        Sin sin = new Sin();
        Assertions.assertEquals(expected, sin.calculate(value, eps), feps);
        csvLog.setFilePath("src/main/resources/Outputs/SinOut.csv");
        csvLog.logger(expected,value, sin.calculate(value, eps));    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/SecIn.csv")
    void testSec(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        Sec sec = new Sec(cosMock);
        Assertions.assertEquals(expected, sec.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/TanIn.csv")
    void testTan(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        Tan tan = new Tan();
        Assertions.assertEquals(expected, tan.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/Log2In.csv")
    void testLog2(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        CommonLogarithm log = new CommonLogarithm();
        Assertions.assertEquals(expected, log.log(2, value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/Log3In.csv")
    void testLog3(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        CommonLogarithm log = new CommonLogarithm();
        Assertions.assertEquals(expected, log.log(3, value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/Log10In.csv")
    void testLog10(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        CommonLogarithm log = new CommonLogarithm();
        Assertions.assertEquals(expected, log.log(10, value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/LnIn.csv")
    void testLn(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        NaturalLogarithm ln = new NaturalLogarithm();
        Assertions.assertEquals(expected, ln.ln(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/FunctionIn.csv")
    void testFunctionOnSin(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        TaskFunction function = new TaskFunction(new Sec(new Cos(new Sin())), cosMock, sinMock, tanMock, logMock, lnMock);
        Assertions.assertEquals(expected, function.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/FunctionIn.csv")
    void testFunctionOnSec(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        TaskFunction function = new TaskFunction(new Sec(cosMock), cosMock, sinMock, tanMock, logMock, lnMock);
        Assertions.assertEquals(expected, function.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/FunctionIn.csv")
    void testFunctionWithMocks(double value, double expected){
        System.out.println("value: " + value + " expected: " + expected);
        TaskFunction function = new TaskFunction(secMock, cosMock, sinMock, tanMock, logMock, lnMock);
        Assertions.assertEquals(expected, function.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/FunctionIn.csv")
    void testFunctionOnCommonLog(double value, double expected) {
        TaskFunction function = new TaskFunction(secMock, cosMock, sinMock, tanMock, new CommonLogarithm(lnMock), lnMock);
        Assertions.assertEquals(expected, function.calculate(value, eps), feps);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/Inputs/FunctionIn.csv")
    void testFunctionOnNaturalLog(double value, double expected) {
        TaskFunction function = new TaskFunction(secMock, cosMock, sinMock, tanMock, logMock, new NaturalLogarithm());
        Assertions.assertEquals(expected, function.calculate(value, eps), feps);
    }


}
