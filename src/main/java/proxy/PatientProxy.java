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

    @org.jetbrains.annotations.NotNull
    private String setNamesFromPatient() {
        StringBuilder namesFromPatientBuilder = new StringBuilder();

       /* try {
            for (StringType lastNameFromPatient : patient.getName().get(0).getGiven()) {
                namesFromPatientBuilder.append(" ").append(lastNameFromPatient.getValueNotNull());
            }
        } catch (Exception e) {*/
            return "";
        //}

        //return namesFromPatientBuilder.toString().trim();
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
