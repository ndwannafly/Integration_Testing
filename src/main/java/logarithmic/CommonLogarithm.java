package logarithmic;


public class CommonLogarithm {
    private NaturalLogarithm ln;

    public CommonLogarithm(){
        ln = new NaturalLogarithm();
    }

    public CommonLogarithm(NaturalLogarithm ln) {
        this.ln = ln;
    }

    public double log(double a, double b, double esp) {
        return ln.ln(b, esp) / ln.ln(a, esp);
    }
}
