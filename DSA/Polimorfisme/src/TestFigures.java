public class TestFigures
{
    public double sum (Figura[] f)
    {
        double res = 0;
        for (Figura fig: f)
        {
            res = res + fig.area();
        }
        return res;
    }
}
