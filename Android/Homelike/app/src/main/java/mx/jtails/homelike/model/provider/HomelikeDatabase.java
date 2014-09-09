package mx.jtails.homelike.model.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */
public class HomelikeDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "homelike.db";

    private static final int VER_2014_RELEASE_A = 103;
    private static final int CUR_DATABASE_VERSION = VER_2014_RELEASE_A;

    private final Context mContext;

    interface Tables {
        String SERVICES = "services";
        String ADDRESSES = "addresses";

        //Tables to be deleted
        interface DeprecatedTables {
        }
    }

    interface Triggers {
        //Possible triggers

        //Triggers to be deleted
        interface DeprecatedTriggers {
        }
    }

    public HomelikeDatabase(Context context) {
        super(context, DATABASE_NAME, null, CUR_DATABASE_VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Tables.SERVICES + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HomelikeContract.ServicesColumns.SERVICE_ID + " INTEGER NOT NULL, "
                + HomelikeContract.ServicesColumns.SERVICE_NAME + " TEXT NOT NULL, "
                + HomelikeContract.ServicesColumns.SERVICE_ICON_URL + " TEXT, "
                + "UNIQUE (" + HomelikeContract.ServicesColumns.SERVICE_ID + ") ON CONFLICT REPLACE)");

        db.execSQL("CREATE TABLE " + Tables.ADDRESSES + "("
                + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HomelikeContract.AddressesColumns.ADDRESS_ALIAS + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_STREET + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_STREET_NUMBER + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_INTERIOR + " TEXT, "
                + HomelikeContract.AddressesColumns.ADDRESS_COLONY + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_ZIP_CODE + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_CITY + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_STATE + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_REFERENCE + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_DEFAULT + " BOOLEAN NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_LATITUDE + " TEXT NOT NULL, "
                + HomelikeContract.AddressesColumns.ADDRESS_LONGITUDE + " TEXT NOT NULL, "
                + "UNIQUE (" + HomelikeContract.AddressesColumns.ADDRESS_ALIAS + ") ON CONFLICT REPLACE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Tables.SERVICES);
        db.execSQL("DROP TABLE IF EXISTS " + Tables.ADDRESSES);
        this.onCreate(db);
    }
}
