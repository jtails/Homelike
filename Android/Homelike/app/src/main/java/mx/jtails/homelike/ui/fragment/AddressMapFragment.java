package mx.jtails.homelike.ui.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import mx.jtails.homelike.R;
import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.request.single.RetrieveLocationTask;
import mx.jtails.homelike.request.single.RetrieveLocationTask.OnLocationDataRetrievedListener;

/**
 * Created by GrzegorzFeathers on 9/4/14.
 */
public class AddressMapFragment extends Fragment
    implements GooglePlayServicesClient.ConnectionCallbacks,
        GooglePlayServicesClient.OnConnectionFailedListener,
        OnLocationDataRetrievedListener {

    private MapView mAddressMap = null;
    private ProgressDialog mLocationDialog;

    private OnAddressLocationSelectedListener mAddressSelectedListener;
    private LocationClient mLocationClient;
    private LatLng mSelectedLatLng;

    public AddressMapFragment(){}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.mAddressSelectedListener = (OnAddressLocationSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement AddressMapFragment.OnAddressLocationSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_address_map, container, false);

        this.mAddressMap = (MapView) view.findViewById(R.id.map_address);
        this.mAddressMap.onCreate(savedInstanceState);
        MapsInitializer.initialize(this.getActivity());

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
        this.mLocationDialog = ProgressDialog.show(this.getActivity(), "Location",
                "Retrieving location data...", false, false);
        new RetrieveLocationTask(this.getActivity(), this).execute(this.mSelectedLatLng);
    }

    private void setupMap() {
        GoogleMap map = this.mAddressMap.getMap();
        map.setMyLocationEnabled(true);
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mSelectedLatLng = latLng;
                moveMapCameraToSelected();
            }
        });

        final TypedArray styledAttributes = this.getActivity().getTheme().obtainStyledAttributes(
                new int[] { android.R.attr.actionBarSize });
        int actionBarSize = (int) styledAttributes.getDimension(0, 0);
        map.setPadding(0, actionBarSize, 0, actionBarSize);
        styledAttributes.recycle();

        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {}

            @Override
            public void onMarkerDrag(Marker marker) {
                moveMapCameraTo(marker.getPosition());
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                mSelectedLatLng = marker.getPosition();
                moveMapCameraToSelected();
            }
        });

        UiSettings mapUISettings = map.getUiSettings();
        mapUISettings.setMyLocationButtonEnabled(true);
        mapUISettings.setRotateGesturesEnabled(false);
        mapUISettings.setZoomControlsEnabled(false);
        mapUISettings.setTiltGesturesEnabled(false);

        this.mLocationClient = new LocationClient(this.getActivity(), this, this);
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
        GoogleMap map = this.mAddressMap.getMap();

        Location currentLocation = this.mLocationClient.getLastLocation();
        LatLng currentLocationLatLng = new LatLng(currentLocation.getLatitude(),
                currentLocation.getLongitude());

        this.mSelectedLatLng = currentLocationLatLng;
        this.moveMapCameraToSelected();
    }

    public void moveMapCameraTo(LatLng latLng) {
        CameraPosition position = new CameraPosition.Builder()
                .target(this.mSelectedLatLng)
                .zoom(17)
                .build();
        this.mAddressMap.getMap().animateCamera(CameraUpdateFactory.newCameraPosition(position));
    }

    private void moveMapCameraToSelected() {
        this.moveMapCameraTo(this.mSelectedLatLng);
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
        Toast.makeText(this.getActivity(), String.valueOf(connectionResult.getErrorCode()), Toast.LENGTH_SHORT).show();
        if(connectionResult.hasResolution()) {
            try {
                connectionResult.startResolutionForResult(this.getActivity(), 300);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this.getActivity(), "Failed to load Google Play Services...",
                    Toast.LENGTH_SHORT).show();
            this.getActivity().finish();
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
        if(address == null){
            AlertDialog alertDialog = new AlertDialog.Builder(this.getActivity())
                    .setCancelable(true)
                    .setTitle("Location")
                    .setMessage("No location data could be found at the point you selected. Would you like to continue?")
                    .setNegativeButton("Reselect", null)
                    .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if(mAddressSelectedListener != null){
                                mAddressSelectedListener.onAddressLocationSelectedListener(address);
                            }
                        }
                    }).show();
        } else {
            if(this.mAddressSelectedListener != null){
                this.mAddressSelectedListener.onAddressLocationSelectedListener(address);
            }
        }
    }

    public interface OnAddressLocationSelectedListener {
        public void onAddressLocationSelectedListener(Direccion address);
    }

}
