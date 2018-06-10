package proxy;

import org.hl7.fhir.dstu3.model.*;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;

import java.util.Date;

public class ObservationProxy {
    private Observation observation;
    private String observationId;
    private Date observationDate;
    private String observedValue;
    private String observationDescription;

    public String getFullText(){
        return getObservationDate() + " " + getObservationDescription() + " " + getObservedValue();
    }

    ObservationProxy(Observation observation) {
        this.observation = observation;
    }

    public Date getObservationDate() {
        // TODO musisz tu popróbować coś z observation.getEffective().castToDate() jak z tego datę wyciągnąć
        //if(observationDate == null)
            //if (observation.getEffective() != null)
                //observationDate = observation.getEffective().castToDate();

        return observationDate;
    }

    public String getObservedValue() {
        if (observedValue == null){
            Quantity qdt = (Quantity)observation.getValue();
            if (qdt.getValue() != null && qdt.getUnit() != null)
                observedValue = qdt.getValue() + " " + qdt.getUnit();
            else
                observedValue = "";
        }
        return observedValue;
    }

    public String getObservationDescription() {
        if (observationDescription == null){
            observationDescription = observation.getCode().getCoding().get(0).getDisplay();
            if (observationDescription == null){
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
