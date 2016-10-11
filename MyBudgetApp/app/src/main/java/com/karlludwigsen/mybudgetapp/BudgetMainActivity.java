package com.karlludwigsen.mybudgetapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.InputStreamReader;

public class BudgetMainActivity extends AppCompatActivity
{
    Button btnHit;
    TextView tvDataTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_main);

        btnHit = (Button) findViewById(R.id.GetDataBtn);
        tvDataTextView = (TextView) findViewById(R.id.tvJsonItem);

        btnHit.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                new JSONTask().execute("http://karlludwigsen.com/myexamplejson.json");

            }
        });
    }
    public class JSONTask extends AsyncTask<String, String, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            HttpURLConnection connection = null;
            BufferedReader reader = null;
            StringBuffer buffer = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader((new InputStreamReader(stream)));
                buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null)
                {
                    buffer.append(line);
                }
                    /*
                    This cannot be done! Gotcha
                    tvDataTextView.setText(buffer.toString());
                    */
            }
            catch (MalformedURLException e)
            {
                e.printStackTrace();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (connection != null)
                {
                    connection.disconnect();
                }
                try
                {
                    if (reader != null)
                    {
                        reader.close();
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            return buffer.toString();
        }
        @Override
        protected void onPostExecute(String result)
        {
            super.onPostExecute(result);
            tvDataTextView.setText(result);
        }
    }

}
