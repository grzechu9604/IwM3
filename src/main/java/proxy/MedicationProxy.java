package proxy;

import org.hl7.fhir.dstu3.model.*;

import java.util.List;
import java.util.stream.Collectors;

public class MedicationProxy {
    private Medication medication;
    private String medicationId;

    MedicationProxy(Medication medication) {
        this.medication = medication;
        this.medicationId = medication.getIdElement().getIdPart();
    }

    public String getProducer() {
        try {
            return medication.getManufacturer().getDisplay();
        } catch (Exception e) {
            return "";
        }
    }

    public String getForm() {
        try {
            return medication.getForm().getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean getIsOverTheCounter() {
        try {
            return medication.getIsOverTheCounter();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMedicationId() {
        return medicationId;
    }
}
