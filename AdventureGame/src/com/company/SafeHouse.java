package com.company;

public class SafeHouse extends NormalLocation {
    public SafeHouse(Player player){
            super(player,"Güvenli Ev");
    }

    @Override
    public boolean onLocation(){
        System.out.println("Güvenli Evdesiniz. Canınız Fulendi!");
        this.getPlayer().setHealth(this.getPlayer().getOrijinalHealth());
        if (this.getPlayer().getInventory().isFullAwards()){
            System.out.println("Tebrikler! Tüm ödülleri topladınız ve oyunu tamamladınız.");
            return false;
        }
        return true;
    }
}
