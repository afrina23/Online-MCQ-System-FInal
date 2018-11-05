package sample;

import java.util.HashMap;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class ModeCheck implements Runnable {
    Information in;
    NetworkUtil NS;
    Thread t;
    HashMap<String,NetworkUtil> clientList;
    ModeCheck(Information in, NetworkUtil ns, HashMap <String,NetworkUtil> clientList){
        this.in=in;
        NS=ns;
        this.clientList=clientList;
        t= new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while(true){
            if(NS.s.isClosed() && NS.s.isClosed()){
                break;
            }
            String mode = NS.read().toString();
            if (mode.equals("Student")) {
                System.out.println("Server Student selected");

                try {
                    new QuestionPart(clientList,NS).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else if(mode.equals("Teacher")){
                System.out.println("Server teacher selected");
                try {
                    new QuestionsAddServer(clientList,NS).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else if(mode.equals("exit")){
                break;
            }
            else if(mode.equals("logOut")){
                break;
            }
        }


    }
}
