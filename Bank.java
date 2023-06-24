import java.util.*;

public class Bank {
    private static int totalMoney = 0;
    private static int userTotalMoney = 0;
    private static HashMap<Integer,Integer> money;
    private static HashMap<Integer,Integer> usermoney;

    public Bank(HashMap<Integer,Integer> money, HashMap<Integer,Integer> usermoney, int totalMoney, int userTotalMoney){
        Bank.money = money;
        Bank.usermoney = usermoney;
        Bank.totalMoney = totalMoney;
        Bank.userTotalMoney = userTotalMoney;
    }
    
    public HashMap<Integer,Integer> getMoney(){
        return money;
    }

    public HashMap<Integer,Integer> getUserMoney(){
        return usermoney;
    }

    public int getTotalMoney(){
        return totalMoney;
    }

    public int getUserTotalMoney(){
        return userTotalMoney;
    }

    public void updateMoney(int denomination, int quantity){
        Bank.money.put(denomination,quantity);
    }

    public void updateUserMoney(int denomination, int quantity){
        Bank.usermoney.put(denomination,quantity);
    }

    public void updateTotalMoney(int totalmoney){
        Bank.totalMoney = totalmoney;
    }

    public void updateUserTotalMoney(int usertotalmoney){
        Bank.userTotalMoney = usertotalmoney;
    }
}
