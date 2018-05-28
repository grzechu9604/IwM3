package connection;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.rest.client.api.IGenericClient;

import java.util.*;

public class HapiService {
    private static final String serverDomainURL = "http://fhirtest.uhn.ca/baseDstu2";
    private static final  String requestUrlPattern = "http://hl7.org/fhir/%s/";
    private IGenericClient client;
    private Map<Class, HapiGenericService> services;

    public HapiService() {
        org.apache.log4j.BasicConfigurator.configure();
        FhirContext ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverDomainURL);
        services = new HashMap<>();
    }

    private String getRequestUrl(Class c) {
        Formatter f = new Formatter();
        return f.format(requestUrlPattern, c.getSimpleName()).toString();
    }

    private String prepareRequestString(Class c, String id) {
        return getRequestUrl(c) + id;
    }

    public Patient getPatientById(String id) {
        String requestString = prepareRequestString(Patient.class, id);
        return (Patient) getService(Patient.class).get(requestString);
    }

    private HapiGenericService getService(Class c) {
        if (services.get(c) == null) {
            services.put(c, new HapiGenericService(this, c));
        }
        return services.get(c);

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
