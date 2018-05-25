package proxy;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.StringDt;

public class PatientProxy {
    private Patient patient;
    private String names;
    private String lastNames;
    private TelecomProxy teleCom;
    private AddressProxy addressProxy;


    public PatientProxy(Patient patient){
        this.patient = patient;
        this.addressProxy = new AddressProxy(patient.getAddress().get(0));
        this.teleCom = new TelecomProxy(patient.getTelecom());
    }

    private String setLastNamesFromPatient(){
        StringBuilder lastNamesFromPatientBuilder = new StringBuilder();

        try {
            for (StringDt lastNameFromPatient : patient.getName().get(0).getFamily()) {
                lastNamesFromPatientBuilder.append(" ").append(lastNameFromPatient.getValueNotNull());
            }
        } catch (Exception e){
            return  "";
        }

        return lastNamesFromPatientBuilder.toString().trim();
    }

    public String getLastNames(){
        if (lastNames != null){
            return lastNames;
        }
        lastNames = setLastNamesFromPatient().trim();
        return lastNames;
    }

    public TelecomProxy getTeleCom(){
        return teleCom;
    }

    @org.jetbrains.annotations.NotNull
    private String setNamesFromPatient(){
        StringBuilder namesFromPatientBuilder = new StringBuilder();

        try {
            for (StringDt lastNameFromPatient : patient.getName().get(0).getGiven()) {
                namesFromPatientBuilder.append(" ").append(lastNameFromPatient.getValueNotNull());
            }
        } catch (Exception e){
            return  "";
        }

        return namesFromPatientBuilder.toString().trim();
    }

    public String getNames(){
        if (names != null){
            return names;
        }
        names = setNamesFromPatient();
        return names;
    }

    public AddressProxy getAddressProxy() {
        return addressProxy;
    }
}