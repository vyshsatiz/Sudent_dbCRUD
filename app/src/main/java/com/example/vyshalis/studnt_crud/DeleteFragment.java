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
public class DeleteFragment extends Fragment implements View.OnClickListener{

    //A View occupies a rectangular area on the screen and
    // is responsible for drawing and event handling
    //myView is defined
    View myView;

    //a button btn is defined
    Button btn;

    //EditText for ID
    EditText ID;

    //an object on DB_Controller class to manage SQLlite
    DB_Controller controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //myView refers  to the delete_layout
        myView = inflater.inflate(R.layout.delete_layout, container, false);

        //ID refers to the EditText ID in the delete_layout
        ID = (EditText)myView.findViewById(R.id.ID);

        //btn refers to the button_delete button in delete_layout
        btn = (Button) myView.findViewById(R.id.button_delete);
        btn.setOnClickListener(this);

        //object controller is initialise  and the constructor in DB_Controller class is invoked
        controller = new DB_Controller(getActivity().getBaseContext(),"", null, 1);
        return myView;
    }

    //this method is invoked the button_delete in delete_layout is clicked
    public void onClick(View V)
    {

        try
        {
            //profile_counts is initialised with the number of rows in table STUDENT with user inputed ID
            int profile_counts = controller.getCount(Integer.parseInt(ID.getText().toString()));

            //profile_counts>0 means table STUDENT have student with input ID
            if(profile_counts>0)
            {
                //the delete_student method in DB_Controller is called with the ID entered by user as the parameters
                controller.delete_student(Integer.parseInt(ID.getText().toString()));

                //a message is displayed saying 'STUDENT RECORD DELETED'
                Toast.makeText(getActivity().getBaseContext(), "STUDENT RECORD DELETED", Toast.LENGTH_SHORT).show();
            }
            else
            //  no student with user inputed ID
            {
                //a message is displayed saying 'RECORD DOESN'T EXIXTS'
                Toast.makeText(getActivity().getBaseContext(),"RECORD DOESN'T EXIXTS",Toast.LENGTH_SHORT).show();
            }
            //the text of ID is set as''
            ID.setText("");
        }
        //catch is invoked when an exeption rise in above try
        catch (SQLException e) {

            //a message is displayed saying 'NO DATA'
            Toast.makeText(getActivity().getBaseContext(), "NO DATA", Toast.LENGTH_SHORT).show();

            //the text of ID is set as''
            ID.setText("");
        }
    }
}
