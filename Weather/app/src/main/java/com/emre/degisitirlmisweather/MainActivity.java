package com.emre.degisitirlmisweather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    TextView tx1;
    TextView tx2;
    TextView tx3;
    TextView tx4;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        identify();
    }
    public void show(View view){
        GetJson getJson = new GetJson();
        try {
            String myJson = getJson.execute("https://api.openweathermap.org/data/2.5/weather?q="+editText.getText().toString()+"&appid=d06a30349ff08b23303af28c37fc06db&lang=tr&units=metric")
                    .get();
            Log.d("MyJSON",myJson);
            JSONObject obj = new JSONObject(myJson);
            String w = obj.getString("weather");
            String m = obj.getString("main");
            JSONObject mw = new JSONObject(m);
            JSONArray wa = new JSONArray(w);
            for(int i=0;i<wa.length();i++){
                JSONObject el = wa.getJSONObject(i);
                Log.d("Main",el.getString("main"));
                Log.d("Main",el.getString("description"));
                tx1.setText("Main : "+el.getString("main"));
                tx2.setText("Description : "+el.getString("description"));
                tx3.setText("Temparature : "+mw.getString("temp"));
                tx4.setText("Humidity : "+mw.getString("humidity"));
            }
            Log.d("Temparature : ",mw.getString("temp"));
            Log.d("Hissedilen Sıcaklık",mw.getString("feels_like"));
            Log.d("Hava basıncı",mw.getString("pressure"));
            Log.d("Main",mw.getString("humidity"));

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            Toast.makeText(MainActivity.this, "Please enter a country correctly ", Toast.LENGTH_SHORT).show();
        }
    }
    public void identify(){
        tx1 = findViewById(R.id.textView2);
        tx2 = findViewById(R.id.textView3);
        tx3 = findViewById(R.id.textView4);
        tx4 = findViewById(R.id.textView5);
        editText = findViewById(R.id.editTextTextPersonName);

    }
}