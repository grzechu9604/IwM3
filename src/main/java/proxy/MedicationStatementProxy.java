package proxy;

import org.hl7.fhir.dstu3.model.*;

public class MedicationStatementProxy {
    private MedicationStatement medicationStatement;
    private String medicationStatementId;
    private String medicationStatementActivity;
    private String medicationStatementName;
    private String medicationStatementDosage;
    private String medicationStatementTaken;

    MedicationStatementProxy(MedicationStatement medicationStatement) {
        this.medicationStatement = medicationStatement;
        this.medicationStatementId = medicationStatement.getIdElement().getIdPart();
        this.medicationStatementActivity = getActivity();
        this.medicationStatementName = getMedicationName();
        this.medicationStatementDosage = getDosage();
        this.medicationStatementTaken = getTaken();
    }

    public String getMedicationStatementId() {
        return medicationStatementId;
    }
    public String getActivity() {
        try {
            if (medicationStatement.getStatus() != null)
                return medicationStatement.getStatus().getDisplay().toString();
        }catch (Exception e){
            return "!";
        }
        return "?";
    }

    public String getMedicationName() {
        try{
        if (medicationStatement.getMedication() != null)
            return ((CodeableConcept)medicationStatement.getMedication()).getText().toString();
        }catch (Exception e){
            return "!";
        }
        return "?";
    }
    public String getDosage() {
        try{
        if (medicationStatement.getDosageFirstRep() != null)
            return medicationStatement.getDosageFirstRep().getText().toString();
        }catch (Exception e){
            return "!";
        }
        return "?";
    }
    public String getTaken() {
        try{
        if (medicationStatement.getTaken() != null)
            return medicationStatement.getTaken().getDisplay().toString();
        }catch (Exception e){
            return "!";
        }
        return "?";
    }

}
