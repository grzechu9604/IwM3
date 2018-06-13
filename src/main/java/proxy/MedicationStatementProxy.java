package proxy;


import ca.uhn.fhir.model.dstu2.resource.*;

public class MedicationStatementProxy {
    private MedicationStatement medicationStatement;
    private String medicationStatementId;
    private String medicationStatementActivity;
    private String medicationStatementName;
    private String medicationStatementDosage;
    private String medicationStatementTaken;

    MedicationStatementProxy(MedicationStatement medicationStatement) {
        this.medicationStatement = medicationStatement;
        this.setMedicationStatementId(medicationStatement.getIdElement().getIdPart());
        this.setMedicationStatementActivity(getActivity());
        this.setMedicationStatementName(getMedicationName());
        this.setMedicationStatementDosage(getDosage());
        this.setMedicationStatementTaken(getTaken());
    }

    public String getMedicationStatementId() {
        return medicationStatementId;
    }
    public String getActivity() {
        try {
            if (medicationStatement.getStatus() != null)
                return medicationStatement.getStatus().toString();
        }catch (Exception e){
            return "";
        }
        return "";
    }

    public String getMedicationName() {
        try{
        if (medicationStatement.getMedication() != null)
            return "";// ((CodeableConcept)medicationStatement.getMedication()).getText().toString();
        }catch (Exception e){
            return "";
        }
        return "";
    }
    public String getDosage() {
        try{
        if (medicationStatement.getDosageFirstRep() != null)
            return medicationStatement.getDosageFirstRep().getText().toString();
        }catch (Exception e){
            return "";
        }
        return "";
    }
    public String getTaken() {
        try{
        if (medicationStatement.getWasNotTaken() != null)
            return medicationStatement.getWasNotTaken().toString();
        }catch (Exception e){
            return "";
        }
        return "";
    }

    public void setMedicationStatementId(String medicationStatementId) {
        this.medicationStatementId = medicationStatementId;
    }

    public String getMedicationStatementActivity() {
        return medicationStatementActivity;
    }

    public void setMedicationStatementActivity(String medicationStatementActivity) {
        this.medicationStatementActivity = medicationStatementActivity;
    }

    public String getMedicationStatementName() {
        return medicationStatementName;
    }

    public void setMedicationStatementName(String medicationStatementName) {
        this.medicationStatementName = medicationStatementName;
    }

    public String getMedicationStatementDosage() {
        return medicationStatementDosage;
    }

    public void setMedicationStatementDosage(String medicationStatementDosage) {
        this.medicationStatementDosage = medicationStatementDosage;
    }

    public String getMedicationStatementTaken() {
        return medicationStatementTaken;
    }

    public void setMedicationStatementTaken(String medicationStatementTaken) {
        this.medicationStatementTaken = medicationStatementTaken;
    }
}
