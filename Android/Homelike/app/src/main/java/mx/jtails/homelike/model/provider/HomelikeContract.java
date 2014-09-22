package mx.jtails.homelike.model.provider;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class HomelikeContract {

    interface ServicesColumns {
        String SERVICE_ID = "services_id";
        String SERVICE_NAME = "service_name";
        String SERVICE_ICON_URL = "service_icon_url";
    }

    interface AddressesColumns {
        String ADDRESS_ID = "address_id";
        String ADDRESS_ALIAS = "address_alias";
        String ADDRESS_STREET = "address_street";
        String ADDRESS_STREET_NUMBER = "address_street_number";
        String ADDRESS_INTERIOR = "address_interior";
        String ADDRESS_COLONY = "address_colony";
        String ADDRESS_ZIP_CODE = "address_zip_code";
        String ADDRESS_CITY = "address_city";
        String ADDRESS_STATE = "address_state";
        String ADDRESS_COUNTRY = "address_country";
        String ADDRESS_DEFAULT = "address_default";
        String ADDRESS_REFERENCE = "address_reference";

        String ADDRESS_LATITUDE = "address_latitude";
        String ADDRESS_LONGITUDE = "address_longitude";
    }

}
