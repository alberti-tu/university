package eetac.dsa;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Track test = new Track();
        test.setId(1);
        test.setName("La Macarena");
        //test.setDescripcion("");
        test.insert();
    }
}
