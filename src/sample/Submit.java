package sample;

import java.io.IOException;

/**
 * Created by Admin_8_1 on 08-Jun-16.
 */
public class Submit implements Runnable {
    Scores s;
    NetworkUtil NS;
    Thread t;
    Submit(Scores s,NetworkUtil ns){
        this.s=s;
        NS=ns;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        Object o= NS.read();
        if(o instanceof Scores){
            s=(Scores) NS.read();
            System.out.println("score "+s.scoreEasy+s.scoreMedium+s.scoreHard);

        }
    }
}
