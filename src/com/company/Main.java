package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static String bossZashita;
    public static int bossUron = 90;
    public static int bossJizn = 4700;

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Bersek", "Thor"};
    public static int[] heroesUron = {25, 20, 15, 0, 5, 15, 15, 20};
    public static int[] heroesJizn = {280, 270, 260, 600, 700, 150, 300, 200};
    public static int roundNumber = 0;


    public static void main(String[] args) {
        printStatistics();
        while (!isGmaeFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        bossZashita = chooseDefence();
        Medic();
        bossHits();
        heroesHit();
        printStatistics();
        Golem();
        Lucky();
        Bersek();
        Thor();
    }


    public static String chooseDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        System.out.println(" Boss chose " + heroesAttackType[randomIndex]);
        return heroesAttackType[randomIndex];
    }


    public static void bossHits() {
        for (int i = 0; i < heroesJizn.length; i++) {
            if (heroesJizn[i] > 0 && bossJizn > 0) {
                if (heroesJizn[i] - bossUron < 0) {
                    heroesJizn[i] = 0;
                } else {
                    heroesJizn[i] = heroesJizn[i] - bossUron;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesUron.length; i++) {
            if (heroesJizn[i] > 0 && bossJizn > 0) {
                if (bossZashita == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2;
                    if (bossJizn - heroesUron[i] * coeff < 0) {
                        bossJizn = 0;
                    } else {
                        bossJizn = bossJizn - heroesUron[i] * coeff;
                    }
                    System.out.println(" Critical Damage: " + heroesUron[i] + "x"
                            + coeff + " = " + heroesUron[i] * coeff);
                } else if (bossJizn - heroesUron[i] < 0) {
                    bossJizn = 0;
                } else {
                    bossJizn = bossJizn - heroesUron[i];
                }
            }
        }
    }

    public static boolean isGmaeFinished() {
        if (bossJizn <= 0) {
            System.out.println("Heroes Won!!!");
            return true;
        }

        int totalHealth = 0;
        for (int health : heroesJizn) {
            totalHealth += health;
        }
        if (totalHealth <= 0) {
            System.out.println("Boss Won!!!");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND ----------------");
        System.out.println("Boss Health: " + bossJizn + "(" + bossUron + ")");
        for (int i = 0; i < heroesJizn.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesJizn[i] + "(" + heroesUron[i] + ")");


        }

    }

    public static void Medic() {
        for (int i = 0; i < heroesJizn.length; i++) {
            if (heroesJizn[i] < 100 && heroesJizn[i] > 0) {
                if (heroesJizn[3] < 100 && heroesJizn[3] > 0) {
                    heroesJizn[3] = heroesJizn[3] - bossUron;
                } else {
                    Random random = new Random();
                    int coeff = random.nextInt(10);
                    heroesJizn[i] = heroesJizn[i] - bossUron + coeff;
                    System.out.println(" Medical HELP: " + heroesUron[i] + "x"
                            + coeff + " = " + heroesUron[i] * coeff);

                }

            }
        }
    }

    public static void Golem() {
        int coeff1 = bossUron / 5;
        int coeff2 = 0;
        for (int i = 0; i < heroesJizn.length; i++) {
            if (heroesJizn[i] > 0) {
                coeff2++;
                heroesJizn[i] = heroesJizn[i] - coeff1;
                heroesJizn[4] = heroesJizn[4] - bossUron * coeff1;
                System.out.println(" Prinyal UDAR: " + heroesUron[i] + "x"
                        + coeff1 + " = " + heroesUron[i] * coeff1);

            }

        }
    }

    public static void Lucky() {
        Random random = new Random();
        boolean udacha = random.nextBoolean();
        if (heroesJizn[5] > 0 && udacha) {
            heroesJizn[5] += bossUron - bossUron / 5;
        } else {
            bossUron = 50;

        }
    }

    public static void Bersek() {
        for (int i = 0; i < heroesJizn.length; i++) {
            Random random = new Random();
            int block = heroesUron[6] + random.nextInt(50);
            heroesJizn[6] = heroesJizn[6] - (bossUron - block);
            heroesUron[6] = heroesUron[6] + block;


        }
    }

    public static void Thor() {
        Random random = new Random();
        boolean out = random.nextBoolean();
        if (heroesJizn[7] > 0 && out) {
            bossUron = 0;
        } else {
            bossUron = 50;
        }
    }
}