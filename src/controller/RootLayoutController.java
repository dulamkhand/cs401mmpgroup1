/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.account.AccountTypeEnum;

/**
 *
 * @author Khandaa
 */
public class RootLayoutController implements Initializable {

    // static member will be used in other controllers
    static public BorderPane borderPane;
    
    // instances for all controllers will be created at the same time as RootLayoutController
    private ProjectsController projectsController;
    private OrderAddController projectAddController;
    private ItemsController itemsController;
    private OrdersController ordersController;
    private OrderAddController orderAddController;
    private InvoicesController invoicesController;
    private InvoiceAddController invoiceAddController;
    private VendorPersonController personController;
    private VendorCompanyController companyController;
    @FXML
    private Button itemsButton;
    @FXML
    private Button vendorsButton;
    @FXML
    private Button freelancersButton;
    
    public RootLayoutController() {
        this.projectsController = new ProjectsController();
        this.projectAddController = new OrderAddController();
        this.itemsController = new ItemsController();
        this.ordersController = new OrdersController();
        this.orderAddController = new OrderAddController();
        this.invoicesController = new InvoicesController();
        this.invoiceAddController = new InvoiceAddController();
        this.personController = new VendorPersonController();
        this.companyController = new VendorCompanyController();
    }
 
    public void go2homepage(ActionEvent event)  {
        try {
            // load rootLayout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/RootLayout.fxml"));
            RootLayoutController.borderPane = (BorderPane) loader.load();
            
            //show the scene containing the borderPane
            Scene scene = new Scene(RootLayoutController.borderPane); //We are sending borderPane to the Scene.
            Main.primaryStage.setScene(scene); //Set the scene in primary stage.
 
            //show the primary stage
            Main.primaryStage.show(); //Display the primary stage
            
            // now redirects to projects/orders index page
            this.go2projects(event);
            
        } catch (Exception ex) {
            Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    public void go2projects(ActionEvent event) throws Exception {
        this.projectsController.index(event);
    }
    
    @FXML
    public void go2items(ActionEvent event) throws Exception {
        this.itemsController.index(event);
    }
    
    @FXML
    public void go2orders(ActionEvent event) throws Exception {
        this.ordersController.index(event);
    }
    
    @FXML
    public void go2invoices(ActionEvent event) throws Exception {
        this.invoicesController.index(event);
    }
    
    @FXML
    public void go2payments(ActionEvent event) throws Exception {
        //this.ordersController.index(event);
    }
        
    @FXML
    public void go2vendors(ActionEvent event) throws Exception {
        this.companyController.index(event);
    }
    
    @FXML
    public void go2persons(ActionEvent event) throws Exception {
        this.personController.index(event);
    }
    
    @FXML
    public void logout(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
 
    public void help(ActionEvent actionEvent) {
        Alert alert = new Alert (Alert.AlertType.INFORMATION);
        alert.setTitle("Program Information");
        alert.setHeaderText("");
        alert.setContentText("");
        alert.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(Main.userType.equals(AccountTypeEnum.COMPANY.toString()) || Main.userType.equals(AccountTypeEnum.PERSON.toString())){
            itemsButton.setVisible(false);
            freelancersButton.setVisible(false);
            vendorsButton.setVisible(false);
        }
    }
    
}
