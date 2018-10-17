package com.fuac.dragonforf.forf1.grades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    EditText editText;
    Button button;
    static ArrayList<Group> groups;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        groups=new ArrayList<Group>();
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        editText=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addGroup();
            }
        });
        readFile();
        refreshList();
    }

    private void readFile(){
        try {
            FileInputStream fin = openFileInput("Users.txt");
            InputStreamReader isr=new InputStreamReader(fin);
            BufferedReader br=new BufferedReader(isr);
            String temp="";
            while(temp!=null) {
                if (temp.startsWith("*")){
                    groups.add(new Group(temp.substring(temp.indexOf("*") + 1)));
                    refreshList();
                }
                if (temp.startsWith("-")){
                    Group tempGroup=groups.get(groups.size()-1);
                    tempGroup.getStudentsInGroup().add(new Student(temp.substring(temp.indexOf("-") + 1)));
                }
                temp=br.readLine();
            }
        }
        catch(FileNotFoundException e){
            try {
                FileOutputStream fOut = openFileOutput("Users.txt", MODE_APPEND);
                String x="";
                fOut.write(x.getBytes());
                fOut.close();
                Toast.makeText(getBaseContext(),"file saved",Toast.LENGTH_SHORT).show();
            }
            catch (Exception ex) {
                // TODO Auto-generated catch block
                ex.printStackTrace();
                Toast.makeText(getBaseContext(),"An error has occurred.",Toast.LENGTH_SHORT).show();
            }
        }
        catch(IOException ee){
            Toast.makeText(getBaseContext(),"An error has occurred.",Toast.LENGTH_SHORT).show();
        }
    }

    private void addGroup(){
        try {
            FileOutputStream fOut = openFileOutput("Users.txt", MODE_APPEND);
            String x="*"+editText.getText().toString()+"\n";
            fOut.write(x.getBytes());
            fOut.close();
            groups.add(new Group(editText.getText().toString()));
            Toast.makeText(getBaseContext(),"Group added",Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex) {
            // TODO Auto-generated catch block
            ex.printStackTrace();
            Toast.makeText(getBaseContext(),"An error has occurred.",Toast.LENGTH_SHORT).show();
        }
        editText.setText("");
        refreshList();
    }

    private void refreshList(){
        linearLayout.removeAllViews();
        for(Group group:groups) {
            TextView tv=new TextView(getApplicationContext());
            tv.setHeight(200);
            tv.setTextSize(50);
            tv.setTextColor(getResources().getColor(android.R.color.background_light));
            tv.setText(group.getName());
            final String tvText=tv.getText().toString();
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(), GroupActivity.class);
                    i.putExtra("activity_title", tvText);
                    startActivity(i);
                }
            });
            linearLayout.addView(tv);
        }
    }
}
