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
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.RAM;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.RAMRepository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class RAMRepositoryImpl extends SQLiteOpenHelper implements RAMRepository {
    public static final String TABLE_NAME = "ram";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_MEMORY_SIZE = "memory_size";
    public static final String COLUMN_VOLTAGE = "voltage";
    public static final String COLUMN_MEMORY_CONFIGURATION = "memory_configuration";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_MEMORY_SIZE + " TEXT  NOT NULL , "
            + COLUMN_VOLTAGE + " REAL  NOT NULL , "
            + COLUMN_MEMORY_CONFIGURATION + " TEXT  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public RAMRepositoryImpl(Context context)
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
    public RAM findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_MEMORY_CONFIGURATION,
                        COLUMN_VOLTAGE,
                        COLUMN_MEMORY_SIZE,
                        COLUMN_STOCK,
                        COLUMN_ACTIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final RAM ram = new RAM.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .voltage(cursor.getDouble(cursor.getColumnIndex(COLUMN_VOLTAGE)))
                    .memoryConfiguration(cursor.getString(cursor.getColumnIndex(COLUMN_MEMORY_CONFIGURATION)))
                    .memorySize(cursor.getString(cursor.getColumnIndex(COLUMN_MEMORY_SIZE)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return ram;
        } else {
            return null;
        }
    }

    @Override
    public RAM save(RAM entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_MEMORY_CONFIGURATION, entity.getMemoryConfiguration());
        values.put(COLUMN_MEMORY_SIZE, entity.getMemorySize());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_VOLTAGE, entity.getVoltage());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        RAM insertedEntity = new RAM.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public RAM update(RAM entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_VOLTAGE, entity.getVoltage());
        values.put(COLUMN_MEMORY_SIZE, entity.getMemorySize());
        values.put(COLUMN_MEMORY_CONFIGURATION, entity.getMemoryConfiguration());
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
    public RAM delete(RAM entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<RAM> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<RAM> ramSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final RAM ram = new RAM.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .voltage(cursor.getDouble(cursor.getColumnIndex(COLUMN_VOLTAGE)))
                        .memorySize(cursor.getString(cursor.getColumnIndex(COLUMN_MEMORY_SIZE)))
                        .memoryConfiguration(cursor.getString(cursor.getColumnIndex(COLUMN_MEMORY_CONFIGURATION)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                ramSet.add(ram);
            } while (cursor.moveToNext());
        }
        return ramSet;
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
