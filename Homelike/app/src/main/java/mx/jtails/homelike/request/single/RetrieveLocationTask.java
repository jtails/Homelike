package mx.jtails.homelike.request.single;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

import mx.jtails.homelike.api.model.Direccion;

/**
 * Created by GrzegorzFeathers on 9/7/14.
 */
public class RetrieveLocationTask extends AsyncTask<LatLng, Integer, Address> {

    private Context mContext;
    private OnLocationDataRetrievedListener mListener;

    public RetrieveLocationTask(Context context, OnLocationDataRetrievedListener listener){
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected Address doInBackground(LatLng... params) {
        LatLng location = params[0];

        Geocoder geocoder = new Geocoder(this.mContext);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(
                    location.latitude, location.longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if(addresses == null || addresses.size() < 1){
            return null;
        } else {
            return addresses.get(0);
        }
    }

    @Override
    protected void onPostExecute(Address gAddress) {
        Direccion address;

        if(gAddress == null){
            address = null;
        } else {
            address = new Direccion();
            address.setLatitud(String.valueOf(gAddress.getLatitude()));
            address.setLongitud(String.valueOf(gAddress.getLongitude()));
            address.setCalle(gAddress.getThoroughfare());
            address.setNexterior(gAddress.getSubThoroughfare());
            address.setCp(gAddress.getPostalCode() != null &&
                    !gAddress.getPostalCode().equals("") ? gAddress.getPostalCode()
                    : gAddress.getAddressLine(2).split(" ")[0]);
            String[] addressLine1 = gAddress.getAddressLine(1).split(",");
            address.setDelegacion(addressLine1.length > 1 ? addressLine1[1] : "");
            address.setEstado(gAddress.getAdminArea());
            address.setColonia(gAddress.getAddressLine(1).split(",")[0]);
            address.setPais(gAddress.getCountryName());
        }

        if(this.mListener != null){
            this.mListener.onLocationDataRetrieved(address);
        }
    }

    public interface OnLocationDataRetrievedListener {
        public void onLocationDataRetrieved(Direccion address);
    }
}