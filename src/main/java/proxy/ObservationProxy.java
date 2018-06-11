package proxy;

import org.hl7.fhir.dstu3.model.*;

import java.util.Date;

public class ObservationProxy {
    private Observation observation;
    private String observationId;
    private String observationDateStr;
    private String observedValue;
    private String observationDescription;

    public String getFullText() {
        return getObservationDateStr() + " " + getObservationDescription() + " " + getObservedValue();
    }

    ObservationProxy(Observation observation) {
        this.observation = observation;
    }

    public Date getObservationDate(){
        if (observation.getEffective() != null) {
            return ((DateTimeType)observation.getEffective()).getValue();
        }
        else {
            return null;
        }
    }

    public String getObservationDateStr() {
        try {
            if (observationDateStr == null) {
                if (observation.getEffective() != null) {
                    observationDateStr = observation.getEffective().primitiveValue();
                }
                else {
                    observationDateStr = "";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return observationDateStr;
    }

    public String getObservedValue() {
        if (observedValue == null) {
            Quantity qdt = (Quantity) observation.getValue();
            if (qdt != null && qdt.getValue() != null && qdt.getUnit() != null)
                observedValue = qdt.getValue() + " " + qdt.getUnit();
            else
                observedValue = "";
        }
        return observedValue;
    }

    public String getObservationDescription() {
        if (observationDescription == null) {
            try {
                observationDescription = observation.getCode().getCoding().get(0).getDisplay();
            } catch (Exception e) {
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
