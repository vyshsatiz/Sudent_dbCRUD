package com.example.vyshalis.studnt_crud;

//imported files
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

//main activity
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    //execution starts from onCreate() method
    protected void onCreate(Bundle savedInstanceState)
    {
        //If the orientation changes(i.e rotating your device from landscape
        // mode to portrait and vice versa), the activity is recreated and onCreate() method
        // is called again, so that you don't lose this prior information.
        // If no data was supplied, savedInstanceState is null
        super.onCreate(savedInstanceState);

        //gives information about our layout resource.
        // Here, our layout resources are defined in activity_main.xml file.
        setContentView(R.layout.activity_main);

        // Find the toolbar view and set as ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window
        setSupportActionBar(toolbar);

        //To add a navigation drawer, declare your user interface
        // with a DrawerLayout object as the root view of your layout.
        // Inside the DrawerLayout, add one view that contains the main content for the screen
        // (your primary layout when the drawer is hidden) and another view that
        // contains the contents of the navigation drawer.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        //Users can open and close the navigation drawer with a swipe gesture
        // from or towards the left edge of the screen, but if you're using the action bar,
        // you should also allow users to open and close it by touching the app icon.
        // And the app icon should also indicate the presence of the navigation drawer with a special icon.
        // You can implement all this behavior by using the ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //Called when the activity has detected the user's press of the back key.
    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    //this function is called when the user click on a particular
    //menu item from the navigation bar
    // i.e. Handle navigation view item when clicked
    public boolean onNavigationItemSelected(MenuItem item) {

        //to store the id of a menu item
        int id = item.getItemId();

        //A Fragment is a piece of an application's user interface or behavior
        // that can be placed in an Activity.
        //Interaction with fragments is done through FragmentManager,
        // which can be obtained via Activity.getFragmentManager() and Fragment.getFragmentManager().
        FragmentManager fragmentManager = getFragmentManager();

        //this block is executed when user clicks on add student
        if (id == R.id.nav_add_layout)
        {
            //current layout replaced by AddFragment's layout
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new AddFragment())
                    .commit();
        }
        //this block is executed when user clicks on update marks
        else if (id == R.id.nav_update_layout)
        {
            //current layout replaced by UpdateFragment's layout
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new UpdateFragment())
                    .commit();
        }
        //this block is executed when user clicks on view students
        else if (id == R.id.nav_display_layout)
        {
            //current layout replaced by DisplayFragment's layout
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new DisplayFragment())
                    .commit();
        }
        //this block is executed when user clicks on delete student
        else if (id == R.id.nav_delete_layout)
        {
            //current layout replaced by DeleteFragment's layout
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame
                            , new DeleteFragment())
                    .commit();

        }

        //To add a navigation drawer, declare your user interface
        // with a DrawerLayout object as the root view of your layout.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}