//package com.example.tony.greycasino;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.animation.AnimationUtils;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.ArrayList;
//import java.util.Objects;
//
//import static java.lang.Integer.getInteger;
////import com.univocity.parsers.csv.CsvParser;
////import com.univocity.parsers.csv.CsvParserSettings;
//
//
//public class rewards extends AppCompatActivity {
//    ImageView image1;
//    ImageView image2;
//EditText member;
//TextView congratulations;
////    TextView winnername;
////    TextView balance;
//    String name;
//    String balanceAcc;
//    static int u =0 ;
//
//    private FirebaseDatabase mFirebaseDatabase;
//private FirebaseDatabase mAuth;
////private FirebaseAuthException.AuthStateListener mAuthStateListener;
//private DatabaseReference mRef;
//private String userID;
//    static String  checkname;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_rewards);
//        this.setTitle("Grey Rock Casino");
//
//mAuth = FirebaseDatabase.getInstance();
//mFirebaseDatabase = FirebaseDatabase.getInstance();
//mRef = mFirebaseDatabase.getReference();
//
//
//        image1 = (ImageView)findViewById(R.id.imageView12);
//        image2 =(ImageView)findViewById(R.id.imageView15);
//        image1.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
//        image2.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
//        member = (EditText)findViewById(R.id.membership);
//        Button submit = (Button)findViewById(R.id.submitbutt);
//
//        congratulations = (TextView)findViewById(R.id.congratulations);
////        winnername = (TextView)findViewById(R.id.winnername);
////        balance = (TextView)findViewById(R.id.balance);
//
//        if (u <=0){
//            congratulations.setVisibility(View.INVISIBLE);
//        }else if ( u > 0){
//            congratulations.setVisibility(View.VISIBLE);
//            congratulations.setText("You already claimed your rewards. Please Check back later on ...");
//        }
//
//
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(),"IN PROCESS ....",Toast.LENGTH_LONG).show();
//// Read from the database
//                // Write a message to the database
//                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//
//                database.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        // This method is called once with the initial value and again
//                        // whenever data at this location is updated.
//
//
////
////                       String val = member.getText().toString();
////                        int  memb = Integer.valueOf(val);
////
////                        ArrayList<String> list = new ArrayList<>();
////                        ArrayList<String> firstnamelist = new ArrayList<>();
////
////
////                        Log.i("DATASNAPSHOT", "onDataChange: " + dataSnapshot.getChildrenCount()+1);
////                       for (int i = 0; i <= dataSnapshot.getChildrenCount(); i++){
////
////                           String check = String.valueOf(dataSnapshot.child(String.valueOf(i)).child("PlayerID").getValue());
////                           list.add(check);
////                           checkname = String.valueOf(dataSnapshot.child(String.valueOf(i)).child("FirstName").getValue());
//////                           Log.i("FIRSTNAME IS ", "onDataChange: " + checkname);
////                           firstnamelist.add(checkname);
////                           Log.i("NAME IS HEERRREEEEE", "onDataChange: " + checkname);
////
////                               }
////                       if(list.contains(String.valueOf(memb))){
////
////                           congratulations.setVisibility(View.VISIBLE);
////                           u++;
////
////                       } else if (firstnamelist.contains(String.valueOf("GUY").toLowerCase())){
////                           Toast.makeText(getApplicationContext(),"Tony is hereee",Toast.LENGTH_LONG).show();
////                       }
////                       else if (!list.contains(String.valueOf(memb))){
////                           Toast.makeText(getApplicationContext(),"Please enter a valid ID number....",Toast.LENGTH_LONG).show();
////                       }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//                        // Failed to read value
//                        Toast.makeText(getApplicationContext(),"IN PROCEFAIILLLLLLLLLLLLSS ....",Toast.LENGTH_LONG).show();
//                        Log.w("FAILURE", "Failed to read value.", error.toException());
//                    }
//                });
//            }
//        });
//    }
//}

package com.example.tony.greycasino;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.lang.Integer.getInteger;
//import com.univocity.parsers.csv.CsvParser;
//import com.univocity.parsers.csv.CsvParserSettings;


public class rewards extends AppCompatActivity {
    static int u = 0;
    ImageView image1;
    ImageView image2;
    EditText etUserId;
    EditText etName;
    TextView congratulations;
    TextView tvPoints;
    TextView newbal;

    String name;
    String balanceAcc;
    FrameLayout frameProgress;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseDatabase mAuth;
    private DatabaseReference mRef;
    private String userID;

    GenericTypeIndicator<ArrayList<UserProfile>> t ;
    ArrayList<UserProfile> yourStringArray;
    UserProfile user;
    int checkplus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        this.setTitle("Grey Rock Casino");

        mAuth = FirebaseDatabase.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();


        image1 = (ImageView) findViewById(R.id.imageView12);
        frameProgress = findViewById(R.id.frameProgress);
        image2 = (ImageView) findViewById(R.id.imageView15);
        image1.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim));
        image2.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim));
        etUserId = (EditText) findViewById(R.id.etUserId);
        etName = (EditText) findViewById(R.id.etName);
        Button btnSubmit = (Button) findViewById(R.id.btnSubmit);

        congratulations = (TextView) findViewById(R.id.congratulations);
        tvPoints= (TextView) findViewById(R.id.tvPoints);
        newbal = (TextView) findViewById(R.id.newbalance);

        if (u <= 0) {
            congratulations.setVisibility(View.INVISIBLE);
        } else if (u > 0) {
            congratulations.setVisibility(View.VISIBLE);
            congratulations.setText("You already claimed your rewards. Please Check back later on ...");
        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (u <= 0) {
                    hideKeyboard(rewards.this);

                    if (!etName.getText().toString().trim().equals("") || !etUserId.getText().toString().trim().equals("")) {
                        frameProgress.setVisibility(View.VISIBLE);

                        final DatabaseReference database = FirebaseDatabase.getInstance().getReference();

                        database.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                // This method is called once with the initial value and again
                                // whenever data at this location is updated.


                                 t = new GenericTypeIndicator<ArrayList<UserProfile>>() {
                                };

                                 yourStringArray = dataSnapshot.getValue(t);

                                boolean foundRecord = false;
                                for (int i = 0; i < yourStringArray.size(); i++) {
                                   user = yourStringArray.get(i);

                                    if (!foundRecord && user != null && user.FirstName != null) {
                                        if (user.PlayerID == Integer.valueOf(etUserId.getText().toString()) && user.FirstName.trim().equalsIgnoreCase(etName.getText().toString())) {
                                            foundRecord = true;
                                            tvPoints.setText("Current Balance: " + user.PointBalance);
                                            user.PointBalance = (user.PointBalance + 10);     //4553
                                            yourStringArray.get(i).PointBalance = user.PointBalance;
                                            updatePoints(yourStringArray, user.PointBalance , i);
                                            String check = String.valueOf(dataSnapshot.child(String.valueOf(i)).child("PointBalance").getValue());
                                            Log.i("HHHIIIBb", "onDataChange: " + check + "  hgehgfwekgfewkfgewkfgkewfgwkegfkewhgfewhkgfewkgfwhkef");
                                            database.child(String.valueOf(i)).child("PointBalance").setValue(check+10);

                                            break;
                                        }

                                    }

                                }


                                if (!foundRecord) {
                                    frameProgress.setVisibility(View.GONE);

                                    Toast.makeText(rewards.this, "No records found", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                                // Failed to read value
                                frameProgress.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Failed to call Firebase", Toast.LENGTH_LONG).show();
                                Log.w("FAILURE", "Failed to read value.", error.toException());
                            }

                        });

                    } else
                        Toast.makeText(rewards.this, "Enter valid details", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(rewards.this, "You can only claim rewards once.", Toast.LENGTH_SHORT).show();

            }
        });



    }

    private void updatePoints(final ArrayList<UserProfile> yourStringArray , final int updatedBalance, final int position) {

        FirebaseDatabase.getInstance().getReference().removeValue(new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {


                FirebaseDatabase.getInstance().getReference().setValue(yourStringArray)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                u++;
                                frameProgress.setVisibility(View.GONE);
                                newbal.setText("Congratulations, your new balance is: "+updatedBalance + "\n\n\n Please take a screenshot or show your screen to front desk at the casino !!!");

                                Toast.makeText(rewards.this, "Point balance Updated", Toast.LENGTH_SHORT).show();
                            }
                        });

            }


        });

    }


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
