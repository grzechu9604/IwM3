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
}
