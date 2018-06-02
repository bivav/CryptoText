package com.cryptotext.cryptotext;

import android.content.DialogInterface;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    HandleBottomNavigation pagerAdapter;
    EncodeMessage encodeMessage = new EncodeMessage();
    DecodeMessage decodeMessage =new DecodeMessage();
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager = (ViewPager)findViewById(R.id.view_pager_bottom_navigation);
        pagerAdapter = new HandleBottomNavigation(getSupportFragmentManager());

        pagerAdapter.addFragments(encodeMessage);
        pagerAdapter.addFragments(decodeMessage);

        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(pageChangeListener);

    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    bottomNavigationView.setSelectedItemId(R.id.encode_message_menu);
                    break;
                case 1:
                    bottomNavigationView.setSelectedItemId(R.id.decode_message_menu);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.encode_message_menu:
                    viewPager.setCurrentItem(0);
                    return true;

                case R.id.decode_message_menu:
                    viewPager.setCurrentItem(1);
                    return true;
            }
            return false;
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){

            case R.id.refresh:

                AlertDialog.Builder builder;
                builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle("Reset Text Fields")
                        .setMessage("Which One?")
                        .setPositiveButton("ENCODE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                encodeMessage.resetText();
                                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("DECODE", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                decodeMessage.resetText();
                                Toast.makeText(getApplicationContext(), "Done!", Toast.LENGTH_SHORT).show();
                            }
                        }).show();


                break;

            case R.id.about_us:

                /* Just a dialog box */
                builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle("About The App")
                        .setMessage("Interesting App Isn't It?")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Yep, Thanks for agreeing!", Toast.LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),
                                        "Hmm, Sad but thanks for downloading :)", Toast.LENGTH_LONG).show();
                            }
                        }).show();

                break;

            case R.id.help:

                /* Just a dialog box */
                builder = new android.support.v7.app.AlertDialog.Builder(this);
                builder.setTitle("Help?")
                        .setMessage("Just type in your text, do the magic and send it to anyone. " +
                                "Make sure they have this app else they will start scratching their head!")
                        .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "AWESOME!", Toast.LENGTH_SHORT).show();
                            }
                        }).show();

                break;
        }
        return true;
    }

}
