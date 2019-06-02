package com.example.tony.greycasino;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.core.Platform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {
    private static final String TAG = "MAINACTIVITY";
    public static GoogleMap mMap;

    Timer timer;

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;

    private ImageView image1;
    private ImageView image2;
    private ImageView image3;


    public static String bon = "";
    public static String badb = "";
    Button notification ;
    String CHANNEL_ID = "my_channel_02";

    Button eventb,bingob,gamingb,restaurantb,pokerb;





    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);

        setSupportActionBar(toolbar);

        createNotificationChannel();

        eventb = (Button)findViewById(R.id.events);
        pokerb = (Button)findViewById(R.id.poker);
        restaurantb = (Button)findViewById(R.id.restaurant);
        gamingb = (Button)findViewById(R.id.gaming);
        bingob = (Button)findViewById(R.id.bingo);

        eventb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ActivityEvents.class);
                startActivity(intent);


            }
        });

        pokerb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),poker.class);
                startActivity(intent);
            }
        });

        restaurantb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),valleyrestaurant.class);
                startActivity(intent);
            }
        });

        gamingb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),gamingcasino.class);
                startActivity(intent);
            }
        });

        bingob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),bingo.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            Intent intent = new Intent(getApplicationContext(),hoursofoperationmenu.class);
            startActivity(intent);
        }else if (id == R.id.contactinformation) {
            Intent intent = new Intent(getApplicationContext(),contactinformationmenu.class);
            startActivity(intent);
        }
        else if (id == R.id.action_help) {
            Intent intent = new Intent(getApplicationContext(),aboutcasinomenu.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
// call activities
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.AboutCasino) {

            Intent intent = new Intent(getApplicationContext(),casinoabout.class);
            startActivity(intent);
        } else if (id == R.id.Gaming) {
            Intent intent = new Intent(getApplicationContext(),gamingcasino.class);
            startActivity(intent);

        } else if (id == R.id.Winners) {
//            Intent intent = new Intent(getApplicationContext(),winnerscasino.class);
//            startActivity(intent);
            Intent intent = new Intent(MainActivity.this,winnerscasino.class);
            startActivity(intent);

        } else if (id == R.id.Hours) {
            Intent intent = new Intent(getApplicationContext(),hourscasino.class);
            startActivity(intent);

        }else if (id == R.id.Contact) {
            Intent intent = new Intent(getApplicationContext(),contactcasino.class);
            startActivity(intent);
        }
        else if (id == R.id.Bingo) {
            Intent intent = new Intent(getApplicationContext(),bingo.class);
            startActivity(intent);
        }else if (id == R.id.Whatishappeningbingo) {
            Intent intent = new Intent(getApplicationContext(),bingowhatshappening.class);
            startActivity(intent);

        }else if (id == R.id.pricingbingo) {

            Intent intent = new Intent(getApplicationContext(),pricingbingo.class);
            startActivity(intent);
        }




        else if (id == R.id.Tourism) {
            Intent intent = new Intent(getApplicationContext(),tourismcasino.class);
            startActivity(intent);
        }else if (id == R.id.Whatshappening) {
            Intent intent = new Intent(getApplicationContext(),poker.class);
            startActivity(intent);

        }else if (id == R.id.Pokertournament) {
            Intent intent = new Intent(getApplicationContext(),pokertournament.class);
            startActivity(intent);


        }else if (id == R.id.Promotionspoker) {
            Intent intent = new Intent(getApplicationContext(),promotionsrecyclerview.class);
            startActivity(intent);

        }else if (id == R.id.Badbeat) {

            Intent intent = new Intent(getApplicationContext(),badbeatmenu.class);
            startActivity(intent);
        }else if (id == R.id.Restaurants) {
            Intent intent = new Intent(getApplicationContext(),thefiedlcafe.class);
            startActivity(intent);

        }else if (id == R.id.ValleyRestaurant) {
            Intent intent = new Intent(getApplicationContext(),valleyrestaurant.class);
            startActivity(intent);

        } else if (id == R.id.Promotions) {
            Intent intent = new Intent(getApplicationContext(),rewardsRecyclerview.class);
            startActivity(intent);
        } else if (id == R.id.proClaim) {
            Intent intent = new Intent(getApplicationContext(),rewards.class);
            startActivity(intent);

        } else if (id == R.id.Proabout) {
            Intent intent = new Intent(getApplicationContext(),rewardsabout.class);
            startActivity(intent);

        }else if (id == R.id.about) {
            Intent intent = new Intent(getApplicationContext(),careersabout.class);
            startActivity(intent);

        }else if (id == R.id.Jobs) {
            Intent intent = new Intent(getApplicationContext(), ActivityAvailableJobs.class);
            startActivity(intent);
        }else if (id == R.id.Events) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {

        Log.i(TAG, "onDestroy: CALLLEEEEDDDDD");
//        timer.cancel();
        super.onDestroy();
        Log.i(TAG, "onDestroy: FINISHEDDDDDDD");

    }
// send notification to users
    private void createNotificationChannel() {

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }

    // sending notification to users
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void sendnotification() {
//
//        Log.d(TAG, "sendnotification: ON");
//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.logo)
//                    .setContentTitle("GREY ROCK CASINO")
//                    .setContentText("GET READY FOR OUR NEW COMING EVENTS AT GREY ROCK CASINO, CELEBRATE CHRISTMAS AND NEW YEAR EVE WITH US AND ENTER THE DRAW TO WIN A GREAT PRIZES . WE ARE WAITING FOR YOU ...")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//        notificationManager.notify(1,mBuilder.build());
//
//            Toast.makeText(getApplicationContext(),"Notification received ",Toast.LENGTH_LONG).show();
//
//
//        Log.d(TAG, "sendnotification: OFF");

//    }


// dial th ephone number of casino
    public void dialContactPhone(View view) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", "(506) 735-2820", null)));
    }

    // send an email to the casino
    public void sendEmail(View view) {
        Intent mailIntent = new Intent(Intent.ACTION_VIEW);
        Uri data = Uri.parse("mailto:?subject=" + "Subject:"+ "&body=" + "Please type in your text here , thank you \n Grey Rock Casino " + "&to=" + "info@greyrock-casino.com");
        mailIntent.setData(data);
        startActivity(Intent.createChooser(mailIntent, "Send mail..."));
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart: callleeeeeeeeeedddddddd");
        super.onStart();
        Log.i(TAG, "onStart: eeennndddddddddddddddd");

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: cccccccaaaaaallllllllllleeeeeeeeeeeeddddddddddddddd");
        super.onResume();
        Log.i(TAG, "onResume: eeeeeeeeeeeeeeeeeeennnnnnnnnnnnnnnnnnddddddddddddddddddd");

    }

    @Override
    protected void onPostResume() {
        Log.i(TAG, "onPostResume: cccccccaaaaaallllllllllleeeeeeeeeeeeddddddddddddddd");

        super.onPostResume();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState: caalllledddddddd");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        Log.i(TAG, "onRestart: calllled");
        super.onRestart();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        Log.i(TAG, "onSaveInstanceState: callllleeeeeeeeeeeeedddddddd");
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @SuppressLint("SetTextI18n")
    public void displayResults(Pair<String, String> pair) {

//        textView = findViewById(R.id.result);
        textView2 = findViewById(R.id.result2);
//        textView3 = findViewById(R.id.result3);
//        textView4 = findViewById(R.id.result4);
        StringBuilder sb = new StringBuilder("Bonanza ");
        sb.append(pair.first).append(" BadBeat ").append(pair.second);
        textView.setText("$" + badb);
//        textView3.setText("$" + badb);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim));
//            }
//        });

        textView2.setText("$" + bon);
//        textView4.setText("$" + bon);
//        textView2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView2.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim));
//            }
//        });


    }
// parse server for bonanza and badbeat
    private class ParserTask extends AsyncTask<Void, Void, Pair<String, String>> {

        private static final String URL = "http://www.greyrockcasino.com/en/";
        private static final String DIV_BONANZA = "div.top-banner-bingo-content";
        private static final String DIV_BAD_BEAT = "div.top-banner-poker-content";
        private static final int INDEX_CHILD = 1;
        private static final int INDEX_ELEMENT = 0;


        @Override
        protected Pair<String, String> doInBackground(Void... voids) {

            try {
                Document document = Jsoup.connect(URL).get();
                // select document
                Elements bonanzaElements = document.select(DIV_BONANZA);
                Elements badBeatElements = document.select(DIV_BAD_BEAT);

                // get the index for each element
                Element bonanzaElement = bonanzaElements.get(INDEX_ELEMENT);
                Element badBeatElement = badBeatElements.get(INDEX_ELEMENT);

                // substring the index for bnanza
                Node bonanza = bonanzaElement.childNode(INDEX_CHILD);
                String bonanzaString = bonanza.toString();
                bonanzaString = bonanzaString.substring(bonanzaString.indexOf('$') + 1, bonanzaString.lastIndexOf('<'));
                bon = bonanzaString;
                Log.i("", "doInBackgroundsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss: " + bon);


                Node badBeat = badBeatElement.childNode(INDEX_CHILD);
                String badBeatString = badBeat.toString();
                badBeatString = badBeatString.substring(badBeatString.indexOf('$') + 1, badBeatString.lastIndexOf('<'));
                badb = badBeatString;
                Log.i("", "doInBackgroundsssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss: " + badb);


                return new Pair<>(bonanzaString, badBeatString);

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Pair<String, String> stringStringPair) {
            super.onPostExecute(stringStringPair);
            displayResults(stringStringPair);
        }
    }

}
