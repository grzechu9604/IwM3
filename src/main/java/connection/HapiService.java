package connection;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.gclient.ICriterion;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.model.dstu2.resource.*;

import java.net.SocketTimeoutException;
import java.util.*;

public class HapiService {
    private static final String serverDomainURL = "http://hapi.fhir.org/baseDstu2";
    private IGenericClient client;
    private Map<Class, HapiGenericService> services;

    public HapiService() {
        org.apache.log4j.BasicConfigurator.configure();
        FhirContext ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverDomainURL);
        services = new HashMap<>();
    }

    private ICriterion<StringClientParam> generateStringCriterion(String criterionName, String value) {
        return new StringClientParam(criterionName).matches().value(value);
    }

    public Patient getPatientById(String id) {
        return (Patient) getService(Patient.class).get(id);
    }

    public Observation getObservationById(String id) {
        return (Observation) getService(Observation.class).get(id);
    }

    public Medication getMedicationById(String id) {
        return (Medication) getService(Medication.class).get(id);
    }

    public MedicationStatement getMedicationStatementById(String id) {
        return (MedicationStatement) getService(MedicationStatement.class).get(id);
    }

    private HapiGenericService getService(Class c) {
        if (services.get(c) == null) {
            services.put(c, new HapiGenericService(this, c));
        }
        return services.get(c);
    }

    public List<Observation> getObservationsByPatient(String patientId) {
        return getService(Observation.class).search(generateStringCriterion("patient", patientId.toString()));
    }

    public List<MedicationStatement> getMedicationStatementByPatient(String patientId) {
        return getService(MedicationStatement.class).search(generateStringCriterion("patient", patientId.toString()));
    }

    public List<Patient> getPatientsByName(String name) {
        return getService(Patient.class).search(generateStringCriterion("name", name), 50, 3);
    }

    public List<Patient> getPatients() {
        return getService(Patient.class).getList();
    }

    public List<Observation> getObservations() {
        return getService(Observation.class).getList();
    }

    public List<Medication> getMedications() {
        return getService(Medication.class).getList();
    }

    public List<MedicationStatement> getMedicationStatements() {
        return getService(MedicationStatement.class).getList();
    }

    IGenericClient getClient() {
        return client;
    }
}
