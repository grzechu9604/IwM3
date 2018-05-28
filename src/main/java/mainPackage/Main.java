package mainPackage;

import javafx.application.Application;
import javafx.stage.Stage;
import proxy.*;

import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        primaryStage.show();


        HapiServiceProxy hsp = new HapiServiceProxy();

        List<PatientProxy> pList = hsp.getPatientProxies();
        List<ObservationProxy> oList = hsp.getObservationProxies();
        List<MedicationProxy> mList = hsp.getMedicationProxies();
        List<MedicationStatementProxy> msList = hsp.getMedicatonStatementProxies();

        PatientProxy p1 = hsp.getPatientProxyById("341");
        ObservationProxy o1 = hsp.getObservationProxyById("151");
        MedicationProxy m1 = hsp.getMedicationProxyById("1082");
        MedicationStatementProxy ms1 = hsp.getMedicationStatementProxyById("166");

        List<PatientProxy> patients = hsp.getPatientProxies();
        List<PatientProxy> patientsByName = hsp.getPatientProxiesByName("Cocinero");

        patients.add(p1);
        patients.addAll(patientsByName);

        patients.forEach(p -> {
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
    }
}
