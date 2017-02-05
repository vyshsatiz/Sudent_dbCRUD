package com.example.vyshalis.studnt_crud;

/**
 * Created by vijisat on 05-02-2017.
 */

//imported files
import android.app.Fragment;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


//A Fragment represents a behavior or a portion of user interface in an Activity
//OnClickListener HELPS for the click event of button
public class UpdateFragment extends Fragment implements View.OnClickListener{

    //A View occupies a rectangular area on the screen and
    // is responsible for drawing and event handling
    //myView is defined
    View myView;

    //a button btn is defined
    Button btn;

    //EditText for ID and marks
    EditText marks,ID;

    //an object on DB_Controller class to manage SQLlite
    DB_Controller controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //myView refers  to the update_layout
        myView = inflater.inflate(R.layout.update_layout, container, false);

        //ID refers to the EditText ID in the update_layout
        ID = (EditText)myView.findViewById(R.id.ID);

        //marks refers to the EditText marks in the update_layout
        marks = (EditText)myView.findViewById(R.id.marks);

        //btn refers to the button_update button in update_layout
        btn = (Button) myView.findViewById(R.id.button_update);
        btn.setOnClickListener(this);

        //object controller is initialise  and the constructor in DB_Controller class is invoked
        controller = new DB_Controller(getActivity().getBaseContext(),"", null, 1);

        return myView;
    }

    //this method is invoked the button_update in update_layout is clicked
    public void onClick(View V)
    {

        try
        {
            //count is initialised with the number of rows in table STUDENT witha specific ID
            int counts = controller.getCount(Integer.parseInt(ID.getText().toString()));

            //count>0 means a record exist with corresponding user inputed ID
            if(counts>0)
            {
                //the update_student method in DB_Controller is called with the marks and ID entered by user as the parameters
                controller.update_student(Integer.parseInt(marks.getText().toString()),Integer.parseInt(ID.getText().toString()));

                //a message is displayed saying 'STUDENT RECORD UPDATED'
                Toast.makeText(getActivity().getBaseContext(), "STUDENT RECORD UPDATED", Toast.LENGTH_SHORT).show();
            }
            else
            {
                //a message is displayed saying 'RECORD DOESN'T EXIXTS'
                Toast.makeText(getActivity().getBaseContext(),"RECORD DOESN'T EXIXTS",Toast.LENGTH_SHORT).show();
            }


            //the text of marks is set as''
            marks.setText("");

            //the text of ID is set as''
            ID.setText("");
        }
        //catch is invoked when an exeption rise in above try
        catch (SQLException e)
        {
            //a message is displayed saying 'DOESN'T EXIXTS'
            Toast.makeText(getActivity().getBaseContext(), "DOESNOT EXIST", Toast.LENGTH_SHORT).show();

            //the text of ID is set as''
            ID.setText("");

            //the text of marks is set as''
            marks.setText("");
        }
    }
}
