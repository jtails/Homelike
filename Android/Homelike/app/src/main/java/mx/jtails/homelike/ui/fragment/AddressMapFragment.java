package mx.jtails.homelike.ui.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.jtails.android.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.request.single.RetrieveLocationTask;
import mx.jtails.homelike.request.single.RetrieveLocationTask.OnLocationDataRetrievedListener;
import mx.jtails.homelike.ui.HomeActivity;

/**
 * Created by GrzegorzFeathers on 9/4/14.
 */
public class AddressMapFragment extends Fragment
    implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        OnLocationDataRetrievedListener, LocationListener {

    private MapView mAddressMap = null;
    private ProgressDialog mLocationDialog;

    private LocationClient mLocationClient;
    private LatLng mSelectedLatLng;

    public AddressMapFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        MapsInitializer.initialize(activity);
        this.mLocationClient = new LocationClient(activity, this, this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_map, container, false);

        this.mAddressMap = (MapView) view.findViewById(R.id.map_address);
        this.mAddressMap.onCreate(savedInstanceState);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        ((Button) view.findViewById(R.id.btn_select)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveLocationData();
            }
        });
        this.setupMap();
    }

    private void retrieveLocationData() {
        this.mLocationDialog = ProgressDialog.show(this.getActivity(), null,
                this.getString(R.string.wait), false, false);
        new RetrieveLocationTask(this.getActivity(), this).execute(this.mSelectedLatLng);
    }

    private void setupMap() {
        GoogleMap map = this.mAddressMap.getMap();
        map.setMyLocationEnabled(true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                moveMapCameraTo(latLng);
            }
        });

        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {}

            @Override
            public void onMarkerDrag(Marker marker) {}

            @Override
            public void onMarkerDragEnd(Marker marker) {
                moveMapCameraTo(marker.getPosition());
            }
        });

        UiSettings mapUISettings = map.getUiSettings();
        mapUISettings.setMyLocationButtonEnabled(true);
        mapUISettings.setRotateGesturesEnabled(false);
        mapUISettings.setZoomControlsEnabled(false);
        mapUISettings.setTiltGesturesEnabled(false);
    }

    @Override
    public void onStart() {
        super.onStart();
        this.mLocationClient.connect();
    }

    @Override
    public void onResume() {
        this.mAddressMap.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.mAddressMap.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAddressMap.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        this.mAddressMap.onLowMemory();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location currentLocation = this.mLocationClient.getLastLocation();
        if(currentLocation == null){
            this.mLocationDialog = ProgressDialog.show(this.getActivity(), null,
                    this.getString(R.string.wait), false, false);
            this.mLocationClient.requestLocationUpdates(
                    new LocationRequest().setNumUpdates(1), this);
            return;
        }
        LatLng currentLocationLatLng = new LatLng(currentLocation.getLatitude(),
                currentLocation.getLongitude());
        this.moveMapCameraTo(currentLocationLatLng);
    }

    public void moveMapCameraTo(LatLng latLng) {
        this.mSelectedLatLng = latLng;
        CameraPosition position = new CameraPosition.Builder()
                .target(this.mSelectedLatLng)
                .zoom(17)
                .build();
        this.mAddressMap.getMap().animateCamera(CameraUpdateFactory.newCameraPosition(position));
        this.mAddressMap.getMap().clear();
        this.mAddressMap.getMap().addMarker(new MarkerOptions()
                .position(this.mSelectedLatLng)
                .draggable(true));
    }

    @Override
    public void onDisconnected() {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this.getActivity(), 300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ((HomeActivity) this.getActivity()).singlePop();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 300 && resultCode == ActionBarActivity.RESULT_OK) {
            this.mLocationClient.connect();
        }
    }

    @Override
    public void onLocationDataRetrieved(final Direccion address) {
        this.mLocationDialog.dismiss();
        this.mLocationDialog = null;
        ((HomeActivity) this.getActivity()).pushToStack(
                AddressDetailsFragment.newInstance(address, false),
                AddressMapFragment.class.getName());
    }

    @Override
    public void onLocationChanged(Location location) {
        if(this.mLocationClient != null){
            this.mLocationClient.removeLocationUpdates(this);
        }
        if(this.mLocationDialog != null){
            this.mLocationDialog.dismiss();
            this.mLocationDialog = null;
        }
        LatLng currentLocationLatLng = new LatLng(location.getLatitude(),
                location.getLongitude());
        this.moveMapCameraTo(currentLocationLatLng);
    }

    public interface OnAddressLocationSelectedListener {
        public void onAddressLocationSelectedListener(Direccion address);
    }

}
