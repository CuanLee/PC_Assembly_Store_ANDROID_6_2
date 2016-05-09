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
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Chassis;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.ChassisRepository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class ChassisRepositoryImpl extends SQLiteOpenHelper implements ChassisRepository{
    public static final String TABLE_NAME = "chassis";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_HDD_BAYS = "hddBays";
    public static final String COLUMN_CASE_FANS = "caseFans";
    public static final String COLUMN_FORM_FACTOR = "formFactor";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_HDD_BAYS + " INTEGER  NOT NULL , "
            + COLUMN_CASE_FANS + " INTEGER  NOT NULL , "
            + COLUMN_FORM_FACTOR + " TEXT  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public ChassisRepositoryImpl(Context context)
    {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    @Override
    public Chassis findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_HDD_BAYS,
                        COLUMN_CASE_FANS,
                        COLUMN_FORM_FACTOR,
                        COLUMN_STOCK,
                        COLUMN_ACTIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final Chassis chassis = new Chassis.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .hddBays(cursor.getInt(cursor.getColumnIndex(COLUMN_HDD_BAYS)))
                    .caseFans(cursor.getInt(cursor.getColumnIndex(COLUMN_CASE_FANS)))
                    .formFactor(cursor.getString(cursor.getColumnIndex(COLUMN_FORM_FACTOR)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return chassis;
        } else {
            return null;
        }
    }

    @Override
    public Chassis save(Chassis entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_HDD_BAYS, entity.getHddBays());
        values.put(COLUMN_CASE_FANS, entity.getCaseFans());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_FORM_FACTOR, entity.getFormFactor());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Chassis insertedEntity = new Chassis.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Chassis update(Chassis entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_HDD_BAYS, entity.getHddBays());
        values.put(COLUMN_CASE_FANS, entity.getCaseFans());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_FORM_FACTOR, entity.getFormFactor());
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
    public Chassis delete(Chassis entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Chassis> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Chassis> chassisSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Chassis chassis = new Chassis.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .hddBays(cursor.getInt(cursor.getColumnIndex(COLUMN_HDD_BAYS)))
                        .caseFans(cursor.getInt(cursor.getColumnIndex(COLUMN_CASE_FANS)))
                        .formFactor(cursor.getString(cursor.getColumnIndex(COLUMN_FORM_FACTOR)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                chassisSet.add(chassis);
            } while (cursor.moveToNext());
        }
        return chassisSet;
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
