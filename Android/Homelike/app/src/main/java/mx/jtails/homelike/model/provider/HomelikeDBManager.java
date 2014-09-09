package mx.jtails.homelike.model.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import mx.jtails.homelike.api.model.Direccion;
import mx.jtails.homelike.api.model.Servicio;

/**
 * Created by GrzegorzFeathers on 9/3/14.
 */
public class HomelikeDBManager {

    private static HomelikeDBManager dbManager = null;

    private HomelikeDatabase dbHelper;
    private SQLiteDatabase db = null;

    private HomelikeDBManager(Context context){
        dbHelper = new HomelikeDatabase(context);
    }

    public static void init(Context context){
        if(dbManager == null){
            dbManager = new HomelikeDBManager(context);
        }
    }

    public static HomelikeDBManager getDBManager() {
        return dbManager;
    }

    private void prepareDB(boolean willWrite) {
        if(this.db != null){
            return;
        }
        if(willWrite){
            this.db = this.dbHelper.getWritableDatabase();
        } else {
            this.db = this.dbHelper.getReadableDatabase();
        }
    }

    private void releaseDB() {
        if(this.db == null){
            return;
        }

        this.db.close();
        this.db = null;
    }

    // TODO validate affected rows
    public int saveServices(List<Servicio> services){

        this.prepareDB(true);

        int affectedRows = 0;
        for(Servicio service : services) {
            ContentValues cv = new ContentValues();
            cv.put(HomelikeContract.ServicesColumns.SERVICE_ID, service.getIdServicio());
            cv.put(HomelikeContract.ServicesColumns.SERVICE_NAME, service.getNombre());
            cv.put(HomelikeContract.ServicesColumns.SERVICE_ICON_URL, "");

            try {
                long res = this.db.insertOrThrow(HomelikeDatabase.Tables.SERVICES, null, cv);
                if(res != -1) { affectedRows += 1; }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        this.releaseDB();

        System.out.println("Services saved: Affected rows - " + affectedRows);
        return affectedRows;

    }

    public Servicio getService(int serviceId){
        this.prepareDB(false);

        String [] projection = {
                HomelikeContract.ServicesColumns.SERVICE_ID,
                HomelikeContract.ServicesColumns.SERVICE_NAME };
        String [] selection = { String.valueOf(serviceId) };

        Cursor c = this.db.query(HomelikeDatabase.Tables.SERVICES,
                projection, HomelikeContract.ServicesColumns.SERVICE_ID + " = ?", selection,
                null, null, null);

        if(!c.moveToFirst()) { return null; }
        Servicio service = new Servicio();
        service.setIdServicio(c.getInt(c.getColumnIndex(HomelikeContract.ServicesColumns.SERVICE_ID)));
        service.setNombre(c.getString(c.getColumnIndex(HomelikeContract.ServicesColumns.SERVICE_NAME)));

        c.close();
        this.releaseDB();
        return service;
    }

    public List<Servicio> loadServices(){
        List<Servicio> services = new ArrayList<Servicio>();
        this.prepareDB(false);

        String [] projection = {
                HomelikeContract.ServicesColumns.SERVICE_ID,
                HomelikeContract.ServicesColumns.SERVICE_NAME };

        Cursor c = db.query(HomelikeDatabase.Tables.SERVICES,
                projection, null, null, null, null, null);

        if(!c.moveToFirst()) { return services; }
        do {
            Servicio s = new Servicio();
            s.setIdServicio(c.getInt(c.getColumnIndex(HomelikeContract.ServicesColumns.SERVICE_ID)));
            s.setNombre(c.getString(c.getColumnIndex(HomelikeContract.ServicesColumns.SERVICE_NAME)));
            services.add(s);
        } while(c.moveToNext());

        c.close();
        this.releaseDB();

        return services;
    }

    // TODO validate affected rows
    public int registerAddress(String alias, Direccion address) {
        this.prepareDB(true);

        ContentValues cv = new ContentValues();
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_ALIAS, alias);
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_STREET, address.getCalle());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_STREET_NUMBER, address.getNexterior());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_INTERIOR, address.getNinterior());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_COLONY, address.getColonia());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_ZIP_CODE, address.getCp());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_CITY, address.getDelegacion());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_STATE, address.getEstado());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_REFERENCE, address.getReferencia1());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_DEFAULT, address.getEsDefault());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_LATITUDE, address.getLatitud());
        cv.put(HomelikeContract.AddressesColumns.ADDRESS_LONGITUDE, address.getLongitud());

        int affectedRows = 0;
        try {
            long res = this.db.insertOrThrow(HomelikeDatabase.Tables.ADDRESSES, null, cv);
            if(res != -1) { affectedRows += 1; }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Address Registered: Affected rows - " + affectedRows);
        this.releaseDB();
        return affectedRows;
    }

    public List<Direccion> loadAddresses() {
        List<Direccion> addresses = new ArrayList<Direccion>();
        this.prepareDB(false);

        String [] projection = {
                BaseColumns._ID,
                HomelikeContract.AddressesColumns.ADDRESS_ALIAS,
                HomelikeContract.AddressesColumns.ADDRESS_STREET,
                HomelikeContract.AddressesColumns.ADDRESS_STREET_NUMBER,
                HomelikeContract.AddressesColumns.ADDRESS_INTERIOR,
                HomelikeContract.AddressesColumns.ADDRESS_COLONY,
                HomelikeContract.AddressesColumns.ADDRESS_ZIP_CODE,
                HomelikeContract.AddressesColumns.ADDRESS_CITY,
                HomelikeContract.AddressesColumns.ADDRESS_STATE,
                HomelikeContract.AddressesColumns.ADDRESS_REFERENCE,
                HomelikeContract.AddressesColumns.ADDRESS_DEFAULT,
                HomelikeContract.AddressesColumns.ADDRESS_LATITUDE,
                HomelikeContract.AddressesColumns.ADDRESS_LONGITUDE };

        Cursor c = db.query(HomelikeDatabase.Tables.ADDRESSES,
                projection, null, null, null, null, null);

        if(!c.moveToFirst()) { return addresses; }
        do {
            Direccion d = new Direccion();
            d.setIdDireccion(c.getInt(c.getColumnIndex(BaseColumns._ID)));
            d.setReferencia2(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_ALIAS)));
            d.setCalle(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_STREET)));
            d.setNexterior(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_STREET_NUMBER)));
            d.setNinterior(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_INTERIOR)));
            d.setColonia(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_COLONY)));
            d.setCp(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_ZIP_CODE)));
            d.setDelegacion(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_CITY)));
            d.setEstado(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_STATE)));
            d.setReferencia1(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_REFERENCE)));
            d.setEsDefault(c.getInt(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_DEFAULT)));
            d.setLatitud(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_LATITUDE)));
            d.setLongitud(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_LONGITUDE)));
            addresses.add(d);
        } while(c.moveToNext());

        c.close();
        this.releaseDB();

        return addresses;
    }

    public Direccion getAddress(int addressId){
        this.prepareDB(false);

        String [] projection = {
                BaseColumns._ID,
                HomelikeContract.AddressesColumns.ADDRESS_ALIAS,
                HomelikeContract.AddressesColumns.ADDRESS_STREET,
                HomelikeContract.AddressesColumns.ADDRESS_STREET_NUMBER,
                HomelikeContract.AddressesColumns.ADDRESS_INTERIOR,
                HomelikeContract.AddressesColumns.ADDRESS_COLONY,
                HomelikeContract.AddressesColumns.ADDRESS_ZIP_CODE,
                HomelikeContract.AddressesColumns.ADDRESS_CITY,
                HomelikeContract.AddressesColumns.ADDRESS_STATE,
                HomelikeContract.AddressesColumns.ADDRESS_REFERENCE,
                HomelikeContract.AddressesColumns.ADDRESS_DEFAULT,
                HomelikeContract.AddressesColumns.ADDRESS_LATITUDE,
                HomelikeContract.AddressesColumns.ADDRESS_LONGITUDE };
        String [] selection = { String.valueOf(addressId) };

        Cursor c = this.db.query(HomelikeDatabase.Tables.ADDRESSES,
                projection, BaseColumns._ID + " = ?", selection, null, null, null);

        if(!c.moveToFirst()) { return null; }
        Direccion address = new Direccion();
        address.setIdDireccion(c.getInt(c.getColumnIndex(BaseColumns._ID)));
        address.setReferencia2(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_ALIAS)));
        address.setCalle(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_STREET)));
        address.setNexterior(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_STREET_NUMBER)));
        address.setNinterior(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_INTERIOR)));
        address.setColonia(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_COLONY)));
        address.setCp(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_ZIP_CODE)));
        address.setDelegacion(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_CITY)));
        address.setEstado(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_STATE)));
        address.setReferencia1(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_REFERENCE)));
        address.setEsDefault(c.getInt(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_DEFAULT)));
        address.setLatitud(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_LATITUDE)));
        address.setLongitud(c.getString(c.getColumnIndex(HomelikeContract.AddressesColumns.ADDRESS_LONGITUDE)));

        c.close();
        this.releaseDB();
        return address;
    }

    public boolean hasFavoriteAddress() {
        this.prepareDB(false);

        String [] projection = { BaseColumns._ID };
        String where = HomelikeContract.AddressesColumns.ADDRESS_DEFAULT + " = 1";

        Cursor c = db.query(HomelikeDatabase.Tables.ADDRESSES,
                projection, where, null, null, null, null);

        boolean hasFavourite = c.getCount() > 0;

        c.close();
        this.releaseDB();

        return hasFavourite;
    }

}
