package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.*;

/**
 * Created by Admin_8_1 on 19-May-16.
 */
public class AfterExamControl {
    public  Scores s=new Scores();
    private  NetworkUtil NS=Main.ns;
    private String Score1="0";
    private String Score2="0";
    private  String Score3="0";
    private int Scorefile1=0;
    private int Scorefile2=0;
    private  int Scorefile3=0;
    private int ans;
    private String mode;
    public Scores score;
    private String examMode;
    private String user;

    public Scores getS() {
        return s;
    }

    @FXML
    Label UserName;
    @FXML
    Label Mode;
    @FXML
    Label Time;
    @FXML
    Label NumberOfQuestions;
    @FXML
    Label CorrectAnswers;
    @FXML
    Button SubmitButton;

    public void initialize(){
        mode=Main.getExamMode();
        ans=Main.getCorrectAns();
        UserName.setText("Name: "+Main.getName());
        long time= (Main.getEndTime()-Main.getStartTime())/1000;
        Time.setText("Time Taken: "+String.valueOf(time)+" s");
        Mode.setText("Exam Mode: "+Main.getExamMode());
        NumberOfQuestions.setText("Number Of Questions: "+Main.getNumOfQuestion());
        CorrectAnswers.setText("Correct Answers: "+Main.getCorrectAns());
    }
    public void exitAction(ActionEvent event) throws IOException {
        Main.ns.close();
        Main.CloseProgram();
    }

    public void submitAction() throws IOException {
        user=Main.getName();

        NS=Main.ns;
        NS.write(ans);
        System.out.println("exam sesh"+ans);
        new Submit(s,NS);
        Main.Statistics();

       // Main.score=s;
       // NS.write(s);
        /*


        try {
            BufferedReader fr= new BufferedReader(new FileReader(user+"score.txt"));
            Score1=fr.readLine();
            Score2=fr.readLine();
            Score3=fr.readLine();
            Scorefile1=Integer.parseInt(Score1);
            Scorefile2=Integer.parseInt(Score2);
            Scorefile3=Integer.parseInt(Score3);
            if(mode.equals("Easy")){
                if(Scorefile1< ans){
                    Scorefile1=ans;
                }
            }
            else if(mode.equals("Medium")){
                if(Scorefile2< ans){
                    Scorefile2=ans;
                }
            }
            else if(mode.equals("Hard")){
                if(Scorefile3< ans){
                    Scorefile3=ans;
                }
            }
            PrintWriter bw=new PrintWriter(new File(user+"score.txt"));
            bw.println(Scorefile1);
            bw.println(Scorefile2);
            bw.println(Scorefile3);
            bw.close();

        } catch (FileNotFoundException e) {
            File file= new File(user+"score.txt");
            PrintWriter bw=new PrintWriter(file);
            if(mode.equals("Easy")){
                Scorefile1=ans;
            }
            else if(mode.equals("Medium")){
                if(Scorefile2< ans){
                    Scorefile2=ans;
                }
            }
            else if(mode.equals("Hard")){
                if(Scorefile3< ans){
                    Scorefile3=ans;
                }
            }
            System.out.println(Scorefile1+" "+Scorefile2+" "+Scorefile3);
            bw.println(Scorefile1);
            bw.println(Scorefile2);
            bw.println(Scorefile3);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
    public void LogOutAction() throws IOException {
        Main.StartPage();
    }
    public void GoToHome() throws IOException {
        NS.write("home");
        Main.HomePage();
    }
}
