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
public class AddFragment extends Fragment implements View.OnClickListener{

    //A View occupies a rectangular area on the screen and
    // is responsible for drawing and event handling
    //myView is defined
    View myView;

    //a button btn is defined
    Button btn;

    //EditText for firstname,lastname and marks
    EditText firstname, lastname, marks;

    //an object on DB_Controller class to manage SQLlite
    DB_Controller controller;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        //myView refers  to the add_layout
        myView = inflater.inflate(R.layout.add_layout, container, false);

        //firstname refers to the EditText firstname_input in the add_layout
        firstname = (EditText)myView.findViewById(R.id.firstname_input);

        //lastname refers to the EditText lastname_input in the add_layout
        lastname = (EditText)myView.findViewById(R.id.lastname_input);

        //marks refers to the EditText marks in the add_layout
        marks = (EditText)myView.findViewById(R.id.marks);

        //btn refers to the button_addbutton in add_layout
        btn = (Button) myView.findViewById(R.id.button_add);
        btn.setOnClickListener(this);

        //object controller is initialise  and the constructor in DB_Controller class is invoked
        controller = new DB_Controller(getActivity().getBaseContext(),"", null, 1);

        return myView;
    }

    //this method is invoked the button_add in add_layout is clicked
    public void onClick(View V) {
        try
        {
            //the insert_student method in DB_Controller is called with the inputs entered by user as the parameters
            controller.insert_student(firstname.getText().toString(), lastname.getText().toString(), Integer.parseInt(marks.getText().toString()));

            //a message is displayed saying 'STUDENT ADDED'
            Toast.makeText(getActivity().getBaseContext(), "STUDENT ADDED", Toast.LENGTH_SHORT).show();

            //the text of firstname is set as''
            firstname.setText("");

            //the text of lastname is set as''
            lastname.setText("");

            //the text of marks is set as''
            marks.setText("");

        }
        //catch is invoked when an exeption rise in above try
        catch (SQLException e) {

            //a message is displayed saying 'ALREADY EXISTS'
            Toast.makeText(getActivity().getBaseContext(), "ALREADY EXIXTS", Toast.LENGTH_SHORT).show();

            //the text of firstname is set as''
            firstname.setText("");

            //the text of lastname is set as''
            lastname.setText("");

            //the text of marks is set as''
            marks.setText("");
        }
    }
}