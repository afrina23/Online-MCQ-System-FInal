package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class PracticePart implements Runnable {
    HashMap<String,NetworkUtil> clientList;
    NetworkUtil NS;
    Thread t;
    ArrayList<String> Right= new ArrayList<>();
    ArrayList<String> Question= new ArrayList<>();
    ArrayList<String> Option= new ArrayList<>();
    Questionspackage questions;
    PracticePart(HashMap<String,NetworkUtil> clientList, NetworkUtil NS){
        this.clientList=clientList;
        this.NS=NS;
        t=new Thread(this);

        t.start();
    }
    @Override
    public void run() {
            while (true){
                if(NS.s.isClosed()){
                    break;
                }
                Scanner Q = null,A=null;
                try{
                    Q = new Scanner(new File("PracticeQuestion.txt"));
                    A = new Scanner(new File("PracticeAnswer.txt"));
                }catch(Exception e) {
                    System.out.println("Error");
                }
                while(Q.hasNextLine()){
                    Question.add(Q.nextLine());
                }
                int i=1;
                while(A.hasNextLine()){
                    if(i%5==0){
                        String s= A.nextLine();
                        Right.add(s);


                    }
                    else{
                        String s= A.nextLine();
                        Option.add(s);

                    }
                    i++;
                }
                questions=new Questionspackage(Question,Right,Option);
                NS.write(questions);
                String next=NS.read().toString();
                if(next.equals("finish")) {
                    try {
                        new AfterPractice(NS).t.join();
                        break;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

    }
}
