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
    private static final String TIP_AMOUNT20 = "";
    private static final String CUSTOM_TIP_AMOUNT ="";

    private static final String TOTAL_AMOUNT10 = "";
    private static final String TOTAL_AMOUNT20 = "";
    private static final String CUSTOM_TOTAL_AMOUNT = "";

    private double charge_amount;

    private double customTipRate;

    private double tipAmount10;
    private double tipAmount20;
    private double customTipAmount;

    private double totalAmount10;
    private double totalAmount20;
    private double customTotalAmount;

    EditText ChargeAmountET;
    EditText customTipRateET;
    EditText seekBarValueIndicatorET;

    EditText tipAmount10ET;
    EditText tipAmount20ET;
    EditText customTipAmountET;

    EditText totalAmount10ET;
    EditText totalAmount20ET;
    EditText customTotalAmountET;

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
            customTipAmount = savedInstanceState.getDouble(CUSTOM_TIP_AMOUNT);
            customTotalAmount = savedInstanceState.getDouble(CUSTOM_TOTAL_AMOUNT);
        }

        customTipRateET = (EditText)findViewById(R.id.custom_tip_rateEditText);
        seekBarValueIndicatorET =(EditText)findViewById(R.id.seekBarIndicatorEditText);

        customTipAmountET = (EditText)findViewById(R.id.custom_tip_amountEditText);
        customTotalAmountET =(EditText)findViewById(R.id.custom_totalAmountEditText);

        ChargeAmountET = (EditText) findViewById(R.id.charge_amountEditText);
        tipAmount10ET = (EditText) findViewById(R.id.tip_amount10EditText);
        tipAmount20ET = (EditText) findViewById(R.id.tip_amount20EditText);

        totalAmount10ET = (EditText) findViewById(R.id.total_amount10EditText);
        totalAmount20ET = (EditText) findViewById(R.id.total_amount20EditText);


        tipseekbar = (SeekBar) findViewById(R.id.tipseekBar);

        tipseekbar.setOnSeekBarChangeListener(tipseekbarListener);
        // add the charge amount listener
        ChargeAmountET.addTextChangedListener(ChargeAmountListener);

    }
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        outState.putDouble(CHARGE_AMOUNT, charge_amount);

        outState.putDouble(TIP_AMOUNT10, tipAmount10);
        outState.putDouble(TIP_AMOUNT20, tipAmount20);
        outState.putDouble(TOTAL_AMOUNT10, totalAmount10);
        outState.putDouble(TOTAL_AMOUNT20, totalAmount20);
    }
    private SeekBar.OnSeekBarChangeListener tipseekbarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            double seekBarValue = (double)(tipseekbar.getProgress());
            // normalize bar from 0 to 40;
            seekBarValue=seekBarValue*30/100;
            customTipRate = seekBarValue *0.01;
            try {
                customTipRateET.setText(String.format("%.02f", seekBarValue) + "%");
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
    private void updateTipAndFinalBill()
    {
        double tmpChargeAmount = charge_amount;

        double tmptipAmount10 = tmpChargeAmount*.15;
        double tmptipAmount20 = tmpChargeAmount*.20;

        double tmptotalAmount10 = tmpChargeAmount + tmptipAmount10;
        double tmptotalAmount20 = tmpChargeAmount + tmptipAmount20;

        customTipAmount = tmpChargeAmount*customTipRate;
        customTotalAmount = customTipAmount + tmpChargeAmount;

        tipAmount10ET.setText("$" + String.format("%.02f", tmptipAmount10));
        tipAmount20ET.setText("$" + String.format("%.02f", tmptipAmount20));

        totalAmount10ET.setText("$" + String.format("%.02f", tmptotalAmount10));
        totalAmount20ET.setText("$" + String.format("%.02f", tmptotalAmount20));

        customTipAmountET.setText("$" + String.format("%.02f", customTipAmount));
        customTotalAmountET.setText("$" + String.format("%.02f", customTotalAmount));
    }
}
