package com.example.gigie.eco;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String[] text = {"Dummy 1", "Dummy2", "Dummy 3", "Dummy 4", "Dummy 5", "Dummy 6"};

    String[] text2 = {"Dummy 1-2", "Dummy2-2", "Dummy 3-2", "Dummy 4-2", "Dummy 5-2", "Dummy 6-2"};

    Integer[] image = {
            R.drawable.cast_ic_notification_2,
            R.drawable.common_full_open_on_phone,
            R.drawable.cast_ic_notification_0,
            R.drawable.cast_ic_notification_1,
            R.drawable.common_signin_btn_text_dark,
            R.drawable.ic_cast_on_2_light
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn_home = (ImageButton)findViewById(R.id.btn_home);
        ImageButton btn_feed = (ImageButton)findViewById(R.id.btn_feed);
        ImageButton btn_add = (ImageButton)findViewById(R.id.btn_add);
        ImageButton btn_search = (ImageButton)findViewById(R.id.btn_search);
        ImageButton btn_profile = (ImageButton)findViewById(R.id.btn_profile);

        HomeFragment homeFragment = new HomeFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, homeFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeFragment homeFragment = new HomeFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, homeFragment);
                //transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedFragment feedFragment = new FeedFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, feedFragment);
                //transaction.addToBackStack(null);
                transaction.commit();

            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //AddFragment addFragment = new AddFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.fragment_container, addFragment);
                //transaction.addToBackStack(null);
                //transaction.commit();
<<<<<<< HEAD

                SelectTypeDialog dialog = new SelectTypeDialog();
                dialog.show(transaction, "AAAAA");

=======
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.addnew);
                dialog.setTitle("Select");

                dialog.findViewById(R.id.btn_add_menu1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Recycled Waste",
                                Toast.LENGTH_LONG).show();
                    }
                });

                dialog.findViewById(R.id.btn_add_menu2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Food Scrape",
                                Toast.LENGTH_LONG).show();
                    }
                });

                dialog.findViewById(R.id.btn_add_menu3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Landfill",
                                Toast.LENGTH_LONG).show();
                    }
                });

                dialog.findViewById(R.id.btn_add_menu4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(),"Freecycle Stuffs",
                                Toast.LENGTH_LONG).show();
                    }
                });



                dialog.show();
>>>>>>> pr/2
            }
        });

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchFragment searchFragment = new SearchFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, searchFragment);
                //transaction.addToBackStack(null);
                transaction.commit();


            }
        });

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileFragment profileFragment = new ProfileFragment();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, profileFragment);
                //transaction.addToBackStack(null);
                transaction.commit();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
