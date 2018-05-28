package proxy;

import ca.uhn.fhir.model.dstu2.resource.Medication;

public class MedicationProxy {
    private Medication medication;

    MedicationProxy(Medication medication) {
        this.medication = medication;
    }
}
