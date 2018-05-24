package connection;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.rest.client.api.IGenericClient;

import java.io.IOException;

public class HapiService {
    private String serverDomainURL = "http://fhirtest.uhn.ca/baseDstu2";
    private String metadataMetode = "metadata";
    private String requestMetodGET = "GET";
    private IGenericClient client;

    public HapiService(){
        org.apache.log4j.BasicConfigurator.configure();
        FhirContext ctx = FhirContext.forDstu2();
        client = ctx.newRestfulGenericClient(serverDomainURL);
    }

    public void getFirstResponse(){

// Perform a search
        Bundle results = client
                .search()
                .forResource(Patient.class)
                .where(Patient.FAMILY.matches().value("duck"))
                .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
                .execute();
    }
}
