package mainPackage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.hl7.fhir.dstu3.model.MedicationStatement;
import proxy.*;

import java.util.Date;
import java.util.List;

public class Main extends Application {

    private TableView tablePatient = new TableView();
    final Label labelPatient = new Label("Patient");
    private final ObservableList<PatientProxy> dataPatient = FXCollections.observableArrayList();

    private TableView tableObservations = new TableView();
    final Label labelObservations = new Label("Observations");
    private final ObservableList<ObservationProxy> dataObservation = FXCollections.observableArrayList();

    private TableView tableMedicationStatement = new TableView();
    final Label labelMedicationStatement = new Label("MedicationStatement");
    private final ObservableList<MedicationStatementProxy> dataMedicationStatement = FXCollections.observableArrayList();

    private TableView tableMedication = new TableView();
    final Label labelMedication = new Label("Medication");
    private final ObservableList<MedicationProxy> dataMedication = FXCollections.observableArrayList();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1600, 800, Color.WHITE);

        GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        // ### PATIENT ###
        TableColumn col_1_id = new TableColumn("Id");
        col_1_id.setMinWidth(50);
        col_1_id.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, Long>("id"));
        TableColumn col_2_name = new TableColumn("Name");
        col_2_name.setMinWidth(150);
        col_2_name.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, String>("names"));
        TableColumn col_3_lastname = new TableColumn("Last Name");
        col_3_lastname.setMinWidth(150);
        col_3_lastname.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, String>("lastNames"));
        TableColumn col_4_genre = new TableColumn("Genre");
        col_4_genre.setMinWidth(50);
        col_4_genre.setCellValueFactory(
                new PropertyValueFactory<PatientProxy, String>("gender"));
        HapiServiceProxy hsp = new HapiServiceProxy();
        List<PatientProxy> pList = hsp.getPatientProxies();
        dataPatient.addAll(pList);
        tablePatient.setItems(dataPatient);
        tablePatient.getColumns().addAll(col_1_id, col_2_name, col_3_lastname, col_4_genre);
        tablePatient.setRowFactory(tv -> {
            TableRow<PatientProxy> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    PatientProxy rowData = row.getItem();

                    //MEDICATION add to table
                    dataMedication.clear();
                    List<MedicationProxy> mList = hsp.getMedicationProxies();
                    dataMedication.addAll(mList);
                    tableMedication.setItems(dataMedication);

                    //MEDICATIONSTATEMENT add to table
                    dataMedicationStatement.clear();
                    List<MedicationStatementProxy> msList = hsp.getMedicationStatementProxiesByPatient(rowData.getId());
                    dataMedicationStatement.addAll(msList);
                    tableMedicationStatement.setItems(dataMedicationStatement);

                    //OBSERVATION add to table
                    dataObservation.clear();
                    List<ObservationProxy> oList = hsp.getObservationProxiesByPatient(rowData.getId()); //rowData.getId() //Long.valueOf(982)
                    dataObservation.addAll(oList);
                    tableObservations.setItems(dataObservation);
                }
            });
            return row;
        });
        gridpane.add(labelPatient, 0, 0);
        gridpane.add(tablePatient, 0, 1);


        // ### OBSERVATIONS ###
        TableColumn col_00 = new TableColumn("Id");
        col_00.setMinWidth(50);
        col_00.setCellValueFactory(
                new PropertyValueFactory<ObservationProxy, Long>("observationId"));
        TableColumn col_11 = new TableColumn("Date");
        col_11.setMinWidth(200);
        col_11.setCellValueFactory(
                new PropertyValueFactory<ObservationProxy, Date>("observationDate"));
        TableColumn col_22 = new TableColumn("Value");
        col_22.setMinWidth(100);
        col_22.setCellValueFactory(
                new PropertyValueFactory<ObservationProxy, String>("observedValue"));
        TableColumn col_33 = new TableColumn("Description");
        col_33.setMinWidth(300);
        col_33.setCellValueFactory(
                new PropertyValueFactory<ObservationProxy, Date>("observationDescription"));

        tableObservations.getColumns().addAll(col_00, col_11, col_22, col_33);
        gridpane.add(labelObservations, 1, 0);
        gridpane.add(tableObservations, 1, 1);

        // ### MEDICATIONSTATEMENT ###
        TableColumn col_111 = new TableColumn("Id");
        col_111.setMinWidth(100);
        col_111.setCellValueFactory(
                new PropertyValueFactory<MedicationStatement, Long>("medicationStatementId"));

        tableMedicationStatement.getColumns().addAll(col_111);
        gridpane.add(labelMedicationStatement, 2, 0);
        gridpane.add(tableMedicationStatement, 2, 1);


        // ### MEDICATION ###
        TableColumn col_1111 = new TableColumn("Id");
        col_1111.setMinWidth(50);
        col_1111.setCellValueFactory(
                new PropertyValueFactory<MedicationStatement, Long>("medicationId"));

        tableMedication.getColumns().addAll(col_1111);
        gridpane.add(labelMedication, 1, 2);
        gridpane.add(tableMedication, 1, 3);

        // ### SET VIEW ###
        root.setCenter(gridpane);
        GridPane.setVgrow(root, Priority.ALWAYS);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}





        /*
        List<MedicationStatementProxy> medicationStatementProxyList = hsp.getMedicationStatementProxiesByPatient(Long.valueOf(982));

        if(medicationStatementProxyList.isEmpty()){
            System.out.println("-->puste");
        }else{
            System.out.println("-->cos tam jest");
        }
        */

        /*tableObservations.setRowFactory(tv -> {
            TableRow<ObservationProxy> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    ObservationProxy rowData = row.getItem();
                    System.out.println(rowData.getObservationId());

                    List<MedicationStatementProxy> medicationStatementProxyList = hsp.getMedicationStatementProxiesByPatient(p.getId())

                    if(medicationStatementProxyList.isEmpty()){
                        System.out.println("puste");
                    }else{
                        System.out.println("cos tam jest");
                    }

                }
            });
            return row;
        });*/



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
