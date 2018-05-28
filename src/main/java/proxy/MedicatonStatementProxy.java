package proxy;

import ca.uhn.fhir.model.dstu2.resource.MedicationStatement;

public class MedicatonStatementProxy {
    private MedicationStatement medicationStatement;

    MedicatonStatementProxy(MedicationStatement medicationStatement) {
        this.medicationStatement = medicationStatement;
    }
}
