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
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.Motherboard;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.MotherboardRepository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class MotherboardRepositoryImpl extends SQLiteOpenHelper implements MotherboardRepository {
    public static final String TABLE_NAME = "motherboards";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CHIPSET = "chipset";
    public static final String COLUMN_SATA_PORTS = "sataPorts";
    public static final String COLUMN_USB_2 = "usb2";
    public static final String COLUMN_USB_3 = "usb3";
    public static final String COLUMN_FORM_FACTOR = "formFactor";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_CHIPSET + " TEXT NOT NULL , "
            + COLUMN_SATA_PORTS + " INTEGER  NOT NULL , "
            + COLUMN_USB_2 + " INTEGER  NOT NULL , "
            + COLUMN_USB_3 + " INTEGER  NOT NULL , "
            + COLUMN_FORM_FACTOR + " TEXT  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public MotherboardRepositoryImpl(Context context)
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
    public Motherboard findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_CHIPSET,
                        COLUMN_SATA_PORTS,
                        COLUMN_USB_2,
                        COLUMN_USB_3,
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
            final Motherboard motherboard = new Motherboard.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .chipset(cursor.getString(cursor.getColumnIndex(COLUMN_CHIPSET)))
                    .sataPorts(cursor.getInt(cursor.getColumnIndex(COLUMN_SATA_PORTS)))
                    .usb2(cursor.getInt(cursor.getColumnIndex(COLUMN_USB_2)))
                    .usb3(cursor.getInt(cursor.getColumnIndex(COLUMN_USB_3)))
                    .formFactor(cursor.getString(cursor.getColumnIndex(COLUMN_FORM_FACTOR)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return motherboard;
        } else {
            return null;
        }
    }

    @Override
    public Motherboard save(Motherboard entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_CHIPSET, entity.getChipset());
        values.put(COLUMN_SATA_PORTS, entity.getSataPorts());
        values.put(COLUMN_USB_2, entity.getUsb2());
        values.put(COLUMN_USB_3, entity.getUsb3());
        values.put(COLUMN_FORM_FACTOR, entity.getFormFactor());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Motherboard insertedEntity = new Motherboard.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Motherboard update(Motherboard entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_CHIPSET, entity.getChipset());
        values.put(COLUMN_SATA_PORTS, entity.getSataPorts());
        values.put(COLUMN_USB_2, entity.getUsb2());
        values.put(COLUMN_USB_3, entity.getUsb3());
        values.put(COLUMN_FORM_FACTOR, entity.getFormFactor());
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
    public Motherboard delete(Motherboard entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Motherboard> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Motherboard> motherboardSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Motherboard motherboard = new Motherboard.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .chipset(cursor.getString(cursor.getColumnIndex(COLUMN_CHIPSET)))
                        .sataPorts(cursor.getInt(cursor.getColumnIndex(COLUMN_SATA_PORTS)))
                        .usb2(cursor.getInt(cursor.getColumnIndex(COLUMN_USB_2)))
                        .usb3(cursor.getInt(cursor.getColumnIndex(COLUMN_USB_3)))
                        .formFactor(cursor.getString(cursor.getColumnIndex(COLUMN_FORM_FACTOR)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                motherboardSet.add(motherboard);
            } while (cursor.moveToNext());
        }
        return motherboardSet;
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
