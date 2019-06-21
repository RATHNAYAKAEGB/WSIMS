package ww.com.wi.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.slf4j.Logger;
import ww.com.wi.business.ManageCustomerGroupBo;
import ww.com.wi.business.ManageCustomersBO;
import ww.com.wi.business.ManageSubCommityBo;
import ww.com.wi.entity.Customer;
import ww.com.wi.entity.CustomerGroup;
import ww.com.wi.entity.SubCommity;
import ww.com.wi.main.AppInitializer;
import ww.com.wi.temp.CustomerDTO;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerController<T> {
    @FXML
    private JFXTextField txtSubComityName;
    @FXML
    private JFXTextField txtCustomerId;
    @FXML
    private JFXTextField txtCustomerName;
    @FXML
    private JFXTextField txtNic;
    @FXML
    private JFXTextField txtFullName;
    @FXML
    private JFXButton btnBrows;
    @FXML
    private JFXTextField txtMobile;
    @FXML
    private Button btnBack;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXComboBox cmbCusGroupId;
    @FXML
    private JFXTextField txtCustomerGroupName;
    @FXML
    private JFXComboBox cmbSubCommity;
    @FXML
    private JFXDatePicker txtRegDate;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnUpdate;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private TableView<CustomerDTO> tblCustomer;
    @FXML
    private JFXTextField txtSeachId;
    @FXML
    private Label lblOpenBalance;
    @FXML
    private ImageView img_pp;

    private List<CustomerGroup> customerGroups = new ArrayList<>();
    private List<SubCommity> subCommities = new ArrayList<>();
    private String img_url = " ";
    private List<Customer> allCustomers= new ArrayList<>();

     Logger() {

    }


    public void initialize() {

        setCustomerGroupIds();
        setCmbSubCommity();
        loadAllCustomers();
        setCustomerId();

        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);


        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("shortName"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("nic"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("reqDate"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("fullName"));
        tblCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("cgId"));
        tblCustomer.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("scId"));
        tblCustomer.getColumns().get(9).setCellValueFactory(new PropertyValueFactory<>("description"));


        tblCustomer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerDTO>() {
            @Override
            public void changed(ObservableValue<? extends CustomerDTO> observable, CustomerDTO oldValue, CustomerDTO c) {
                if (c == null) {
                    return;
                }
                btnSave.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                Customer c2 = findCustomer(c.getCustomerId());
                txtAddress.setText(c2.getAddress());
                txtCustomerGroupName.setText(c2.getCgId().getId());
                txtDescription.setText(c2.getDescription()+" ");
                txtMobile.setText(c2.getMobile());
                txtCustomerName.setText(c2.getFullName());
                txtRegDate.setValue(LocalDate.parse(c2.getReqDate()+""));
                txtCustomerGroupName.setText(c2.getCgId().getGroupName());
                txtSubComityName.setText(c2.getScId().getSname());
                cmbCusGroupId.setValue(c2.getCgId().getId());
                cmbSubCommity.setValue(c2.getScId().getSubCId());
                txtFullName.setText(c2.getFullName());
                txtNic.setText(c2.getNic());
                txtCustomerId.setText(c2.getCustomerId());
                img_pp.setImage(new Image(new ByteArrayInputStream(c2.getProfilePicture())));//Byte Array to Image View
                lblOpenBalance.setText("Openning Balance of the Month : "+c2.getOpenBlance());

            }
        });


    }

    private ManageCustomersBO manageCustomersBO = AppInitializer.ctx.getBean(ManageCustomersBO.class);
    private ManageCustomerGroupBo manageCustomerGroupBo = AppInitializer.ctx.getBean(ManageCustomerGroupBo.class);
    private ManageSubCommityBo manageSubCommityBo = AppInitializer.ctx.getBean(ManageSubCommityBo.class);

    @FXML
    private void btnBrows_OnAction(ActionEvent actionEvent) {

        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            Image image1 = new Image(file.toURI().toString());
            this.img_url = file.getAbsolutePath();
            ImageView ip = new ImageView(image1);
            BackgroundSize backgroundSize = new BackgroundSize(200, 222, true, true, true, false);
            BackgroundImage backgroundImage = new BackgroundImage(image1, BackgroundRepeat.ROUND, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
            img_pp.setImage(image1);
        } else {
            new Alert(Alert.AlertType.ERROR, "No Iamage Found ", ButtonType.OK).showAndWait();
        }

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

    @FXML
    private void txtSeachId_OnAction(ActionEvent actionEvent) {



    }

    @FXML
    private void txtSearch_OnKeyPressed(KeyEvent keyEvent) {
        findByCustomerId(txtSeachId.getText().toUpperCase());
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent actionEvent) {

        if(btnSave.isDisable()){
            try {
                manageCustomersBO.deleteCustomerGroup(txtCustomerId.getText().trim());
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Deleted !", ButtonType.OK).showAndWait();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Not Deleted  !", ButtonType.OK).showAndWait();
            }
        }

        reset();

    }

    @FXML
    private void btnUpdate_OnAction(ActionEvent actionEvent) {

        if (txtCustomerId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Id Is Empty !", ButtonType.OK).showAndWait();
            txtCustomerId.requestFocus();
            return;
        }
        if (txtCustomerName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Name With Initial is Empty !", ButtonType.OK).showAndWait();
            txtCustomerName.requestFocus();
            return;
        }
        if (txtNic.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer NIC is Empty !", ButtonType.OK).showAndWait();
            txtNic.requestFocus();
            return;
        }

        if (txtMobile.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Mobile Number is Empty !", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }

        if (null == txtRegDate.getValue()) {
            new Alert(Alert.AlertType.ERROR, "Customer Register Date is Empty !", ButtonType.OK).showAndWait();
            txtRegDate.requestFocus();
            return;
        }

        if (txtFullName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Full Name is Empty !", ButtonType.OK).showAndWait();
            txtFullName.requestFocus();
            return;
        }

        if (txtAddress.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Address is Empty !", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }


        if (null == cmbCusGroupId.getValue()) {
            new Alert(Alert.AlertType.ERROR, "Customer Group No is Empty !", ButtonType.OK).showAndWait();
            cmbCusGroupId.requestFocus();
            return;
        }

        if (null == cmbSubCommity.getValue()) {
            new Alert(Alert.AlertType.ERROR, "Customer Sub Commitee No is Empty !", ButtonType.OK).showAndWait();
            cmbSubCommity.requestFocus();
            return;
        }

//        if (img_url.trim().isEmpty()) {
//            new Alert(Alert.AlertType.ERROR, "Customer Image is Empty !", ButtonType.OK).showAndWait();
//            cmbSubCommity.requestFocus();
//            return;
//        }

        String customerId = txtCustomerId.getText().trim();
        String nwi = txtCustomerName.getText().trim();
        String nic = txtNic.getText().trim();
        String mobile = txtMobile.getText().trim();
        String fullName = txtFullName.getText();
        LocalDate regD = txtRegDate.getValue();
        String description = txtDescription.getText().trim() +" ";
        byte[] pp = new byte[0];
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(img_pp.getImage(), null);
            ByteArrayOutputStream s = new ByteArrayOutputStream();
            ImageIO.write(bImage, "png", s);
            byte[] res  = s.toByteArray();
            System.out.println("Byte Array ----"+res);
            pp = res;
        } catch (Exception e) {
            e.printStackTrace();
        }


        SubCommity subCommity = findSubCommity(cmbSubCommity.getValue().toString().trim());
        CustomerGroup customerGroup = findCustomerGroup(cmbCusGroupId.getValue().toString().trim());
        String address = txtAddress.getText().trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = null;
        try {
            regDate = simpleDateFormat.parse(regD.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            manageCustomersBO.updateCustomerGroup(new Customer(customerId, nwi, nic, mobile, regDate, fullName, address, pp, customerGroup, subCommity, LoginController.users.getUserName(),description));
            new Alert(Alert.AlertType.INFORMATION, "Customer Customer Account Updated Successfully !", ButtonType.OK).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).showAndWait();
            e.printStackTrace();
        }

        reset();

    }

    @FXML
    private void btnSave_OnAction(ActionEvent actionEvent) throws IOException, SQLException, ParseException {


        if (txtCustomerId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Id Is Empty !", ButtonType.OK).showAndWait();
            txtCustomerId.requestFocus();
            return;
        }
        if (txtCustomerName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Name With Initial is Empty !", ButtonType.OK).showAndWait();
            txtCustomerName.requestFocus();
            return;
        }
        if (txtNic.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer NIC is Empty !", ButtonType.OK).showAndWait();
            txtNic.requestFocus();
            return;
        }

        if (txtMobile.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Mobile Number is Empty !", ButtonType.OK).showAndWait();
            txtMobile.requestFocus();
            return;
        }

        if (null == txtRegDate.getValue()) {
            new Alert(Alert.AlertType.ERROR, "Customer Register Date is Empty !", ButtonType.OK).showAndWait();
            txtRegDate.requestFocus();
            return;
        }

        if (txtFullName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Full Name is Empty !", ButtonType.OK).showAndWait();
            txtFullName.requestFocus();
            return;
        }

        if (txtAddress.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Address is Empty !", ButtonType.OK).showAndWait();
            txtAddress.requestFocus();
            return;
        }


        if (null == cmbCusGroupId.getValue()) {
            new Alert(Alert.AlertType.ERROR, "Customer Group No is Empty !", ButtonType.OK).showAndWait();
            cmbCusGroupId.requestFocus();
            return;
        }

        if (null == cmbSubCommity.getValue()) {
            new Alert(Alert.AlertType.ERROR, "Customer Sub Commitee No is Empty !", ButtonType.OK).showAndWait();
            cmbSubCommity.requestFocus();
            return;
        }

        if (img_url.trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Customer Image is Empty !", ButtonType.OK).showAndWait();
            cmbSubCommity.requestFocus();
            return;
        }

        String customerId = txtCustomerId.getText().trim();
        String nwi = txtCustomerName.getText().trim();
        String nic = txtNic.getText().trim();
        String mobile = txtMobile.getText().trim();
        String fullName = txtFullName.getText();
        LocalDate regD = txtRegDate.getValue();
        String description = txtDescription.getText().trim();
        byte[] pp = Files.readAllBytes(Paths.get(img_url));
        System.out.println("image---Path" + img_url);
        SubCommity subCommity = findSubCommity(cmbSubCommity.getValue().toString().trim());
        CustomerGroup customerGroup = findCustomerGroup(cmbCusGroupId.getValue().toString().trim());
        String address = txtAddress.getText().trim();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date regDate = simpleDateFormat.parse(regD.toString());

        try {
            manageCustomersBO.createCustomer(new Customer(customerId, nwi, nic, mobile, regDate, fullName, address, pp, customerGroup, subCommity, LoginController.users.getUserName(), 0,description));
            new Alert(Alert.AlertType.INFORMATION, "Customer Customer Account Created Successfully !", ButtonType.OK).showAndWait();

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Error", ButtonType.OK).showAndWait();
            e.printStackTrace();
        }

        reset();

    }

    @FXML
    private void cmbSubCommity_OnAction(ActionEvent actionEvent) {

        String groupId = cmbSubCommity.getValue().toString();
        for (SubCommity c : subCommities) {
            if (c.getSubCId().equals(groupId)) {
                txtSubComityName.setText(c.getSname());
                return;
            }
        }
    }

    @FXML
    private void cmbCusGroupId_OnAction(ActionEvent actionEvent) {
        String groupId = cmbCusGroupId.getValue().toString();
        for (CustomerGroup c : customerGroups) {
            if (c.getId().equals(groupId)) {
                txtCustomerGroupName.setText(c.getGroupName());
                return;
            }
        }

    }

    public void setCustomerGroupIds() {
        ArrayList<String> list = new ArrayList<>();
        try {
            List<CustomerGroup> cg = manageCustomerGroupBo.findAll();
            customerGroups = cg;
            for (CustomerGroup c : cg) {
                list.add(c.getId());
            }
            cmbCusGroupId.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCmbSubCommity() {
        ArrayList<String> list = new ArrayList<>();
        try {
            List<SubCommity> cg = manageSubCommityBo.findAll();
            subCommities = cg;
            for (SubCommity c : cg) {
                list.add(c.getSubCId());
            }
            cmbSubCommity.setItems(FXCollections.observableArrayList(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public SubCommity findSubCommity(String id) {
        for (SubCommity c : subCommities) {
            if (c.getSubCId().equals(id)) {
                return c;
            }
        }
        return null;

    }

    public CustomerGroup findCustomerGroup(String id) {
        for (CustomerGroup c : customerGroups) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private void setCustomerId() {
        ObservableList<CustomerDTO> items = tblCustomer.getItems();
        int max = 0;
        if (items == null || items.size() == 0) {
            txtCustomerId.setText("RDC0" + 1);
            return;
        }
        for (CustomerDTO c : items) {
            String subCId = c.getCustomerId();
            int i = Integer.parseInt(subCId.substring(3));
            if (max < i) {
                max = i;
            }
            txtCustomerId.setText("RDC0" + (++max));

        }

    }

    private void loadAllCustomers(){

        try {
            allCustomers=manageCustomersBO.findAll();
            ArrayList<CustomerDTO> temp = new ArrayList<>();


            for (Customer c:allCustomers) {
                temp.add( new CustomerDTO(c.getCustomerId(),c.getShortName(),c.getNic(),c.getMobile(),
                        c.getReqDate(),c.getFullName(),c.getAddress(),c.getProfilePicture(),c.getCgId().getId(),c.getScId()
                        .getSubCId(),c.getCreateBy(),c.getOpenBlance(),c.getDescription()));
            }

            tblCustomer.setItems(FXCollections.observableArrayList(temp));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private Customer findCustomer(String id){
        for (Customer c:allCustomers) {
            if(id.equals(c.getCustomerId())){
                return c;
            }
        }
        return null;
    }


    private void findByCustomerId(String id){

        ArrayList<CustomerDTO> findList = new ArrayList<>();
        ObservableList<CustomerDTO> items = tblCustomer.getItems();
        for (CustomerDTO d:items) {
            String idh = d.getCustomerId().substring(0, id.length());
            if(id.equals(idh)){
                findList.add(d);
            }
        }

        tblCustomer.setItems(FXCollections.observableArrayList(findList));

    }


    private void reset(){
        txtCustomerId.clear();
        txtAddress.clear();
        txtFullName.clear();
        txtDescription.clear();
        txtCustomerGroupName.clear();
        txtNic.clear();
        txtRegDate.setValue(null);
        txtSeachId.clear();
        txtSubComityName.clear();
        cmbSubCommity.setValue("");
        cmbCusGroupId.setValue("");
        txtMobile.clear();
        txtCustomerName.clear();
        loadAllCustomers();
        setCustomerId();
        btnSave.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
    }




}