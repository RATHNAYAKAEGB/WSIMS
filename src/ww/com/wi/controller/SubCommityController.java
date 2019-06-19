package ww.com.wi.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import ww.com.wi.business.ManageSubCommityBo;
import ww.com.wi.business.ManageUserBo;
import ww.com.wi.entity.SubCommity;
import ww.com.wi.main.AppInitializer;

import java.io.IOException;
import java.util.List;

public class SubCommityController<T> {
    @FXML
    private JFXTextField txtSubId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtCreatedBy;
    @FXML
    private TableView<SubCommity> tblSubCommity;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private Button btnBack;

    private ManageSubCommityBo manageSubCommityBo = AppInitializer.ctx.getBean(ManageSubCommityBo.class);

    public void initialize() {
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        subCommityId();
        loadAll();

        tblSubCommity.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("subCId"));
        tblSubCommity.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("sname"));
        tblSubCommity.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("createBy"));
        txtCreatedBy.setText(LoginController.users.getUserName());

        tblSubCommity.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<SubCommity>() {
            @Override
            public void changed(ObservableValue<? extends SubCommity> observable, SubCommity oldValue, SubCommity newValue) {
                if (newValue == null) {
                    return;
                }
                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                txtCreatedBy.setText(newValue.getCreateBy());
                txtName.setText(newValue.getSname());
                txtSubId.setText(newValue.getSubCId());
            }
        });
    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) {
        if (txtSubId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Sub Comity Id Is Empty", ButtonType.CANCEL).showAndWait();
            txtSubId.requestFocus();
            return;
        }
        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Sub Comity Name Is Empty", ButtonType.CANCEL).showAndWait();
            txtName.requestFocus();
            return;
        }

        String id = txtSubId.getText().trim();
        String name = txtName.getText().trim();
        String createBy = txtCreatedBy.getText().trim();

        SubCommity subCommity = new SubCommity(id, name, createBy);

        try {
            manageSubCommityBo.createSubCommity(subCommity);
            new Alert(Alert.AlertType.CONFIRMATION, " Sub Comity Saved !", ButtonType.CANCEL).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, " Error !", ButtonType.CANCEL).showAndWait();
            e.printStackTrace();
        }
        reset();

    }

    @FXML
    private void btnUpdate_OnAction(ActionEvent actionEvent) {

        if (txtSubId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Sub Comity Id Is Empty", ButtonType.CANCEL).showAndWait();
            txtSubId.requestFocus();
            return;
        }
        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please Sub Comity Name Is Empty", ButtonType.OK).showAndWait();
            txtName.requestFocus();
            return;
        }

        String id = txtSubId.getText().trim();
        String name = txtName.getText().trim();
        String createBy = txtCreatedBy.getText().trim();

        SubCommity subCommity = new SubCommity(id, name, createBy);

        try {
            manageSubCommityBo.updateSubComity(subCommity);
            new Alert(Alert.AlertType.CONFIRMATION, " Sub Comity Updated !", ButtonType.OK).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, " Error !", ButtonType.CANCEL).showAndWait();
            e.printStackTrace();
        }
        reset();
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {

        if(btnSave.isDisable()){
            try {
                manageSubCommityBo.deleteSubCommity(txtSubId.getText().trim());
                new Alert(Alert.AlertType.CONFIRMATION, " Deleted !", ButtonType.OK).showAndWait();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, " Error !", ButtonType.CANCEL).showAndWait();

        }
        reset();
    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/ww/com/wi/view/Home.fxml"));
        Scene subScene = new Scene(root);
        Stage primaryStage = (Stage) btnDelete.getScene().getWindow();
        primaryStage.setScene(subScene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
        tt.setFromX(-subScene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    private void reset() {
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnSave.setDisable(false);
        txtCreatedBy.clear();
        txtSubId.clear();
        txtName.clear();
        txtCreatedBy.setText(LoginController.users.getUserName());
        loadAll();
        subCommityId();
    }

    private void subCommityId() {
        ObservableList<SubCommity> items = tblSubCommity.getItems();
        int max = 0;
        if (items == null || items.size() == 0) {
            txtSubId.setText("SUC" + 1);
            return;
        }
        for (SubCommity s : items) {
            String subCId = s.getSubCId();
            int i = Integer.parseInt(subCId.substring(3));
            if (max < i) {
                max = i;
            }
            txtSubId.setText("SUC" + (++max));

        }

    }

    private void loadAll(){
        try {
            List<SubCommity> all = manageSubCommityBo.findAll();
            if(all.size()<0){return;}
            ObservableList<SubCommity> list2 = FXCollections.observableArrayList(all);
            tblSubCommity.setItems(list2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
