package com.eneyeitech;

import java.util.Scanner;

public class CoffeeMachine {
    private String l;
    private int aWater = 400;
    private int aMilk = 540;
    private int aCBeans = 120;
    private int aCash = 550;
    private int aCups = 9;
    private final int[] EXPRESSO = {250, 0, 16, 4};
    private final int[] LATTE = {350, 75, 20, 7};
    private final int[] CAPPUCCINO = {200, 100, 12, 6};
    private Scanner scanner_ = new Scanner(System.in);

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();

        String selection;
        do{
            selection = machine.userActionInput();
            machine.userActionSelection(selection);
        }while(!selection.equalsIgnoreCase("exit"));


    }

    public void userActionSelection(String selection){

        switch (selection){
            case "buy":
                String choice = userCoffeeOptionInput();
                buy(choice);
                break;
            case "fill":
                int w = Integer.parseInt(enterWater());
                int m = Integer.parseInt(enterMilk());
                int c = Integer.parseInt(enterCoffee());
                int cup = Integer.parseInt(enterCups());
                fill(w,m,c,cup);
                break;
            case "take":
                String s = "I gave you $"+take();
                displayScreenOption(s);
                break;
            case "remaining":
                showStock();
                break;
            case "exit":
                break;
        }
    }

    public String userActionInput(){
        displayScreenOption("Write action (buy, fill, take, remaining, exit): ");
        return userInput();
    }

    public String userCoffeeOptionInput(){
        displayScreenOption("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        return userInput();
    }

    public String enterWater(){
        displayScreenOption("Write how many ml of water you want to add: ");
        return userInput();
    }

    public String enterMilk(){
        displayScreenOption("Write how many ml of milk you want to add: ");
        return userInput();
    }

    public String enterCoffee(){
        displayScreenOption("Write how many grams of coffee beans you want to add: ");
        return userInput();
    }

    public String enterCups(){
        displayScreenOption("Write how many disposable cups of coffee you want to add: ");
        return userInput();
    }

    public String userInput(){
        return scanner_.next();
    }

    public void displayScreenOption(String msg){
        System.out.println(msg);
    }

    public void showStock(){
        System.out.println("The coffee machine has:\n" +
                ""+aWater+" ml of water\n" +
                ""+aMilk+" ml of milk\n" +
                ""+aCBeans+" g of coffee beans\n" +
                ""+aCups+" disposable cups\n" +
                "$"+aCash+" of money");
    }

    public boolean canCompleteOrder(int w, int m, int c){
        boolean canComplete = false;
        if(aWater > w && aMilk > m && aCBeans > c && aCups > 0){
            canComplete = true;
            displayScreenOption("I have enough resources, making you a coffee!");
        }else{
            if(w > aWater){
                displayScreenOption("Sorry, not enough water!");
            }
            if(m > aMilk){
                displayScreenOption("Sorry, not enough milk!");
            }
            if(c > aCBeans){
                displayScreenOption("Sorry, not enough coffee beans!");
            }
            if(aCups <= 0){
                displayScreenOption("Sorry, not enough disposable cup(s)!");
            }
        }
        return canComplete;
    }

    public void buy(String i){
        switch (i){
            case "1":
                if(canCompleteOrder(EXPRESSO[0],EXPRESSO[1],EXPRESSO[2]))
                    serveCoffee(EXPRESSO[0],EXPRESSO[1],EXPRESSO[2],EXPRESSO[3]);
                break;
            case "2":
                if(canCompleteOrder(LATTE[0],LATTE[1],LATTE[2]))
                    serveCoffee(LATTE[0],LATTE[1],LATTE[2],LATTE[3]);
                break;
            case "3":
                if(canCompleteOrder(CAPPUCCINO[0],CAPPUCCINO[1],CAPPUCCINO[2]))
                    serveCoffee(CAPPUCCINO[0],CAPPUCCINO[1],CAPPUCCINO[2],CAPPUCCINO[3]);
                break;
            case "back":
                break;
        }
    }

    public void serveCoffee(int w, int m, int c, int cash){
        aWater = aWater - w;
        aMilk = aMilk - m;
        aCBeans = aCBeans - c;
        aCash = aCash + cash;
        aCups--;
    }

    public void fill(int w, int m, int c, int cup){
        aWater += w;
        aMilk += m;
        aCBeans += c;
        aCups += cup;
    }

    public int take(){
        int tCash = aCash;
        aCash = 0;
        return tCash;
    }
}
