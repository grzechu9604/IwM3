package connection;

import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.rest.gclient.ICriterion;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.instance.model.api.IBaseBundle;

import java.util.ArrayList;
import java.util.List;

public class HapiGenericService<E extends BaseResource> {
    private HapiService hs;
    private final Class<E> type;

    HapiGenericService(HapiService hs, Class<E> type) {
        this.hs = hs;
        this.type = type;
    }


    public List<E> getList() {
        List<E> list = new ArrayList<>();
        try {
            IBaseBundle bundle = hs.getClient().search().forResource(type)
                    .prettyPrint()
                    .execute();
            ((Bundle) bundle).getEntry().forEach(b -> list.add((E) b.getResource()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public E search(ICriterion<StringClientParam> criterion) {
        try {
            return (E) hs.getClient()
                    .search()
                    .forResource(type.getSimpleName().toLowerCase())
                    .where(criterion)
                    .execute();
        } catch (ResourceNotFoundException rnf) {
            return null;
        }
    }

    public E get(String id) {
        try {
            return hs.getClient()
                    .read()
                    .resource(type)
                    .withId(id)
                    .execute();
        } catch (ResourceNotFoundException rnf) {
            return null;
        }
    }
}
