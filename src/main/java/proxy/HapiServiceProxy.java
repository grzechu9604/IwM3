package proxy;

import ca.uhn.fhir.model.dstu2.resource.Patient;
import connection.HapiService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HapiServiceProxy {
    private HapiService hs;

    @Deprecated
    public HapiServiceProxy(HapiService hs) {
        this.hs = hs;
    }

    public HapiServiceProxy(){
        this.hs = new HapiService();
    }

    public List<PatientProxy> getPatientProxies() {
        List<Patient> hsPatients = hs.getPatients();
        return hsPatients.stream().map(PatientProxy::new).collect(Collectors.toList());
    }
}
