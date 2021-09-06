package puky;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PokeGame extends Thread{

    String[] nums = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};
    String[] names = {"黑桃","红心","梅花","方块"};
    List<Card> cardStartList;  //  开始创建扑克牌时的序列
    List<Card> cardEndList;   // 洗牌后的序列
    List<Player> playerList;  //  玩家列表

    public PokeGame(){
        this.cardStartList= new ArrayList<Card>();
        this.cardEndList = new  ArrayList<Card>();
        this.playerList = new ArrayList<Player>();
    }
    //  创建扑克牌
    void createCard(){
        System.out.println("规则：扑克牌比大小，同花色情况下，按黑桃、红心、梅花、方块作大小排序");
        System.out.println("-----------创建扑克牌---------------");
        for (String name : names) {
            for (String num : nums) {
                cardStartList.add(new Card(num,name));
            }
        }
        cardStartList.add(new Card("C","大王"));
        cardStartList.add(new Card("B","小王"));
        System.out.println("---------扑克牌创建成功--------------");
        System.out.print("[");
        for(int i=0;i<cardStartList.size();i++){
            Card card = cardStartList.get(i);
            if(i%13!=0) System.out.print(",");
            if((i+1)%13==1 && i!=0){
                System.out.println("]");
                System.out.print("[");
            }
            System.out.print(card.getName()+card.getNum());
        }
        System.out.println("]");
    }

    //  洗牌
    void shuffleCard(){
        System.out.println("-----------开始洗牌---------------");
        Random random = new Random();
        Integer listSize = cardStartList.size();
        for(int i=0;i<listSize;i++){
            Card cd = new Card();
            do{
                cd = cardStartList.get(random.nextInt(listSize));
            }while(cardEndList.contains(cd));
            cardEndList.add(cd);
        }
        System.out.println("-----------洗牌结束---------------");
    }

    //   创建角色
    private void createPlayers() {
        Integer playerNum = 2;
        playerList.add(new Player(1,"A"));
        playerList.add(new Player(2,"B"));
        for (Player player : playerList) {
            System.out.println("----欢迎玩家 :" + player.getName());
        }
    }

    //   给角色发牌
    private void sendCard() {
        System.out.println("-----------开始发牌---------------");
        Collections.shuffle(playerList);
        int cardSendNum = 27,index = 0;
        for(int i=0; i<cardSendNum; i++){
            for(int j=0; j<playerList.size(); j++){
                Player player = playerList.get(j);
                player.cardList.add(cardEndList.get(index++));
                System.out.println("----玩家 :" + player.getName()+" 拿牌");
            }
        }
        System.out.println("-----------发牌结束---------------");
        playerCardPrint();
        System.out.println("----------10s后开始游戏-------------");
        try{
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    //   角色手牌打印
    private void playerCardPrint(){
        System.out.println("玩家各自的手牌为:");
        for(int j=0;j<playerList.size();j++){
            Player player = playerList.get(j);
            System.out.print("玩家 :" + player.getName()+"[");
            for(int i=0;i<player.cardList.size();i++){
                if(i!=0) System.out.print(",");
                System.out.print(player.cardList.get(i).getName()+
                        player.cardList.get(i).getNum());
            }
            System.out.print("]\n");
        }
    }

    //  游戏开始
    private void playGame() {
        playerCardPrint();
        System.out.println("-----------游戏开始---------------");
        Player player1 = playerList.get(0);
        Player player2 = playerList.get(1);
        List<Card> cardList1 = player1.cardList;
        List<Card> cardList2 = player2.cardList;
        long beginTime = System.currentTimeMillis();
        while(cardList1.size()>0 && cardList2.size()>0) {
            System.out.println("-------------------------------");
            Random random=new Random();
            int card1num=random.nextInt(cardList1.size());
            int card2num=random.nextInt(cardList2.size());
            try{
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("玩家"+player1.getName()+"抽牌"+cardList1.get(card1num).getName()+cardList1.get(card1num).getNum());
            System.out.println("玩家"+player2.getName()+"抽牌"+cardList2.get(card2num).getName()+cardList2.get(card2num).getNum());
            Card card1 = cardList1.get(card1num),card2 = cardList2.get(card2num);
            if (card1.compareTo(card2) < 0) {
                System.out.println("玩家"+player2.getName()+"赢");
                cardList1.remove(card1);
                cardList2.add(card1);
            }else{
                System.out.println("玩家"+player1.getName()+"赢");
                cardList2.remove(card2);
                cardList1.add(card2);
            }
            playerCardPrint();
            long endTime = System.currentTimeMillis();
            if ((double) (endTime - beginTime) / 1000 > 30) {

                System.out.println("30秒时间到");
                break;
            }
        }
        System.out.println("-----------游戏结束---------------");
        System.out.println(player1.getName()+"手牌数为"+cardList1.size());
        System.out.println(player2.getName()+"手牌数为"+cardList2.size());
        if (cardList1.size() < cardList2.size()) {
            System.out.println("最后赢家为"+player2.getName());
        }else if(cardList1.size() > cardList2.size()){
            System.out.println("最后赢家为"+player1.getName());
        }else{
            System.out.println("平局");
        }

    }

    public static void main(String[] args) {
        PokeGame game = new PokeGame();
        game.createCard();  // 创建扑克牌
        game.shuffleCard(); // 洗牌
        game.createPlayers();  // 创建角色
        game.sendCard();  // 给角色发牌
        game.playGame();  // 游戏开始
    }
}