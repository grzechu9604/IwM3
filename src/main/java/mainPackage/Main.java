package mainPackage;

import ca.uhn.fhir.model.dstu2.resource.Medication;
import ca.uhn.fhir.model.dstu2.resource.MedicationStatement;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import connection.HapiGenericService;
import connection.HapiService;
import javafx.application.Application;
import javafx.stage.Stage;
import proxy.AddressProxy;
import proxy.HapiServiceProxy;
import proxy.TelecomProxy;

import java.util.List;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        primaryStage.show();

        HapiService hs = new HapiService();

        List<Patient> pList = hs.getPatients();
        List<Observation> oList = hs.getObservations();
        List<Medication> mList = hs.getMedications();
        List<MedicationStatement> msList = hs.getMedicationStatements();

        HapiServiceProxy hsp = new HapiServiceProxy(hs);

        Patient p1 = hs.getPatientById("f001");

        hsp.getPatientProxies().forEach(p -> {
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
