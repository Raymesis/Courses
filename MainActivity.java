package com.example.raymesis.wrtointernalstorageexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        editText=findViewById(R.id.edittext);
    }
    public void save(View v){
        String text =editText.getText().toString();
        FileOutputStream Fos=null;
        try {
            Fos=openFileOutput("example.txt",MODE_PRIVATE);
            Fos.write(text.getBytes());
            editText.getText().clear();
            Toast.makeText(this,"Saved to "+getFilesDir()+"/"+"example.txt",Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(Fos!=null){
                try {
                    Fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void load(View v){
        FileInputStream Fis=null;
        try {
            Fis = openFileInput("example.txt");
            InputStreamReader isr=new InputStreamReader(Fis);
            BufferedReader br=new BufferedReader(isr);
            StringBuilder sb=new StringBuilder();
            String text;
            while ((text=br.readLine())!=null){
                sb.append(text).append("\n");
            }
            editText.setText(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(Fis != null){
                try {
                    Fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
