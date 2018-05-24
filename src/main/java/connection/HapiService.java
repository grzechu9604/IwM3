package connection;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.api.Include;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.resource.Person;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HapiService {
    private String serverDomainURL = "http://fhirtest.uhn.ca/baseDstu2";
    private String patientRequestString = "http://hl7.org/fhir/Patient/";
    private String patientClassNameForRequest = "patient";
    private IGenericClient client;
    private Map<Class, String> requestUrlMap;

    public HapiService(){
        org.apache.log4j.BasicConfigurator.configure();
        FhirContext ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverDomainURL);
        requestUrlMap = prepareRequestUrlMap();
    }

    private Map<Class, String> prepareRequestUrlMap(){
        Map<Class, String> map = new HashMap<Class, String>();

        map.put(Patient.class, patientRequestString);

        return map;
    }

    private String prepareRequestString(Class c, String id) {
        return requestUrlMap.get(c) + id;
    }

    public Patient getPatientById(String id){
        try {
            String requestString = prepareRequestString(Patient.class, id);
            return (Patient) client.read().resource(patientClassNameForRequest).withUrl(requestString).execute();
        } catch (ResourceNotFoundException rnf){
            return null;
        }
    }
}
