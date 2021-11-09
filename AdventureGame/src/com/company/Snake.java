package com.company;

import java.util.Random;

public class Snake extends  Monster{
    public Snake() {
        super(4,"Yılan",12,0,snakeDamage());
    }

    public static int snakeDamage(){
        Random randomSnakeDamage = new Random();
        return randomSnakeDamage.nextInt(3)+3;
    }
}
