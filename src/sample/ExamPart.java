package sample;

import javafx.scene.control.RadioButton;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class ExamPart implements Runnable {
    HashMap<String,NetworkUtil> clientList;
    NetworkUtil NS;
    Thread t;
    String catagory;
    Questionspackage q;
    ArrayList<String> RightAnswers= new ArrayList<>();

    ArrayList<String> QuestionString= new ArrayList<>();
    ArrayList<String> OptionString= new ArrayList<>();
    ExamPart(String s,HashMap<String,NetworkUtil> clientList, NetworkUtil NS){
        catagory=s;
        this.clientList=clientList;
        this.NS=NS;
        t=new Thread(this);

        t.start();
    }
    @Override
    public void run() {
        while (true) {
            if (NS.s.isClosed()) {
                //break;
            }
            Scanner Q = null, A = null;


            try {
                Q = new Scanner(new File(catagory + "Question.txt"));
                A = new Scanner(new File(catagory + "Answer.txt"));
            } catch (Exception e) {
                System.out.println("Error");
            }
            while (Q.hasNextLine()) {
                QuestionString.add(Q.nextLine());
            }
            int i = 1;
            while (A.hasNextLine()) {
                if (i % 5 == 0) {
                    String s = A.nextLine();
                    RightAnswers.add(s);


                } else {
                    String s = A.nextLine();
                    OptionString.add(s);

                }
                i++;
            }
            q = new Questionspackage(QuestionString, RightAnswers, OptionString);
            NS.write(q);
            String next=NS.read().toString();
            if(next.equals("finish")) {
                try {
                    new numberCheck(catagory, NS, clientList).t.join();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
