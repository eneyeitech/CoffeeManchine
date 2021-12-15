package com.eneyeitech;

public class Test {

    enum Secret{
        USD,
        EUR,
        GBP,
        RUB,
        STAR,
        KZT,
        CAD,
        START,
        STARTING
    }

    public static void main(String args[]) {
        int i = 0;
        for(Secret secret: Secret.values()){
            if(secret.toString().startsWith("STAR")){
                i++;
            }
        }
        System.out.println(i);

    }

}
