package org.example;

public class TypeGenerator {
    private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static int getInt(){

        return (int) (Math.random() * 100);

    }

    public static long getLong(){

        return (long) (Math.random() * 10_000);

    }

    public static float getFloat(){

        return (float) (Math.random() * 1_000);

    }

    public static double getDouble(){

        return Math.random() * 100;

    }

    public static String getString(){

        StringBuilder sb = new StringBuilder();
        int length = getInt();

        for( int i = 0; i < length; i++ ){
            int pozFromAlphabet = getInt() % 26;
            sb.append(alphabet.charAt(pozFromAlphabet));
        }

        return sb.toString();
    }

    public static boolean getBoolean(){

        return getInt() % 2 == 0;

    }

    public static char getChar(){

        return alphabet.charAt(getInt() % 26);

    }

}
