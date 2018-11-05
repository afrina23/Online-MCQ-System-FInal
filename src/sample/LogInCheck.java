package sample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by Admin_8_1 on 03-Jun-16.
 */
public class LogInCheck implements Runnable {
    Thread t;
    NetworkUtil ns;
    Information in;
    int ID;
    HashMap <String,NetworkUtil> clientList;
    LogInCheck(NetworkUtil ns,  HashMap <String,NetworkUtil> clientList,int id){
        this.clientList=clientList;
        this.ns=ns;
        ID=id;
        t=new Thread(this);
        t.start();
    }
    @Override
    public void run() {
        while(true){
            System.out.println(ns.s.isClosed());
            if(ns.s.isClosed() && ns.s.isClosed()){
                System.out.println(ns.s.isClosed());
                break;
            }
            String m=ns.read().toString();
            if(m.equals("Log")){
                in=(Information) ns.read();
                if(in.mode.equals("Student")) {
                    clientList.put(in.Name,ns);
                    System.out.println("added in hash map");
                    printHashMap();

                }
                try {
                    new InformationCheck(in,ns,clientList).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(m.equals("Sign")){
                System.out.println("sign in threaad");
                in=(Information) ns.read();
                System.out.println("information"+in.Name+"v "+in.mode);
                if(in.mode.equals("Student")) {
                    clientList.put(in.Name,ns);

                }
                try {
                    new CreateAccount(ns,in,clientList).t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            else if(m.equals("exit")){
                break;
            }
            else if(m.equals("logOut")){
                break;
            }
        }


    }
    public void printHashMap(){

        Set set = clientList.entrySet();

        Iterator i = set.iterator();
        System.out.println("Current User--");
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            System.out.println(me.getKey() + " : ");
        }


    }

}
