package sample;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin_8_1 on 06-Jun-16.
 */
public class scoreWrite implements Runnable {
    NetworkUtil NS;
    Thread t;
    String catagory;
    Scores s=new Scores();
    String s1,s2,s3,User;

    HashMap<String,NetworkUtil> clientList;
    scoreWrite(String s, NetworkUtil ns, HashMap<String,NetworkUtil> clientList,String user){
        NS=ns;
        catagory=s;
        this.clientList=clientList;
        User=user;
        t=new Thread(this);
        t.start();
        this.s.scoreEasy=0;
        this.s.scoreHard=0;
        this.s.scoreMedium=0;
    }
    @Override
    public void run() {
        int ans=Integer.parseInt(User);
        NetworkUtil value=NS;
        String name=null;
        for(Map.Entry entry: clientList.entrySet()){

            if(value.equals(entry.getValue())){
                name = (String) entry.getKey();
                break; //breaking because its one to one map
            }
        }

        BufferedReader fr= null;
        try {
            fr = new BufferedReader(new FileReader(name+"score.txt"));
            s1=fr.readLine();
            s2=fr.readLine();
            s3=fr.readLine();
            s.scoreEasy=Integer.parseInt(s1);
            s.scoreMedium=Integer.parseInt(s2);
            s.scoreHard=Integer.parseInt(s3);

            if(catagory.equals("Easy")){
                if(s.scoreEasy< ans){

                    s.scoreEasy=ans;
                }
            }
            else if(catagory.equals("Medium")){
                if(s.scoreMedium< ans){

                    s.scoreMedium=ans;
                }
            }
            else if(catagory.equals("Hard")){
                if(s.scoreHard< ans){

                    s.scoreHard=ans;
                }
            }

            PrintWriter bw=new PrintWriter(new File(name+"score.txt"));
            bw.println(s.scoreEasy);
            bw.println(s.scoreMedium);
            bw.println(s.scoreHard);
            bw.close();
            System.out.println("writing from server scores");
            NS.write(s);
            System.out.println("writen from server scores");
        } catch (Exception e) {
            File file= new File(name+"score.txt");
            try {
                if(catagory.equals("Easy")){
                    if(s.scoreEasy< ans){

                        s.scoreEasy=ans;
                    }
                }
                else if(catagory.equals("Medium")){
                    if(s.scoreMedium< ans){

                        s.scoreMedium=ans;
                    }
                }
                else if(catagory.equals("Hard")){
                    if(s.scoreHard< ans){

                        s.scoreHard=ans;
                    }
                }
                PrintWriter bw=new PrintWriter(file);


                bw.println(s.scoreEasy);
                bw.println(s.scoreMedium);
                bw.println(s.scoreHard);

                bw.close();
                System.out.println("writing from server scores");
                NS.write(s);
                System.out.println("writn from server scores");
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
