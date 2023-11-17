package com.example.shopapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_PRESS_COUNT = 4;
    private static final double VAT_PERCENTAGE = 0.16;
    private static final int DISCOUNT_THRESHOLD = 1000;  //RETURN TO 10,000

    private int flourPressCount = 0;
    private int sugarPressCount = 0;
    private int milkPressCount = 0;
    private int breadPressCount = 0;

    private static final int FLOUR_PRICE = 400;
    private static final int SUGAR_PRICE = 100;
    private static final int MILK_PRICE = 70;
    private static final int BREAD_PRICE = 65;

    int flourActualPrice;
    int sugarActualPrice;
    int milkActualPrice;
    int breadActualPrice;

    private TextView flourPriceTextView;
    private TextView sugarPriceTextView;
    private TextView milkPriceTextView;
    private TextView breadPriceTextView;
    private TextView flourVatTextView;
    private TextView sugarVatTextView;
    private TextView milkVatTextView;
    private TextView breadVatTextView;
    private TextView grandTotalTextView;
    private TextView discountTextView;
    private TextView netPayTextView;
    private TextView milkactualPriceTextView;
    private  TextView sugaractualPriceTextView;
    private TextView flouractualPriceTextView;
    private TextView breadactualPriceTextView;

    private Button flourButton;
    private Button sugarButton;
    private Button milkButton;
    private Button breadButton;
    private Button grandTotalButton;
    private Button discountButton;
    private Button netPayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flourPriceTextView = findViewById(R.id.flourPrice);
        sugarPriceTextView = findViewById(R.id.sugarPrice);
        milkPriceTextView = findViewById(R.id.milkPrice);
        breadPriceTextView = findViewById(R.id.breadPrice);
        flourVatTextView = findViewById(R.id.flourVat);
        sugarVatTextView = findViewById(R.id.sugarVat);
        milkVatTextView = findViewById(R.id.milkVat);
        breadVatTextView = findViewById(R.id.breadVat);
        milkactualPriceTextView = findViewById(R.id.milkActual);
        breadactualPriceTextView =findViewById(R.id.breadActual);
        flouractualPriceTextView = findViewById(R.id.flourActual);
        sugaractualPriceTextView = findViewById(R.id.sugarActual);
        grandTotalTextView = findViewById(R.id.grandTotalValue);
        discountTextView = findViewById(R.id.discountValue);
        netPayTextView = findViewById(R.id.netPayValue);

        flourButton = findViewById(R.id.flour);
        sugarButton = findViewById(R.id.sugar);
        milkButton = findViewById(R.id.milk);
        breadButton = findViewById(R.id.bread);
        grandTotalButton = findViewById(R.id.grandTotal);
        discountButton = findViewById(R.id.discount);
        netPayButton = findViewById(R.id.netPay);

        flourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flourPressCount < MAX_PRESS_COUNT) {
                    flourPressCount++;
                    int total = updatePriceTextView(flourPriceTextView, FLOUR_PRICE, flourPressCount);
                    int vat = updateVatTextView(flourVatTextView, FLOUR_PRICE, flourPressCount);
                    updateActualPrice(flouractualPriceTextView, vat, total);
                } else {
                    Toast.makeText(MainActivity.this, "Maximum presses reached for FLOUR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sugarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sugarPressCount < MAX_PRESS_COUNT) {
                    sugarPressCount++;
                    int total = updatePriceTextView(sugarPriceTextView, SUGAR_PRICE, sugarPressCount);
                    int vat = updateVatTextView(sugarVatTextView, SUGAR_PRICE, sugarPressCount);
                    updateActualPrice(sugaractualPriceTextView, vat, total);
                } else {
                    Toast.makeText(MainActivity.this, "Maximum presses reached for SUGAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        milkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (milkPressCount < MAX_PRESS_COUNT) {
                    milkPressCount++;
                    int total = updatePriceTextView(milkPriceTextView, MILK_PRICE, milkPressCount);
                    int vat = updateVatTextView(milkVatTextView, MILK_PRICE, milkPressCount);
                    updateActualPrice(milkactualPriceTextView, vat, total);
                } else {
                    Toast.makeText(MainActivity.this, "Maximum presses reached for MILK", Toast.LENGTH_SHORT).show();
                }
            }
        });

        breadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (breadPressCount < MAX_PRESS_COUNT) {
                    breadPressCount++;
                    int total = updatePriceTextView(breadPriceTextView, BREAD_PRICE, breadPressCount);
                    int vat = updateVatTextView(breadVatTextView, BREAD_PRICE, breadPressCount);
                    updateActualPrice(breadactualPriceTextView, vat, total);
                } else {
                    Toast.makeText(MainActivity.this, "Maximum presses reached for BREAD", Toast.LENGTH_SHORT).show();
                }
            }
        });
        grandTotalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateGrandTotal();
            }
        });
        discountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDiscount();
            }
        });
        netPayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateNetPay();
            }
        });


        // Continue with the remaining code...
    }

    private int updateVatTextView(TextView textView, int price, int pressCount) {
        int total = price * pressCount;
        int vat = (int) (VAT_PERCENTAGE * total);
        textView.setText(String.valueOf(vat));
        return vat;
    }

    private int updatePriceTextView(TextView textView, int price, int pressCount) {
        int total = price * pressCount;
        textView.setText(String.valueOf(total));
        return total;
    }
    private void updateActualPrice(TextView textView,int vat, int total) {
        int actualPrice = total + vat;
        textView.setText(String.valueOf(actualPrice));
    }
    private void calculateGrandTotal() {
        if(flouractualPriceTextView.getText().toString().equals("")){
            flourActualPrice = 0;
        }
        else{
            flourActualPrice = Integer.parseInt(flouractualPriceTextView.getText().toString());
        }
        if(sugaractualPriceTextView.getText().toString().equals("")){
            sugarActualPrice = 0;
        }
        else{
            sugarActualPrice = Integer.parseInt(sugaractualPriceTextView.getText().toString());
        }
        if(milkactualPriceTextView.getText().toString().equals("")){
            milkActualPrice = 0;
        }
        else{
            milkActualPrice = Integer.parseInt(milkactualPriceTextView.getText().toString());
        }
        if(breadactualPriceTextView.getText().toString().equals("")){
            breadActualPrice = 0;
        }
        else{
            breadActualPrice = Integer.parseInt(breadactualPriceTextView.getText().toString());
        }
        int grandTotal = flourActualPrice + sugarActualPrice + milkActualPrice + breadActualPrice;
        grandTotalTextView.setText(String.valueOf(grandTotal));
    }
    private void checkDiscount(){
        int gTotal = Integer.parseInt(grandTotalTextView.getText().toString());
        System.out.println(gTotal);
        if(gTotal < DISCOUNT_THRESHOLD){
            Toast.makeText(this,"No discount has been awarded",Toast.LENGTH_LONG).show();
            discountTextView.setText("0");
        }
        else{
            float discount = (float) (gTotal * 0.15);
            discountTextView.setText(Float.toString(discount));
        }
    }
    private void calculateNetPay(){
        float discount = Float.parseFloat(discountTextView.getText().toString());
        float gTotal = Float.parseFloat(grandTotalTextView.getText().toString());
        if(discount == 0){
            netPayTextView.setText(Float.toString(gTotal));
        } else if (discount > 0) {
            float netPay = gTotal - discount;
            netPayTextView.setText(Float.toString(netPay));

        }
    }

}

