package proxy;

import ca.uhn.fhir.model.dstu2.resource.MedicationStatement;

public class MedicationStatementProxy {
    private MedicationStatement medicationStatement;

    MedicationStatementProxy(MedicationStatement medicationStatement) {
        this.medicationStatement = medicationStatement;
    }
}
