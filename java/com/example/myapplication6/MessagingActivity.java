package com.example.myapplication6;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.media.RingtoneManager;
import android.media.SoundPool;
import android.media.SoundPool.OnLoadCompleteListener;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapplication6.database.Message;
import com.example.myapplication6.database.MyDBAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;


public class MessagingActivity extends ListActivity implements OnClickListener, OnLoadCompleteListener, OnInitListener {
    private final String[] AI_KNOW = new String[]{"Of course, I am a good cook. I can teach you how to cook too."};
    final String LOG_TAG = "myLogs";
    final int MAX_STREAMS = 5;
    private final int REQ_CODE_VOICE_INPUT = 100;
    private final String[] access_bank = new String[]{"*901*Amount#", "*901*Amount*Phone Number#", "*901*Amount*Account Number#", "*901*00#", "*901*0#", "*901*911#", "*901*3#", "*901*11#", "*901*14#", "*901*10#", "*901*5#", "*901*13#", "*901*13#"};
    private final String[] africa_salad = new String[]{"Ingredients Needed\n7 Handfuls of Abacha known as Jigbo by Achi people.\nPalm oil\nGrind Dry Pepper\nSalt\n2 cubes of Maggi\n2 wraps of Ogiri\nNcha Jigbo/Akanwu/nUtazi leaves\nFried Cricket\nFried/Dried Fish\n\nSteps in cooking Africa Salad\n*Wash your dry fish in warm water and soak it in salty water.\n*Pour the Abacha in a sieve and sprinkle hot water on it and turn simultaneously until it softens a bit.\n*Place the ncha jigbo/potash in a cup of bowl and pour about 2 cups of water.\n*Place your frying pan on fire, when it gets hot, bring down and pour oil in it.\n*Bring out your clean Aluminium pot/Clay pot. Open the 2 wraps of Ogiri.\n*Pour in the sieved ncha Jigbo/akanwu and knead the Ogiri with your hand till it mix well.\n*Pour the Abacha/Jigbo into the pot(Aluminium/Clay)\n*Add half of the melted palm oil.\n*Turn the mixture\n*Add Salt, Pepper, Maggi to taste\n*If the Abacha/Jigbo is still not soft to your taste, add the ncha jigbo/akanwu hot water and turn the mixture again\n*The delicacy is ready to be served."};
    private final String[] best_cook = new String[]{"The best cook is Emmanuel Linus. \nBirth Age: 31st May, 1998\nAlive: Active\nCook Type: Legend\nHe knows how to cook fried rice, chicken, meat-pie and was soon made a Sultan in the Kitchen ward room"};
    Button btnSend;
    private String coinbtc = "http://www.nse.com.ng/";
    private final String[] comments = new String[]{"Ingredients Needed\n2 cups of rice\n1 onion\n1 red bell pepper\n100g tomato\n2 scott bonnet\n100ml vegetable oil\nMaggi\nSalt to taste\n1 teaspoon curry\n1 teaspoon thyme\n1/2 teaspoon garlic\n1/4 teaspoon ginger\n2 cups water or stock\nSteps in cooking Jellof rice\n*Blend your tomatoes, scotch bonnet and tatashe together\n*Put your rice into a bowl and soak with hot water.\n*Put oil in a pot and allow it to heat up.\n*Add your chopped onions and allow to fry, but be sure not to burn them.\n*Add your blended ingredients and puree and allow to fry, just until you get rid of the sour taste(about 10-15 minutes)\n*Add your maggi, garlic, ginger, curry, thyme and salt and mix together\n*Pour your rice into the pot and mix together, making sure it is covered in the tomato sauce.\n*Cook on low medium heat.\n* Cook until soft.\n*Jellof rice is ready to serve.  "};
    Cursor cursor;
    private CustomCursorAdapter customAdapter;
    Date date1 = new Date();
    Date date2 = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    SimpleDateFormat dateFormat2 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
    private final String[] error = new String[]{"Sorry, an error occured when fetching data!"};
    EditText etmyMessage;
    private final String[] fake1 = new String[]{"Analyzing...\nAlert Notified!!!"};
    private final String[] fk = new String[]{"50,000", "100,000", "200,000", "70,000", "65,000", "300,000", "500,000", "85,000", "90,000"};
    private final String[] fufu = new String[]{"Time to Hack..."};
    private final String[] how_u = new String[]{"I am fine. You are such a nice person."};
    private String htmlContentInStringFormat;
    private Document htmlDocument;
    private final String[] hy = new String[]{"Hello, I am a Live A.I programmed by Emmanuel Elexy. I am here to talk to you."};
    CharSequence[] items = new CharSequence[]{"TRUE", "FALSE"};
    boolean[] itemsChecked = new boolean[this.items.length];
    private boolean listIsTouched = false;
    ListView lvData;
    private Button micButton;
    MyDBAdapter mydatbase;
    private final String[] q1 = new String[]{"Today date is ".concat(this.dateFormat.format(this.date1))};
    private final String[] q2 = new String[]{"The time is ".concat(this.dateFormat2.format(this.date2))};
    private final String[] q3 = new String[]{"Business is a deal between two or more people to accomplish a common goal."};
    private final String[] q4 = new String[]{"I dont really know, but you can start a business with what you love doing."};
    private final String[] q5 = new String[]{"You can start a business either solely, or with your friends/family based on trust."};
    private final String[] roasted_yam = new String[]{"Ingredients Needed\n1 large yam, peeled and cut into 1/4 inch(6mm) thick slices\n1 teaspoon (5g) kosher salt\n1 teaspoon (2g) freshly ground black pepper\n2 tablespoons (30ml) olive oil\nSteps in cooking Roasted Yam\n*Preheat the oven/stove and prepare a pan.\n*Cut the yam and place it on the sheet.\n*Season the yam with ingredients\n*Roast the sliced yam for about 30-minutes."};
    int soundIdMessage;
    SoundPool sp;
    private final String[] tenk_biz = new String[]{"London used clothes(Okrika)\nPure water\nBuying/Reselling\nNoodles preparation\nLiquid soap production\nDaycare center\nSelling fruits\nHome tutoring\nWeb publishing\nBead-making\nCrafting\nExercise instructor\nGraphic designer\nMobile phone repairs\nForeign language teacher\ngreetings card\nFreelance photographer\nCooking service\nE-books publishing\nMake-up artistry"};
    private TextToSpeech textToSpeech;
    private TextView voiceInput;
    private final String[] welcome = new String[]{"Hello user, I'm Emmabot. the smartest bot in Nigeria that can tell you anthing!"};
    private final String[] yam_sauce = new String[]{"Sorry "};

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        private JsoupAsyncTask() {
        }

        /* synthetic */ JsoupAsyncTask(MessagingActivity messagingActivity, JsoupAsyncTask jsoupAsyncTask) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* Access modifiers changed, original: protected|varargs */
        public Void doInBackground(Void... params) {
            try {
                MessagingActivity.this.htmlDocument = Jsoup.connect(MessagingActivity.this.coinbtc).get();
                MessagingActivity.this.htmlContentInStringFormat = MessagingActivity.this.htmlDocument.title();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /* Access modifiers changed, original: protected */
        public void onPostExecute(Void result) {
            Builder hanny = new Builder(MessagingActivity.this);
            hanny.setTitle(MessagingActivity.this.htmlContentInStringFormat);
            hanny.setIcon(R.drawable.ic_launcher);
            hanny.setMessage(Html.fromHtml("Title: ".concat(MessagingActivity.this.htmlContentInStringFormat)));
            hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            hanny.create().show();
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.textToSpeech = new TextToSpeech(this, this);
        this.sp = new SoundPool(5, 3, 0);
        this.sp.setOnLoadCompleteListener(this);
        this.soundIdMessage = this.sp.load(this, R.raw.message, 1);
        setContentView(R.layout.messaging_screen);
        this.btnSend = (Button) findViewById(R.id.btnSend);
        this.btnSend.setOnClickListener(this);
        this.lvData = (ListView) findViewById(android.R.id.list);
        this.etmyMessage = (EditText) findViewById(R.id.text);
        ImageView img = (ImageView) findViewById(R.id.image2);
        img.setBackgroundResource(R.drawable.spin_animation);
        ((AnimationDrawable) img.getBackground()).start();
        this.mydatbase = new MyDBAdapter(this);
        this.mydatbase.open();
        this.textToSpeech = new TextToSpeech(this, this);
        this.customAdapter = new CustomCursorAdapter(this, this.mydatbase.getAllEntries());
        this.lvData.setAdapter(this.customAdapter);
        getListView().setSelection(this.mydatbase.getNumberOfMessages() - 1);
        try {
            InputStream in = getBaseContext().getAssets().open("juuc/index.html", 3);
            this.htmlContentInStringFormat = StreamToString(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.lvData.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                int i;
                int i2 = 0;
                if (scrollState == 1) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (scrollState == 2) {
                    i2 = 1;
                }
                if ((i2 | i) != 0) {
                    MessagingActivity.this.listIsTouched = true;
                }
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
        this.micButton = (Button) findViewById(R.id.btnSend1);
        this.micButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MessagingActivity.this.getVoiceInput();
            }
        });
    }

    private void getVoiceInput() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
        intent.putExtra("android.speech.extra.PROMPT", "Please Speak something!");
        try {
            startActivityForResult(intent, 100);
        } catch (ActivityNotFoundException e) {
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.messaging, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_clear_history) {
            this.listIsTouched = false;
            this.mydatbase.clearHistory();
            this.customAdapter = new CustomCursorAdapter(this, this.mydatbase.getAllEntries());
            this.lvData.setAdapter(this.customAdapter);
            this.customAdapter.notifyDataSetChanged();
            getListView().setSelection(this.mydatbase.getNumberOfMessages() - 1);
            return true;
        }
        Builder hanny;
        if (id == R.id.about_us) {
            hanny = new Builder(this);
            hanny.setTitle("About HourLive App");
            hanny.setIcon(R.drawable.ic_launcher);
            hanny.setMessage(Html.fromHtml("<font color='#ff6288' size=+0.4>Thank you for downloading HourLive App.<br>I hope you enjoy the app. Our <b>mission</b> is answering users' question.<br><br><hr><br><font color='green'>Powered By Greentions Inc.</font>"));
            hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            hanny.create().show();
        }
        if (id == R.id.help_us) {
            hanny = new Builder(this);
            hanny.setTitle("Help me?");
            hanny.setIcon(R.drawable.ic_launcher);
            hanny.setMessage(Html.fromHtml("<font face='harrington' color='#ff6288' size=+0.4>In order to start using HourLive, there are some steps needed to help you:<br><b>STEP ONE:</b><br>1. text 'Cook Rice', and it will text you back on how rice could be cooked.<br>2. text 'Plant Rice', the Food AI will give you hint on how rice could planted and harvested if you are a farmer with little or no knowledge of farming.<br>3. Never text 'How to cook Rice or any anonymous text' that differs from step 1 and 2.</font><br><font color='green'>Powered By Green&copy Inc. 2017</font>"));
            hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            hanny.create().show();
        }
        if (id == R.id.basic_commands) {
            hanny = new Builder(this);
            hanny.setTitle("Basic Command Guide");
            hanny.setIcon(R.drawable.ic_launcher);
            hanny.setMessage(Html.fromHtml("<font face='harrington' color='#ff6288' size=+0.4><b>Welcome Address</b><br>Type in 'hi,hello,whatsup,xup or hy' and Food AI will respond back.<br><b>Comment Address</b><br>*Type in 'how are you?' and Food AI will respond back.<br><b>List all food I have</b><br>*Type in 'All Food' and it will list all its 400 Free Food, its names.<br><b>Food Recipe</b><br>*Type in '<Food Name>' and Food AI will respond to you, but if no food found, it will tell its developer about it.<br><br><font color='green'>Powered By Green&copy Inc. 2017</font>"));
            hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            hanny.create().show();
        }
        if (id == R.id.phone_info) {
            Builder rateUs = new Builder(this);
            String manufaturer_model = Build.MANUFACTURER;
            String hardware_model = Build.MODEL;
            String model_access = Build.BRAND;
            String product_model = Build.ID;
            String language = Locale.getDefault().getLanguage();
            String AndroidID = Secure.getString(getContentResolver(), "android_id");
            rateUs.setTitle("Phone Info");
            rateUs.setIcon(R.drawable.ic_launcher);
            rateUs.setMessage(Html.fromHtml("<b><font color='#aaaaaa'>Phone Manufaturer: </font></b>" + manufaturer_model + "<br><b><font color='#aaaaaa'>Phone Model: </font></b>" + hardware_model + "<br><b><font color='#aaaaaa'>Phone Brand: </font></b>" + model_access + "<br><b><font color='#aaaaaa'>Phone ID: </font></b>" + product_model + "<br><b><font color='#aaaaaa'>Phone Language: </b></font>" + language + "<br><b><font color='#aaaaaa'>AndroidID: </font></b>" + AndroidID + "<br><br><b><font color='#aaaaaa'>Your device supports <i>Food AI</i>. Thanks for Installing...</font></b>"));
            rateUs.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.cancel();
                }
            });
            rateUs.create().show();
        }
        return super.onOptionsItemSelected(item);
    }

    private String getDateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
    }

    public Message createMessage(String text, int user) {
        return new Message(text, getDateTime(), user);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSend /*2131427360*/:
                Builder hanny;
                String myMessageText = this.etmyMessage.getText().toString().trim();
                if (myMessageText.length() > 0) {
                    this.etmyMessage.setText("");
                    this.listIsTouched = false;
                    addNewMessage(myMessageText, 1);
                }
                if (myMessageText.contains("hy") || myMessageText.contains("hello") || myMessageText.contains("whatsup") || myMessageText.contains("hi") || myMessageText.contains("xup") || myMessageText.contains("Hi") || myMessageText.contains("Hello") || myMessageText.contains("Hy") || myMessageText.contains("Whatsup") || myMessageText.contains("Xup")) {
                    addNewMessage(getRandomMessage6(), 0);
                    convertTextToSpeech3();
                }
                if (myMessageText.contains("Bill Gates")) {
                    hanny = new Builder(this);
                    hanny.setTitle("Bill Gate");
                    hanny.setIcon(R.drawable.bill_gate);
                    hanny.setMessage(Html.fromHtml("<font face='harrington' color='#ff6288' size=+0.4><b>Born:</b> October 28, 1955 <br>William Henry Bill Gates is an American business magnate, philanthropist, author and chairman of Microsoft(the software he founded with Paul Allen). He is consistently ranked among the world wealthiest people in the world. During his career at Microsoft, Gates held the positions of CEO and chief software architect.</font>"));
                    hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    hanny.setPositiveButton("Speak Chinese", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MessagingActivity.this.convertTextToSpeech20();
                        }
                    });
                    hanny.create().show();
                }

                if (myMessageText.contains("how are you?") || myMessageText.contains("How are you?") || myMessageText.contains("how are you") || myMessageText.contains("How are you")) {
                    addNewMessage(getRandomMessage7(), 0);
                    convertTextToSpeech4();
                }
                if (myMessageText.contains("Jellof Rice")) {
                    addNewMessage(getRandomMessage(), 0);
                    convertTextToSpeech();
                }
                if (myMessageText.contains("Access Bank") || myMessageText.contains("Access Bank codes")) {
                    Builder access1 = new Builder(this);
                    access1.setTitle("Access Bank Short-codes");
                    final List<String> codes1 = new ArrayList();
                    codes1.add("Self airtime purchase");
                    codes1.add("Airtime to others");
                    codes1.add("Transfer");
                    codes1.add("Balance Enquiry");
                    codes1.add("Pin change/reset");
                    codes1.add("To deactivate Phone number");
                    codes1.add("Bill Payments");
                    codes1.add("PayDay Loan");
                    codes1.add("Dual Transaction Service(DTS)");
                    codes1.add("Access Yellow");
                    codes1.add("Update Customer");
                    codes1.add("Diamond Xtra");
                    codes1.add("Access Africa");
                    access1.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, codes1), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            if (((String) codes1.get(arg1)).contains("Self airtime purchase")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage26(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Airtime to others")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage27(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Transfer")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage28(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Balance Enquiry")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage29(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Pin change/reset")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage30(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("To deactivate Phone number")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage31(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Bill Payments")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage32(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("PayDay Loan")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage33(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Dual Transaction Service(DTS)")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage34(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Access Yellow")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage35(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Update Customer")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage36(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Diamond Xtra")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage37(), 0);
                            }
                            if (((String) codes1.get(arg1)).contains("Access Africa")) {
                                MessagingActivity.this.addNewMessage(MessagingActivity.this.getRandomMessage38(), 0);
                            }
                            Toast.makeText(MessagingActivity.this, "You have selected " + ((String) codes1.get(arg1)), Toast.LENGTH_SHORT).show();
                        }
                    });
                    access1.create().show();
                }
                if (myMessageText.contains("Do you know how to cook?") || myMessageText.contains("do you know how to cook?") || myMessageText.contains("Do you know how to cook") || myMessageText.contains("do you know how to cook?")) {
                    addNewMessage(getRandomMessage8(), 0);
                    convertTextToSpeech5();
                }
                if (myMessageText.contains("Roasted Yam")) {
                    addNewMessage(getRandomMessage4(), 0);
                    convertTextToSpeech1();
                }
                if (myMessageText.contains("10k business") || myMessageText.contains("How to start a 10k business?") || myMessageText.contains("How to start a 10,000 naira business?") || myMessageText.contains("Business to start with 10,000?")) {
                    addNewMessage(getRandomMessage15(), 0);
                    convertTextToSpeech12();
                }
                if (myMessageText.contains("Submain")) {
                    startActivity(new Intent(this, Submain.class));
                }
                if (myMessageText.contains("Title?")) {
                    addNewMessage(getRandomMessage16(), 0);
                    convertTextToSpeech13();
                }
                if (myMessageText.contains("Link?")) {
                    addNewMessage(getRandomMessage17(), 0);
                    convertTextToSpeech14();
                }
                if (myMessageText.contains("ID 1?")) {
                    addNewMessage(getRandomMessage18(), 0);
                    convertTextToSpeech15();
                }
                if (myMessageText.contains("btc") || myMessageText.contains("BTC")) {
                    new JsoupAsyncTask(this, null).execute(new Void[0]);
                }
                if (myMessageText.contains("Who is the best cook in the world?")) {
                    addNewMessage(getRandomMessage9(), 0);
                    convertTextToSpeech6();
                }
                if (myMessageText.contains("African Salad")) {
                    addNewMessage(getRandomMessage5(), 0);
                    convertTextToSpeech2();
                }
                if (myMessageText.contains("Fufu")) {
                    addNewMessage(getRandomMessage2(), 0);
                }
                if (myMessageText.contains("Yam") && Build.BRAND.contains("generic-1")) {
                    addNewMessage(getRandomMessage3(), 0);
                }
                if (myMessageText.equals("")) {
                    Toast Check_text = Toast.makeText(this, "Please Enter a Message!!!", Toast.LENGTH_SHORT);
                    Check_text.setGravity(17, 0, 0);
                    Check_text.show();
                }
                if (myMessageText.contains("Date") || myMessageText.contains("date")) {
                    addNewMessage(getRandomMessage10(), 0);
                    convertTextToSpeech7();
                }
                if (myMessageText.contains("Time") || myMessageText.contains(MyDBAdapter.MESSAGES_TIME)) {
                    addNewMessage(getRandomMessage11(), 0);
                    convertTextToSpeech8();
                }
                if (myMessageText.contains("What is Business") || myMessageText.contains("what is business")) {
                    addNewMessage(getRandomMessage12(), 0);
                    convertTextToSpeech9();
                }
                if (myMessageText.contains("How do i start a business") || myMessageText.contains("how do i start a business")) {
                    addNewMessage(getRandomMessage13(), 0);
                    convertTextToSpeech10();
                }
                if (myMessageText.contains("who to start business with") || myMessageText.contains("Who to start business with")) {
                    addNewMessage(getRandomMessage14(), 0);
                    convertTextToSpeech11();
                }
                if (myMessageText.contains("Fake Alert1")) {
                    addNewMessage(getRandomMessage20(), 0);
                    simple_Notification();
                }
                if (myMessageText.contains("Yam Only") && Build.BRAND.contains("")) {
                    hanny = new Builder(this);
                    hanny.setTitle("Premium Version Only");
                    hanny.setIcon(R.drawable.ic_launcher);
                    hanny.setMessage(Html.fromHtml("<font face='harrington' color='#ff6288' size=+0.4>Our application has restricted you not to view the preparation of yam and sauce. but in order to view and let the Food AI response to your request, you need to pay an outstanding fee for N500.00 to just not get Yam and Sauce only, but to get more than a 100 food preparation/planting.<br>Thank you for using Food AI<br><br></font><font color='green'>Powered By Green&copy Inc. 2017</font>"));
                    hanny.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
                    hanny.create().show();
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 100:
                if (resultCode == -1 && data != null) {
                    this.etmyMessage.setText((CharSequence) data.getStringArrayListExtra("android.speech.extra.RESULTS").get(0));
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        Log.d("myLogs", "onResume");
        this.mydatbase.open();
        super.onResume();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        Log.d("myLogs", "onPause");
        this.mydatbase.close();
        super.onPause();
    }

    public void addNewMessage(String m, int user) {
        int index = this.lvData.getFirstVisiblePosition();
        View v = this.lvData.getChildAt(0);
        int top = v == null ? 0 : v.getTop();
        this.mydatbase.addMessageToDB(createMessage(m, user));
        this.customAdapter = new CustomCursorAdapter(this, this.mydatbase.getAllEntries());
        this.lvData.setAdapter(this.customAdapter);
        this.customAdapter.notifyDataSetChanged();
        this.sp.play(this.soundIdMessage, 1.0f, 1.0f, 0, 0, 1.0f);
        if (this.listIsTouched) {
            this.lvData.setSelectionFromTop(index, top);
        } else {
            getListView().setSelection(this.mydatbase.getNumberOfMessages() - 1);
        }
    }

    public void onBackPressed() {
        Builder yes_no = new Builder(this);
        yes_no.setTitle("Exit Button");
        yes_no.setMessage(Html.fromHtml("<font size=0.01>Are you sure you want</font> to exit?"));
        yes_no.setIcon(android.R.drawable.ic_dialog_alert);
        yes_no.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                MessagingActivity.this.finish();
            }
        });
        yes_no.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        yes_no.create().show();
    }

    public void run() {
        if (this.etmyMessage.getText().toString().trim().contains("Rice")) {
            Toast Check_text = Toast.makeText(this, "Please Enter a Message!!!", Toast.LENGTH_SHORT);
            Check_text.setGravity(17, 0, 0);
            Check_text.show();
            addNewMessage(getRandomMessage(), 0);
        }
    }

    public String getRandomMessage() {
        return this.comments[new Random().nextInt(this.comments.length)];
    }

    public String getRandomMessage2() {
        return this.fufu[new Random().nextInt(this.fufu.length)];
    }

    public String getRandomMessage3() {
        return this.yam_sauce[new Random().nextInt(this.yam_sauce.length)];
    }

    public String getRandomMessage4() {
        return this.roasted_yam[new Random().nextInt(this.roasted_yam.length)];
    }

    public String getRandomMessage5() {
        return this.africa_salad[new Random().nextInt(this.africa_salad.length)];
    }

    public String getRandomMessage6() {
        return this.hy[new Random().nextInt(this.hy.length)];
    }

    public String getRandomMessage7() {
        return this.how_u[new Random().nextInt(this.how_u.length)];
    }

    public String getRandomMessage8() {
        return this.AI_KNOW[new Random().nextInt(this.AI_KNOW.length)];
    }

    public String getRandomMessage9() {
        return this.best_cook[new Random().nextInt(this.best_cook.length)];
    }

    public String getRandomMessage10() {
        return this.q1[new Random().nextInt(this.q1.length)];
    }

    public String getRandomMessage11() {
        return this.q2[new Random().nextInt(this.q2.length)];
    }

    public String getRandomMessage12() {
        return this.q3[new Random().nextInt(this.q3.length)];
    }

    public String getRandomMessage13() {
        return this.q4[new Random().nextInt(this.q4.length)];
    }

    public String getRandomMessage14() {
        return this.q5[new Random().nextInt(this.q5.length)];
    }

    public String getRandomMessage22() {
        return this.fk[new Random().nextInt(this.fk.length)];
    }

    private String getRandomMessage20() {
        return this.fake1[new Random().nextInt(this.fake1.length)];
    }

    private String getRandomMessage27() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[1];
    }

    private String getRandomMessage26() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[0];
    }

    private String getRandomMessage28() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[2];
    }

    private String getRandomMessage29() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[3];
    }

    private String getRandomMessage30() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[4];
    }

    private String getRandomMessage31() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[5];
    }

    private String getRandomMessage32() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[6];
    }

    private String getRandomMessage33() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[7];
    }

    private String getRandomMessage34() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[8];
    }

    private String getRandomMessage35() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[9];
    }

    private String getRandomMessage36() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[10];
    }

    private String getRandomMessage37() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[11];
    }

    private String getRandomMessage38() {
        int nextInt = new Random().nextInt(this.access_bank.length);
        return this.access_bank[12];
    }

    public String getRandomMessage15() {
        return this.tenk_biz[new Random().nextInt(this.tenk_biz.length)];
    }

    public String getRandomMessage16() {
        this.htmlDocument = Jsoup.parse(this.htmlContentInStringFormat);
        return this.htmlDocument.title();
    }

    public String getRandomMessage17() {
        this.htmlDocument = Jsoup.parse(this.htmlContentInStringFormat);
        return Jsoup.clean(this.htmlDocument.select("a[href]").toString(), Whitelist.none());
    }

    public String getRandomMessage18() {
        this.htmlDocument = Jsoup.parse(this.htmlContentInStringFormat);
        String id1 = this.htmlDocument.getElementById("hey").outerHtml();
        String id3 = this.htmlDocument.getElementsByClass("c v").outerHtml();
        return Jsoup.clean(id1, Whitelist.none());
    }

    @TargetApi(11)
    private void simple_Notification() {
        Notification.Builder builder = new Notification.Builder(this).setSmallIcon(R.drawable.access1).setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)).setContentTitle("Access Bank").setContentText("Credit\nAmt:NGN".concat(getRandomMessage22()).concat(".00 Cr\nAcc:004*****158\nDesc: TRANSFER FROM JAMES/PAYSTACK")).setAutoCancel(true).setDefaults(-1);
        builder.setSound(RingtoneManager.getDefaultUri(2));
        ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).notify(0, builder.getNotification());
    }

    public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
    }

    public void onInit(int status) {
        if (status == 0) {
            int result = this.textToSpeech.setLanguage(Locale.ENGLISH);
            if (result == -1 || result == -2) {
                Log.e("error", "This language is not suppported");
                return;
            }
            return;
        }
        Log.e("error", "Initilization Failed!");
    }

    public void onDestroy() {
        super.onDestroy();
        this.textToSpeech.shutdown();
    }

    private void convertTextToSpeech() {
        this.textToSpeech.speak("Ingredients Needed\n1. 2 cups of r1ice\n2. 1 onion\n3. 1 red bell pepper\n4. 100g tomato\n5. 2 scott bonnet\n6. 100ml vegetable oil\n7. Maggi\n8. Salt to taste\n9. 1 teaspoon curry\n10. 1 teaspoon thyme\n11. 1/2 teaspoon garlic\n1. 1/4 teaspoon ginger\n1. 2 cups water or stock\nSteps in cooking Jellof rice\n1. Blend your tomatoes, scotch bonnet and tatashe together\n2. Put your rice into a bowl and soak with hot water.\n3. Put oil in a pot and allow it to heat up.\n4. Add your chopped onions and allow to fry, but be sure not to burn them.\n5. Add your blended ingredients and puree and allow to fry, just until you get rid of the sour taste(about 10-15 minutes)\n6. Add your maggi, garlic, ginger, curry, thyme and salt and mix together\n7. Pour your rice into the pot and mix together, making sure it is covered in the tomato sauce.\n8. Cook on low medium heat.\n9. Cook until soft.\n10. Jellof rice is ready to serve.\n\nPowered By Green Incorporation", 0, null);
    }

    private void convertTextToSpeech1() {
        this.textToSpeech.speak("Ingredients Needed\n1 large yam, peeled and cut into 1/4 inch(6mm) thick slices\n1 teaspoon (5g) kosher salt\n1 teaspoon (2g) freshly ground black pepper\n2 tablespoons (30ml) olive oil\nSteps in cooking Roasted Yam\n1. Preheat the oven/stove and prepare a pan.\n2. Cut the yam and place it on the sheet.\n3.Season the yam with ingredients\n4. Roast the sliced yam for about 30-minutes.\n\nPowered By Green Incorporation", 0, null);
    }

    private void convertTextToSpeech2() {
        this.textToSpeech.speak("Ingredients Needed\n7 Handfuls of Abacha known as Jigbo by Achi people.\nPalm oil\nGrind Dry Pepper\nSalt\n2 cubes of Maggi\n2 wraps of Ogiri\nNcha Jigbo/Akanwu/nUtazi leaves\nFried Cricket\nFried/Dried Fish\n\nSteps in cooking Africa Salad\n1. Wash your dry fish in warm water and soak it in salty water.\n2. Pour the Abacha in a sieve and sprinkle hot water on it and turn simultaneously until it softens a bit.\n3. Place the ncha jigbo/potash in a cup of bowl and pour about 2 cups of water.\n4. Place your frying pan on fire, when it gets hot, bring down and pour oil in it.\n5. Bring out your clean Aluminium pot/Clay pot. Open the 2 wraps of Ogiri.\n6. Pour in the sieved ncha Jigbo/akanwu and knead the Ogiri with your hand till it mix well.\n7. Pour the Abacha/Jigbo into the pot(Aluminium/Clay)\n8. Add half of the melted palm oil.\n9. Turn the mixture\n10. Add Salt, Pepper, Maggi to taste\n11. If the Abacha/Jigbo is still not soft to your taste, add the ncha jigbo/akanwu hot water and turn the mixture again\n12. The delicacy is ready to be served.\n\nPowered By Green Incorporation", 0, null);
    }

    private void convertTextToSpeech3() {
        this.textToSpeech.speak("Hello, I am a Live AI programmed by Emmanuel Elexy, CEO/Founder of Green Inc. I am here to guide you on the steps and procedure on how to cook the food i know. If you have any question/problem, email us @ Emmanuelekpe0007@yahoo.com.\nThank YOU...", 0, null);
    }

    private void convertTextToSpeech4() {
        this.textToSpeech.speak("I am fine. You are such a nice person.", 0, null);
    }

    private void convertTextToSpeech5() {
        this.textToSpeech.speak("Of course, I am a good cook. I can teach you how to cook too.", 0, null);
    }

    private void convertTextToSpeech6() {
        this.textToSpeech.speak("The best cook is Emmanuel Linus. \nBirth Age: 31st May, 1998\nAlive: Active\nCook Type: Legend\nHe knows how to cook fried rice, chicken, meat-pie and was soon made a Sultan in the Kitchen ward room", 0, null);
    }

    private void convertTextToSpeech7() {
        this.textToSpeech.speak("Today date is ".concat(this.dateFormat.format(this.date1)), 0, null);
    }

    private void convertTextToSpeech8() {
        this.textToSpeech.speak("The time is ".concat(this.dateFormat2.format(this.date2)), 0, null);
    }

    private void convertTextToSpeech9() {
        this.textToSpeech.speak("Business is a deal between two or more people to accomplish a common goal", 0, null);
    }

    private void convertTextToSpeech10() {
        this.textToSpeech.speak("I dont really know, but you can start a business with what you love doing.", 0, null);
    }

    private void convertTextToSpeech11() {
        this.textToSpeech.speak("You can start a business either solely, or with your friends/family based on trust", 0, null);
    }

    private void convertTextToSpeech12() {
        this.textToSpeech.speak("London used clothes(Okrika)\nPure water\nBuying/Reselling\nNoodles preparation\nLiquid soap production\nDaycare center\nSelling fruits\nHome tutoring\nWeb publishing\nBead-making\nCrafting\nExercise instructor\nGraphic designer\nMobile phone repairs\nForeign language teacher\ngreetings card\nFreelance photographer\nCooking service\nE-books publishing\nMake-up artistry", 0, null);
    }

    private void convertTextToSpeech13() {
        this.textToSpeech.speak(this.htmlDocument.title(), 0, null);
    }

    private void convertTextToSpeech14() {
        this.textToSpeech.speak(Jsoup.clean(this.htmlDocument.select("a[href]").toString(), Whitelist.none()), 0, null);
    }

    private void convertTextToSpeech15() {
        this.textToSpeech.speak(Jsoup.clean(this.htmlDocument.getElementById("hey").outerHtml(), Whitelist.none()), 0, null);
    }

    public void onInit2(int status) {
        if (status == 0) {
            int result = this.textToSpeech.setLanguage(Locale.CHINA);
            if (result == -1 || result == -2) {
                Log.e("error", "This language is not suppported");
                return;
            } else {
                convertTextToSpeech20();
                return;
            }
        }
        Log.e("error", "Initilization Failed!");
    }

    private void convertTextToSpeech20() {
        this.textToSpeech.speak("Born on October 28, 1955, William Henry Bill Gates is an American business magnate, philanthropist, author and chairman of Microsoft(the software he founded with Paul Allen). He is consistently ranked among the world wealthiest people in the world. During his career at Microsoft, Gates held the positions of CEO and chief software architect.", 0, null);
    }

    public void onInit3(int status) {
        if (status == 0) {
            int result = this.textToSpeech.setLanguage(Locale.CHINA);
            if (result == -1 || result == -2) {
                Log.e("error", "This language is not suppported");
                return;
            } else {
                convertTextToSpeech21();
                return;
            }
        }
        Log.e("error", "Initilization Failed!");
    }



    public static String StreamToString(InputStream in) throws IOException {
        if (in == null) {
            return "";
        }
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        while (true) {
            int n = reader.read(buffer);
            if (n == -1) {
                return writer.toString();
            }
            writer.write(buffer, 0, n);
        }
    }
}
