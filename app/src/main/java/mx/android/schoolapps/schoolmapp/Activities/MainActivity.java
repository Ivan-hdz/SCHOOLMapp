package mx.android.schoolapps.schoolmapp.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

import java.util.ArrayList;

import mx.android.schoolapps.schoolmapp.Database.DownloadScheduleInfo;
import mx.android.schoolapps.schoolmapp.Database.ScheduleContract;
import mx.android.schoolapps.schoolmapp.Database.ScheduleDBHelper;
import mx.android.schoolapps.schoolmapp.Fragments.AboutFragment;
import mx.android.schoolapps.schoolmapp.Fragments.CafeteriaFragment;
import mx.android.schoolapps.schoolmapp.Fragments.CalendarFragment;
import mx.android.schoolapps.schoolmapp.Fragments.ClassroomsFragment;
import mx.android.schoolapps.schoolmapp.Fragments.ClubsFBFragment;

import mx.android.schoolapps.schoolmapp.Fragments.FLFB;
import mx.android.schoolapps.schoolmapp.Fragments.MainFragment;
import mx.android.schoolapps.schoolmapp.Fragments.SAESFragment;
import mx.android.schoolapps.schoolmapp.Fragments.StationaryFragment;
import mx.android.schoolapps.schoolmapp.Fragments.StudentsCounselorFragment;
import mx.android.schoolapps.schoolmapp.Fragments.TeachersFragment;
import mx.android.schoolapps.schoolmapp.Fragments.VersionFragment;
import mx.android.schoolapps.schoolmapp.Models.Schedule;
import mx.android.shcoolapps.schoolmap.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, DrawerLayout.DrawerListener{

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ScheduleDBHelper scheduleDBHelper= new ScheduleDBHelper(this);

    private int SHOW_MENU_CAFETERIA_AND_STATIONAY= 3;
    private int SHOW_MENU_CLASSROOM= 2;
    private int SHOW_SEARCH_MENU= 1;
    private int HIDE_MENU= 0;
    private int menuState= HIDE_MENU;
    private SearchView searchView;

    private int navItemIndex= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar= findViewById(R.id.toolbarMainActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SQLiteDatabase sqLiteDatabase= scheduleDBHelper.getReadableDatabase();

        long count= DatabaseUtils.queryNumEntries(sqLiteDatabase, ScheduleContract.ScheduleColumns.TABLE_NAME);
        System.out.println("Database filled with " + count + " elements");

        if(count <= 0){
            DownloadScheduleInfo downloadSchedule= new DownloadScheduleInfo(this);
            downloadSchedule.delegate = new DownloadScheduleInfo.DownloadScheduleInterface() {
                @Override
                public void onScheduleDownloaded(ArrayList<Schedule> schedulesList) {
                }
            };
            downloadSchedule.execute();
        }

        drawerLayout= findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.navView);

        View header= navigationView.inflateHeaderView(R.layout.header_navigation_drawer);
        ImageView test= header.findViewById(R.id.imageViewNavigationHeader);
        CircleImageView userImage= header.findViewById(R.id.circleImageViewHeader);
        TextView userName= header.findViewById(R.id.textViewUserNameHeader);

        setFragmentByDefault();
        Bundle inBundle = getIntent().getExtras();

        try{
            String userNameUrl = inBundle.get("name").toString();
            String userImageUrl = inBundle.get("imageUrl").toString();

            Glide.with(this).load(userImageUrl).into(userImage);
            userName.setText("Bienvenido, " + userNameUrl);
        }catch(Exception e){
            userImage.setVisibility(View.INVISIBLE);
            userName.setText("Bienvenido");
        }

        try{
            String coverImageUrl= inBundle.get("coverImageUrl").toString();
            System.out.println("CoverImageURL= " + coverImageUrl);

            Glide.with(this).load(coverImageUrl).into(test);

        }catch(Exception e){
            e.printStackTrace();
        }

        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout.addDrawerListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);

                return true;
            case R.id.searchButton:
                return true;
        }
        return false;
    }

    private void setFragmentByDefault(){
        createFragment(new MainFragment(), navigationView.getMenu().getItem(0));
    }

    private void createFragment(Fragment fragment, MenuItem item){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
        item.setChecked(true);
        if(item == navigationView.getMenu().getItem(0))
            getSupportActionBar().setTitle("SCHOOLMapp - Mapa");
        else
            getSupportActionBar().setTitle(item.getTitle());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        boolean fragmentTransaction= false;
        Fragment fragment= null;

        menuState= HIDE_MENU;
        invalidateOptionsMenu();

        switch (item.getItemId()){
            case R.id.navMenuMain:
                navItemIndex= 0;
                fragment= new MainFragment();
                fragmentTransaction= true;
                break;
            case R.id.navMenuClassrooms:
                navItemIndex= 1;
                fragment= new ClassroomsFragment();
                fragmentTransaction= true;
                menuState= SHOW_MENU_CLASSROOM;
                invalidateOptionsMenu();
                break;
            case R.id.navMenuClubs:
                navItemIndex= 2;
                fragment= new ClubsFBFragment();
                fragmentTransaction= true;
                break;
            case R.id.navMenuLibrary:
                navItemIndex= 3;
                //fragment= new LibraryFragment();
                //fragment = new FragmentLibraryFB();
                fragment = new FLFB();
                fragmentTransaction= true;
                invalidateOptionsMenu();
                break;
            case R.id.navMenuStudentCounselor:
                navItemIndex= 4;
                fragment= new StudentsCounselorFragment();
                fragmentTransaction= true;
                break;
            case R.id.navMenuTeachers:
                navItemIndex= 5;
                fragment= new TeachersFragment();
                fragmentTransaction= true;
                invalidateOptionsMenu();
                break;
            case R.id.navMenuStationary:
                navItemIndex= 6;
                fragment= new StationaryFragment();
                fragmentTransaction= true;
                menuState= SHOW_MENU_CAFETERIA_AND_STATIONAY;
                invalidateOptionsMenu();
                break;
            case R.id.navMenuCafeteria:
                navItemIndex= 7;
                fragment= new CafeteriaFragment();
                fragmentTransaction= true;
                menuState= SHOW_MENU_CAFETERIA_AND_STATIONAY;
                invalidateOptionsMenu();
                break;
            case R.id.navMenuAbout:
                navItemIndex= 8;
                fragment= new AboutFragment();
                fragmentTransaction= true;
                break;
            case R.id.navMenuSAES:
                navItemIndex= 9;
                fragment= new SAESFragment();
                fragmentTransaction= true;
                break;
            case R.id.navMenuCalendar:
                navItemIndex=10;
                fragment = new CalendarFragment();
                fragmentTransaction=true;
                break;
            case R.id.navMenuVersion:
                navItemIndex= 11;
                fragment= new VersionFragment();
                fragmentTransaction= true;
                break;
        }

        if(fragmentTransaction){
            createFragment(fragment, item);
            drawerLayout.closeDrawers();
        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_classroom_schedule, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        if(menuState == SHOW_MENU_CLASSROOM){
            menu.findItem(R.id.updateButton).setVisible(true);
        }else if(menuState == SHOW_MENU_CAFETERIA_AND_STATIONAY){
            menu.findItem(R.id.updateButton).setVisible(true);
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        // checking if user is on other navigation menu
        // rather than home

        if (navItemIndex != 0) {
            navItemIndex = 0;
            menuState= HIDE_MENU;
            setFragmentByDefault();
            return;
        }

        AlertDialog.Builder exitDialog= new AlertDialog.Builder(this);
        exitDialog.setTitle("¿Desea salir?");
        exitDialog.setPositiveButton("Salir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        exitDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        exitDialog.show();
    }

    @Override
    protected void onDestroy() {
        scheduleDBHelper.close();
        super.onDestroy();
    }


    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(View drawerView) {
        // Toast.makeText(getApplicationContext(), "Open", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        // Toast.makeText(getApplicationContext(), "Close", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }
}
