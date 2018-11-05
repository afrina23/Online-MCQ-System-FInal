package sample;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Admin_8_1 on 05-Jun-16.
 */
public class Questionspackage implements Serializable {


    ArrayList <String>RightAnswers= new ArrayList<>();
    ArrayList<String> QuestionString= new ArrayList<>();
    ArrayList<String> OptionString= new ArrayList<>();
    Questionspackage(ArrayList <String>Question,ArrayList <String>Right, ArrayList <String>Options){
        QuestionString=Question;
        RightAnswers=Right;
        OptionString=Options;
    }
    Questionspackage(){

    }

}
