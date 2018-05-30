package proxy;

import ca.uhn.fhir.model.dstu2.resource.Medication.*;

public class IngredientProxy {
    private ProductIngredient productIngredient;

    IngredientProxy(ProductIngredient pi) {
        this.productIngredient = pi;
    }

    public String getName() {
        try {
            return productIngredient.getItem().getDisplay().getValue();
        } catch (Exception e) {
            return "";
        }
    }

    public String getQuantity() {
        try {
            return productIngredient.getAmount().getDenominator().getUnit();
        } catch (Exception e) {
            return "";
        }
    }

    public String getFullText() {
        return getName() + " " + getQuantity();
    }
}
