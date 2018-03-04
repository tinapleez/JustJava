/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displayQuantitys an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private TextView quantityTextView;
    private TextView priceTextView;

    private void assignViews() {
        quantityTextView = findViewById(R.id.quantity_text_view);
        priceTextView = findViewById(R.id.price_text_view);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
    }

    /** This is the declaration of the global variable to make the quantity picker work */
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
        int price = quantity * 5;
        return price;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        String priceMessage = "Total: $" + (price);
        priceMessage = priceMessage + "\nThank you!";
        displayQuantityMessage(priceMessage);
        calculatePrice();
    }

    /**
     * This method displayQuantitys the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        quantityTextView.setText("" + quantity);
    }

    /**
     * This method displayQuantitys the given price on the screen.
     */
    private void displayQuantityPrice(int number) {
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displayQuantitys the given text on the screen.
     */
    private void displayQuantityMessage(String message) {
        priceTextView.setText(message);
    }
}