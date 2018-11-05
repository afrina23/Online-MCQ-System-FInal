package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

/**
 * Created by Admin_8_1 on 12-May-16.
 */
public class AfterPracticeController {
    public NetworkUtil n=Main.ns;
    @FXML
    private Label UserName;
    @FXML
    private Label time;

    @FXML
    public void initialize(){
        UserName.setText("Name: "+Main.getName());
        long Time= (Main.getEndTime()-Main.getStartTime())/1000;

        time.setText(String.valueOf(Time)+"s");
    }
    public void exitAction(ActionEvent event) throws IOException {
        n.write("exit");
        Main.ns.close();
        Main.CloseProgram();
    }


    public void LogOutAction() throws IOException {
        n.write("logout");
        Main.StartPage();
    }
    public void GoToHome() throws IOException {
        n.write("home");
        Main.HomePage();
    }
}
