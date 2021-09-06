package puky2;
//import puky.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//创建一个卡牌类
class Pai{
    // 点数
    protected String count;
    // 花色
    protected String suit;

    public Pai(){
    }

    public Pai(String suit,String count){
        this.suit = suit;
        this.count = count;
    }
    @Override
    public String toString(){
        return "("+suit+count+")";
    }
}

//对卡牌进行处理

public class CardGame {
    public static void main(String[] args){
        //构造一副牌
        List<Pai> poker=buyCard();//buyCard()为构造派函数
        //洗牌，直接运用库函数进行洗牌
        Collections.shuffle(poker);
        //进行分发牌
        List<List<Pai>> players=new ArrayList<>();
        //创建三个玩家
        players.add(new ArrayList<Pai>());
        players.add(new ArrayList<Pai>());
        players.add(new ArrayList<Pai>());
        for(int i=0;i<players.size();i++)
        {
            for (int CardIndex=0;CardIndex<3;CardIndex++)
            {
                players.get(i).add(poker.remove(0));
            }
        }
        //打印规则
        System.out.println("卡牌游戏名称：炸金花简易版");
        System.out.println("牌面大小依次是豹子，顺子，对子，和单张，然后再比较点数大小");
        //打印玩家手里的牌
        System.out.println("玩家各自的手牌为:");
        for(int j=0;j<players.size();j++)
        {
            System.out.println("玩家"+(j+1)+":");
            System.out.println(players.get(j));
        }
        //比大小
        match(players);
    }
    private final static String[] SUITS={"黑桃","红心","梅花","方块"};
    //构造牌，buyCard
    public static List<Pai>  buyCard(){
        List<Pai> poker=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j <= 10; j++) {
                poker.add(new Pai(SUITS[i], j + ""));
            }
            //加入JQKA，不加大小王，我的玩法没有大小王
            poker.add(new Pai(SUITS[i], "J"));
            poker.add(new Pai(SUITS[i], "Q"));
            poker.add(new Pai(SUITS[i], "K"));
            poker.add(new Pai(SUITS[i], "A"));
        }
        return poker;
    }
    //进行比大小
    public static void match(List<List<Pai>> players){
        float[] player = new float[3];
        String[] arr1 = {players.get(0).get(0).count,players.get(0).get(1).count,
                players.get(0).get(2).count};
        String[] arr2 = {players.get(1).get(0).count,players.get(1).get(1).count,
                players.get(1).get(2).count};
        String[] arr3 = {players.get(2).get(0).count,players.get(2).get(1).count,
                players.get(2).get(2).count};
        swap(arr1); swap(arr2); swap(arr3);
        String[][] cur = {arr1,arr2,arr3};
        //对各种牌型进行赋值，然后加上牌的点数权重
        for (int i = 0; i < 3; i++) {
            if (cur[i][0].equals(cur[i][1]) && cur[i][0].equals(cur[i][2])) {
                player[i] = 5000.0f;
                player[i] += compaire(cur[i]);
            } else if (cur[i][0].compareTo(cur[i][1]) == 1 && cur[i][1].compareTo(cur[i][2]) == 1) {
                player[i] = 4000.0f;
                player[i] += compaire(cur[i]);
            } else if (players.get(i).get(0).suit.equals(players.get(i).get(1).suit)
                    && players.get(i).get(0).suit.equals(players.get(i).get(2).suit)) {
                player[i] = 3000.0f;
                player[i] += compaire(cur[i]);
            } else if (cur[i][0].equals(cur[i][1]) || cur[i][0].equals(cur[i][2]) || cur[i][1].equals(cur[i][2])) {
                player[i] = 2000.0f;
                player[i] += compaire(cur[i]);
            } else if (cur[i][0].compareTo(cur[i][1]) != 0 && cur[i][0].compareTo(cur[i][2]) != 0
                    && cur[i][1].compareTo(cur[i][2]) != 0 ) {
                player[i] = 1000.0f;
                player[i] += compaire(cur[i]);
            }
        }
        float max = -1;
        int flag = -1;
        int flag2 = -1;
        int flag3 = -1;
        for (int i = 0; i < 3; i++) {
            if (player[0] == player[1] && player[0] == player[2]) {
                flag3 = 0;
            }
            if (player[i] > max) {
                max = player[i];
                flag = i + 1;
            } else if (player[i] == max) {
                flag2 = i + 1;
            }
        }
        if (flag2 != -1 && flag3 == -1) {
            System.out.println("玩家" + flag + "和" + flag2 + "的牌一样大");
        } else if (flag3 != -1) {
            System.out.println("三个玩家的牌一样大");
        } else {
            System.out.println("玩家" + flag + "的牌最大");
        }
    }
    private static void swap(String[] arr) {
        String max1 = "";
        String max2 = "";
        String max3 = "";
        //根据ASCLL码来进行转换的值，9后面依次是：；<=>
        for (int i = 0; i < 3; i++) {
            if (arr[i].equals("10")) {
                arr[i] = ":";
            }
            if (arr[i].equals("J")) {
                arr[i] = ";";
            }
            if (arr[i].equals("Q")) {
                arr[i] = "<";
            }
            if (arr[i].equals("K")) {
                arr[i] = "=";
            }
            if (arr[i].equals("A")) {
                arr[i] = ">";
            }
        }
        if (arr[0].compareTo(arr[1]) > 0) {
            max2 = arr[0];
            max3 = arr[1];
            if (arr[2].compareTo(max2) > 0) {
                max1 = arr[2];
            } else if (arr[2].compareTo(max2) < 0){
                max1 = max2;
                if (arr[2].compareTo(max3) > 0) {
                    max2 = arr[2];
                } else {
                    max2 = max3;
                    max3 = arr[2];
                }
            } else {
                max1 = arr[2];
            }
        } else if(arr[0].compareTo(arr[1]) < 0){
            max2 = arr[1];
            max3 = arr[0];
            if (arr[2].compareTo(max2) > 0) {
                max1 = arr[2];
            } else if (arr[2].compareTo(max2) < 0) {
                max1 = max2;
                if (arr[2].compareTo(max3) > 0) {
                    max2 = arr[2];
                } else {
                    max2 = max3;
                    max3 = arr[2];
                }
            } else {
                max1 = arr[2];
            }
        } else if (arr[0].compareTo(arr[1]) == 0) {
            if (arr[2].compareTo(arr[1]) == 0) {
                max1 = arr[0];
                max2 = arr[0];
                max3 = arr[0];
            } else if (arr[2].compareTo(arr[1]) > 0) {
                max1 = arr[2];
                max2 = arr[1];
                max3 = arr[0];
            } else {
                max3 = arr[2];
                max2 = arr[0];
                max1 = arr[1];
            }
            arr[0] = max1;
            arr[1] = max2;
            arr[2] = max3;
            return;
        }
        arr[0] = max1;
        arr[1] = max2;
        arr[2] = max3;
        return;
    }
    private static float compaire(String[] arr) {
        float[] ret = new float[3];
        for (int i = 0; i < 3; i++) {
            if (arr[i] == ">") {
                ret[i] = 0.13f;
            } else if (arr[i] == "=") {
                ret[i] = 0.12f;
            } else if (arr[i] == "<") {
                ret[i] = 0.11f;
            } else if (arr[i] == ";") {
                ret[i] = 0.10f;
            } else if (arr[i] == ":") {
                ret[i] = 0.09f;
            } else if (arr[i] == "9") {
                ret[i] = 0.08f;
            } else if (arr[i] == "8") {
                ret[i] = 0.07f;
            } else if (arr[i] == "7") {
                ret[i] = 0.06f;
            } else if (arr[i] == "6") {
                ret[i] = 0.05f;
            } else if (arr[i] == "5") {
                ret[i] = 0.04f;
            } else if (arr[i] == "4") {
                ret[i] = 0.03f;
            } else if (arr[i] == "3") {
                ret[i] = 0.02f;
            } else if (arr[i] == "2") {
                ret[i] = 0.01f;
            }
            if (i == 0) {
                ret[i] *= 1000;
            } else if (i == 1) {
                ret[i] *= 10;
            } else {
                ret[i] *= 1;
            }
        }
        return ret[0] + ret[1] + ret[2];
    }
}
