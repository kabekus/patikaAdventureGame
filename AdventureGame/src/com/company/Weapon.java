package com.company;

public class Weapon {
    private String name;
    private int id, damage, money;


    public Weapon(String name, int id, int damage,int money){
        this.name=name;
        this.id=id;
        this.damage=damage;
        this.money=money;
    }


    public static Weapon[] weapons(){
        Weapon[] weaponList = new Weapon[3];
        weaponList[0]= new Weapon("Tabanca", 1, 2,5);
        weaponList[1]= new Weapon("Kılıç", 2, 3,35);
        weaponList[2]= new Weapon("Tüfek", 3, 7,45);
        return weaponList;
    }

    public static Weapon getWeaponObjByID(int id){
        for (Weapon w : Weapon.weapons()){
            if (w.getId()==id){
                return w;
            }
        }
        return null;
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


    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
