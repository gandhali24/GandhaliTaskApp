package com.example.gandhalitaskapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.gandhalitaskapp.model.CommonRepo;
import com.example.gandhalitaskapp.model.Facility;
import com.example.gandhalitaskapp.model.Option;

import java.util.ArrayList;

public class  DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "commonRepo";
    private static final int DB_VERSION = 1;
    private static final String FAC_TABLE_NAME = "ms_facilities";
    private static final String ID = "id";
    private static final String FAC_ID = "facility_id";
    private static final String FAC_NAME = "name";

    private static final String OPTIONS_TABLE_NAME = "ms_options";
    // private static final String FAC_TABLE_NAME = "ms_facilities";

    //private static final String OPTIONS_FAC_ID = "facility_id";
    private static final String OPTION_ID = "option_id";

    private static final String OPTION_NAME = "option_name";
    private static final String OPTION_ICON = "icon";

    private static final String Emp_Last_Name = "employeeLastName";
    private static final String Emp_Department = "employeeDepartment";


    private static final String COMMONREPO_TABLE_NAME = "ms_common_repo";
    private static final String COMMONREPO_ID = "common_id";
    private static final String COMMONREPO_FAC_ID = "common_facility_id";
    private static final String COMMONREPO_FAC_NAME = "fac_name";
    private static final String COMMONREPO_OPT_ID = "common_option_id";

    private static final String COMMONREPO_OPT_NAME = "common_option_name";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query_facility = "Create Table " + FAC_TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FAC_ID + " TEXT NOT NULL , "
                //+ FAC_NAME + " TEXT NOT NULL, "
                + FAC_NAME + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(query_facility);

        String query_option = "Create Table " + OPTIONS_TABLE_NAME + "("
                // + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                //  + FAC_ID + " TEXT NOT NULL , "
                + OPTION_ID + " TEXT NOT NULL , "
                + OPTION_NAME + " TEXT NOT NULL , "
                // + OPTION_ICON + " TEXT NOT NULL , "

                //+ FAC_NAME + " TEXT NOT NULL, "
                + OPTION_ICON + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(query_option);


//
//        String query_commonRepo= "Create Table " + COMMONREPO_TABLE_NAME + "("
//                + COMMONREPO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//                + COMMONREPO_FAC_ID + " TEXT NOT NULL , "
//                + COMMONREPO_FAC_NAME + " TEXT NOT NULL , "
//                + COMMONREPO_OPT_ID + " TEXT NOT NULL , "
//
//                //+ FAC_NAME + " TEXT NOT NULL, "
//                + COMMONREPO_OPT_NAME + " TEXT NOT NULL);";
//
//        sqLiteDatabase.execSQL(query_commonRepo);


    }
    public boolean addCommonRepo(CommonRepo commonRepo) {


        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
for(int i=0;i<commonRepo.facilities.size();i++)
{
    for(int j=0;j<commonRepo.facilities.get(i).options.size();j++)
    {
        values.put(COMMONREPO_FAC_ID, commonRepo.facilities.get(i).getFacility_id());
        values.put(COMMONREPO_FAC_NAME, commonRepo.facilities.get(i).getName());

        values.put(COMMONREPO_OPT_ID, commonRepo.facilities.get(i).options.get(j).getId() );
        values.put(COMMONREPO_OPT_NAME, commonRepo.facilities.get(i).options.get(j).getName() );
        //values.put(COMMONREPO_FAC_ID, commonRepo.facilities.get(i).getFacility_id());

    }

}




        long result = database.insert(COMMONREPO_TABLE_NAME, null, values);
        // Log.e(TAG,""+upc+"  Inserted");
        if (result == -1) {
            return false;
        } else {
            //  Log.e(TAG,"value inserted");
            return true;
        }

    }

    public boolean addFacilities(Facility facility) {


        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(FAC_ID, facility.getFacility_id());
        values.put(FAC_NAME, facility.getName());


        long result = database.insert(FAC_TABLE_NAME, null, values);
        // Log.e(TAG,""+upc+"  Inserted");
        if (result == -1) {
            return false;
        } else {
            return true;
        }


    }


    public boolean addOptions(Option option) {


        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();


        values.put(OPTION_ID, option.getId());
        values.put(OPTION_NAME, option.getId());

        values.put(OPTION_ICON, option.getIcon());


        long result = database.insert(OPTIONS_TABLE_NAME, null, values);
        // Log.e(TAG,""+upc+"  Inserted");
        if (result == -1) {
            return false;
        } else {
            //  Log.e(TAG,"value inserted");
            return true;
        }

    }


    public void deleteTableData() {


        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + FAC_TABLE_NAME);
        db.execSQL("delete from " + OPTIONS_TABLE_NAME);

    //    db.execSQL("delete from " + COMMONREPO_TABLE_NAME);


    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FAC_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + OPTIONS_TABLE_NAME);
     //   sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + COMMONREPO_TABLE_NAME);

        onCreate(sqLiteDatabase);
    }


//    public ArrayList<Facility> getData() {
//        // on below line we are creating a
//        // database for reading our database.
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        // on below line we are creating a cursor with query to read data from database.
//        Cursor cursorEmp = db.rawQuery("SELECT * FROM " + FAC_TABLE_NAME, null);
//
//        // on below line we are creating a new array list.
//        ArrayList<Facility> facilityArrayList = new ArrayList<>();
//
//        // moving our cursor to first position.
//        if (cursorEmp.moveToFirst()) {
//            do {
//                // on below line we are adding the data from cursor to our array list.
//                facilityArrayList.add(new Facility(
//                        cursorEmp.getString(1),
//                        cursorEmp.getString(2);
//            } while (cursorEmp.moveToNext());
//            // moving our cursor to next.
//        }
//        // at last closing our cursor
//        // and returning our array list.
//        cursorEmp.close();
//        return facilityArrayList;
//    }

}