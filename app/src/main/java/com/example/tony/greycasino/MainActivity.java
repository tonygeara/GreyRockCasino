package com.example.tony.greycasino;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
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
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Timer;
import java.util.TimerTask;

import static android.graphics.PorterDuff.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "MAINACTIVITY";
    Timer timer;
    Timer timer2;
    Timer timer3;
    Timer timer4;

    ImageView money;



    Button notification ;
    String CHANNEL_ID = "my_channel_02";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.logo);

        setSupportActionBar(toolbar);

        createNotificationChannel();





        VideoView videoView = (VideoView)findViewById(R.id.video);
        videoView.setMinimumWidth(383);
        videoView.setMinimumHeight(135);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.greyrock;
        videoView.setVideoURI(Uri.parse(path));
                videoView.setMediaController(new MediaController(this));
        videoView.start();

        final ViewPager viewPager1 = (ViewPager)findViewById(R.id.pager1);
        viewPager1.setMinimumHeight(50);
        viewPager1.setMinimumWidth(25);

        final ViewPager viewPager2 = (ViewPager)findViewById(R.id.pager2);
        viewPager2.setMinimumHeight(50);
        viewPager2.setMinimumWidth(25);

        final ViewPager viewPager3 = (ViewPager)findViewById(R.id.pager3);
        viewPager2.setMinimumHeight(50);
        viewPager2.setMinimumWidth(25);

        final ViewPager viewPager4 = (ViewPager)findViewById(R.id.pager4);
        viewPager2.setMinimumHeight(50);
        viewPager2.setMinimumWidth(25);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager1.setAdapter(viewPagerAdapter);

        ViewPager2Adapter viewPager2Adapter = new ViewPager2Adapter(this);
        viewPager2.setAdapter(viewPager2Adapter);

        viewPager3.setAdapter(viewPager2Adapter);
        viewPager4.setAdapter(viewPager2Adapter);

//        final LinearLayout gallery = findViewById(R.id.gallery);
//
//        LayoutInflater inflater = LayoutInflater.from(this);
//         final Integer [] images = {R.drawable.hotseat,R.drawable.fiddle,R.drawable.magnificent,R.drawable.winter,R.drawable.totem,R.drawable.second};
//        for (int i = 0 ; i < 6; i++){
//
//            final View view = inflater.inflate(R.layout.items,gallery,false);
//            final ImageView image = view.findViewById(R.id.imageitems);
//            final int j = i;
//            TimerTask timeritems = new TimerTask() {
//                @Override
//                public void run() {
//                    image.setImageResource(images[j]);
//
//                }
//
//            };
//            gallery.addView(view);
//            timer = new Timer();
//            timer.schedule(timeritems, 3000, 2000);
//
//        }

        TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    viewPager1.post(new Runnable() {
                        @Override
                        public void run() {
                            viewPager1.setCurrentItem(viewPager1.getCurrentItem() + 1);
                            if (viewPager1.getCurrentItem() == 2 ){
                                viewPager1.setCurrentItem(0);
                            }
                        }
                    });
                }
            };
            timer = new Timer();
            timer.schedule(timerTask, 3000, 4000);


        TimerTask timer2Task = new TimerTask() {
            @Override
            public void run() {
                viewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
                        if (viewPager2.getCurrentItem() == 4 ){
                            viewPager2.setCurrentItem(0);
                        }
                    }
                });
            }
        };
        timer2 = new Timer();
        timer2.schedule(timer2Task, 3000, 2000);

        TimerTask timer3Task = new TimerTask() {
            @Override
            public void run() {
                viewPager2.post(new Runnable() {
                    @Override
                    public void run() {

                        viewPager3.setCurrentItem(viewPager3.getCurrentItem() + 2);

                        if (viewPager3.getCurrentItem() == 4 ){
                            viewPager3.setCurrentItem(0);
                        }
                    }
                });
            }
        };
        timer3 = new Timer();
        timer3.schedule(timer3Task, 3000, 2000);

        TimerTask timer4Task = new TimerTask() {
            @Override
            public void run() {
                viewPager2.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager4.setCurrentItem(viewPager4.getCurrentItem() + 3);

                        if (viewPager4.getCurrentItem() == 4 ){
                            viewPager4.setCurrentItem(0);
                        }
                    }
                });
            }
        };
        timer4 = new Timer();
        timer4.schedule(timer4Task, 3000, 2000);



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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
            Intent intent = new Intent(getApplicationContext(),winnerscasino.class);
            startActivity(intent);

        } else if (id == R.id.Hours) {
            Intent intent = new Intent(getApplicationContext(),hourscasino.class);
            startActivity(intent);

        }else if (id == R.id.Contact) {
            Intent intent = new Intent(getApplicationContext(),contactcasino.class);
            startActivity(intent);
        }else if (id == R.id.Tourism) {
            Intent intent = new Intent(getApplicationContext(),tourismcasino.class);
            startActivity(intent);
        }else if (id == R.id.Whatshappening) {

        }else if (id == R.id.Pokertournament) {

        }else if (id == R.id.Promotionspoker) {

        }else if (id == R.id.Badbeat) {

        }else if (id == R.id.Restaurants) {

        }else if (id == R.id.ValleyRestaurant) {

        } else if (id == R.id.Promotions) {

        }else if (id == R.id.Proabout) {

        }else if (id == R.id.about) {

        }else if (id == R.id.Jobs) {

        }else if (id == R.id.Events) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                sendnotification();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {

timer2.cancel();
        timer.cancel();
        timer3.cancel();
        timer4.cancel();
        super.onDestroy();
    }

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

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendnotification() {

        Log.d(TAG, "sendnotification: ON");
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.logo)
                    .setContentTitle("GREYCAS")
                    .setContentText("HELOO FROMM TONY HEREEEEEEE")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(1,mBuilder.build());

            Toast.makeText(getApplicationContext(),"Notification received ",Toast.LENGTH_LONG).show();


        Log.d(TAG, "sendnotification: OFF");

    }

}
