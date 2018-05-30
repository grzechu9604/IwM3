package proxy;

import ca.uhn.fhir.model.dstu2.resource.Medication;

import java.util.List;
import java.util.stream.Collectors;

public class MedicationProxy {
    private Medication medication;

    MedicationProxy(Medication medication) {
        this.medication = medication;
    }

    public String getProducer() {
        try {
            return medication.getManufacturer().getDisplay().getValue();
        }catch (Exception e){
            return null;
        }
    }

    public String getForm() {
        try {
            return medication.getProduct().getForm().getText();
        }
        catch (Exception e) {
            return null;
        }
    }

    public List<IngredientProxy> getIngredients() {
        return medication.getProduct().getIngredient().stream().map(IngredientProxy::new).collect(Collectors.toList());
    }
}
