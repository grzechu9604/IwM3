package proxy;


import ca.uhn.fhir.model.dstu2.resource.*;

import java.util.Date;

public class PatientProxy {
    private Patient patient;
    private String names;
    private String lastNames;
    private String gender;
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
            return patient.getGender();//. getDisplay();
        }catch (Exception e){
            return "";
        }
    }

    PatientProxy(Patient patient) {
        this.patient = patient;
    }

    private String setLastNamesFromPatient() {
        try {
            return  patient.getName().get(0).getFamily().get(0).getValueNotNull();
        } catch (Exception e) {
            return  "";
        }
    }

    public String getLastNames() {
        if (lastNames != null) {
            return lastNames;
        }
        lastNames = setLastNamesFromPatient().trim();
        return lastNames;
    }

    @org.jetbrains.annotations.NotNull
    private String setNamesFromPatient() {
        try {
            return  patient.getName().get(0).getGiven().get(0).getValueNotNull();
        }
        catch (Exception e) {
            return "";
        }
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

}
