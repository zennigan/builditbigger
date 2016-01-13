package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jokedisplay.JokeActivity;
import com.jokefactory.JokeFactory;


public class MainActivity extends ActionBarActivity {

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void tellJoke(View view){
        /*Toast.makeText(this, "derp", Toast.LENGTH_SHORT).show();

        Intent jokeIntent = new Intent(this, JokeActivity.class);
        JokeFactory jokeFactory = new JokeFactory();
        jokeIntent.putExtra("joke",jokeFactory.getJoke());
        startActivity(jokeIntent);*/

        new EndpointsAsyncTask(new EndpointsAsyncTaskListener() {

            @Override
            public void taskCompleted(String joke) {

                if(joke == null){
                    if(!isNetworkAvailable()){
                        joke = getString(R.string.network_error);
                    } else {
                        joke = getString(R.string.service_error);
                    }
                }

                Intent jokeIntent = new Intent(getApplicationContext(), JokeActivity.class);
                jokeIntent.putExtra("joke", joke);
                startActivity(jokeIntent);

            }
        }).execute();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

}
