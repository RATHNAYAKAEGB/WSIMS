package ww.com.wi.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.util.Duration;
import ww.com.wi.business.ManageUserBo;
import ww.com.wi.entity.Users;
import ww.com.wi.main.AppInitializer;

import java.io.IOException;

public class LoginController {
    @FXML
    private JFXTextField txtUserName;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;


    private ManageUserBo manageUserBo = AppInitializer.ctx.getBean(ManageUserBo.class);
    public static Users users= new Users();

    @FXML
    private void btnLogin_OnAction(ActionEvent actionEvent) throws IOException {

//        try {
//            manageUserBo.saveUser(new Users("Gihan Ratnayaka","gihan@gmail.com","932943760v","gihan","1234"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        if (txtUserName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Enter User Name", ButtonType.CANCEL).showAndWait();
            txtUserName.requestFocus();
            return;
        }

        if (txtPassword.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Enter Password", ButtonType.OK).showAndWait();
            txtPassword.requestFocus();
            return;
        }

        try {
            Users u = manageUserBo.findUser(txtUserName.getText().trim());
            users=u;
            if (null == u) {
                new Alert(Alert.AlertType.ERROR, "Invalide Your Name Or Password !", ButtonType.OK).showAndWait();
                txtPassword.requestFocus();
                return;
            }

            if(!u.getPassword().equals(txtPassword.getText().trim())){
                new Alert(Alert.AlertType.ERROR, "Invalide Your Name Or Password !", ButtonType.CANCEL).showAndWait();
                txtPassword.requestFocus();
                return;
            }
            Parent root = FXMLLoader.load(this.getClass().getResource("/ww/com/wi/view/Home.fxml"));
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) btnLogin.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();



        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
