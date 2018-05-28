package proxy;

import ca.uhn.fhir.model.dstu2.resource.Observation;

public class ObservationProxy {
    private Observation observation;

    ObservationProxy(Observation observation) {
        this.observation = observation;
    }
}
