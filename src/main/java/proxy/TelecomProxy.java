package proxy;

import org.hl7.fhir.dstu3.model.*;

import java.util.List;

public class TelecomProxy {
    private List<ContactPoint> contactPointDts;
    private String mails;
    private String phoneNumbers;

    private String phoneString = "phone";
    private String mailString = "email";

    TelecomProxy(List<ContactPoint> contactPointDts) {
        this.contactPointDts = contactPointDts;
    }

    private String collectElementByType(String type) {
        StringBuilder sb = new StringBuilder();

        contactPointDts.forEach(contactPointDt -> {
            if (contactPointDt.getSystem() != null && contactPointDt.getSystem().equals(type)) {
                sb.append(" ").append(contactPointDt.getValue());
            }
        });

        return sb.toString().trim();
    }

    private String getMailsFromArray() {
        return collectElementByType(mailString);
    }

    private String getPhoneNumbersFromArray() {
        return collectElementByType(phoneString);
    }

    public String getMails() {
        if (mails == null) {
            mails = getMailsFromArray();
        }
        return mails;
    }

    public String getPhoneNumbers() {
        if (phoneNumbers == null) {
            phoneNumbers = getPhoneNumbersFromArray();
        }
        return phoneNumbers;
    }
}
