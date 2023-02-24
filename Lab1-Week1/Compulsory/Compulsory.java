public class Compulsory {
    private String [] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

    public int generateRandomNumber(){
        return (int)(Math.random()*1_000_000);
    }

    public int changeTheResult( int n){
        n*=3;
        n = n + 0b10101;
        n = n + 0XFF;
        n=n*6;

        return n;
    }
    public int cifraDeControl(int n){
        /**if (n%9 == 0 ){
         return 9 ;
         }
         else{
         return n%9;
         }
         //cifra de control
         */

        int cControl = n;
        while ( cControl >=10 ){
            int aux = cControl;
            cControl = 0;
            while (aux != 0){
                cControl += aux%10;
                aux/=10;
            }
        }
        return cControl;
    }
    public String showTheMagicMessage(int n){
        return "Willy-nilly, this semester I will learn " + languages[n];
    }

}
