package assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.DBConstants;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.PSU;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.PSURepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Repository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class PSURepositoryImpl extends SQLiteOpenHelper implements PSURepository {
    public static final String TABLE_NAME = "psu";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_WATTS = "watts";
    public static final String COLUMN_FOUR_PIN_MOLEX = "four_pin_molex";
    public static final String COLUMN_SATA_CONNECTORS = "sata_connectors";
    public static final String COLUMN_FLOPPY_CONNECTORS = "floppy_connectors";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_WATTS + " INTEGER  NOT NULL , "
            + COLUMN_FOUR_PIN_MOLEX + " INTEGER  NOT NULL , "
            + COLUMN_SATA_CONNECTORS + " INTEGER  NOT NULL , "
            + COLUMN_FLOPPY_CONNECTORS + " INTEGER  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public PSURepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public PSU findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_WATTS,
                        COLUMN_FOUR_PIN_MOLEX,
                        COLUMN_SATA_CONNECTORS,
                        COLUMN_FLOPPY_CONNECTORS,
                        COLUMN_STOCK,
                        COLUMN_ACTIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final PSU psu = new PSU.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .watts(cursor.getInt(cursor.getColumnIndex(COLUMN_WATTS)))
                    .four_pin_molex(cursor.getInt(cursor.getColumnIndex(COLUMN_FOUR_PIN_MOLEX)))
                    .sata_connectors(cursor.getInt(cursor.getColumnIndex(COLUMN_SATA_CONNECTORS)))
                    .floppy_connectors(cursor.getInt(cursor.getColumnIndex(COLUMN_FLOPPY_CONNECTORS)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return psu;
        } else {
            return null;
        }
    }

    @Override
    public PSU save(PSU entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_WATTS, entity.getWatts());
        values.put(COLUMN_FOUR_PIN_MOLEX, entity.getFour_pin_molex());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_SATA_CONNECTORS, entity.getSata_connectors());
        values.put(COLUMN_FLOPPY_CONNECTORS, entity.getFloppy_connectors());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        PSU insertedEntity = new PSU.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public PSU update(PSU entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_WATTS, entity.getWatts());
        values.put(COLUMN_FOUR_PIN_MOLEX, entity.getFour_pin_molex());
        values.put(COLUMN_SATA_CONNECTORS, entity.getSata_connectors());
        values.put(COLUMN_FLOPPY_CONNECTORS, entity.getFloppy_connectors());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_ACTIVE, entity.isActive());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public PSU delete(PSU entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<PSU> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PSU> psuSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PSU psu = new PSU.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .watts(cursor.getInt(cursor.getColumnIndex(COLUMN_WATTS)))
                        .four_pin_molex(cursor.getInt(cursor.getColumnIndex(COLUMN_FOUR_PIN_MOLEX)))
                        .sata_connectors(cursor.getInt(cursor.getColumnIndex(COLUMN_SATA_CONNECTORS)))
                        .floppy_connectors(cursor.getInt(cursor.getColumnIndex(COLUMN_FLOPPY_CONNECTORS)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                psuSet.add(psu);
            } while (cursor.moveToNext());
        }
        return psuSet;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
