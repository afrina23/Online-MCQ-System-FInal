package sample;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Admin_8_1 on 06-Jun-16.
 */
public class numberCheck implements Runnable {
    NetworkUtil NS;
    Thread t;
    String catagory;
    Scores s=new Scores();
    String s1,s2,s3;
    HashMap<String,NetworkUtil> clientList;
    numberCheck(String s,NetworkUtil ns,HashMap<String,NetworkUtil> clientList){
        NS=ns;
        catagory=s;
        this.clientList=clientList;
        t=new Thread(this);
        t.start();
        this.s.scoreEasy=0;
        this.s.scoreHard=0;
        this.s.scoreMedium=0;
    }

    @Override
    public void run() {
        while(true){
            String User=NS.read().toString();
            System.out.println("accepted score");
            if(User.equals("home")){
                break;
            }
            if(User.equals("Practice")){
                break;
            }
            if(User.equals("Easy")){
                break;
            }
            if(User.equals("Medium")){
                break;
            }
            if(User.equals("Hard")){
                break;
            }
            try {
                new scoreWrite(catagory,NS,clientList,User).t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
