package com.example.vyshalis.studnt_crud;

/**
 * Created by vijisat on 05-02-2017.
 */

//imported files
import android.app.Fragment;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by smoot on 2016-06-10.
 */

//A Fragment represents a behavior or a portion of user interface in an Activity
public class DisplayFragment extends Fragment{

    //A View occupies a rectangular area on the screen and
    // is responsible for drawing and event handling
    //myView is defined
    View myView;

    //textView to add content to table
    TextView textView;

    //EditText for firstname,lastname and marks
    EditText firstname, lastname, marks;

    //TableLayout to display student list
    TableLayout t1;

    //an object on DB_Controller class to manage SQLlite
    DB_Controller controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //myView refers  to the display_layout
        myView = inflater.inflate(R.layout.display_layout, container, false);

        //TableLayout t1 is refers to the ouputtable ro show student records
        t1 = (TableLayout) myView.findViewById(R.id.main_table);

        //object controller is initialise  and the constructor in DB_Controller class is invoked
        controller = new DB_Controller(getActivity().getBaseContext(), "", null, 1);

        //to add data to table in layout
        textView = (TextView) myView.findViewById(R.id.textView);


        //table row for headings is created with ID as 10, color as gray with the specified height and width
        TableRow tr_head = new TableRow(getActivity().getBaseContext());
        tr_head.setId(10);
        tr_head.setBackgroundColor(Color.GRAY);
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));


        //Column Heading for ID created using TextView, ID is 20 , text is "ID" and color of text is BLACK
        TextView label_id = new TextView(getActivity().getBaseContext());
        label_id.setId(20);
        label_id.setText("  ID");
        label_id.setTextColor(Color.BLACK);
        tr_head.addView(label_id);// add the column to the table row here

        //Column Heading for firstname created using TextView, ID is 21, text is "FIRSTNAME" and color of text is BLACK
        TextView label_firstname = new TextView(getActivity().getBaseContext());
        label_firstname.setId(21);// define id that must be unique
        label_firstname.setText("FIRSTNAME"); // set the text for the header
        label_firstname.setTextColor(Color.BLACK); // set the color
        tr_head.addView(label_firstname); // add the column to the table row here

        //Column Heading for lastname created using TextView
        TextView label_lastname = new TextView(getActivity().getBaseContext());
        label_lastname.setId(22);// define id that must be unique
        label_lastname.setText("LASTNAME"); // set the text for the header
        label_lastname.setTextColor(Color.BLACK); // set the color
        tr_head.addView(label_lastname); // add the column to the table row here

        //Column Heading for marks created using TextView
        TextView label_marks = new TextView(getActivity().getBaseContext());
        label_marks.setId(23);// define id that must be unique
        label_marks.setText("MARKS   "); // set the text for the header
        label_marks.setTextColor(Color.BLACK); // set the color
        tr_head.addView(label_marks); // add the column to the table row here

        //heading row added to table
        t1.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.FILL_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        //c gets the number of rows in tble STUDENT
        int c=0;
        c=controller.getProfilesCount();
        if(c>0) {
            Cursor cursor = controller.getReadableDatabase().rawQuery("SELECT * FROM STUDENT", null);
            while (cursor.moveToNext()) {
                //saves output from table to firstnme,lastname,id and marks
                String firstname = cursor.getString(1);
                String lastname = cursor.getString(2);
                String id = cursor.getString(0);
                String marks = cursor.getString(3);

                // Create the table row
                TableRow tr = new TableRow(getActivity().getBaseContext());

                //set ID
                tr.setId(100);
                //set height and width
                tr.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));

                // Create column for ID
                TextView labelID = new TextView(getActivity().getBaseContext());
                labelID.setId(200);//set id
                labelID.setText("  "+id);//set text
                labelID.setTextColor(Color.BLACK);//set color
                tr.addView(labelID);//add column

                // Create column for firstname
                TextView labelFname = new TextView(getActivity().getBaseContext());
                labelFname.setId(200);// set id
                labelFname.setText(firstname);//set text
                labelFname.setTextColor(Color.BLACK);//set color
                tr.addView(labelFname);//add column to table

                // Create column for lastname
                TextView labelLname = new TextView(getActivity().getBaseContext());
                labelLname.setId(300);//set id
                labelLname.setText(lastname);// set text
                labelLname.setTextColor(Color.BLACK);//set color
                tr.addView(labelLname);//add column to table

                // Create column for marks
                TextView labelMarks = new TextView(getActivity().getBaseContext());
                labelMarks.setId(400);//set id
                labelMarks.setText(marks);//set text
                labelMarks.setTextColor(Color.BLACK);//set color
                tr.addView(labelMarks);//add column to table

                // finally add this to the table row
                t1.addView(tr, new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.FILL_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
            }
        }
        else
        {
            //a message is displayed saying 'NO DATA'
            Toast.makeText(getActivity().getBaseContext(), "NO DATA", Toast.LENGTH_SHORT).show();
        }
        return myView;
    }

}
