package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Admin_8_1 on 02-Jun-16.
 */
public class StatisticsControl implements Initializable {
    private String Scorefile1;
    private String Scorefile2;
    private  String Scorefile3;
    private String Name;
    private AfterExamControl a=new AfterExamControl();
    @FXML
    private Label Easy;
    @FXML
    private Label Medium;
    @FXML
    private Label Hard;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Name=Main.getName();
        Scores s1=new Scores();
/*
        s1=a.getS();
        Easy.setText(String.valueOf(s1.scoreEasy));
        Medium.setText(String.valueOf(s1.scoreMedium));
        Hard.setText(String.valueOf(s1.scoreHard));
        /*

*/
        BufferedReader fr= null;
        try {
            fr = new BufferedReader(new FileReader(Name+"score.txt"));
            Scorefile1=(fr.readLine());
            Scorefile2=(fr.readLine());
            Scorefile3=(fr.readLine());
            Easy.setText(Scorefile1);
            Medium.setText(Scorefile2);
            Hard.setText(Scorefile3);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void LogOutAction() throws IOException {
        Main.StartPage();
    }
    public void GoToHome() throws IOException {
        Main.HomePage();
    }
}
