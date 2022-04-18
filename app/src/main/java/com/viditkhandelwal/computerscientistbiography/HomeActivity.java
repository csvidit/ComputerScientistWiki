package com.viditkhandelwal.computerscientistbiography;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.viditkhandelwal.computerscientistbiography.databinding.ActivityHomeBinding;

import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityHomeBinding binding;

    public static final int FROM_DONATE_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonBiography.setOnClickListener(this);
        binding.buttonMoreInfo.setOnClickListener(this);
        binding.buttonDonate.setOnClickListener(this);
        binding.textviewBornLocation.setOnClickListener(this);
        binding.textviewDeathLocation.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        Log.d("shawn", String.valueOf(view.getId()));
        switch(view.getId())
        {
            case R.id.button_biography:
                Log.d("shawn", String.valueOf(R.id.button_biography));
                Intent toBiography = new Intent(this, BiographyActivity.class);
                startActivity(toBiography);
                break;
            case R.id.button_more_info:
                Intent toMoreInfo = new Intent();
                toMoreInfo.setAction(Intent.ACTION_VIEW);
                toMoreInfo.setData(Uri.parse("https://en.wikipedia.org/wiki/Alan_Turing"));
//                if(toMoreInfo.resolveActivity(getPackageManager()) != null)
//                {
//                    startActivity(toMoreInfo);
//                }
                startActivity(toMoreInfo);

                break;
            case R.id.button_donate:
                Log.d("shawn", String.valueOf(R.id.button_donate));
                Intent toDonate = new Intent(this, DonateActivity.class);
                startActivityForResult(toDonate, FROM_DONATE_ACTIVITY);
                break;
            case R.id.textview_born_location:
                String geoLocation = "geo:0,0?q=London,England,United+Kingdom";
//                String[] words = binding.textviewBornLocation.getText().toString().substring(1).split(",");
//                Iterator<String> iterator = Arrays.stream(words).iterator();
//                while(iterator.hasNext())
//                {
//                    geoLocation+=iterator.next().trim()+"+";
//                }
//                geoLocation=geoLocation.substring(0, geoLocation.length()-2);
                Log.d("shawn", geoLocation);
                Intent toBornLocation = new Intent();
                toBornLocation.setAction(Intent.ACTION_VIEW);
                toBornLocation.setData(Uri.parse(geoLocation));
//                if(toBornLocation.resolveActivity(getPackageManager()) != null)
//                {
//                    startActivity(toBornLocation);
//                }
                startActivity(toBornLocation);
                break;
            case R.id.textview_death_location:
                String deathLocation = "geo:0,0?q=Cheshire,England,United+Kingdom";
                Intent toDeathLocation = new Intent();
                toDeathLocation.setAction(Intent.ACTION_VIEW);
                toDeathLocation.setData(Uri.parse(deathLocation));
                startActivity(toDeathLocation);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == FROM_DONATE_ACTIVITY && resultCode == Activity.RESULT_OK && data.getBooleanExtra(DonateActivity.EXTRA_RECEIVE_RECEIPT, false))
        {
            String messageUri = "smsto:"+data.getStringExtra(DonateActivity.EXTRA_PHONE_NUMBER);
            String smsBody = "Thank you "+data.getStringExtra(DonateActivity.EXTRA_FULL_NAME)+
                    " for your donation of $"+
                    data.getDoubleExtra(DonateActivity.EXTRA_AMOUNT, 0.0)+
                    " using the card number ending in "+
                    data.getStringExtra(DonateActivity.EXTRA_LAST_FOUR_DIGITS);
            Intent messageIntent = new Intent();
            messageIntent.setAction(Intent.ACTION_SENDTO);
            messageIntent.setData(Uri.parse(messageUri));
            messageIntent.putExtra("sms_body", smsBody);
//            if(messageIntent.resolveActivity(getPackageManager()) != null)
//            {
//                startActivity(messageIntent);
//            }
            startActivity(messageIntent);
        }
    }
}