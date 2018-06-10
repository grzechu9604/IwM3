package proxy;

import org.hl7.fhir.dstu3.model.*;
import ca.uhn.fhir.model.primitive.StringDt;

import java.util.Date;

public class PatientProxy {
    private Patient patient;
    private String names;
    private String lastNames;
    private String gender;
    private TelecomProxy teleCom;
    private AddressProxy addressProxy;
    private Date dateOfBirth;
    private String id;

    public Date getDateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = getDateOfBirthFromPatient();
        }
        return dateOfBirth;
    }

    private Date getDateOfBirthFromPatient() {
        return patient.getBirthDate();
    }

    public String getGender() {
        if (gender == null) {
            gender = getGenderFromPatient();
        }
        return gender;
    }

    private String getGenderFromPatient() {
        try{
            return patient.getGender().getDisplay();
        }catch (Exception e){
            return "";
        }
    }

    PatientProxy(Patient patient) {
        this.patient = patient;
        if (!patient.getAddress().isEmpty())
            this.addressProxy = new AddressProxy(patient.getAddress().get(0));
        this.teleCom = new TelecomProxy(patient.getTelecom());
    }

    private String setLastNamesFromPatient() {
        StringBuilder lastNamesFromPatientBuilder = new StringBuilder();
        try {
            lastNamesFromPatientBuilder.append(patient.getName().get(0).getFamily());
        } catch (Exception e) {
            return "";
        }

        return lastNamesFromPatientBuilder.toString().trim();
    }

    public String getLastNames() {
        if (lastNames != null) {
            return lastNames;
        }
        lastNames = setLastNamesFromPatient().trim();
        return lastNames;
    }

    public TelecomProxy getTeleCom() {
        return teleCom;
    }

    @org.jetbrains.annotations.NotNull
    private String setNamesFromPatient() {
        StringBuilder namesFromPatientBuilder = new StringBuilder();

        try {
            for (StringType lastNameFromPatient : patient.getName().get(0).getGiven()) {
                namesFromPatientBuilder.append(" ").append(lastNameFromPatient.getValueNotNull());
            }
        } catch (Exception e) {
            return "";
        }

        return namesFromPatientBuilder.toString().trim();
    }

    public String getNames() {
        if (names != null) {
            return names;
        }
        names = setNamesFromPatient();
        return names;
    }

    public String getId() {
        if (id == null){
            id = patient.getIdElement().getIdPart();
        }
        return id;
    }

    public AddressProxy getAddressProxy() {
        return addressProxy;
    }
}
