package connection;

import org.hl7.fhir.dstu3.model.*;
import ca.uhn.fhir.rest.gclient.ICriterion;
import ca.uhn.fhir.rest.gclient.StringClientParam;
import ca.uhn.fhir.rest.server.exceptions.ResourceNotFoundException;
import org.hl7.fhir.instance.model.api.IBaseBundle;

import java.util.ArrayList;
import java.util.List;

public class HapiGenericService<E extends BaseResource> {
    private HapiService hs;
    private final Class<E> type;
    private final int maxListSize = 100;

    HapiGenericService(HapiService hs, Class<E> type) {
        this.hs = hs;
        this.type = type;
    }

    public List<E> getList() {
        return getList(maxListSize);
    }


        public List<E> getList(int maxResultAmount) {
        List<E> list = new ArrayList<>();
        try {
            IBaseBundle bundle = hs.getClient().search().forResource(type)
                    .prettyPrint()
                    .count(maxResultAmount)
                    .execute();
            ((Bundle) bundle).getEntry().forEach(b -> list.add((E) b.getResource()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<E> search(ICriterion<StringClientParam> criterion){
        return search(criterion, maxListSize);
    }

    public List<E> search(ICriterion<StringClientParam> criterion, int maxResultsAmount) {
        List<E> list = new ArrayList<>();
        try {
            IBaseBundle bundle = hs.getClient()
                    .search()
                    .forResource(type)
                    .where(criterion)
                    .count(maxResultsAmount)
                    .execute();
            ((Bundle) bundle).getEntry().forEach(b -> list.add((E) b.getResource()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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
