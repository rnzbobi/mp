
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

    public static HashMap<Integer,Integer> getMoney(){
        return money;
    }

    public static HashMap<Integer,Integer> getUserMoney(){
        return usermoney;
    }

    public static int getTotalMoney(){
        return totalMoney;
    }

    public static int getUserTotalMoney(){
        return userTotalMoney;
    }

    public void updateMoney(int denomination, int quantity){
        Bank.money.put(denomination,quantity);
    }

    public void updateMoneyMap(HashMap<Integer,Integer> money){
        Bank.money = money;
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
