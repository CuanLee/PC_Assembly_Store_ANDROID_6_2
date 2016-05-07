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
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.PC.GPU;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.PC.GPURepository;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Repository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class GPURepositoryImpl extends SQLiteOpenHelper implements GPURepository{
    public static final String TABLE_NAME = "gpu";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CODE = "code";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_BIT_MEMORY = "bitMemory";
    public static final String COLUMN_MEMORY_SIZE_GB = "memorySize_GB";
    public static final String COLUMN_MEMORY_TYPE = "memoryType";
    public static final String COLUMN_MEMEORY_CLOCK_MHZ = "memoryClock_MHz";
    public static final String COLUMN_CARD_BUS = "cardBus";
    public static final String COLUMN_STOCK = "stock";
    public static final String COLUMN_ACTIVE = "active";

    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_CODE + " TEXT  NOT NULL , "
            + COLUMN_DESCRIPTION + " TEXT  NOT NULL , "
            + COLUMN_BIT_MEMORY + " INTEGER  NOT NULL , "
            + COLUMN_MEMORY_SIZE_GB + " INTEGER  NOT NULL , "
            + COLUMN_MEMORY_TYPE + " TEXT  NOT NULL , "
            + COLUMN_MEMEORY_CLOCK_MHZ + " REAL  NOT NULL , "
            + COLUMN_CARD_BUS + " TEXT  NOT NULL , "
            + COLUMN_STOCK + " INTEGER  NOT NULL , "
            + COLUMN_ACTIVE + " BOOLEAN NOT NULL );";

    public GPURepositoryImpl(Context context)
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
    public GPU findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_CODE,
                        COLUMN_DESCRIPTION,
                        COLUMN_BIT_MEMORY,
                        COLUMN_MEMORY_SIZE_GB,
                        COLUMN_MEMORY_TYPE,
                        COLUMN_MEMEORY_CLOCK_MHZ,
                        COLUMN_CARD_BUS,
                        COLUMN_STOCK,
                        COLUMN_ACTIVE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final GPU hdd = new GPU.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                    .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                    .bitMemory(cursor.getInt(cursor.getColumnIndex(COLUMN_BIT_MEMORY)))
                    .memorySize_GB(cursor.getInt(cursor.getColumnIndex(COLUMN_MEMORY_SIZE_GB)))
                    .memoryType(cursor.getString(cursor.getColumnIndex(COLUMN_MEMORY_TYPE)))
                    .memoryClock_MHz(cursor.getDouble(cursor.getColumnIndex(COLUMN_MEMEORY_CLOCK_MHZ)))
                    .cardBus(cursor.getString(cursor.getColumnIndex(COLUMN_CARD_BUS)))
                    .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                    .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                    .build();

            return hdd;
        } else {
            return null;
        }
    }

    @Override
    public GPU save(GPU entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_BIT_MEMORY, entity.getBitMemory());
        values.put(COLUMN_MEMORY_SIZE_GB, entity.getMemorySize_GB());
        values.put(COLUMN_MEMORY_TYPE, entity.getMemoryType());
        values.put(COLUMN_MEMEORY_CLOCK_MHZ, entity.getMemoryClock_MHz());
        values.put(COLUMN_CARD_BUS, entity.getCardBus());
        values.put(COLUMN_STOCK, entity.getStock());
        values.put(COLUMN_ACTIVE, entity.isActive());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        GPU insertedEntity = new GPU.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public GPU update(GPU entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CODE, entity.getCode());
        values.put(COLUMN_DESCRIPTION, entity.getDescription());
        values.put(COLUMN_BIT_MEMORY, entity.getCardBus());
        values.put(COLUMN_MEMORY_SIZE_GB, entity.getMemorySize_GB());
        values.put(COLUMN_MEMORY_TYPE, entity.getMemoryType());
        values.put(COLUMN_MEMEORY_CLOCK_MHZ, entity.getMemoryClock_MHz());
        values.put(COLUMN_CARD_BUS, entity.getCardBus());
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
    public GPU delete(GPU entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<GPU> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<GPU> gpuSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final GPU gpu = new GPU.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .code(cursor.getString(cursor.getColumnIndex(COLUMN_CODE)))
                        .description(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)))
                        .bitMemory(cursor.getInt(cursor.getColumnIndex(COLUMN_BIT_MEMORY)))
                        .memorySize_GB(cursor.getInt(cursor.getColumnIndex(COLUMN_MEMORY_SIZE_GB)))
                        .memoryType(cursor.getString(cursor.getColumnIndex(COLUMN_MEMORY_TYPE)))
                        .memoryClock_MHz(cursor.getDouble(cursor.getColumnIndex(COLUMN_MEMEORY_CLOCK_MHZ)))
                        .cardBus(cursor.getString(cursor.getColumnIndex(COLUMN_CARD_BUS)))
                        .stock(cursor.getInt(cursor.getColumnIndex(COLUMN_STOCK)))
                        .active(cursor.getInt(cursor.getColumnIndex(COLUMN_ACTIVE)))
                        .build();
                gpuSet.add(gpu);
            } while (cursor.moveToNext());
        }
        return gpuSet;
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
