import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello World!");

        Triangle triangle = new Triangle(1,2);
        Cercle cercle = new Cercle(1);
        triangle.area();
        cercle.area();

        System.out.println("Area triangle = " + triangle.area());
        System.out.println("Area cercle = " + cercle.area());

        Figura[] fig = new Figura[2];
        fig[0] = triangle;
        fig[1] = cercle;

        TestFigures test = new TestFigures();
        test.sum(fig);

        System.out.println("Suma = " + test.sum(fig));

        System.out.println("Compareto = " + triangle.compareTo(triangle));
    }
}