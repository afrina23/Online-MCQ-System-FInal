package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Admin_8_1 on 23-May-16.
 */
public class QuestionAddController {
    NetworkUtil NS= Main.ns;
    @FXML
    private TextArea question;
    @FXML
    private TextField op1;
    @FXML
    private TextField op2;

    @FXML
    private TextField op3;

    @FXML
    private TextField op4;
    @FXML
    private TextField ans;

    @FXML
    private RadioButton Practice;
    @FXML
    private RadioButton OnlineTest;

    @FXML
    private  RadioButton Easy;

    @FXML
    private  RadioButton Medium;

    @FXML
    private  RadioButton Hard;

    public  void Add() throws IOException {
        NS.write("add");
        boolean select=false;
        String ques= question.getText();
        String Option1=op1.getText();
        String Option2=op2.getText();
        String Option3=op3.getText();
        String Option4=op4.getText();
        String Answer=ans.getText();
        OneQuestion one=new OneQuestion(ques,Option1,Option2,Option3,Option4,Answer);
        File fQ=null,fA=null;
        if(Practice.isSelected()){
            select=true;
            one.setMode("Practice");
            //fQ= new File("Question.txt");
            //fA= new File("Answer.txt");

        }
        else if(OnlineTest.isSelected()){

            if(Easy.isSelected()){
                one.setMode("Easy");
                select=true;
                //fQ= new File("EasyQuestion.txt");
                //fA= new File("EasyAnswer.txt");

            }
            else if(Medium.isSelected()){
                select=true;
                one.setMode("Medium");
                //fQ= new File("MediumQuestion.txt");
               // fA= new File("MediumAnswer.txt");
            }
            else if(Hard.isSelected()){
                select=true;
                one.setMode("Hard");
                //fQ= new File("HardQuestion.txt");
               // fA= new File("HardAnswer.txt");
            }


        }
        if(select){
            NS.write(one);
            /*
            FileWriter fwQ= new FileWriter(fQ,true);
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
            */
            Main.AfterQuestionAdd();
        }

    }
    public void AddAgain() throws IOException {
       // NS.write("addAgain");
        Main.QuestionAdd();
    }
    public void goHome() throws IOException {
        //NS.write("home");
        Main.StartPage();
    }

}
