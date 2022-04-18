package com.viditkhandelwal.computerscientistbiography;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.viditkhandelwal.computerscientistbiography.databinding.ActivityDonateBinding;

public class DonateActivity extends AppCompatActivity {

    public static final String EXTRA_FULL_NAME="com.viditkhandelwal.computerscientistbiography.EXTRA_FULL_NAME";
    public static final String EXTRA_PHONE_NUMBER="com.viditkhandelwal.computerscientistbiography.EXTRA_PHONE_NUMBER";
    public static final String EXTRA_AMOUNT="com.viditkhandelwal.computerscientistbiography.EXTRA_AMOUNT";
    public static final String EXTRA_LAST_FOUR_DIGITS="com.viditkhandelwal.computerscientistbiography.EXTRA_LAST_FOUR_DIGITS";
    public static final String EXTRA_CVC="com.viditkhandelwal.computerscientistbiography.EXTRA_CVC";
    public static final String EXTRA_RECEIVE_RECEIPT="com.viditkhandelwal.computerscientistbiography.RECEIVE_RECEIPT";

    ActivityDonateBinding binding;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);
        binding = ActivityDonateBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonDonateSubmit.setOnClickListener(button_donate_submit_clickListener);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error").setMessage("The Credit Card number is invalid").setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.hide();
            }
        });
        dialog = builder.create();
    }

    private View.OnClickListener button_donate_submit_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String cardNum = binding.edittextCreditCardNumber.getText().toString();
            Log.d("shawn", String.valueOf(cardNum.indexOf("-")));
            Log.d("shawn", String.valueOf(cardNum.indexOf("-", cardNum.indexOf("-"))));
            Log.d("shawn", String.valueOf(cardNum.lastIndexOf("-")));
            if(cardNum.indexOf("-") == 4
                    && cardNum.indexOf("-", cardNum.indexOf("-")) == 4
                    && cardNum.lastIndexOf("-") == 14)
            {
                Intent returnIntent = new Intent();
                returnIntent.putExtra(EXTRA_FULL_NAME, binding.edittextFullName.getText().toString());
                returnIntent.putExtra(EXTRA_AMOUNT, Double.valueOf(binding.edittextAmount.getText().toString()));
                returnIntent.putExtra(EXTRA_PHONE_NUMBER, binding.edittextPhoneNumber.getText().toString());
                returnIntent.putExtra(EXTRA_LAST_FOUR_DIGITS, binding.edittextCreditCardNumber.getText().toString().substring(0, 4));
                returnIntent.putExtra(EXTRA_CVC, Integer.valueOf(binding.edittextCvc.getText().toString()));
                returnIntent.putExtra(EXTRA_RECEIVE_RECEIPT, binding.switchReceipt.isChecked());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
            else
            {
                dialog.show();
            }
        }
    };
}