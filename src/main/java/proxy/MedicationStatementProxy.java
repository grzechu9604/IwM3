package proxy;

import org.hl7.fhir.dstu3.model.*;

public class MedicationStatementProxy {
    private MedicationStatement medicationStatement;
    private String medicationStatementId;

    MedicationStatementProxy(MedicationStatement medicationStatement) {
        this.medicationStatement = medicationStatement;
        this.medicationStatementId = medicationStatement.getIdElement().getIdPart();
    }

    public String getMedicationStatementId() {
        return medicationStatementId;
    }
    public String getActivity() {
        if (medicationStatement.getStatus()!= null)
            return medicationStatement.getStatus().getDisplay();
        else
            return "";
    }
    public String getMedicationName() {
        if (medicationStatement.getMedication() != null)
            return ((CodeableConcept)medicationStatement.getMedication()).getText();
        else
            return "";
    }
    public String getDosage() {
        if (medicationStatement.getDosageFirstRep() != null)
            return medicationStatement.getDosageFirstRep().getText();
        else
            return "";
    }
    public String getTaken() {
        if (medicationStatement.getTaken() != null)
            return medicationStatement.getTaken().getDisplay();
        else
            return "";
    }

}
