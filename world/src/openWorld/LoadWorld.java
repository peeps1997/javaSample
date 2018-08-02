package openWorld;
import java.util.ArrayList;
import java.util.List;
import players.Child;
public class LoadWorld extends WorldAttribute {

    LoadWorld() {
        super();
    }


    public static void main(String args[]) {
        int i;
        new LoadWorld();
        //ThreadGroup t1=new ThreadGroup("PlayerBunch");
        Thread t1 = null, t2 = null, t3 = null, t4 = null, t5 = null, t6 = null;
        PlayerThread p1, p2, p3, p4, p5, p6;
        //		for(i=0;i<10;i++)
        //			{
        p1 = new PlayerThread(new Child(1));
        t1 = new Thread(p1);
        p2 = new PlayerThread(new Child(2));
        t2 = new Thread(p2);
        p3 = new PlayerThread(new Child(3));
        t3 = new Thread(p3);
        p4 = new PlayerThread(new Child(4));
        t4 = new Thread(p4);
        p5 = new PlayerThread(new Child(5));
        t5 = new Thread(p5);
        p6 = new PlayerThread(new Child(6));
        t6 = new Thread(p6);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("------------>"+i);
        //}
    }



}