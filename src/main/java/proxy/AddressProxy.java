package proxy;

import org.hl7.fhir.dstu3.model.*;

public class AddressProxy {
    private Address address;

    private String country;
    private String line;
    private String postalCode;
    private String city;

    AddressProxy(Address addressDt) {
        this.address = addressDt;
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
            return address.getCountry();
        } catch (Exception e) {
            return "";
        }
    }

    private String getLineFromDt() {
        try {
            return address.getLine().get(0).getValueNotNull();
        } catch (Exception e) {
            return "";
        }
    }

    private String getPostalCodeFromDt() {
        try {
            return address.getPostalCode();
        } catch (Exception e) {
            return "";
        }
    }

    private String getCityFromDt() {
        try {
            return address.getCity();
        } catch (Exception e) {
            return "";
        }
    }
}
