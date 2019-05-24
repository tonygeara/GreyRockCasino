package com.example.tony.greycasino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Integer.getInteger;
//import com.univocity.parsers.csv.CsvParser;
//import com.univocity.parsers.csv.CsvParserSettings;


public class rewards extends AppCompatActivity {
    ImageView image1;
    ImageView image2;
EditText member;
TextView congratulations;
//    TextView winnername;
//    TextView balance;
    String name;
    String balanceAcc;
    static int u =0 ;

    private FirebaseDatabase mFirebaseDatabase;
private FirebaseDatabase mAuth;
//private FirebaseAuthException.AuthStateListener mAuthStateListener;
private DatabaseReference mRef;
private String userID;
    static String  checkname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        this.setTitle("Grey Rock Casino");

mAuth = FirebaseDatabase.getInstance();
mFirebaseDatabase = FirebaseDatabase.getInstance();
mRef = mFirebaseDatabase.getReference();


        image1 = (ImageView)findViewById(R.id.imageView12);
        image2 =(ImageView)findViewById(R.id.imageView15);
        image1.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
        image2.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.anim));
        member = (EditText)findViewById(R.id.membership);
        Button submit = (Button)findViewById(R.id.submitbutt);

        congratulations = (TextView)findViewById(R.id.congratulations);
//        winnername = (TextView)findViewById(R.id.winnername);
//        balance = (TextView)findViewById(R.id.balance);

        if (u <=0){
            congratulations.setVisibility(View.INVISIBLE);
        }else if ( u > 0){
            congratulations.setVisibility(View.VISIBLE);
            congratulations.setText("You already claimed your rewards. Please Check back later on ...");
        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"IN PROCESS ....",Toast.LENGTH_LONG).show();
// Read from the database
                // Write a message to the database
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();

                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.


//
//                       String val = member.getText().toString();
//                        int  memb = Integer.valueOf(val);
//
//                        ArrayList<String> list = new ArrayList<>();
//                        ArrayList<String> firstnamelist = new ArrayList<>();
//
//
//                        Log.i("DATASNAPSHOT", "onDataChange: " + dataSnapshot.getChildrenCount()+1);
//                       for (int i = 0; i <= dataSnapshot.getChildrenCount(); i++){
//
//                           String check = String.valueOf(dataSnapshot.child(String.valueOf(i)).child("PlayerID").getValue());
//                           list.add(check);
//                           checkname = String.valueOf(dataSnapshot.child(String.valueOf(i)).child("FirstName").getValue());
////                           Log.i("FIRSTNAME IS ", "onDataChange: " + checkname);
//                           firstnamelist.add(checkname);
//                           Log.i("NAME IS HEERRREEEEE", "onDataChange: " + checkname);
//
//                               }
//                       if(list.contains(String.valueOf(memb))){
//
//                           congratulations.setVisibility(View.VISIBLE);
//                           u++;
//
//                       } else if (firstnamelist.contains(String.valueOf("GUY").toLowerCase())){
//                           Toast.makeText(getApplicationContext(),"Tony is hereee",Toast.LENGTH_LONG).show();
//                       }
//                       else if (!list.contains(String.valueOf(memb))){
//                           Toast.makeText(getApplicationContext(),"Please enter a valid ID number....",Toast.LENGTH_LONG).show();
//                       }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        Toast.makeText(getApplicationContext(),"IN PROCEFAIILLLLLLLLLLLLSS ....",Toast.LENGTH_LONG).show();
                        Log.w("FAILURE", "Failed to read value.", error.toException());
                    }
                });
            }
        });
    }
}
