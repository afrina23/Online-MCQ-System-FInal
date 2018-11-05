package sample;

/**
 * Created by Admin_8_1 on 08-Jun-16.
 */
public class AfterPractice implements Runnable{
    Thread t;
    String action;
    NetworkUtil n;
    AfterPractice(NetworkUtil ns){
        n=ns;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        action=n.read().toString();
        if(action.equals("exit")){
            t.stop();
        }
    }
}
