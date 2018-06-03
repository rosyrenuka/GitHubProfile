package com.example.hp.githubprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


      editText=findViewById(R.id.et1);
      button=findViewById(R.id.b1);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               String Text = editText.getText().toString();

               int count=0;
               for(int i=0;i<Text.length();i++)
               {
                   char c=Text.charAt(i);
                   if(c==' ')
                   {
                       count++;
                   }
               }


               if(Text.length()!=0 && count==0) {

                   // now user activity will open

                   Intent intent = new Intent(MainActivity.this, UserActivity.class);
                  intent.putExtra("userName", Text);
                   startActivity(intent);
                   Toast.makeText(MainActivity.this, "fnvjdfnv", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   Toast.makeText(MainActivity.this, "Invalid Username", Toast.LENGTH_SHORT).show();
               }


           }
       });

    }
}
