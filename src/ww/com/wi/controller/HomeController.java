package ww.com.wi.controller;


import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class HomeController {
    @FXML
    private ImageView img_SubCommity;
    @FXML
    private ImageView img_CustomerGroup;
    @FXML
    private ImageView img_Customer;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblTime;


    public void initialize(){
        initClock();

    }

    private int minute;
    private int hour;
    private int second;


    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/ww/com/wi/view/login.fxml"));
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();

    }


    private void initClock()  {

        Thread clock = new Thread(){
            public void run(){
                try {
                    for (;;){
                        Calendar calendar = new GregorianCalendar();

                        int month = calendar.get(Calendar.MONTH) + 1;
                        int day = calendar.get(Calendar.DAY_OF_MONTH);
                        int year = calendar.get(Calendar.YEAR);

                        int hour = calendar.get(Calendar.HOUR);
                        int min = calendar.get(Calendar.MINUTE);
                        int sec = calendar.get(Calendar.SECOND);

                        String am_pm;

                        if (calendar.get(Calendar.AM_PM) == 0) {
                            am_pm = "AM";
                        } else {
                            am_pm = "PM";
                            hour = hour + 12;
                        }

                        lblTime.setText(month + "-" + day + "-" + year + " - " + hour + ":" + min + "" +  " " + am_pm );

                        sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        clock.start();
    }

    @FXML
    private void img_CustomerGroup_OnAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/ww/com/wi/view/CustomerGroup.fxml"));
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    @FXML
    private void img_Customer_OnClicked(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/ww/com/wi/view/Customer.fxml"));
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    @FXML
    private void img_SubCommity_OnAction(MouseEvent mouseEvent) throws IOException {

        Parent root = FXMLLoader.load(this.getClass().getResource("/ww/com/wi/view/SubCommity.fxml"));
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnBack.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }
}
