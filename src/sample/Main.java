package sample;

import com.sun.org.apache.regexp.internal.REDebugCompiler;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.Socket;
import java.util.*;

public class Main extends Application {
    public Socket s;
    public static NetworkUtil ns;

    private static Stage stage;
    static Pane LogInPage,SignUpPage ,SignUpSuccessPage,LogInSuccessPage,HomePage,
            AfterExamPage;
    static Pane AfterPracticePage,QuestionAddPage,AfterQuestionAddPage,StatisticsPage;
    private static ControllerLogIn login;
    private static String Name,Subject,ExamMode;
    private static long  StartTime,EndTime;
    private static int numOfQuestion=10,CorrectAns=0;
    public static void setName(String name) {
        Name = name;
    }
    public static void setNumOfQuestion(int n) {numOfQuestion=n;}

    public static void setSubject(String subject) {
        Subject = subject;
    }

    public static long   getStartTime() {
        return StartTime;
    }

    public static long getEndTime() {
        return EndTime;
    }
    public static void setExamMode(String mode){
        ExamMode=mode;
    }

    public static String getName() {
        return Name;
    }

    public static int getNumOfQuestion() {
        return numOfQuestion;
    }

    public static int getCorrectAns() {
        return CorrectAns;
    }

    public static String getExamMode() {
        return ExamMode;
    }
    public static String mode;
    public static Scores score;
    public boolean refresh=false;
    public Scores getScore() {
        return score;
    }

    public void setScore(Scores score) {
        this.score = score;
    }

    @Override

    public  void start(Stage primaryStage) throws Exception{
        stage=primaryStage;
        StartPage();
         //ExamPage();
        //PracticeQuestion();
        //HomePage();
    }
    //page 1, contains login,sign up button
    public static void StartPage() throws IOException {
        ns= new NetworkUtil("127.0.0.1",11111);
        System.out.println("client has created");
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("FrontPage.fxml"));
        LogInPage=loader.load();
        Scene scene= new Scene(LogInPage);
        stage.setScene(scene);
        stage.show();

    }
    // page 2, contains sign up information
    public static void SignUpPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SignUpPage.fxml"));
        SignUpPage=loader.load();
        Scene scene= new Scene(SignUpPage);
        stage.setScene(scene);
        stage.show();

    }

    // page 3, if sign in is successful
    public static void SignUpSuccess() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("SignUpSuccessful.fxml"));
        SignUpSuccessPage=loader.load();
        Scene scene= new Scene(SignUpSuccessPage);
        stage.setScene(scene);
        stage.show();

    }

    public static void LogInSuccess() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("LogInSuccessful.fxml"));
        LogInSuccessPage=loader.load();
        Scene scene= new Scene(LogInSuccessPage);
        stage.setScene(scene);
        stage.show();

    }
    //switches to the home page
    public static void HomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("HomePage.fxml"));
        HomePage=loader.load();
        Scene scene= new Scene(HomePage);

        stage.setScene(scene);

        stage.show();
    }
    // practice page
    public static void PracticeQuestion() throws IOException {
        ExamMode="Practice";
        Questionspackage q=new Questionspackage();
        q=(Questionspackage) ns.read();
        Date d= new Date();
        StartTime=d.getTime();
        System.out.println("s "+StartTime);
        ArrayList<ToggleGroup> toggle= new ArrayList<>();
        ArrayList<Label> QuestionsLabel= new ArrayList<>();
        ArrayList <String>RightAnswers= new ArrayList<>();
        ArrayList<RadioButton> OptionsLabel= new ArrayList<>();
        ArrayList <RadioButton>QuesAnswered= new ArrayList<>();
        ArrayList<String> QuestionString= new ArrayList<>();
        ArrayList<String> OptionString= new ArrayList<>();

        QuestionString=q.QuestionString;
        OptionString=q.OptionString;
        RightAnswers=q.RightAnswers;
        ArrayList<String> finalQuestionString = QuestionString;
        ArrayList<String> finalOptionString = OptionString;
        ArrayList<String> finalRightAnswers1 = RightAnswers;
        Vector<Integer> Clicked=new Vector<>();
        /*
        Scanner Q = null,A=null;

        numOfQuestion=30;
        try{
            Q = new Scanner(new File("Question.txt"));
            A = new Scanner(new File("Answer.txt"));
        }catch(Exception e) {
            System.out.println("Error");
        }
        while(Q.hasNextLine()){
            QuestionString.add(Q.nextLine());
        }
        int i=1;
        while(A.hasNextLine()){
            if(i%5==0){
                String s= A.nextLine();
                RightAnswers.add(s);


            }
            else{
                String s= A.nextLine();
                OptionString.add(s);

            }
            i++;
        }

        */
       // final int[] answered = {0};
        numOfQuestion=QuestionString.size();
        //in the uper portion of question page
        VBox Box = new VBox(10);
        VBox ques = new VBox(10);
        final ScrollPane sp = new ScrollPane();
        HBox QuestionNumber=new HBox(180);
        final boolean[] back = {false};
        Label Number= new Label("Number of Questions : "+QuestionString.size());
        Number.setFont(Font.font("Bodoni MT",25));
        Label answeredQuestion= new Label("Subject: GRE");
        Number.setFont(Font.font("Bodoni MT",20));
        answeredQuestion.setFont(Font.font("Bodoni MT",20));
        QuestionNumber.getChildren().addAll(Number,answeredQuestion);
        QuestionNumber.setAlignment(Pos.BASELINE_LEFT);
        ques.setPadding(new Insets(10));
        Scene scene= new Scene(Box,640,480);
        stage.setScene(scene);
        HBox up=new HBox(70);
        Label practice=new Label("Mode:Practice");
        practice.setFont(Font.font("Bodoni MT",25));
        Label name=new Label("Name: "+Name);
        Button Refresh=new Button("Refresh");

       // Label subject= new Label("Subject: ");
        name.setFont(Font.font("Bodoni MT",25));
        //subject.setFont(Font.font("Bodoni MT",25));
        up.getChildren().addAll(practice,name,Refresh);
        HBox forButtons = new HBox(450);
        Button Back= new Button("BACK");
        Button Show= new Button("Show Answer");
        Button  Finish = new Button("Finish");
        forButtons.getChildren().addAll(Show);
        Refresh.setDisable(true);

        //ques.getChildren().add(up);
        // ques.getChildren().addAll(QuestionNumber);
        Box.setPadding(new Insets(10));
        Box.getChildren().addAll(up,QuestionNumber,sp,forButtons);
        VBox.setVgrow(sp, Priority.ALWAYS);
       // Box.getChildren().addAll();
        //here comes the question part


        for(int j=0;j<QuestionString.size();j++){
            Label QuesLine= new Label();
            QuesLine.setText((j+1)+"."+QuestionString.get(j));
            QuestionsLabel.add(QuesLine);
        }
        for(int j=0;j<QuestionsLabel.size();j++){
            ToggleGroup mygroup = new ToggleGroup();
            toggle.add(mygroup);
        }
        System.out.println(OptionString.size());
        for(int j=0;j<OptionString.size();j++){

            RadioButton option= new RadioButton();
            option.setText(OptionString.get(j));
            option.setToggleGroup(toggle.get(j/4));
            OptionsLabel.add(option);
            Clicked.add(j,0);
        }
        for(int j=0;j<RightAnswers.size();j++){
            String answerString= RightAnswers.get(j);
            int answer= answerString.charAt(0)-'1';
            if(answer==0){
                OptionsLabel.get(j*4+answer).setTextFill(Color.BLUE);
                OptionsLabel.get(j*4+answer+1).setTextFill(Color.BLUE);
            }
            else{
                OptionsLabel.get(j*4+answer).setTextFill(Color.BLUE);
                OptionsLabel.get(j*4+answer-1).setTextFill(Color.BLUE);
            }
        }
        for(int j=0,k=0;j<QuestionsLabel.size();j++,k+=4){
            ques.getChildren().addAll(QuestionsLabel.get(j));
            ques.getChildren().addAll(OptionsLabel.get(k));
            ques.getChildren().addAll(OptionsLabel.get(k+1));
            ques.getChildren().addAll(OptionsLabel.get(k+2));
            ques.getChildren().addAll(OptionsLabel.get(k+3));
        }

        sp.setVmax(440);
        sp.setPrefSize(440, 150);
        sp.setContent(ques);
       // Label question;
        int []answeredprev= new int[QuestionString.size()];
        int[] answered = new int[QuestionString.size()];
        for(int j=0;j<answered.length;j++){
            answered[j]=0;
        }
        for(int j=0;j<OptionsLabel.size();j++){
            final int jj=j;
            System.out.println("option selection" +jj);
            OptionsLabel.get(jj).setOnAction(e->{
                System.out.println("option selected" +jj);
                if(OptionsLabel.get(jj).isSelected() && answeredprev[jj/4]==0){
                    Clicked.remove(jj);
                    Clicked.add(jj,1);
                    answered[0]++;

                    answeredprev[jj/4]=1;

                }
                if(!OptionsLabel.get(jj).isSelected()){
                    Clicked.remove(jj);
                    Clicked.add(jj,0);
                    answered[0]--;
                }

                //answeredQuestion.setText("Answered Questions : "+String.valueOf(answered[0]));
            });
        }



        //ques.getChildren().addAll(forButtons);


        Back.setOnAction(e->{
            ns.write("back");
            back[0] =true;
            try {
                Main.HomePage();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        ArrayList<String> finalRightAnswers;
        if(finalRightAnswers1.size()==RightAnswers.size()){
            finalRightAnswers = RightAnswers;
        }
        else{
            finalRightAnswers = finalRightAnswers1;
        }
        Show.setOnAction(e->{
            ns.write("show");
            Date d2= new Date();
            EndTime=d2.getTime();
            System.out.println("e "+EndTime);
            for(int j = 0; j< finalRightAnswers.size(); j++){
                String answerString= finalRightAnswers.get(j);
                int answer= answerString.charAt(0)-'1';
                OptionsLabel.get(j*4+answer).setTextFill(Color.GREEN);
                for(int k=0;k<4;k++){
                    if(k!=answer){
                        if(OptionsLabel.get(j*4+k).isSelected()){
                            OptionsLabel.get(j*4+k).setTextFill(Color.RED);
                        }
                    }
                }
            }
            //answeredQuestion.setText("Correct Answer: "+CorrectAns);
            forButtons.getChildren().remove(Show);
            forButtons.setSpacing(500);
            forButtons.getChildren().addAll(Finish);
        });
        CheckNotification c=new CheckNotification(Refresh,ns,back[0]);
        System.out.println("refresh "+Refresh.isDisabled());
        Refresh.setOnAction(e->{

            System.out.println("yee refresh");
            OneQuestion one=new OneQuestion();
            one=(OneQuestion) c.returnQuestion();
            for(int j=-0;j<Clicked.size();j++){
                System.out.println("clicked"+Clicked.get(j));
            }
            // ns.write("refresh");
            // Questionspackage qr= new Questionspackage();
            // qr=(Questionspackage)ns.read();
            /// System.out.println(qr.QuestionString);

            // System.out.println("client socket "+ns.s.getLocalSocketAddress());
            // one=(OneQuestion) ns.read();
            System.out.println(one.mode+" "+one.ques+" "+one.op1+" "+one.op2+" "+one.op3+" "+one.op4+" "+ExamMode);
            if(one.mode.equals(ExamMode)){
                int n=QuestionsLabel.size();
                int s=OptionsLabel.size();
                System.out.println("qouetion size"+n+" option Size"+s);
                ToggleGroup t= new ToggleGroup();
                RadioButton o1=new RadioButton(one.op1);
                RadioButton o2=new RadioButton(one.op2);
                RadioButton o3=new RadioButton(one.op3);
                RadioButton o4=new RadioButton(one.op4);
                o1.setToggleGroup(t);
                o2.setToggleGroup(t);
                o3.setToggleGroup(t);
                o4.setToggleGroup(t);
                toggle.add(t);
                QuestionsLabel.add(new Label(n+"."+one.ques));
                OptionsLabel.add(o1);
                OptionsLabel.add(o2);
                OptionsLabel.add(o3);
                OptionsLabel.add(o4);
                finalRightAnswers1.add(one.ans);
                finalQuestionString.add(one.ques);
                finalOptionString.add(one.op1);
                finalOptionString.add(one.op2);
                finalOptionString.add(one.op3);
                finalOptionString.add(one.op4);
                ques.getChildren().addAll(QuestionsLabel.get(n),OptionsLabel.get(s),OptionsLabel.get(s+1),OptionsLabel.get(s+2),OptionsLabel.get(s+3));
                sp.setContent(ques);
                Number.setText("Number of Questions : "+finalQuestionString.size());

                //answeredQuestion.setText("Answered Questions : "+String.valueOf(answered[0]));
            }

            Refresh.setDisable(true);
        });
        Finish.setOnAction(e->{
            ns.write("finish");
            try {
                afterPractice();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        stage.show();
    }

    // closes the practice program
    public static void afterPractice() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AfterPractice.fxml"));

        AfterPracticePage=loader.load();
        Scene scene= new Scene(AfterPracticePage);
        stage.setScene(scene);
        stage.show();
    }

    //exam question easy medium hard
    public  static void ExamPage(){
        Questionspackage q= new Questionspackage();
        q= (Questionspackage) ns.read();
        Date d= new Date();
        StartTime=d.getTime();
        System.out.println("s "+StartTime);
        ArrayList<ToggleGroup> toggle= new ArrayList<>();
        ArrayList<Label> QuestionsLabel= new ArrayList<>();
        ArrayList <String>RightAnswers= new ArrayList<>();
        ArrayList<RadioButton> OptionsLabel= new ArrayList<>();
        ArrayList <RadioButton>QuesAnswered= new ArrayList<>();
        ArrayList<String> QuestionString= new ArrayList<>();
        ArrayList<String> OptionString= new ArrayList<>();

        QuestionString=q.QuestionString;
        RightAnswers=q.RightAnswers;
        OptionString=q.OptionString;
        ArrayList<String> finalQuestionString = QuestionString;
        ArrayList<String> finalOptionString = OptionString;
        ArrayList<String> finalRightAnswers1 = RightAnswers;
        Vector<Integer> Clicked= new Vector<>();


        /*
        Scanner Q = null,A=null;
        final int[] answered = {0};

        try{
            Q = new Scanner(new File(ExamMode+"Question.txt"));
            A = new Scanner(new File(ExamMode+"Answer.txt"));
        }catch(Exception e) {
            System.out.println("Error");
        }
        while(Q.hasNextLine()){
            QuestionString.add(Q.nextLine());
        }
        int i=1;
        while(A.hasNextLine()){
            if(i%5==0){
                String s= A.nextLine();
                RightAnswers.add(s);


            }
            else{
                String s= A.nextLine();
                OptionString.add(s);

            }
            i++;
        }
        */
        //in the uper portion of question page
        numOfQuestion=QuestionString.size();
        VBox Box = new VBox(10);
        VBox ques = new VBox(10);
        final ScrollPane sp = new ScrollPane();
        HBox QuestionNumber=new HBox(180);

        Label Number= new Label("Number of Questions : "+QuestionString.size());
        Number.setFont(Font.font("Bodoni MT",25));
        Label answeredQuestion= new Label("Subject: GRE");
        Number.setFont(Font.font("Bodoni MT",20));
        answeredQuestion.setFont(Font.font("Bodoni MT",20));
        QuestionNumber.getChildren().addAll(Number,answeredQuestion);
        QuestionNumber.setAlignment(Pos.BASELINE_LEFT);
        ques.setPadding(new Insets(10));
        Scene scene= new Scene(Box,640,480);
        stage.setScene(scene);
        HBox up=new HBox(70);
        Label practice=new Label("Mode: "+ExamMode);
        practice.setFont(Font.font("Bodoni MT",25));
        Label name=new Label("Name:"+Name);
        Button Refresh=new Button("Refresh");
        //Label subject= new Label("Subject: "+Subject);
        name.setFont(Font.font("Bodoni MT",25));
       // subject.setFont(Font.font("Bodoni MT",25));
        up.getChildren().addAll(practice,name,Refresh);
        HBox forButtons = new HBox(450);
        Button Back= new Button("BACK");
        Button Show= new Button("Show Answer");
        Button  Finish = new Button("Finish");
        forButtons.getChildren().addAll(Show);

        Refresh.setDisable(true);
        //ques.getChildren().add(up);
        // ques.getChildren().addAll(QuestionNumber);
        Box.setPadding(new Insets(10));
        Box.getChildren().addAll(up,QuestionNumber,sp,forButtons);
        VBox.setVgrow(sp, Priority.ALWAYS);

        //here comes the question part


        for(int j=0;j<QuestionString.size();j++){
            Label QuesLine= new Label();
            QuesLine.setText((j+1)+"."+QuestionString.get(j));

            QuestionsLabel.add(QuesLine);
        }
        for(int j=0;j<QuestionsLabel.size();j++){
            ToggleGroup mygroup = new ToggleGroup();
            toggle.add(mygroup);
        }
        System.out.println(OptionString.size());
        for(int j=0;j<OptionString.size();j++){

            RadioButton option= new RadioButton();
            option.setText(OptionString.get(j));
            System.out.println(option.getText());
            //System.out.println("j "+j);
            System.out.println(j/4);
            option.setToggleGroup(toggle.get(j/4));
            OptionsLabel.add(option);
            Clicked.add(j,0);
        }

        for(int j=0,k=0;j<QuestionsLabel.size();j++,k+=4){
            ques.getChildren().addAll(QuestionsLabel.get(j));
            ques.getChildren().addAll(OptionsLabel.get(k));
            ques.getChildren().addAll(OptionsLabel.get(k+1));
            ques.getChildren().addAll(OptionsLabel.get(k+2));
            ques.getChildren().addAll(OptionsLabel.get(k+3));
        }

        sp.setVmax(440);
        sp.setPrefSize(440, 150);
        sp.setContent(ques);
        int []answeredprev= new int[10000];
        int[] answered = new int[QuestionString.size()];
        for(int j=0;j<answered.length;j++){
            answered[j]=0;
        }
        for(int j=0;j<OptionsLabel.size();j++){
            final int jj=j;
            System.out.println("option selection" +jj);
            OptionsLabel.get(jj).setOnAction(e->{
                System.out.println("option selected" +jj);
                if(OptionsLabel.get(jj).isSelected() && answeredprev[jj/4]==0){
                    answered[0]++;
                    Clicked.remove(jj);
                    Clicked.add(jj,1);
                    answeredprev[jj/4]=1;

                }
                if(!OptionsLabel.get(jj).isSelected()){
                    answered[0]--;
                    Clicked.remove(jj);
                    Clicked.add(jj,0);
                }

                //answeredQuestion.setText("Answered Questions : "+String.valueOf(answered[0]));
            });
        }


        final boolean[] back = {false};

        Back.setOnAction(e->{
            ns.write("back");
            back[0] =true;
            try {
                Main.HomePage();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        ArrayList<String> finalRightAnswers;
        if(finalRightAnswers1.size()==RightAnswers.size()){
           finalRightAnswers = RightAnswers;
        }
        else{
            finalRightAnswers = finalRightAnswers1;
        }
        Show.setOnAction(e->{
            ns.write("show");
            back[0]=true;
            Date d2= new Date();
            EndTime=d2.getTime();
            System.out.println("e "+EndTime);
            for(int j = 0; j< finalRightAnswers.size(); j++){
                String answerString= finalRightAnswers.get(j);
                int answer= answerString.charAt(0)-'0'-1;
                System.out.println(answer);
                OptionsLabel.get(j*4+answer).setTextFill(Color.GREEN);
                for(int k=0;k<4;k++){
                    if(k!=answer){
                        if(OptionsLabel.get(j*4+k).isSelected()){
                            OptionsLabel.get(j*4+k).setTextFill(Color.RED);
                        }
                    }
                    else if (OptionsLabel.get(j*4+k).isSelected()){
                        CorrectAns++;
                    }
                }
            }
            answeredQuestion.setText("Correct Answer: "+CorrectAns);
            forButtons.getChildren().remove(Show);
            forButtons.setSpacing(500);
            Back.setDisable(true);
            forButtons.getChildren().addAll(Finish);

        });
        CheckNotification c=new CheckNotification(Refresh,ns,back[0]);
        System.out.println("refresh "+Refresh.isDisabled());
        Refresh.setOnAction(e->{
            System.out.println("yee refresh");
            OneQuestion one=new OneQuestion();
            one=(OneQuestion) c.returnQuestion();
            for(int j=-0;j<Clicked.size();j++){
                System.out.println("clicked"+Clicked.get(j));
            }
           // ns.write("refresh");
           // Questionspackage qr= new Questionspackage();
           // qr=(Questionspackage)ns.read();
           /// System.out.println(qr.QuestionString);

           // System.out.println("client socket "+ns.s.getLocalSocketAddress());
           // one=(OneQuestion) ns.read();
            System.out.println(one.mode+" "+one.ques+" "+one.op1+" "+one.op2+" "+one.op3+" "+one.op4+" "+ExamMode);
            if(one.mode.equals(ExamMode)){
                int n=QuestionsLabel.size();
                int s=OptionsLabel.size();
                System.out.println("qouetion size"+n+" option Size"+s);
                ToggleGroup t= new ToggleGroup();
                RadioButton o1=new RadioButton(one.op1);
                RadioButton o2=new RadioButton(one.op2);
                RadioButton o3=new RadioButton(one.op3);
                RadioButton o4=new RadioButton(one.op4);
                o1.setToggleGroup(t);
                o2.setToggleGroup(t);
                o3.setToggleGroup(t);
                o4.setToggleGroup(t);
                toggle.add(t);
                QuestionsLabel.add(new Label(n+"."+one.ques));
                OptionsLabel.add(o1);
                OptionsLabel.add(o2);
                OptionsLabel.add(o3);
                OptionsLabel.add(o4);
                finalRightAnswers1.add(one.ans);
                finalQuestionString.add(one.ques);
                finalOptionString.add(one.op1);
                finalOptionString.add(one.op2);
                finalOptionString.add(one.op3);
                finalOptionString.add(one.op4);
                ques.getChildren().addAll(QuestionsLabel.get(n),OptionsLabel.get(s),OptionsLabel.get(s+1),OptionsLabel.get(s+2),OptionsLabel.get(s+3));
                sp.setContent(ques);
                Number.setText("Number of Questions : "+finalQuestionString.size());


                //answeredQuestion.setText("Answered Questions : "+String.valueOf(answered[0]));
            }

            Refresh.setDisable(true);
        });
        Finish.setOnAction(e->{
            ns.write("finish");
            try {
                AfterExam();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        stage.show();


    }
    //after exam option
    public static void AfterExam() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AfterExam.fxml"));
        AfterExamPage=loader.load();
        Scene scene= new Scene(AfterExamPage);
        stage.setScene(scene);
        stage.show();

    }
    //Question adding page
    public static void QuestionAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("QuestionAdd.fxml"));
        QuestionAddPage=loader.load();
        Scene scene= new Scene(QuestionAddPage);
        stage.setScene(scene);
        stage.show();

    }
    public static void AfterQuestionAdd() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("AfterAddition.fxml"));
        AfterQuestionAddPage=loader.load();
        Scene scene= new Scene(AfterQuestionAddPage);
        stage.setScene(scene);
        stage.show();

    }
    public static void Statistics() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("ShowStatistics.fxml"));
        StatisticsPage=loader.load();
        Scene scene= new Scene(StatisticsPage);
        stage.setScene(scene);
        stage.show();

    }

    public static void CloseProgram() throws IOException {
        ns.close();
        stage.close();
    }


    public static void main(String[] args) throws IOException {

        launch(args);

    }
}
