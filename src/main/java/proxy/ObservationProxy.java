package proxy;

import ca.uhn.fhir.model.dstu2.resource.Observation;
import ca.uhn.fhir.model.primitive.DateTimeDt;

import java.util.Date;

public class ObservationProxy {
    private Observation observation;
    private Date observationDate;

    ObservationProxy(Observation observation) {
        this.observation = observation;
    }

    public Date getObservationDate() {
        if(observationDate == null)
            observationDate = ((DateTimeDt)(observation.getEffective())).getValue();

        return observationDate;
    }
}
