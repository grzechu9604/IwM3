package proxy;

import ca.uhn.fhir.model.dstu2.composite.QuantityDt;
import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.primitive.DateTimeDt;
import ca.uhn.fhir.model.primitive.IdDt;

import java.util.Date;

public class ObservationProxy {
    private Observation observation;
    private Long observationId;
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
        if(observationDate == null)
            if (observation.getEffective() != null)
                observationDate = ((DateTimeDt)(observation.getEffective())).getValue();

        return observationDate;
    }

    public String getObservedValue() {
        if (observedValue == null){
            QuantityDt qdt = (QuantityDt)observation.getValue();
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

    public Long getObservationId() {
        IdDt iddt = (IdDt) observation.getId();
        setObservationId(iddt.getIdPartAsLong());
        return observationId;
    }

    public void setObservationId(Long observationId) {
        this.observationId = observationId;
    }
}
