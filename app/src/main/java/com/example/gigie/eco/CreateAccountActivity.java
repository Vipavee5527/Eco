package com.example.gigie.eco;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "3JVUVxK7YwlophTfcpYOCZcxuh4VG9zmTl1wi2r2", "sFBwfAWFhvYgoMPaM5sYCzGlLQAB1Jbh8SfEu0HY");
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText displayname = (EditText) findViewById(R.id.displayname);
        final EditText mail = (EditText) findViewById(R.id.mail);
        Button buttonSignup = (Button) findViewById(R.id.btn_signup);
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ////////////
                ParseObject player = new ParseObject("AccountUser");
                player.put("username", username.getText().toString());
                player.put("password", password.getText().toString());
                player.put("displayName", displayname.getText().toString());
                player.put("mail", mail.getText().toString());
/*
                player.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            // Save success!
                            username.setText("");
                            password.setText("");
                            displayname.setText("");
                            mail.setText("");
                        } else {
                            // some errors!
                            Log.e("Parse Error", e.toString());
                        }
                    }
                });
                */

////////////
                ParseQuery<ParseObject> query = ParseQuery.getQuery("AccountUser");
                query.whereEqualTo("displayName", "testuser");
                query.findInBackground(new FindCallback<ParseObject>() {
                                           public void done(List<ParseObject> object, ParseException e) {
                                               if (e == null) {
                                                   String disname = ((ParseObject) object).getString("displayName");
                                                   String mail = ((ParseObject) object).getString("mail").toString();
                                                   String pass = ((ParseObject) object).getString("password").toString();
                                                   String user = ((ParseObject) object).getString("username").toString();

                                                   Log.i("DISPLAY : ", disname);
                                                   Log.i("PASS : ", pass);
                                                   Log.i("MAIL : ", mail);
                                                   Log.i("USER : ", user);

                                                   Toast.makeText(getApplicationContext(),
                                                           "DisPlay: " + disname + " Mail: " + mail +
                                                                   " Pass: " +
                                                                   pass + " User: " +
                                                                   user,
                                                           Toast.LENGTH_LONG
                                                   ).show();
                                               } else {
                                                   // check errors.
                                                   Log.e("Query Error: ", e.toString());
                                               }
                                           }
                                       }
                );
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_account, menu);
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

