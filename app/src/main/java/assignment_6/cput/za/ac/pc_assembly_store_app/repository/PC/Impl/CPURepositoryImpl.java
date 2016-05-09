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
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.CPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.CPURepository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class CPURepositoryImpl extends SQLiteOpenHelper implements CPURepository{
    public static final String TABLE_NAME = "cpu";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_SOCKET = "socket";
    public static final String COLUMN_PROCESSOR_BRAND = "processorBrand";
    public static final String COLUMN_SPEED_GHZ = "speed_Ghz";
    public static final String COLUMN_CACHE_MB = "cache_MB";
    public static final String COLUMN_CORES = "cores";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_SOCKET + " INTEGER NOT NULL , "
            + COLUMN_PROCESSOR_BRAND + " TEXT  NOT NULL , "
            + COLUMN_SPEED_GHZ + " REAL  NOT NULL , "
            + COLUMN_CACHE_MB + " REAL  NOT NULL , "
            + COLUMN_CORES + " INTEGER  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public CPURepositoryImpl(Context context)
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
    public CPU findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_SOCKET,
                        COLUMN_PROCESSOR_BRAND,
                        COLUMN_SPEED_GHZ,
                        COLUMN_CACHE_MB,
                        COLUMN_CORES,
                        COLUMN_STOCK,
                        COLUMN_ACTIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final CPU cpu = new CPU.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .socket(cursor.getInt(cursor.getColumnIndex(COLUMN_SOCKET)))
                    .processorBrand(cursor.getString(cursor.getColumnIndex(COLUMN_PROCESSOR_BRAND)))
                    .speed_Ghz(cursor.getDouble(cursor.getColumnIndex(COLUMN_SPEED_GHZ)))
                    .cache_MB(cursor.getDouble(cursor.getColumnIndex(COLUMN_CACHE_MB)))
                    .cores(cursor.getInt(cursor.getColumnIndex(COLUMN_CORES)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return cpu;
        } else {
            return null;
        }
    }

    @Override
    public CPU save(CPU entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_SOCKET, entity.getSocket());
        values.put(COLUMN_PROCESSOR_BRAND, entity.getProcessorBrand());
        values.put(COLUMN_SPEED_GHZ, entity.getSpeed_Ghz());
        values.put(COLUMN_CACHE_MB, entity.getCache_MB());
        values.put(COLUMN_CORES, entity.getCores());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        CPU insertedEntity = new CPU.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public CPU update(CPU entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_SOCKET, entity.getSocket());
        values.put(COLUMN_PROCESSOR_BRAND, entity.getProcessorBrand());
        values.put(COLUMN_SPEED_GHZ, entity.getSpeed_Ghz());
        values.put(COLUMN_CACHE_MB, entity.getCache_MB());
        values.put(COLUMN_CORES, entity.getCores());
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
    public CPU delete(CPU entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<CPU> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<CPU> cpuSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final CPU cpu = new CPU.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .socket(cursor.getInt(cursor.getColumnIndex(COLUMN_SOCKET)))
                        .processorBrand(cursor.getString(cursor.getColumnIndex(COLUMN_PROCESSOR_BRAND)))
                        .speed_Ghz(cursor.getDouble(cursor.getColumnIndex(COLUMN_SPEED_GHZ)))
                        .cache_MB(cursor.getDouble(cursor.getColumnIndex(COLUMN_CACHE_MB)))
                        .cores(cursor.getInt(cursor.getColumnIndex(COLUMN_CORES)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                cpuSet.add(cpu);
            } while (cursor.moveToNext());
        }
        return cpuSet;
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
