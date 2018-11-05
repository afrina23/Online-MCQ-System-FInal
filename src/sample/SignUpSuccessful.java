package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.io.IOException;

/**
 * Created by Admin_8_1 on 01-Jun-16.
 */
public class SignUpSuccessful {
    @FXML
    private RadioButton teacher;

    @FXML
    private RadioButton student;
    NetworkUtil NS;
    public void EnterAction() throws IOException {
        NS=Main.ns;
        System.out.println("mode "+Main.mode);
        NS.write(Main.mode);
        System.out.println(Main.mode+" is selected");
        if(student.isSelected() && Main.mode.equals("Student")){


            Main.HomePage();
        }

        else if(teacher.isSelected() && Main.mode.equals("Teacher")){
            Main.QuestionAdd();
        }

    }
    public void LogOutAction() throws IOException {
        NS=Main.ns;
        NS.write("logOut");
        NS.close();
        NS.s.close();
        Main.StartPage();

    }

    //action of back button

    //action of exit button
    public void exitAction(ActionEvent event) throws IOException {
        NS= Main.ns;
        NS.write("exit");
        Main.ns.close();
        Main.CloseProgram();
    }
}
