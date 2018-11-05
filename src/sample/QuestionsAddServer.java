package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class QuestionsAddServer implements Runnable {
    Thread t;
    HashMap<String,NetworkUtil> clientList;
    OneQuestion add;
    String ques;
    String Option1;
    String Option2;
    String Option3;
    String Option4;
    String Answer;
    String ExamMode;
    NetworkUtil NS;
    QuestionsAddServer(HashMap<String,NetworkUtil> clientList,NetworkUtil NS){
        this.clientList=clientList;
        this.NS=NS;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while(true){
            if(NS.s.isClosed() && NS.s.isClosed()){
                break;
            }
            String toDo=NS.read().toString();
            if(toDo.equals("add") ){
                add=(OneQuestion) NS.read();
                ques=add.ques;
                Option1=add.op1;
                Option2=add.op2;
                Option3=add.op3;
                Option4=add.op4;
                Answer=add.ans;
                ExamMode=add.mode;
                File fQ=null,fA=null;
                fQ= new File(ExamMode+"Question.txt");
                fA= new File(ExamMode+"Answer.txt");
                FileWriter fwQ= null;
                try {
                    fwQ = new FileWriter(fQ,true);
                    BufferedWriter bwQ= new BufferedWriter(fwQ);
                    bwQ.newLine();
                    bwQ.write(ques);
                    bwQ.close();

                    FileWriter fwA= new FileWriter(fA,true);
                    BufferedWriter bwA= new BufferedWriter(fwA);
                    bwA.newLine();
                    bwA.write(Option1);
                    bwA.newLine();
                    bwA.write(Option2);
                    bwA.newLine();
                    bwA.write(Option3);
                    bwA.newLine();
                    bwA.write(Option4);
                    bwA.newLine();
                    bwA.write(Answer);
                    bwA.close();
                    System.out.println("question ta server e  add hoise "+ ExamMode);
                    // printHashMap();
                    new InformEveryBody(clientList,add,NS).t.join();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }




        }


    }
    public void printHashMap(){

        Set set = clientList.entrySet();

        System.out.println("Size server"+clientList.size());
        Iterator i = set.iterator();
        System.out.println("Current User--");
        NetworkUtil ns;
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.println(me.getKey() + " : ");
            ns= (NetworkUtil) me.getValue();
            System.out.println("server socket "+ns.s.getInetAddress());
            if(!ns.s.isClosed()) ns.write(add);
            //ns.write(add);

        }


    }
}
