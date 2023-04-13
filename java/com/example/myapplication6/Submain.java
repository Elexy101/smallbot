package com.example.myapplication6;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.*;

//Testing ChatGPT
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;


public class Submain<RequestNetwork> extends Activity {
    static final Integer[] imageview1 = new Integer[]{Integer.valueOf(R.drawable.chat1), Integer.valueOf(R.drawable.card), Integer.valueOf(R.drawable.website), Integer.valueOf(R.drawable.marketing), Integer.valueOf(R.drawable.social), Integer.valueOf(R.drawable.apk), Integer.valueOf(R.drawable.business), Integer.valueOf(R.drawable.code), Integer.valueOf(R.drawable.human)};
    static final String[] textview1 = new String[]{"Offline Chat", "Bank Short-Codes", "Scholarship News", "Investment Calculator", "IP Information", "Recommended Apps", "Naija-ICT business", "Dev Ops", "Contact Developer"};
    static final String[] textview2 = new String[]{"Have fun chatting with Smallbot", "Access/Fidelity/First/GT Bank", "Get Latest updates of scholarships", "Catfish/Rental/Poultry Business", "Find/Track Your IP", "Games/Photo Apps", "List of business with quality services", "coming soon...", "Ping me..."};
    int count = 0;
    ListView mListView;

    //===============================================
    //============= test 1 ==========================
    private HashMap<String, Object> requestBody = new HashMap<>();

    private HashMap<String, Object> requestHeaders = new HashMap<>();

    private String requestBodyText = "";

    private String requestBodyGson = "";

    private String Input_value = "";

    private HashMap<String, Object> response = new HashMap<>();

    private String clean1 = "";

    private String output = "";

    private String clean2 = "";

    private String clean3 = "";

    private String clean4 = "";

    private RequestNetwork host;

    //======================================================



    public class CustomAdapter extends BaseAdapter {
        Activity sActivity;

        public CustomAdapter(Activity mActivity) {
            this.sActivity = mActivity;
        }

        public int getCount() {
            Submain.this.count = Submain.textview1.length;
            return Submain.this.count;
        }

        public Object getItem(int position) {
            return Integer.valueOf(position);
        }

        public long getItemId(int position) {
            return (long) position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View mView = convertView;
            if (mView == null) {
                mView = ((LayoutInflater) this.sActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.listview_activity, null, false);
            }
            TextView sTV2 = (TextView) mView.findViewById(R.id.text2);
            ImageView sIMG = (ImageView) mView.findViewById(R.id.image1);
            ((TextView) mView.findViewById(R.id.text)).setText(Submain.textview1[position]);
            sTV2.setText(Submain.textview2[position]);
            sIMG.setImageResource(Submain.imageview1[position].intValue());
            return mView;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.submain);
        this.mListView = (ListView) findViewById(R.id.list_view);
        this.mListView.setAdapter(new CustomAdapter(this));
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (Submain.textview1[position].equals("Offline Chat")) {
                    Intent intent = new Intent(getApplicationContext(), MessagingActivity.class);
                    startActivity(intent);
                }
                if (Submain.textview1[position].equals("Bank Short-Codes")) {
                    //Submain.this.startActivity(new Intent(Submain.this, Bank_codes.class));

                    try {
                        String url = "https://api.openai.com/v1/completions";
                        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Authorization", "your_auth_code");

                        con.connect();
                        JSONObject json = new JSONObject();
                        json.put("model", "text-davinci-003");
                        json.put("prompt", "How are you?");
                        json.put("max_tokens", 4000);
                        json.put("temperature", 1.0);


                        /*
                        //requestBodyGson = new Gson().toJson(json);
                            String res = null;
                            StringBuilder sb = new StringBuilder();

                                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                                BufferedReader bfr = new BufferedReader(isr);
                                String  line = new Gson().toJson(json);
                                while (line != null) {
                                    sb.append(line);
                                    line = bfr.readLine();
                                }

                            res = sb.toString();

                            Log.e("Respond Code", "Error respond code :" + con.getResponseCode());



                        String text2 = "Hello world".toString();


                        response = new Gson().fromJson(res, new TypeToken<HashMap<String, Object>>(){}.getType());

                        output = response.get("choices").toString();
                        clean1 = output.substring(7);
                        String[] clean2 = clean1.split("index");
                        String clean3 = clean2[0];
                        clean3 = clean3.trim();
                        clean4 = clean3.substring(0, clean3.length() - 2);

*/
                        //Toast message
                        Toast toast_clean4 = Toast.makeText(getApplicationContext(), "hey", Toast.LENGTH_LONG);
                        toast_clean4.show();


                    } catch (Exception e) {
                        AlertDialog.Builder hanny;
                        hanny = new AlertDialog.Builder(Submain.this);
                        hanny.setTitle("Error");
                        hanny.setIcon(R.drawable.bill_gate);
                        hanny.setMessage(Html.fromHtml("sorry guyd"));
                        hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                        hanny.create().show();
                    }
                }

                if (Submain.textview1[position].equals("Google News")) {
                    //Submain.this.startActivity(new Intent(Submain.this, google_news.class));
                }
                if (Submain.textview1[position].equals("AndroHack(Specified user)")) {
                    //Submain.this.startActivity(new Intent(Submain.this, sub_hack.class));
                }
                           }
        });
    }


   /* private static String readFromStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (is!=null){
            InputStreamReader isr =new InputStreamReader(is);
            BufferedReader bfr = new BufferedReader(isr);
            String line = bfr.readLine();
            while (line!=null){
                sb.append(line);
                line=bfr.readLine();
            }
        }
        return sb.toString();
    } */


    //================================================================
    //======================== CHATGPT TEST FRAME ====================
    //================================================================

    /*
    public void chatGPT(String text) throws Exception {
        String url = "https://api.openai.com/v1/completions";
        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Authorization", "");

        JSONObject data = new JSONObject();
        data.put("model", "text-davinci-003");
        data.put("prompt", text);
        data.put("max_tokens", 4000);
        data.put("temperature", 1.0);

        con.setDoOutput(true);
        con.getOutputStream().write(data.toString().getBytes());


        String output = new BufferedReader(new InputStreamReader(con.getInputStream())).lines()
                .reduce((a, b) -> a + b).get();

        Toast Check_text = Toast.makeText(this, new JSONObject(output).getJSONArray("choices").getJSONObject(0).getString("text"), Toast.LENGTH_SHORT);
        Check_text.setGravity(17, 0, 0);
        Check_text.show();
    }

    public void main(String[] args) throws Exception {
        chatGPT("Hello, how are you?");
    }
     */

    }
