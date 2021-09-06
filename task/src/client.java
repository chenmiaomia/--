import java.util.Date;
import java.text.*;
class client {
    private int id;
    client(int i){
        id=i;
    }
    public int getId(){
        return id;
    }
    public void buy(int product){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
        System.out.println("用户"+id+"买到商品"+product+"，此时时间为"+ft.format(dNow));
    }
}
