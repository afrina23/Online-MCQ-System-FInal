package sample;

import java.util.HashMap;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class QuestionPart implements Runnable {
    HashMap<String,NetworkUtil> clientList;
    NetworkUtil NS;
    Thread t;

    QuestionPart(HashMap<String,NetworkUtil> clientList,NetworkUtil NS){
        this.clientList=clientList;
        this.NS=NS;
        t=new Thread(this);

        t.start();
    }


    @Override
    public void run() {
        while(true) {
            if(NS.s.isClosed()){
                break;
            }
            String examMode = NS.read().toString();
            System.out.println(examMode + " server ");
            if (examMode.equals("Practice")) {
                System.out.println("practice server selected");
                try {
                    new PracticePart(clientList, NS).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(examMode.equals("Easy")){
                System.out.println("Easy server selected");
                try {
                    new ExamPart("Easy",clientList,NS).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(examMode.equals("Medium")){
                System.out.println("Medium server selected");
                try {
                    new ExamPart("Medium",clientList,NS).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(examMode.equals("Hard")){
                System.out.println("Hard server selected");
                try {
                    new ExamPart("Hard",clientList,NS).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(examMode.equals("logOut")){

                break;
            }
            else if(examMode.equals("exit")){

                break;
            }
        }
    }
}
