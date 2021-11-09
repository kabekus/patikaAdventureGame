package com.company;

public class ToolStore extends  NormalLocation {

    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        boolean showStore=true;
        while (showStore){
            System.out.println("------> Mağazaya Hoş Geldiniz <------" +
                    "\n1- Silahlar " +
                    "\n2- Zırhlar " +
                    "\n3- Çıkış Yap" +
                    "\nSeçiniz ! ");
            int selectCase = scan.nextInt();
            while (selectCase < 1 || selectCase > 3) {
                System.out.print("Geçersiz Değer ! Tekrar deneyin !");
                selectCase = scan.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Güle Güle !");
                    showStore = false;
                    break;
            }

        }
        return true;
    }

    public void printWeapon() {
        System.out.println(">>> Silahlar <<< \n");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " Adı " + w.getName() + " Para: " + w.getMoney() + " Hasar: " + w.getDamage());
        }
        System.out.println("0- Çıkış yap");
    }

    public void buyWeapon(){

        System.out.print("Bir Silah Seçiniz ");
        int selectWeaponID = scan.nextInt();

        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.print("Geçersiz Değer ! Tekrar deneyin !");
            selectWeaponID = scan.nextInt();
        }
        if (selectWeaponID !=0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if(selectedWeapon != null) {
                if (selectedWeapon.getMoney() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli bakiyeniz bulunmamaktadır ! ");

                }else{
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız! ");
                    int balance =this.getPlayer().getMoney() - selectedWeapon.getMoney();
                    this.getPlayer().setMoney(balance);
                    System.out.println(" Kalan paranız : " + this.getPlayer().getMoney());
                    System.out.println("Önceki silahınız: "+this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                }

            }
        }

    }

    public  void printArmor(){

        System.out.println(">>> Zırhlar <<< \n");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName()+ " Zırh Değeri: "+a.getBlock()+" Ücret: "+a.getPrice());
        }
        System.out.println("0- Çıkış yap");
    }

    private void buyArmor(){
        System.out.print("Bir Zırh Seçiniz ");
        int selectArmorID = scan.nextInt();

        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.print("Geçersiz Değer ! Tekrar deneyin !");
            selectArmorID = scan.nextInt();
        }

        if (selectArmorID !=0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if(selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli bakiyeniz bulunmamaktadır ! ");

                }else{
                    System.out.println(selectedArmor.getName() + " zırh satın alıdı! ");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                    System.out.println("Önceki Zırhınız: "+this.getPlayer().getInventory().getArmor().getName());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }

            }
        }
    }
}
