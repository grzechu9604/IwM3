package mainPackage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import proxy.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.chrono.ChronoLocalDate;
import java.util.*;

public class Main extends Application {

    private TableView tablePatient = new TableView();
    final Label labelPatient = new Label("Patient");
    final Label labelPatientFilter = new Label("PatientFilter");
    private final ObservableList<PatientProxy> dataPatient = FXCollections.observableArrayList();

    private TableView tableObservations = new TableView();
    final Label labelObservations = new Label("Observations");
    final Label labelObservationsFilter = new Label("ObservationsFilter");
    private final ObservableList<ObservationProxy> dataObservation = FXCollections.observableArrayList();

    private TableView tableMedicationStatement = new TableView();
    final Label labelMedicationStatement = new Label("MedicationStatement");
    private final ObservableList<MedicationStatementProxy> dataMedicationStatement = FXCollections.observableArrayList();

    private TableView tableMedication = new TableView();
    final Label labelMedication = new Label("Medication");
    private final ObservableList<MedicationProxy> dataMedication = FXCollections.observableArrayList();

    private String helperId = "";

    private boolean shouldAddObservationToList(Date from, Date to, Date observationDate){
        return (from == null || observationDate != null && from.before(observationDate))
                && (to == null || observationDate != null && to.after(observationDate));
    }

    private DatePicker checkInDatePickerFrom;
    private DatePicker checkInDatePickerTo;

    private void updateObservationList(Date from, Date to, String patientId, HapiServiceProxy hsp){
        dataObservation.clear();
        List<ObservationProxy> oList = hsp.getObservationProxiesByPatient(patientId);

        oList.forEach(o -> {
            if (shouldAddObservationToList(from, to, o.getObservationDate()))
                dataObservation.add(o);
        });

        tableObservations.setItems(dataObservation);
    }

    private void updatePatientsList(String name, HapiServiceProxy hsp){
        List<PatientProxy> pList = hsp.getPatientProxiesByName(name);
        dataPatient.clear();
        //ADD EXAMPLE OF PATIENT
        //#BEGIN
        //dataPatient.add(hsp.getPatientProxyById("123"));
        //#END
        dataPatient.addAll(pList);
        tablePatient.setItems(dataPatient);
    }

    private void getPatientsList(HapiServiceProxy hsp){
        List<PatientProxy> pList = hsp.getPatientProxies();
        dataPatient.clear();
        //ADD EXAMPLE OF PATIENT
        //#BEGIN
        //dataPatient.add(hsp.getPatientProxyById("123"));
        //#END
        dataPatient.addAll(pList);
        tablePatient.setItems(dataPatient);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 1280, 900, Color.WHITE);

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

        tablePatient.getColumns().addAll(col_1_id, col_2_name, col_3_lastname, col_4_genre);
        tablePatient.setRowFactory(tv -> {
            TableRow<PatientProxy> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
                    PatientProxy rowData = row.getItem();
                    helperId = rowData.getId();

                    //MEDICATIONSTATEMENT add to table
                    dataMedicationStatement.clear();
                    List<MedicationStatementProxy> msList = hsp.getMedicationStatementProxiesByPatient(rowData.getId());
                    dataMedicationStatement.addAll(msList);
                    tableMedicationStatement.setItems(dataMedicationStatement);

                    //OBSERVATION add to table
                    updateObservationList(null, null, rowData.getId(), hsp);

                    //MEDICATION add to table
                    dataMedication.clear();
                    List<MedicationProxy> mList = hsp.getMedicationProxies();
                    dataMedication.addAll(mList);
                    tableMedication.setItems(dataMedication);
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
                new PropertyValueFactory<MedicationStatementProxy, String>("medicationStatementId"));
        TableColumn col_222 = new TableColumn("Activity");
        col_222.setMinWidth(100);
        col_222.setCellValueFactory(
                new PropertyValueFactory<MedicationStatementProxy, String>("medicationStatementActivity"));
        TableColumn col_333 = new TableColumn("Name");
        col_333.setMinWidth(100);
        col_333.setCellValueFactory(
                new PropertyValueFactory<MedicationStatementProxy, String>("medicationStatementName"));
        TableColumn col_444 = new TableColumn("Dosage");
        col_444.setMinWidth(100);
        col_444.setCellValueFactory(
                new PropertyValueFactory<MedicationStatementProxy, String>("medicationStatementDosage"));
        TableColumn col_555 = new TableColumn("Taken");
        col_555.setMinWidth(100);
        col_555.setCellValueFactory(
                new PropertyValueFactory<MedicationStatementProxy, String>("medicationStatementTaken"));

        tableMedicationStatement.getColumns().addAll(col_111, col_222, col_333, col_444, col_555);
        gridpane.add(labelMedicationStatement, 2, 0);
        gridpane.add(tableMedicationStatement, 2, 1);

        // ### MEDICATION ###
        TableColumn col_1111 = new TableColumn("Id");
        col_1111.setMinWidth(50);
        col_1111.setCellValueFactory(
                new PropertyValueFactory<MedicationProxy, Long>("medicationId"));
        TableColumn col_2222 = new TableColumn("Producer");
        col_2222.setMinWidth(50);
        col_2222.setCellValueFactory(
                new PropertyValueFactory<MedicationProxy, String>("medicationProducer"));
        //#TODO Form nie wiem czy dziala?
        TableColumn col_3333 = new TableColumn("Form");
        col_3333.setMinWidth(50);
        col_3333.setCellValueFactory(
                new PropertyValueFactory<MedicationProxy, String>("medicationForm"));
        TableColumn col_4444 = new TableColumn("IsOverTheCounter");
        col_4444.setMinWidth(50);
        col_4444.setCellValueFactory(
                new PropertyValueFactory<MedicationProxy, Boolean>("medicationIsOverTheCounter"));
        tableMedication.getColumns().addAll(col_1111, col_2222, col_3333, col_4444);
        gridpane.add(labelMedication, 1, 2);
        gridpane.add(tableMedication, 1, 3);

        //FILTER FOR PATIENT
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.add(labelPatientFilter, 0, 0);
        final TextField lastName = new TextField();
        lastName.setMinWidth(200);
        lastName.setPromptText("Wprowadź nazwisko pacjenta");
        GridPane.setConstraints(lastName, 0, 1);
        grid.getChildren().add(lastName);
        Button submit = new Button("Szukaj po nazwisku");
        GridPane.setConstraints(submit, 1, 1);
        grid.getChildren().add(submit);
        Button clear = new Button("Wyczyść filtr lub wyszukaj ponownie");
        GridPane.setConstraints(clear, 1, 2);
        grid.getChildren().add(clear);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if ((lastName.getText() != null && !lastName.getText().isEmpty())) {
                    dataMedication.clear();
                    dataMedicationStatement.clear();
                    dataObservation.clear();
                    updatePatientsList(lastName.getText(), hsp);
                } else {
                    dataMedication.clear();
                    dataMedicationStatement.clear();
                    dataObservation.clear();
                    getPatientsList(hsp);
                }
            }
        });
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dataMedication.clear();
                dataMedicationStatement.clear();
                dataObservation.clear();
                lastName.setText("");
                getPatientsList(hsp);
            }
        });

        //FILTER FOR OBSERVATION
        grid.add(labelObservationsFilter, 0, 4);
        checkInDatePickerFrom = new DatePicker();
        checkInDatePickerFrom.setPromptText("Data from");
        GridPane.setConstraints(checkInDatePickerFrom, 0, 5);
        grid.getChildren().add(checkInDatePickerFrom);
        checkInDatePickerTo = new DatePicker();
        checkInDatePickerTo.setPromptText("Data To");
        GridPane.setConstraints(checkInDatePickerTo, 0, 6);
        grid.getChildren().add(checkInDatePickerTo);
        Button submitObservation  = new Button("Dodaj filtr");
        GridPane.setConstraints(submitObservation, 1, 5);
        grid.getChildren().add(submitObservation);
        Button clearObservation = new Button("Usuń filtr");
        GridPane.setConstraints(clearObservation, 1, 6);
        grid.getChildren().add(clearObservation);
        gridpane.add(grid, 0, 3);


        submitObservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (checkInDatePickerFrom != null && checkInDatePickerTo != null && !helperId.isEmpty() ) {
                    updateObservationList(java.sql.Date.valueOf(String.valueOf(checkInDatePickerFrom.getValue())),
                            java.sql.Date.valueOf(String.valueOf(checkInDatePickerTo.getValue())), helperId, hsp);
                } else {
                    dataObservation.clear();
                    if(!helperId.isEmpty()) {
                        updateObservationList(null, null, helperId, hsp);
                    }

                }
            }
        });
        clearObservation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                dataObservation.clear();
                if(!helperId.isEmpty()) {
                    updateObservationList(null, null, helperId, hsp);
                }
            }
        });

        // ### SET VIEW ###
        root.setCenter(gridpane);
        GridPane.setVgrow(root, Priority.ALWAYS);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}