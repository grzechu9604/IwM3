package connection;

import ca.uhn.fhir.model.dstu2.resource.BaseResource;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import org.hl7.fhir.instance.model.api.IBaseBundle;

import java.util.ArrayList;
import java.util.List;

public class HapiListService<E extends BaseResource> {
    private HapiService hs;
    private final Class<E> type;

    public HapiListService(HapiService hs, Class<E> type){
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
}
