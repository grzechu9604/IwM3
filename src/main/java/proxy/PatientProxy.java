package proxy;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.primitive.StringDt;

public class PatientProxy {
    private Patient patient;
    private String names;
    private String lastNames;

    public PatientProxy(Patient patient){
        this.patient = patient;
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

        return lastNamesFromPatientBuilder.toString();
    }

    public String getLastNames(){
        if (lastNames != null){
            return lastNames;
        }
        lastNames = setLastNamesFromPatient();
        return lastNames;
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

        return namesFromPatientBuilder.toString();
    }

    public String getNames(){
        if (names != null){
            return names;
        }
        names = setNamesFromPatient();
        return names;
    }
}
