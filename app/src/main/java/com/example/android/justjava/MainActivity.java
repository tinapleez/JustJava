package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displayQuantity an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private TextView quantityTextView;
    private TextView orderSummaryTextView;
    private CheckBox whippedCreamCheckBox;
    private CheckBox chocolateCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
    }

    /**
     * This is the declaration of the global variable to make the quantity picker work
     */
    int quantity = 0;


        /**
         * This method is called when the plus button is clicked.
         */
    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */
    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice();
        displayMessage(createOrderSummary(price, hasWhippedCream, hasChocolate));
    }

    /**
     * This method creates an order summary
     *
     * @param price of the order
     * @param addWhippedCream is whether person wants whipped cream
     * @return text summary
     */
    private String createOrderSummary(int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "Name: " + "Kaptain Kunal";
        priceMessage += "\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + (price);
        priceMessage += "\nThank you!";
        return priceMessage;
    }

    /**
     * This method displayQuantity the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displayQuantity the given text on the screen.
     */
    private void displayMessage(String message) {
        orderSummaryTextView.setText(message);
    }
}