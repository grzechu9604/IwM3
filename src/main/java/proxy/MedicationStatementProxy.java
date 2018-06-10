package proxy;

import ca.uhn.fhir.model.dstu2.resource.MedicationStatement;

public class MedicationStatementProxy {
    private MedicationStatement medicationStatement;
    private Long medicationStatementId;

    MedicationStatementProxy(MedicationStatement medicationStatement) {
        this.medicationStatement = medicationStatement;
        this.medicationStatementId = medicationStatement.getId().getIdPartAsLong();
    }

    public Long getMedicationStatementId() {
        return medicationStatementId;
    }
}
