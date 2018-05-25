package mainPackage;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import connection.HapiService;
import javafx.application.Application;
import javafx.stage.Stage;
import proxy.PatientProxy;

import java.util.Date;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("IwM3");
        primaryStage.show();

        HapiService hs = new HapiService();

        Patient p1 = hs.getPatientById("f001");

        PatientProxy p = new PatientProxy(p1);
        String names = p.getNames();
        String lastNames = p.getLastNames();
        String teleCom = p.getTeleCom().getPhoneNumbers();
        String mails = p.getTeleCom().getMails();

        String country = p.getAddressProxy().getCountry();
        String city = p.getAddressProxy().getCity();
        String line = p.getAddressProxy().getLine();
        String postalCode = p.getAddressProxy().getPostalCode();
        Date dateOfBirth = p.getDateOfBirth();
        System.out.println(names);
    }
}
