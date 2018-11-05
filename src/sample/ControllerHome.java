package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

/**
 * Created by Admin_8_1 on 12-May-16.
 */
public class ControllerHome {
    private Main main;
    ObservableList<String> Subject= FXCollections.
            observableArrayList("English","Bangla","General Knowledge");

    NetworkUtil NS=Main.ns;
    @FXML
    private GridPane GridBox;

    @FXML
    private ChoiceBox SubjectChoice;

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
    @FXML
    public void initialize(){
        SubjectChoice.setItems(Subject);
        SubjectChoice.setValue("English");
    }


    public void exitAction(ActionEvent event) throws IOException {
        Main.ns.close();

        Main.CloseProgram();
    }


    public void LogOutAction() throws IOException {
        NS=Main.ns;
        NS.write("logOut");
        Main.StartPage();
    }
    public void StartButton() throws IOException {
        NS=Main.ns;
        if(Practice.isSelected()){
            NS.write("Practice");

            System.out.println("practice selected");
            Main.PracticeQuestion();
        }
        if(OnlineTest.isSelected()){

             if(Easy.isSelected()){
                 NS.write("Easy");
                 Main.setNumOfQuestion(10);
                 Main.setExamMode("Easy");
             }
             else if(Medium.isSelected()){
                 NS.write("Medium");
                 Main.setNumOfQuestion(20);
                 Main.setExamMode("Medium");
             }
             else if(Hard.isSelected()){
                 NS.write("Hard");
                 Main.setNumOfQuestion(30);
                 Main.setExamMode("Hard");
             }
             else{
                 NS.write("Easy");
                 Easy.setSelected(true);
                 Main.setNumOfQuestion(10);
                 Main.setExamMode("Easy");
             }
            Main.ExamPage();
        }
        System.out.println("Started ");
    }
}
