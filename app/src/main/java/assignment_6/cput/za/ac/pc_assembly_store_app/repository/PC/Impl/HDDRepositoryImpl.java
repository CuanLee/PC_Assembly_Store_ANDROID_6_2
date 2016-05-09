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

import assignment_6.cput.za.ac.pc_assembly_store_app.conf.database.database.DBConstants;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.HDD;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.HDDRepository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class HDDRepositoryImpl extends SQLiteOpenHelper implements HDDRepository{
    public static final String TABLE_NAME = "hdd";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_SIZE_MB = "watts";
    public static final String COLUMN_RPM = "four_pin_molex";
    public static final String COLUMN_SATA = "sata_connectors";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_SIZE_MB + " REAL  NOT NULL , "
            + COLUMN_RPM + " REAL  NOT NULL , "
            + COLUMN_SATA + " INTEGER  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public HDDRepositoryImpl(Context context)
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
    public HDD findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_SIZE_MB,
                        COLUMN_RPM,
                        COLUMN_SATA,
                        COLUMN_STOCK,
                        COLUMN_ACTIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final HDD hdd = new HDD.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .size_MB(cursor.getDouble(cursor.getColumnIndex(COLUMN_SIZE_MB)))
                    .rpm(cursor.getDouble(cursor.getColumnIndex(COLUMN_RPM)))
                    .sata(cursor.getInt(cursor.getColumnIndex(COLUMN_SATA)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return hdd;
        } else {
            return null;
        }
    }

    @Override
    public HDD save(HDD entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_SIZE_MB, entity.getSize_MB());
        values.put(COLUMN_RPM, entity.getRpm());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_SATA, entity.getSata());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        HDD insertedEntity = new HDD.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public HDD update(HDD entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_SIZE_MB, entity.getSize_MB());
        values.put(COLUMN_RPM, entity.getRpm());
        values.put(COLUMN_SATA, entity.getSata());
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
    public HDD delete(HDD entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<HDD> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<HDD> hddSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final HDD ram = new HDD.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .size_MB(cursor.getDouble(cursor.getColumnIndex(COLUMN_SIZE_MB)))
                        .rpm(cursor.getDouble(cursor.getColumnIndex(COLUMN_RPM)))
                        .sata(cursor.getInt(cursor.getColumnIndex(COLUMN_SATA)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                hddSet.add(ram);
            } while (cursor.moveToNext());
        }
        return hddSet;
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
