package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Admin_8_1 on 08-May-16.
 */
public class ControllerSignUp implements Initializable{
    private Main main;
    public NetworkUtil NS=Main.ns;
    private String name;
    private String email;
    private String password;
    private String mode;
    ObservableList<String> Mode= FXCollections.
            observableArrayList("Student","Teacher");

    @FXML
    private TextField UserName;
    @FXML
    private TextField Email;

    @FXML
    private PasswordField PassWord;

    @FXML
    private PasswordField Confirm;



    @FXML
    private Label WelcomeUser;

    @FXML
    private ChoiceBox ModeChoice;
    public void initialize(URL location, ResourceBundle resources) {

        ModeChoice.setItems(Mode);
        ModeChoice.setValue("Student");

    }

    public String getName() {
        return name;
    }

    //action of sign up button
    public void SignAction(ActionEvent event ) throws IOException {
        NS=Main.ns;
        name=UserName.getText();
        email= Email.getText();
        password=PassWord.getText();

        mode=ModeChoice.getValue().toString();

        Main.mode=mode;
        String confirm=Confirm.getText();
        System.out.println("n "+name+" e "+email+" p "+password);
        Information information= new Information(name,email,password);
        information.mode=mode;
        if(!password.equals(confirm)){
            System.out.println("ERROR");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("password doesn't match");
            alert.setHeaderText("Sign Up failed");
            alert.setContentText("Confirm your Password");
            alert.showAndWait();
            UserName.setText(null);
            Email.setText(null);
            PassWord.setText(null);
            Confirm.setText(null);
        }
        else{
            // creating new file for information
            NS=Main.ns;
            System.out.println("writing "+information.Name);
            NS.write(information);
            System.out.println("written "+information.Name);
            String check=NS.read().toString();
            if(check.equals("0")){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username already exists");
                alert.setHeaderText("Sign In failed");
                alert.setContentText("The username you provided already exists.");
                alert.showAndWait();
                UserName.setText(null);
                Email.setText(null);
                PassWord.setText(null);
                Confirm.setText(null);
            }
            else{
                Main.setName(name);
                WelcomeUser=new Label("Welcome "+name);
                NS.write(Main.mode);
                Main.SignUpSuccess();
            }
            // checks if same named account exists
            /*try{
                BufferedReader fr= new BufferedReader(new FileReader(name+".txt"));
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Username already exists");
                alert.setHeaderText("Sign In failed");
                alert.setContentText("The username you provided already exists.");
                alert.showAndWait();
                UserName.setText(null);
                Email.setText(null);
                PassWord.setText(null);
                Confirm.setText(null);

            }

           catch(FileNotFoundException e){
                File fw= new File(name+".txt");
                PrintWriter bw=new PrintWriter(fw);
                bw.println(name);
                bw.println(email);
                bw.println(password);
                bw.println(mode);
                bw.close();
                Main.setName(name);
                WelcomeUser=new Label("Welcome "+name);
                bw.close();
                Main.SignUpSuccess();
            }*/

        }


    }
    //action of enter button


    //action of logout option
    public void LogOutAction() throws IOException {
        Main.StartPage();
    }


    //action of back button
    public void BackAction() throws IOException {
        try {
          // NS.write(new Information());
            NS.close();
            Main.ns.close();
            //NS.s.close();
            Main.StartPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //action of exit button
    public void exitAction(ActionEvent event) throws IOException {
        Main.ns.close();
        Main.CloseProgram();
    }




}
