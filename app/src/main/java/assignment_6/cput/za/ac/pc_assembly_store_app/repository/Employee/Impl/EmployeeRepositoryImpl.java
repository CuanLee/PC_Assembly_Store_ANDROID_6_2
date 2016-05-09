package assignment_6.cput.za.ac.pc_assembly_store_app.repository.Employee.Impl;

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
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.ContactDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.Employee;
import assignment_6.cput.za.ac.pc_assembly_store_app.domain.Employee.GeographicalDetails;
import assignment_6.cput.za.ac.pc_assembly_store_app.repository.Employee.EmployeeRepository;

/**
 * Created by CuanL on 19/04/2016.
 */
public class EmployeeRepositoryImpl extends SQLiteOpenHelper implements EmployeeRepository{
    public static final String TABLE_NAME = "hdd";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ID_NUMBER = "idNumber";
    public static final String COLUMN_TELEPHONE = "telephone";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_PROVINCE = "province";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_SUBURB = "suburb";
    public static final String COLUMN_STREET = "street";
    public static final String COLUMN_HOUSE_NUMBER = "houseNumber";
    public static final String COLUMN_LOGGED_IN = "loggedIn";


    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERNAME + " TEXT  NOT NULL , "
            + COLUMN_PASSWORD + " TEXT  NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT  NOT NULL , "
            + COLUMN_LASTNAME + " TEXT  NOT NULL , "
            + COLUMN_GENDER + " TEXT  NOT NULL , "
            + COLUMN_TELEPHONE + " TEXT  NULL , "
            + COLUMN_EMAIL + " TEXT  NULL , "
            + COLUMN_COUNTRY + " TEXT NULL , "
            + COLUMN_PROVINCE + " TEXT NULL , "
            + COLUMN_CITY + " TEXT NULL , "
            + COLUMN_SUBURB + " TEXT NULL , "
            + COLUMN_STREET + " TEXT NULL , "
            + COLUMN_HOUSE_NUMBER + " INTEGER NULL , "
            + COLUMN_LOGGED_IN + " INTEGER NOT NULL , "
            + COLUMN_ID_NUMBER + " TEXT NOT NULL );";

    public EmployeeRepositoryImpl(Context context)
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
    public Employee findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_USERNAME,
                        COLUMN_PASSWORD,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_GENDER,
                        COLUMN_TELEPHONE,
                        COLUMN_EMAIL,
                        COLUMN_COUNTRY,
                        COLUMN_PROVINCE,
                        COLUMN_CITY,
                        COLUMN_SUBURB,
                        COLUMN_STREET,
                        COLUMN_HOUSE_NUMBER,
                        COLUMN_LOGGED_IN,
                        COLUMN_ID_NUMBER},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final ContactDetails contactDetails = new ContactDetails.Builder()
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .telephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)))
                    .build();

            final GeographicalDetails geographicalDetails = new GeographicalDetails.Builder()
                    .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                    .country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                    .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                    .suburb(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                    .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                    .houseNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_HOUSE_NUMBER)))
                    .build();

            final Employee employee = new Employee.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .contactDetails(contactDetails)
                    .geographicalDetails(geographicalDetails)
                    .idNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ID_NUMBER)))
                    .loggedIn(cursor.getInt(cursor.getColumnIndex(COLUMN_LOGGED_IN)))
                    .build();

            return employee;
        } else {
            return null;
        }
    }

    @Override
    public Employee save(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_ID_NUMBER, entity.getIdNumber());
        values.put(COLUMN_EMAIL, entity.getContactDetails().getEmail());
        values.put(COLUMN_TELEPHONE, entity.getContactDetails().getTelephone());
        values.put(COLUMN_CITY, entity.getGeographicalDetails().getCity());
        values.put(COLUMN_COUNTRY, entity.getGeographicalDetails().getCountry());
        values.put(COLUMN_PROVINCE, entity.getGeographicalDetails().getProvince());
        values.put(COLUMN_SUBURB, entity.getGeographicalDetails().getSuburb());
        values.put(COLUMN_STREET, entity.getGeographicalDetails().getStreet());
        values.put(COLUMN_HOUSE_NUMBER, entity.getGeographicalDetails().getHouseNumber());
        values.put(COLUMN_LOGGED_IN, entity.getLoggedIn());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Employee insertedEntity = new Employee.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Employee update(Employee entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getID());
        values.put(COLUMN_USERNAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_ID_NUMBER, entity.getIdNumber());
        values.put(COLUMN_EMAIL, entity.getContactDetails().getEmail());
        values.put(COLUMN_TELEPHONE, entity.getContactDetails().getTelephone());
        values.put(COLUMN_CITY, entity.getGeographicalDetails().getCity());
        values.put(COLUMN_COUNTRY, entity.getGeographicalDetails().getCountry());
        values.put(COLUMN_PROVINCE, entity.getGeographicalDetails().getProvince());
        values.put(COLUMN_SUBURB, entity.getGeographicalDetails().getSuburb());
        values.put(COLUMN_STREET, entity.getGeographicalDetails().getStreet());
        values.put(COLUMN_HOUSE_NUMBER, entity.getGeographicalDetails().getHouseNumber());
        values.put(COLUMN_LOGGED_IN, entity.getLoggedIn());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())}
        );
        return entity;
    }

    @Override
    public Employee delete(Employee entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getID())});
        return entity;
    }

    @Override
    public Set<Employee> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Employee> employeeSet = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final ContactDetails contactDetails = new ContactDetails.Builder()
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .telephone(cursor.getString(cursor.getColumnIndex(COLUMN_TELEPHONE)))
                        .build();

                final GeographicalDetails geographicalDetails = new GeographicalDetails.Builder()
                        .city(cursor.getString(cursor.getColumnIndex(COLUMN_CITY)))
                        .country(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                        .province(cursor.getString(cursor.getColumnIndex(COLUMN_PROVINCE)))
                        .suburb(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                        .street(cursor.getString(cursor.getColumnIndex(COLUMN_STREET)))
                        .houseNumber(cursor.getInt(cursor.getColumnIndex(COLUMN_HOUSE_NUMBER)))
                        .build();

                final Employee employee = new Employee.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .contactDetails(contactDetails)
                        .geographicalDetails(geographicalDetails)
                        .idNumber(cursor.getString(cursor.getColumnIndex(COLUMN_ID_NUMBER)))
                        .loggedIn(cursor.getInt(cursor.getColumnIndex(COLUMN_LOGGED_IN)))
                        .build();

                employeeSet.add(employee);
            } while (cursor.moveToNext());
        }
        return employeeSet;
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
