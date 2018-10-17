package com.fuac.dragonforf.forf1.grades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GroupActivity extends AppCompatActivity {
    EditText editText;
    Button button;
    LinearLayout linearLayout;
    ArrayList<Student> studentsInGroup;
    TextView textView;
    String activity_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        activity_title=getIntent().getStringExtra("activity_title");
        setTitle(activity_title);
        linearLayout=(LinearLayout)findViewById(R.id.linearLayout);
        editText=(EditText)findViewById(R.id.editText);
        button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("Average: "+getAverage());
            }
        });
        studentsInGroup=new ArrayList<Student>();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addStudent();
            }
        });
        loadStudents();
    }

    private void loadStudents() {
        try {
            for (Group g:MainActivity.groups) {
                if(g.getName().equals(activity_title))
                    for (Student s:g.getStudentsInGroup()) {
                        addStudent(s.getName());
                    }
            }
        }
        catch(Exception e){
        }
    }

    private void addStudent(){
        if(editText.getText().toString().equals(""))
            return;
        addStudent(editText.getText().toString());
    }

    private void addStudent(String ss){
        final Student s=new Student(ss);
        LinearLayout l=new LinearLayout(getApplicationContext());
        l.setOrientation(LinearLayout.HORIZONTAL);
        TextView t=new TextView(getApplicationContext());
        t.setText(s.getName());
        l.addView(t);
        final TextView finalNote=new TextView(getApplicationContext());
        finalNote.setWidth(100);
        final EditText firstTerm=new EditText(getApplicationContext());
        firstTerm.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!(firstTerm.getText().toString().equals("")))
                        s.setFirstTerm(Float.parseFloat(firstTerm.getText().toString()));
                    float theNote=getFinalNote(s);
                    finalNote.setText(theNote+"");
                    s.setFinalNote(theNote);
                }
            }
        });
        final EditText secondTerm=new EditText(getApplicationContext());
        secondTerm.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!(secondTerm.getText().toString().equals("")))
                        s.setSecondTerm(Float.parseFloat(secondTerm.getText().toString()));
                    float theNote=getFinalNote(s);
                    finalNote.setText(theNote+"");
                    s.setFinalNote(theNote);
                }
            }
        });
        final EditText thirdTerm=new EditText(getApplicationContext());
        thirdTerm.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!(thirdTerm.getText().toString().equals("")))
                        s.setThirdTerm(Float.parseFloat(thirdTerm.getText().toString()));
                    float theNote=getFinalNote(s);
                    finalNote.setText(theNote+"");
                    s.setFinalNote(theNote);
                }
            }
        });
        l.addView(firstTerm);
        l.addView(secondTerm);
        l.addView(thirdTerm);
        l.addView(finalNote);
        studentsInGroup.add(s);
        editText.setText("");
        linearLayout.addView(l);
    }

    private float getAverage(){
        float currentSum=0;
        for(Student s: studentsInGroup){
            currentSum+=s.getFinalNote();
        }
        return currentSum/studentsInGroup.size();
    }

    private float getFinalNote(Student s){
        float finalNote=0;
        finalNote=(s.getFirstTerm()*0.3f)+(s.getSecondTerm()*0.3f)+(s.getThirdTerm()*0.4f);
        return finalNote;
    }
}
