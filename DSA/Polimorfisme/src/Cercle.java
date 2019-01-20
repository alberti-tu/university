public class Cercle extends Figura
{
    private double r;

    public Cercle (double radi)
    {
        this.r = radi;
    }

    public double area()
    {
        double res;
        res = Math.PI*this.r*this.r;
        return res;
    }
}
