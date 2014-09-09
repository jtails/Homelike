package mx.jtails.homelike.model.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * Created by GrzegorzFeathers on 9/1/14.
 */

// TODO Replace HomelikeDBManager
public class HomelikeProvider extends ContentProvider {

    private HomelikeDatabase mOpenHelper;

    @Override
    public boolean onCreate() {
        this.mOpenHelper = new HomelikeDatabase(this.getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        return null;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
