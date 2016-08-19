package com.karlludwigsen.tipcalcapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.widget.EditText;
import android.text.Editable;
import android.widget.SeekBar;

public class TipCalculator extends AppCompatActivity {

    private static final String CHARGE_AMOUNT = "";
    private static final String CUSTOM_TIP_RATE = "";

    private static final String TIP_AMOUNT10 = "";

    private static final String CUSTOM_TIP_AMOUNT ="";

    private static final String TOTAL_AMOUNT10 = "";

    private static final String CUSTOM_TOTAL_AMOUNT = "";

    private double charge_amount;

    private double customTipRate;
    private double customTipAmountPercentage;

    private double tipAmount10;

    private double customTipRateAmount;
    private double customTipAmount;

    private double totalAmount10;

    private double customTipRateTotalAmount;

     private static final String TOTAL_AMOUNT20 = "";
    private static final String TIP_AMOUNT20 = "";
     private double tipAmount20;
     private double totalAmount20;
    EditText seekBarValueIndicatorET;

    EditText ChargeAmountET;



    EditText tipAmount10ET;
    EditText tipAmount20ET;
    EditText totalAmount10ET;
    EditText totalAmount20ET;

    EditText customTipRateET;
    EditText customTipRateAmountET;
    EditText customTipRateTotalAmountET;

    EditText customTipAmountET;
    EditText customTipAmountPercentageET;
    EditText customTipTotalAmountET;

    SeekBar tipseekbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        if (savedInstanceState == null) {
            charge_amount = 0.0;
        } else {
            charge_amount = savedInstanceState.getDouble(CHARGE_AMOUNT);
            tipAmount10 = savedInstanceState.getDouble(TIP_AMOUNT10);
            tipAmount20 = savedInstanceState.getDouble(TIP_AMOUNT20);
            totalAmount10 = savedInstanceState.getDouble(TOTAL_AMOUNT10);
            totalAmount20 = savedInstanceState.getDouble(TOTAL_AMOUNT20);

            customTipRate =savedInstanceState.getDouble(CUSTOM_TIP_RATE);
            customTipRateAmount = savedInstanceState.getDouble(CUSTOM_TIP_AMOUNT);
            customTipRateTotalAmount = savedInstanceState.getDouble(CUSTOM_TOTAL_AMOUNT);
        }

        customTipRateET = (EditText)findViewById(R.id.custom_tip_rateEditText);

        customTipRateAmountET = (EditText)findViewById(R.id.custom_tip_amountEditText);
        customTipRateTotalAmountET =(EditText)findViewById(R.id.custom_tiprate_totalAmountEditText);

        ChargeAmountET = (EditText) findViewById(R.id.charge_amountEditText);
        tipAmount10ET = (EditText) findViewById(R.id.tip_amount10EditText);
        tipAmount20ET = (EditText) findViewById(R.id.tip_amount20EditText);

        totalAmount10ET = (EditText) findViewById(R.id.total_amount10EditText);
        totalAmount20ET = (EditText) findViewById(R.id.total_amount20EditText);

        customTipAmountET = (EditText) findViewById(R.id.custom_tip_amount_editText);
        customTipAmountPercentageET = (EditText) findViewById(R.id.custom_tip_amount_percentageEditText);
        customTipTotalAmountET = (EditText) findViewById(R.id.custom_tip_amount_totalEditText);

        tipseekbar = (SeekBar) findViewById(R.id.tipseekBar);

        tipseekbar.setOnSeekBarChangeListener(tipseekbarListener);
        // add the charge amount listener
        ChargeAmountET.addTextChangedListener(ChargeAmountListener);
        customTipAmountET.addTextChangedListener(TipAmountListener);
        customTipRateET.addTextChangedListener(TipRateListener);

    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putDouble(CHARGE_AMOUNT, charge_amount);

        outState.putDouble(TIP_AMOUNT10, tipAmount10);
        outState.putDouble(CUSTOM_TIP_RATE, customTipRate);
        outState.putDouble(TIP_AMOUNT20, tipAmount20);
        outState.putDouble(TOTAL_AMOUNT10, totalAmount10);
        outState.putDouble(TOTAL_AMOUNT20, totalAmount20);
    }

    private SeekBar.OnSeekBarChangeListener tipseekbarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            double seekBarValue = (double)(tipseekbar.getProgress());
            // normalize bar from 5 to 35;
            seekBarValue=Math.abs(seekBarValue*30/100+5);

            customTipRate =seekBarValue *0.01;
            try {
                customTipRateET.setText(String.format("%.02f", seekBarValue));
                seekBarValueIndicatorET.setText(String.format("%.02f", seekBarValue)+ "%");
            }
            catch(Exception e)

            {

            }

            updateTipAndFinalBill();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private TextWatcher ChargeAmountListener = new TextWatcher()
    {
        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3)
        {
            // TODO Auto-generated method stub
        }
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
        {
            try
            {
                charge_amount = Double.parseDouble(arg0.toString());
            }

            catch(NumberFormatException e){
                charge_amount = 0.0;
            }

            updateTipAndFinalBill();
        }
    };
    private TextWatcher TipAmountListener = new TextWatcher()
    {
        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3)
        {
            // TODO Auto-generated method stub
        }
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
        {
            try
            {
                customTipAmount = Double.parseDouble(arg0.toString());
            }

            catch(NumberFormatException e){
                customTipAmount = 0.0;
            }

            updateTipAndFinalBill();
        }
    };


    private TextWatcher TipRateListener = new TextWatcher()
    {
        @Override
        public void afterTextChanged(Editable arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,int arg3)
        {
            // TODO Auto-generated method stub
        }
        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3)
        {
            if( !(arg0.toString().indexOf("%") >= 0))
            {
                try {
                    customTipRate = Double.parseDouble(arg0.toString());
                    //customTipRateET.setText(String.format("%.02f", customTipRate )+ "%");
                    customTipRate /= 100;
                } catch (NumberFormatException e) {
                    customTipRate = 0.0;
                }

                updateTipAndFinalBill();
            }
        }
    };

    private void updateTipAndFinalBill()
    {
        double tmpChargeAmount = charge_amount;

        double tmptipAmount10 = tmpChargeAmount*.10;
        double tmptipAmount20 = tmpChargeAmount*.20;

        double tmptotalAmount10 = tmpChargeAmount + tmptipAmount10;
        double tmptotalAmount20 = tmpChargeAmount + tmptipAmount20;

        customTipRateAmount = tmpChargeAmount*customTipRate;
        customTipRateTotalAmount = customTipRateAmount + tmpChargeAmount;

        customTipAmountPercentage = (customTipAmount/charge_amount)*100;

        tipAmount10ET.setText("$" + String.format("%.02f", tmptipAmount10));
        tipAmount20ET.setText("$" + String.format("%.02f", tmptipAmount20));

        totalAmount10ET.setText("$" + String.format("%.02f", tmptotalAmount10));
        totalAmount20ET.setText("$" + String.format("%.02f", tmptotalAmount20));

        customTipRateAmountET.setText("$" + String.format("%.02f", customTipRateAmount));
        customTipRateTotalAmountET.setText("$" + String.format("%.02f", customTipRateTotalAmount));

        customTipAmountPercentageET.setText(String.format("%.02f", customTipAmountPercentage) + "%");
        customTipTotalAmountET.setText("$" + String.format("%.02f", customTipAmount+charge_amount));
    }
}
