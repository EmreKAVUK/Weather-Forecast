package com.emre.degisitirlmisweather;


import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetJson extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        //Gelen Url'i connect edip geri döndürücez
        try {
            URL url = new URL(strings[0]);//URL sınıfından bir nesne yaratılır. Bağlantı kurulması istenilen HTTP bağlantısı parametre olarak geçilir.
            HttpURLConnection con = (HttpURLConnection) url.openConnection();// ile HTTP bağlantısı açılır.
            InputStream is = con.getInputStream();//Okumak için
            InputStreamReader reader = new InputStreamReader(is);//Yani bağlantı kurulmus olan URL üzerinden akış nesnesi alınır. Bu akış üzerinde ilgili HTTP bağlantısından dönen veri kümesi yer almaktadır.
            StringBuilder sb = new StringBuilder("");//Dönen değeri eklemek için kullanıyoruz karakter karakter dönecek çünkü.Başlangıc değeri boş olsun
            int a = reader.read();//Character buffer ıle blok blok okunabılır ama karakter okumak en garantisi
            while(a!=-1){//-1 gelirse okudugumuz değer yok demektir çünkü ascıı tüm degerler pozitif degerdir
                sb.append((char) a);
                a = reader.read();
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
