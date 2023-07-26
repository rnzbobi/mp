/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author steve
 */
import java.util.*;

/**
 * Represents a bank with money and user-specific money data.
 */
public class Bank {
    private static int totalMoney = 0;
    private static int userTotalMoney = 0;
    private static HashMap<Integer, Integer> money;
    private static HashMap<Integer, Integer> usermoney;

    /**
     * Constructs a new Bank object with the specified initial money and user money data.
     *
     * @param money          the initial money data with denominations and quantities
     * @param usermoney      the initial user-specific money data with denominations and quantities
     * @param totalMoney     the initial total money in the bank
     * @param userTotalMoney the initial total money of the user
     */
    public Bank(HashMap<Integer, Integer> money, HashMap<Integer, Integer> usermoney, int totalMoney, int userTotalMoney) {
        Bank.money = money;
        Bank.usermoney = usermoney;
        Bank.totalMoney = totalMoney;
        Bank.userTotalMoney = userTotalMoney;
    }

    /**
     * Returns the current money data of the bank.
     *
     * @return the money data with denominations and quantities
     */
    public static HashMap<Integer, Integer> getMoney() {
        return money;
    }

    /**
     * Returns the current user-specific money data of the bank.
     *
     * @return the user-specific money data with denominations and quantities
     */
    public static HashMap<Integer, Integer> getUserMoney() {
        return usermoney;
    }

    /**
     * Returns the total money in the bank.
     *
     * @return the total money in the bank
     */
    public static int getTotalMoney() {
        return totalMoney;
    }

    /**
     * Returns the total money of the user.
     *
     * @return the total money of the user
     */
    public static int getUserTotalMoney() {
        return userTotalMoney;
    }

    /**
     * Updates the quantity of a specific denomination in the bank's money data.
     *
     * @param denomination the denomination of the money to update
     * @param quantity     the new quantity of the money denomination
     */
    public void updateMoney(int denomination, int quantity) {
        Bank.money.put(denomination, quantity); //updates the quantity for that specific denomination for the owner
    }

    /**
     * Updates the bank's money data with a new money data map.
     *
     * @param money the new money data map with denominations and quantities
     */
    public void updateMoneyMap(HashMap<Integer, Integer> money) {
        Bank.money = money; //updates the whole denomination and the quantity for each denomination for the owner
    }

    /**
     * Updates the quantity of a specific denomination in the user's money data.
     *
     * @param denomination the denomination of the user-specific money to update
     * @param quantity     the new quantity of the user-specific money denomination
     */
    public void updateUserMoney(int denomination, int quantity) {
        Bank.usermoney.put(denomination, quantity); //updates the quantity for that specific denomination for the user
    }

    /**
     * Updates the total money in the bank.
     *
     * @param totalmoney the new total money in the bank
     */
    public void updateTotalMoney(int totalmoney) {
        Bank.totalMoney = totalmoney; //updates the total money of the owner
    }

    /**
     * Updates the total money of the user.
     *
     * @param usertotalmoney the new total money of the user
     */
    public void updateUserTotalMoney(int usertotalmoney) {
        Bank.userTotalMoney = usertotalmoney; //updates the total money of the user
    }
}
