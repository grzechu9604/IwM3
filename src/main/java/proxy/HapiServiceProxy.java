package proxy;

import connection.HapiService;

import java.util.List;
import java.util.stream.Collectors;

public class HapiServiceProxy {
    private HapiService hs;

    public HapiServiceProxy() {
        this.hs = new HapiService();
    }

    public List<PatientProxy> getPatientProxies() {
        return hs.getPatients().stream().map(PatientProxy::new).collect(Collectors.toList());
    }

    public List<MedicationProxy> getMedicationProxies() {
        return hs.getMedications().stream().map(MedicationProxy::new).collect(Collectors.toList());
    }

    public List<MedicationStatementProxy> getMedicatonStatementProxies() {
        return hs.getMedicationStatements().stream().map(MedicationStatementProxy::new).collect(Collectors.toList());
    }

    public List<ObservationProxy> getObservationProxies() {
        return hs.getObservations().stream().map(ObservationProxy::new).collect(Collectors.toList());
    }

    public PatientProxy getPatientProxyById(String id) {
        return new PatientProxy(hs.getPatientById(id));
    }

    public MedicationProxy getMedicationProxyById(String id) {
        return new MedicationProxy(hs.getMedicationById(id));
    }

    public MedicationStatementProxy getMedicationStatementProxyById(String id) {
        return new MedicationStatementProxy(hs.getMedicationStatementById(id));
    }

    public ObservationProxy getObservationProxyById(String id) {
        return new ObservationProxy(hs.getObservationById(id));
    }

    public List<PatientProxy> getPatientProxiesByName(String name) {
        return hs.getPatientsByName(name).stream().map(PatientProxy::new).collect(Collectors.toList());
    }
}
