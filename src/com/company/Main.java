package com.company;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static String bossDefenceType;
    public static int bossDamage = 50;
    public static int bossHealth = 700;

    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic"};
    public static int[] heroesDamage = {25, 20, 15};
    public static int[] heroesHealth = {280, 270, 260};
    public static int roundNumber = 0;

    public static int[] medicHealth = {600};
    public static int[] medicalTreat = {20};

    public static void main(String[] args) {
        printStatistics();
        while (!isGmaeFinished()) {
            round();
        }
    }

    public static void round() {
        roundNumber++;
        bossDefenceType = chooseDefence();
        medicalTreat = chooseHelp();
        bossHits();
        heroesHit();
        medicalTreat();
        printStatistics();
    }

    private static int[] chooseHelp() {
        ThreadLocalRandom.current().nextInt(heroesHealth.length);
        return new int[heroesHealth.length];

    }



    public static String chooseDefence(){
    Random random = new Random();
    int randomIndex = random.nextInt(heroesAttackType.length);
    System.out.println(" Boss chose " + heroesAttackType[randomIndex]);
    return heroesAttackType[randomIndex];
}


    public static void medicalTreat() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 100) {
            }else{
                heroesHealth[i] = heroesHealth[i] + medicalTreat[i];
                System.out.println(" Medical HELP: " + medicalTreat[i]);
            }
            }
        }


    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2;
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(" Critical Damage: " + heroesDamage[i] + "x"
                            + coeff + " = " + heroesDamage[i] * coeff);
                } else
                if (bossHealth - heroesDamage[i] < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }
    public static boolean isGmaeFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes Won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <=0) {
            System.out.println("Boss Won!!!");
            return true;
                     */
        int totalHealth = 0;
        for (int health: heroesHealth) {
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
        System.out.println("Boss Health: " + bossHealth + "(" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " +
                    heroesHealth[i] +  "(" + heroesDamage[i] + ")");


        }
    }
}
