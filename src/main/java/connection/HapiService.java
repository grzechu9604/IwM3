package connection;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.dstu2.resource.*;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HapiService {
    private String serverDomainURL = "http://fhirtest.uhn.ca/baseDstu2";
    private String patientRequestString = "http://hl7.org/fhir/Patient/";
    private String patientClassNameForRequest = "patient";
    private IGenericClient client;
    private Map<Class, String> requestUrlMap;
    private Map<Class, HapiGenericService> services;

    public HapiService() {
        org.apache.log4j.BasicConfigurator.configure();
        FhirContext ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverDomainURL);
        requestUrlMap = prepareRequestUrlMap();
        services = new HashMap<>();
    }

    private Map<Class, String> prepareRequestUrlMap() {
        Map<Class, String> map = new HashMap<>();

        map.put(Patient.class, patientRequestString);

        return map;
    }

    private String prepareRequestString(Class c, String id) {
        return requestUrlMap.get(c) + id;
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
