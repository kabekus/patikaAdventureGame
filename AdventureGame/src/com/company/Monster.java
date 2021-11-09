package com.company;

import java.util.Random;

public class Monster {
    private int id,damage, health, orijinalHealth,award;
    private String name;

    public Monster(int id,String name,int health,int award,int damage){
        this.id=id;
        this.name=name;
        this.health =health;
        this.orijinalHealth =health;
        this.award=award;
        this.damage=damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0){
            health = 0;
        }
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAward() {
        if (getId()==4){
        rand();
        }
        return award;
    }

    public void setAward(int award) {
        this.award = award;
    }

    public int getOrijinalHealth() {
        return orijinalHealth;
    }

    public void setOrijinalHealth(int orijinalHealth) {
        this.orijinalHealth = orijinalHealth;
    }

    public int snakeAward(){
        Random randomSnakeAward = new Random();
        return randomSnakeAward.nextInt(100);
    }

    public int snakePresent(){
        Random randomSnakePresent = new Random();
        return randomSnakePresent.nextInt(100);
    }

    public void rand(){
        int randomSnakeAward = snakeAward();
        int randomSnakePresent = snakePresent();
        if (randomSnakeAward>=0 && randomSnakeAward<16){
            if (randomSnakePresent>=0 && randomSnakePresent<21){
                System.out.println("Tüfek ");
            }else if (randomSnakePresent>=21 && randomSnakePresent<51){
                System.out.println("Kılıç ");
            }else if (randomSnakePresent>=51){
                System.out.println("Tabanca ");
            }
        }
        if (randomSnakeAward>=16 && randomSnakeAward<31){
            if (randomSnakePresent>=0 && randomSnakePresent<21){
                System.out.println("Ağır Zırh ");
            }else if (randomSnakePresent>=21 && randomSnakePresent<51){
                System.out.println("Orta Zırh ");
            }else if (randomSnakePresent>=51){
                System.out.println("Hafif Zırh ");
            }
        }
        if (randomSnakeAward>=31 && randomSnakeAward<56){
            if (randomSnakePresent>=0 && randomSnakePresent<21){
                System.out.println("\n10 Para  !!! ");
            }else if (randomSnakePresent>=21 && randomSnakePresent<51){
                System.out.println("\n5 Para  !!! ");
            }else if (randomSnakePresent>=51){
                System.out.println("\n1 Para  !!! ");
            }
        }
        if (randomSnakeAward>=56 ){
            System.out.println("\nHiçbir şey kazanamadın!!!! :( ");
        }
    }
}
