package sg.edu.np.mad.practical3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button Group1=findViewById(R.id.button3);
        Button Group2=findViewById(R.id.button4);
        Group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
                ft.replace(R.id.Fragment, new Group1());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
                ft.commit();
            }
        });
        Group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
                ft.replace(R.id.Fragment, new Group2());
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
                ft.commit();

            }
        });
    }
}