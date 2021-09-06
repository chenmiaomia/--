package puky;

import java.util.ArrayList;
import java.util.List;

public class Player {
    //  角色id
    private Integer ID;
    //  角色姓名
    private String name;
    //  角色获得的牌
    List<Card> cardList;

    public Player(){
    }

    public Player(Integer ID,String name){
        this.ID = ID;
        this.name = name;
        this.cardList = new ArrayList<Card>();
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
