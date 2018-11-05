package sample;

import java.io.Serializable;

/**
 * Created by Admin_8_1 on 03-Jun-16.
 */
public class Information implements Serializable {
    public String Name;
    public String email;
    public String password;
    public String mode;
    Information(String n,String e,String p){
        Name=n;
        email=e;
        password=p;
        mode="\0";
    }
    Information(){
        Name=null;
    }
}
