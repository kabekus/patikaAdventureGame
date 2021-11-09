package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Macera Oyununa Hoşgeldiniz ^_^");
        System.out.print("Lütfen Adınızı Girin --> ");
        String playerName = input.nextLine();

        Player player = new Player(playerName);
        System.out.println(player.getName() + " Bu Sanal Dünyaya Hoş Geldin! \nBir karakter seçmekle başlayalım ");
        player.selectChar();

        while (true) {
            player.printInfo();
            System.out.println("\nBölgeler: \n0- Çıkış --> Oyunu Sonlandır! " +
                    "\n1- Güvenli Ev --> Burası sizin için güvenli. Düşman yok! " +
                    "\n2- Envanter Dükkanı --> Silah ve zırh satın alabilirsiniz! " +
                    "\n3- Mağara --> [Ödül: Yemek] Ayı Çıkabilir! " +
                    "\n4- Nehir  --> [Ödül: Su] Zombi Çıkabilir! " +
                    "\n5- Orman  --> [Ödül: Odun] Vampir Çıkabilir! " +
                    "\n6- Maden  --> [Ödül: Para,Silah veya Zırh] Yılan Çıkabilir! ");
            int locationChoice = 0;
            boolean isChoiceValid = false;
            boolean isAvailableLocation;
            Location location = null;
            do {
                isAvailableLocation = true;
                try {
                    System.out.print("Lütfen gitmek itdiğiniz bölgeyi seçin! ");
                    locationChoice = input.nextInt();
                    if (locationChoice == 3 && player.getInventory().isFood()) {
                        isAvailableLocation = false;
                    } else if (locationChoice == 4 && player.getInventory().isWater()) {
                        isAvailableLocation = false;
                    } else if (locationChoice == 5 && player.getInventory().isFirewood()) {
                        isAvailableLocation = false;
                    }

                    System.out.println();
                    if (isAvailableLocation) {
                        switch (locationChoice) {
                            case 0:
                                location = null;
                                break;
                            case 1:
                                location = new SafeHouse(player);
                                isChoiceValid = true;
                                break;
                            case 2:
                                location = new ToolStore(player);
                                isChoiceValid = true;
                                break;
                            case 3:
                                location = new Cave(player);
                                isChoiceValid = true;
                                break;
                            case 4:
                                location = new River(player);
                                isChoiceValid = true;
                                break;
                            case 5:
                                location = new Forest(player);
                                isChoiceValid = true;
                                break;
                            case 6:
                                location = new Coal(player);
                                isChoiceValid = true;
                                break;
                            default:
                                System.out.println("[ Geçerli Bi Değer Gir ]");
                        }
                    } else {
                        System.out.println("Bu bölgeyi temizlediniz.Buraya tekrar giremezsiniz.");
                    }
                } catch (InputMismatchException | NullPointerException e) {
                    System.out.println("Bir rakam giriniz");
                }
                input.nextLine();
            } while (!isChoiceValid);

            if (location.getName().equals("Güvenli ev") && !location.onLocation()){
                return;
            }
            if (location == null || !location.onLocation()){
                System.out.println("Oyun bitti!");
                return;
            }
        }
    }
}


