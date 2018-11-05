package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ControllerLogIn {

      private Main main;

      public  static NetworkUtil NS=Main.ns;
      String mode;
      private static String Name="User";
      @FXML
      private TextField LogInName;

      @FXML
      private TextField LogInEmail;

      @FXML
      private PasswordField LogInPassword;

      @FXML
      private Label WelcomeName;
      @FXML
      private RadioButton teacher;

      @FXML
      private RadioButton student;

      public  String getName() {
            return Name;
      }
      public String getMode(){return mode;}
      //go to sign up page
      public void SignAction(ActionEvent event) throws IOException {
            NS=Main.ns;
            System.out.println("Signing in");
            NS.write("Sign");
          main.SignUpPage();
          System.out.println("Noting");
      }

      public void LogInAction(ActionEvent event) throws IOException {
            NS=Main.ns;
            NS.write("Log");
            String name=LogInName.getText();
            String email=LogInEmail.getText();
            String password= LogInPassword.getText();
            System.out.println(name+" "+email+" "+password);
            Information information=new Information(name,email,password);

            NS.write(information);
            String s=NS.read().toString();

            if(s.equals("Teacher") || s.equals("Student")){
                  System.out.println("Log in Successful"+mode);
                  Main.mode=s;
                  information.mode=s;
                  Main.setName(name);
                  NS.write(Main.mode);
                  //Welcome.setText("Welcome "+name);
                  // WelcomeUser.setText("Afrina ");

                  main.LogInSuccess();
            }
            else if(s.equals("0")){
                  reset();
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Username does not exist");
                  alert.setHeaderText("Log In failed");
                  alert.setContentText("The username you provided does not  exist.");
                  alert.showAndWait();
                  System.out.println("No account in this name ");
            }
            else if(s.equals("2")){
                  reset();
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Wrong information");
                  alert.setHeaderText("Log In failed");
                  alert.setContentText("The information you provided is wrong.");
                  alert.showAndWait();
                  System.out.println("No account in this name ");
            }
            /*
            try {
                  // reading from file
                  BufferedReader fr= new BufferedReader(new FileReader(name+".txt"));
                  String UserName=fr.readLine();
                  String UserEmail=fr.readLine();
                  String UserPassword=fr.readLine();
                  mode=fr.readLine();
                  Main.mode=mode;
                  fr.close();
                  if(name.equals(UserName) &&  email.equals(UserEmail) &&password.equals(UserPassword)){
                        System.out.println("Log in Successful"+mode);
                        Main.setName(name);
                        //Welcome.setText("Welcome "+name);
                       // WelcomeUser.setText("Afrina ");

                        main.LogInSuccess();
                  }
                  else{
                        reset();
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Wrong information");
                        alert.setHeaderText("Log In failed");
                        alert.setContentText("The information you provided is wrong.");
                        alert.showAndWait();
                        System.out.println("No account in this name ");
                  }
            } catch (FileNotFoundException e) {
                  reset();
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                  alert.setTitle("Username does not exist");
                  alert.setHeaderText("Log In failed");
                  alert.setContentText("The username you provided does not  exist.");
                  alert.showAndWait();
                  System.out.println("No account in this name ");
            }*/
      }
      public void EnterAction() throws IOException {
            System.out.println("mode "+mode);
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
            NS.write("logOut");
            NS.close();
            NS.s.close();
            Main.StartPage();

      }
      public void reset() {
            LogInName.setText(null);
            LogInEmail.setText(null);
            LogInPassword.setText(null);
      }
      public void exitAction(ActionEvent event) throws IOException {
            NS.write("exit");
            Main.ns.close();
            Main.CloseProgram();
      }

}
