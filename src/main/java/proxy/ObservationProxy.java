package proxy;

import org.hl7.fhir.dstu3.model.*;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;

import java.util.Date;
import java.util.List;

public class ObservationProxy {
    private Observation observation;
    private String observationId;
    private String observationDate;
    private String observedValue;
    private String observationDescription;

    public String getFullText(){
        return getObservationDate() + " " + getObservationDescription() + " " + getObservedValue();
    }

    ObservationProxy(Observation observation) {
        this.observation = observation;
    }

    public String getObservationDate() {
        // TODO musisz tu popróbować coś z observation.getEffective().castToDate() jak z tego datę wyciągnąć
        try {
            if (observationDate == null){
                if (observation.getEffective() != null) {
                    //observation.getValueDateTimeType().casToDa;
                    System.out.println("#");
                    observationDate = observation.getEffective().primitiveValue();
                }
            }
            else
            {
                observationDate = "";
            }
            }catch (Exception e){
                System.out.println("#");
            }
        //observationDate = observation.getEffective().castToDate();
        return observationDate;
    }

    public String getObservedValue() {
        if (observedValue == null){
            Quantity qdt = (Quantity)observation.getValue();
            if (qdt != null && qdt.getValue() != null && qdt.getUnit() != null)
                observedValue = qdt.getValue() + " " + qdt.getUnit();
            else
                observedValue = "";
        }
        return observedValue;
    }

    public String getObservationDescription() {
        if (observationDescription == null){
            try{
                observationDescription = observation.getCode().getCoding().get(0).getDisplay();
            }catch(Exception e){
                observationDescription = observation.getCode().getText();
            }
            }
        return observationDescription;
    }

    public String getObservationId() {
        String id = observation.getIdElement().getIdPart();
        setObservationId(id);
        return observationId;
    }

    public void setObservationId(String observationId) {
        this.observationId = observationId;
    }
}
