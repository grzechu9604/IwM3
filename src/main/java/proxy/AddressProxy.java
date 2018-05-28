package proxy;

import ca.uhn.fhir.model.dstu2.composite.AddressDt;
import ca.uhn.fhir.model.dstu2.resource.Patient;

public class AddressProxy {
    private AddressDt addressDt;

    private String country;
    private String line;
    private String postalCode;
    private String city;

    AddressProxy(AddressDt addressDt) {
        this.addressDt = addressDt;
    }

    public String getCountry() {
        if (country == null) {
            country = getCountryFromDt();
        }
        return country;
    }

    public String getLine() {
        if (line == null) {
            line = getLineFromDt();
        }
        return line;
    }

    public String getPostalCode() {
        if (postalCode == null) {
            postalCode = getPostalCodeFromDt();
        }
        return postalCode;
    }

    public String getCity() {
        if (city == null) {
            city = getCityFromDt();
        }
        return city;
    }

    private String getCountryFromDt() {
        try {
            return addressDt.getCountry();
        } catch (Exception e) {
            return "";
        }
    }

    private String getLineFromDt() {
        try {
            return addressDt.getLineFirstRep().getValueNotNull();
        } catch (Exception e) {
            return "";
        }
    }

    private String getPostalCodeFromDt() {
        try {
            return addressDt.getPostalCode();
        } catch (Exception e) {
            return "";
        }
    }

    private String getCityFromDt() {
        try {
            return addressDt.getCity();
        } catch (Exception e) {
            return "";
        }
    }
}
