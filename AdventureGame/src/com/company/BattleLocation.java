package com.company;
import java.util.Random;

public abstract class BattleLocation extends Location{

    private Monster monster;
    private String award;
    private int maxMonster;

    public BattleLocation(Player player,String name,Monster monster,String award,int maxMonster){
        super(player,name);
        this.monster=monster;
        this.award=award;
        this.maxMonster=maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monsterNum =randomMonsterNum();
        System.out.println("Şuan "+this.getName()+" bölgesindesin. "+
                           "\nDikkatli ol! Burada "+monsterNum+" tane "+this.getMonster().getName()+" yaşıyor!" +
                           "\n[S] Savaş  [K] Kaç");
         String selectCase = scan.nextLine().toUpperCase();
         if (selectCase.equals("S") && combat(monsterNum)){
            System.out.println(this.getName() + " bölgesindeki tüm "+this.getMonster().getName()+" yendiniz \n");
            return true;
        }
        if (this.getPlayer().getHealth() <= 0){
            System.out.println("!!! ÖLDÜNÜZ !!!");
            return false;
        }
        return true;
    }

    public boolean combat(int monsterNum){
        for (int i=1 ; i <= monsterNum;i++){ //kaç canavarla savaşacağımızın döngüsü.
            this.getMonster().setHealth(this.getMonster().getOrijinalHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0){
                System.out.print("[V] Vur & [K] Kaç    ");
                String selectCombat = scan.nextLine().toUpperCase();
                if (selectCombat.equals("V")){
                    firstStrike();
                    System.out.println("Siz vurdunuz !");
                    this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth()>0) {
                        System.out.println("\n" + this.getMonster().getName() + " size vurdu!");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage < 0) {
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                        afterHit();
                        }
                }else {
                    return false;
                }
            }
            if (this.getMonster().getHealth() < this.getPlayer().getHealth()){
                System.out.println(this.getMonster().getName()+" Yendin !\n" );
               if (this.getMonster().getId()!=4){
                   System.out.println(this.getMonster().getAward() + " Para kazandın !");
                   this.getPlayer().setMoney(this.getPlayer().getMoney()+this.getMonster().getAward());
                   System.out.println("Güncel para miktarınız: " + this.getPlayer().getMoney());
               }else {
                   monster.getAward();
               }

            }else{
               return false;
            }
        }
        switch (this.getAward().toLowerCase()) {
            case "su" -> this.getPlayer().getInventory().setWater(true);
            case "yemek" -> this.getPlayer().getInventory().setFood(true);
            case "odun" -> this.getPlayer().getInventory().setFirewood(true);
        }
        if (!this.getName().equals("Maden")) {
            System.out.println(this.getAward().toUpperCase()+ " ödülü alındı.");
        }
        return true;
    }

    public void afterHit() {
        System.out.println("Canınız: "+this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName()+" Canı: "+this.getMonster().getHealth());
    }
    public void playerStats() {
        System.out.println("\n Oyuncu Değerleri");
        System.out.println("Sağlık: "+this.getPlayer().getHealth() +
                           " Silah: "+this.getPlayer().getInventory().getWeapon().getName() +
                           " Zırh: "+this.getPlayer().getInventory().getArmor().getName() +
                           " Bloklama: "+this.getPlayer().getInventory().getArmor().getBlock() +
                           " Hasar: "+this.getPlayer().getTotalDamage()+
                           " Para: "+this.getPlayer().getMoney());
    }


    public void monsterStats(int i) {
        System.out.println(i+". "+this.getMonster().getName()+" Değerleri ");
        System.out.println("Sağlık: "+this.getMonster().getHealth()+" Hasar: "+this.getMonster().getDamage());
    }

    public int randomStrikeChange(){
        Random r = new Random();
        return  r.nextInt(3);
    }

    public void firstStrike(){
        int randomStrike = randomStrikeChange();
        if (randomStrike == 1) {
            System.out.println("İlk Vuruş Size Ait");
            this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
            afterHit();
            if (this.getMonster().getHealth() > 0) {
                System.out.println("\n" + this.getMonster().getName() + " size vurdu!");
                int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                if (monsterDamage < 0) {
                    monsterDamage = 0;
                }
                this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                afterHit();
            } else if (this.getMonster().getHealth() < 0) {
                if (this.getMonster().getHealth() < this.getPlayer().getHealth()) {
                    System.out.println(this.getMonster().getName() + " Yendin !\n" +
                            this.getMonster().getAward() + " kazandın !");
                }
            }
            if (randomStrike == 2) {
                System.out.println("İlk Vuruş Düşmana Ait");
                int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                if (monsterDamage < 0) {
                    monsterDamage = 0;
                }
                this.getPlayer().setHealth(this.getPlayer().getHealth() - monsterDamage);
                afterHit();
            }
        }
    }

    public int randomMonsterNum(){
        Random r = new Random();
        return r.nextInt(this.getMaxMonster())+1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }


    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
