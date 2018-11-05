package sample;

import java.io.Serializable;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class OneQuestion implements Serializable {
    String ques;
    String op1;
    String op2;
    String op3;
    String op4;
    String ans;
    String mode;

    OneQuestion(String q,String o1,String o2,String o3,String o4,String a){
        ques=q;
        op1=o1;
        op2=o2;
        op3=o3;
        op4=o4;
        ans=a;

    }
    OneQuestion(){

    }
    void setMode(String s){
        mode=s;
    }

}
