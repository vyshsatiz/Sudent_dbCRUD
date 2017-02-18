package com.example.vyshalis.studnt_crud;

/**
 * Created by vijisat on 05-02-2017.
 */
//imported files
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//DBController.java is going to be our custom java class that will manage the SQLite database

public class DB_Controller extends SQLiteOpenHelper
{
    //The constructor is used to setup the database.
    // While running the Android application, the database will be
    // created for the first time.
    public DB_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        //Test.db is the database
        super(context,"TEST.db", factory, version);
    }

    //onCreate(SQLiteDatabase database) method is called first time
    //when the database is created and we need to use this method
    // to create the tables and populate it as per the need
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //Table Student with attributes ID,FIRSTNAME,LASTNAME AND MARKS
        //ID is auto generated
        sqLiteDatabase.execSQL("CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT, LASTNAME TEXT, MARKS INTEGER);");
    }

    //onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) method called
    //when upgrade is done. We can drop the datbase and reset if required.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENT");
        onCreate(sqLiteDatabase);
    }

    //this method is called when the user click on ADD button
    //Helps to insert a student record to table STUDENT
    public void insert_student(String firstname, String lastname,int  marks)
    {
        //FIRSTNAME, LASTNAME and MARKS inserted to table
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME",firstname);
        contentValues.put("LASTNAME",lastname);
        contentValues.put("MARKS",marks);
        this.getWritableDatabase().insertOrThrow("STUDENT","",contentValues);
    }

    //this method is called when the user click on DELETE button
    //Helps to delete a student record from table STUDENT
    public void delete_student(int id)
    {
        //student record with ID=id is deleted from table
        this.getWritableDatabase().delete("STUDENT","ID='"+id+"'",null);
    }

    //this method is called when the user click on DELETE button
    //Helps to delete a student record to table STUDENT
    public void update_student(int marks, int id, String firstname, String lastname)
    {
        //The MARKS of student with ID =id is updated with marks
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET MARKS='"+marks+"'WHERE ID='"+id+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET FIRSTNAME = '"+firstname+"' WHERE ID = '"+id+"'");
        this.getWritableDatabase().execSQL("UPDATE STUDENT SET LASTNAME = '"+lastname+"' WHERE ID = '"+id+"'");
    }

    //method to  find the count of rows in the table with ID=id
    public int getCount(int id)
    {
        //cnt initialised to zero
        int cnt=0;
        String countQuery = "SELECT * FROM STUDENT WHERE ID='"+id+"'";

        //Create and/or open a database.
        //the database to be opened read-only. I
        SQLiteDatabase db = this.getReadableDatabase();

        //This interface provides random read-write access to the result
        // set returned by a database query.
        Cursor cursor = db.rawQuery(countQuery, null);
        cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    //method to  find the count of rows in the table
    public int getProfilesCount()
    {
        //cnt initialised to zero
        int cnt=0;

        //Create and/or open a database.
        //the database to be opened read-only. I
        String countQuery = "SELECT * FROM STUDENT";
        SQLiteDatabase db = this.getReadableDatabase();

        //This interface provides random read-write access to the result
        // set returned by a database query.
        Cursor cursor = db.rawQuery(countQuery, null);
        cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }


}