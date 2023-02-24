public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world! - This is Lab1 in Java \uD83D\uDE0D");


        Compulsory ex1 = new Compulsory();

        int result = ex1.generateRandomNumber();
        System.out.println(result);

        result = ex1.changeTheResult(result);
        System.out.println(result);


        result = ex1.cifraDeControl(result);

        System.out.println(ex1.showTheMagicMessage(result));//it will always show Java ;) as n is multiplied by 9 (*3 and * 6 (2*3),therefor n%9 == 0 )
    }

}