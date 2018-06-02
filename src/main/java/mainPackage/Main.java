package mainPackage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import proxy.*;

import java.util.List;

public class Main extends Application {

    private TableView tablePatient = new TableView();
    final Label labelPatient = new Label("Patient");

    private TableView tableObservations= new TableView();
    final Label labelObservations = new Label("Observations");


    private final ObservableList<PatientProxy> data = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1024, 768, Color.WHITE);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        // ### PATIENT ###
        TableColumn col_1_id = new TableColumn("Id");
        col_1_id.setMinWidth(100);
        col_1_id.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, Long>("id"));
        TableColumn col_2_name = new TableColumn("Name");
        col_2_name.setMinWidth(100);
        col_2_name.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, String>("names"));

        TableColumn col_3_lastname = new TableColumn("Last Name");
        col_3_lastname.setMinWidth(100);
        col_3_lastname.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, String>("lastNames"));
        TableColumn col_4_genre = new TableColumn("Genre");
        col_4_genre.setMinWidth(100);
        col_4_genre.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, String>("gender"));

        HapiServiceProxy hsp = new HapiServiceProxy();
        List<PatientProxy> pList = hsp.getPatientProxies();
        data.addAll(pList);

        tablePatient.setItems(data);
        tablePatient.getColumns().addAll(col_1_id, col_2_name, col_3_lastname, col_4_genre);

        tablePatient.setRowFactory(tv -> {
            TableRow<PatientProxy> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    PatientProxy rowData = row.getItem();
                    System.out.println(rowData.getId());
                    //clicklistener

                    //####
                    //List<ObservationProxy> observationProxyList = hsp.getObservationProxiesByPatient(rowData.getId());

                    //observationProxyList.forEach( obs-> {
                        //System.out.println(obs.getFullText());
                    //});

                    //####

                }
            });
            return row;
        });
        gridpane.add(labelPatient, 0, 0);
        gridpane.add(tablePatient, 0, 1);


        // ### OBSERVATIONS ###
        gridpane.add(labelObservations, 2, 0);
        //gridpane.add(tablePatient, 1, 1);
        List<ObservationProxy> oList = hsp.getObservationProxies();




        // ### SET VIEW ###
        root.setCenter(gridpane);
        GridPane.setVgrow(root, Priority.ALWAYS);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



//List<MedicationProxy> mList = hsp.getMedicationProxies();
//List<MedicationStatementProxy> msList = hsp.getMedicatonStatementProxies();

//PatientProxy p1 = hsp.getPatientProxyById("341");
//ObservationProxy o1 = hsp.getObservationProxyById("151");
//MedicationProxy m1 = hsp.getMedicationProxyById("1082");
//MedicationStatementProxy ms1 = hsp.getMedicationStatementProxyById("166");

//List<PatientProxy> patients = hsp.getPatientProxies();
//List<PatientProxy> patientsByName = hsp.getPatientProxiesByName("Smith");

//patients.add(p1);
//patients.addAll(patientsByName);
        /*

        pList.forEach(p -> {
            //List<ObservationProxy> observationProxyList = hsp.getObservationProxiesByPatient(p.getId());
            //List<MedicationStatementProxy> medicationStatementProxyList = hsp.getMedicationStatementProxiesByPatient(p.getId());

            //observationProxyList.forEach(o -> System.out.println(o.getFullText()));
            //medicationStatementProxyList.forEach(ms -> System.out.println("MAM"));

            System.out.println("\n\n\nID: " + p.getId());
            System.out.println("NAME: " + p.getNames());
            System.out.println("LAST NAME: " + p.getLastNames());

            TelecomProxy telecom = p.getTeleCom();
            if (telecom != null) {
                System.out.println("PHONE NUMBERS: " + telecom.getPhoneNumbers());
                System.out.println("MAILS: " + telecom.getMails());
            }
            System.out.println("GENDER: " + p.getGender());

            AddressProxy addressProxy = p.getAddressProxy();
            if (p.getAddressProxy() != null) {
                System.out.println("COUNTRY: " + addressProxy.getCountry());
                System.out.println("CITY: " + addressProxy.getCity());
                System.out.println("LINE: " + addressProxy.getLine());
                System.out.println("POSTAL CODE: " + addressProxy.getPostalCode());
            }
            System.out.println("BIRTH DATE: " + p.getDateOfBirth());
        });
*/
