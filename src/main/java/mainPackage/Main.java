package mainPackage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import proxy.*;

import java.util.List;

public class Main extends Application {

    private TableView table = new TableView();
    final Label labelPatient = new Label("Patient");
    private final ObservableList<PatientProxy> data = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(new Group());
        primaryStage.setTitle("IwM3");
        primaryStage.setWidth(1024);
        primaryStage.setHeight(768);
        table.setEditable(false);


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

        TableColumn col_5_observation = new TableColumn("Observation");
        col_5_observation.setMinWidth(100);
        col_5_observation.setCellValueFactory(new PropertyValueFactory("null"));

        HapiServiceProxy hsp = new HapiServiceProxy();
        List<PatientProxy> pList = hsp.getPatientProxies();
        data.addAll(pList);


        //List<ObservationProxy> oList = hsp.getObservationProxies();
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

        table.setItems(data);
        table.getColumns().addAll(col_1_id, col_2_name, col_3_lastname, col_4_genre, col_5_observation);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(labelPatient, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
