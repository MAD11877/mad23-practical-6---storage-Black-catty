package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    final String TITLE ="Main Activity";
    User myUser=new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");
        TextView userText= findViewById(R.id.textView);
        TextView descriptionText=findViewById(R.id.textView2);
        Intent myRecvIntent =getIntent();
        String myRecvUsername;
        String myRecvdescription;
        Boolean followed;
        Integer id;
        myRecvUsername=myRecvIntent.getStringExtra("myNumber");
        myRecvdescription=myRecvIntent.getStringExtra("myNumber2");
        followed=myRecvIntent.getBooleanExtra("Followed",false);
        id=myRecvIntent.getIntExtra("ID",0);
        userText.setText(myRecvUsername);
        descriptionText.setText(myRecvdescription);
        myUser=new User(myRecvUsername,myRecvdescription,id,followed);







    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.v(TITLE, "On Start!");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.v(TITLE, "On Pause!");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TITLE, "On Resume!");

        String name=myUser.getName();
        String desc= myUser.getDescription();
        Button myButton = findViewById(R.id.button);
        Button message=findViewById(R.id.button2);
        if(myUser.getFollowed())
        {myButton.setText("UNFOLLOw");}
        else
        {
            myButton.setText("FOLLOW");
        }
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!myUser.getFollowed())
                {
                    Log.v(TITLE,"You have followed "+ name +" he is "+desc);
                    myUser.followed=true;
                    Button btn = (Button)findViewById(R.id.button);
                    btn.setText("UNFOLLOW");
                    Toast.makeText(getApplicationContext(),"Followed", Toast.LENGTH_SHORT).show();

                }
                else if (myUser.getFollowed())
                {
                    Log.v(TITLE, "You have unfollowed " + name + " he is " + desc);
                    myUser.followed = false;
                    Button btn = (Button) findViewById(R.id.button);
                    btn.setText("FOLLOW");
                    Toast.makeText(getApplicationContext(),"Unfollowed", Toast.LENGTH_SHORT).show();
                }


            }


        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent=new Intent(MainActivity.this,MessageGroup.class);
                startActivity(myintent);
            }
        });






    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.v(TITLE,"On Stop!");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.v(TITLE, "On Destroy!");
    }

}