package proxy;

import ca.uhn.fhir.model.dstu2.resource.*;

public class MedicationProxy {
    private Medication medication;
    private String medicationId;
    private String medicationProducer;
    private String medicationForm;
    private boolean medicationIsOverTheCounter;


    MedicationProxy(Medication medication) {
        this.medication = medication;
        this.setMedicationId(medication.getIdElement().getIdPart());
        this.setMedicationProducer(getProducer());
        this.setMedicationForm(getForm());
        this.setMedicationIsOverTheCounter(getIsOverTheCounter());
    }

    public String getProducer() {
        try {
            return medication.getManufacturer().getDisplay().getValueNotNull();
        } catch (Exception e) {
            return "";
        }
    }

    public String getForm() {
        try {
            return "";//medication.get getForm().getText();
        } catch (Exception e) {
            return "";
        }
    }

    public boolean getIsOverTheCounter() {
        try {
            return false; //medication.is getIsOverTheCounter();
        } catch (Exception e) {
            return false;
        }
    }

    public String getMedicationId() {
        return medicationId;
    }

    public String getMedicationProducer() {
        return medicationProducer;
    }

    public void setMedicationProducer(String medicationProducer) {
        this.medicationProducer = medicationProducer;
    }

    public String getMedicationForm() {
        return medicationForm;
    }

    public void setMedicationForm(String medicationForm) {
        this.medicationForm = medicationForm;
    }

    public boolean isMedicationIsOverTheCounter() {
        return medicationIsOverTheCounter;
    }

    public void setMedicationIsOverTheCounter(boolean medicationIsOverTheCounter) {
        this.medicationIsOverTheCounter = medicationIsOverTheCounter;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }
}
