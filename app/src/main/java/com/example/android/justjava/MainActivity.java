package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app is an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private TextView quantityTextView;
    private CheckBox whippedCreamCheckBox;
    private CheckBox chocolateCheckBox;
    private EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameField = findViewById(R.id.name_field);
        whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        quantityTextView = findViewById(R.id.quantity_text_view);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("quan", quantity);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        quantity = savedInstanceState.getInt("quan");
        displayQuantity(quantity);
    }

    /**
     * This is the declaration of the quantity of cups of coffee
     */
    int quantity = 2;

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            // Display Toast if person tries to go above 100
            Toast.makeText(getApplicationContext(), R.string.toast_more_than_100,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            // Display Toast if person tries to go below 1 cup of coffee
            Toast.makeText(getApplicationContext(), R.string.toast_less_than_1,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order.
     *
     * @param hasWhippedCream if user wants Whipped Cream
     * @param hasChocolate if user wants Chocolate
     * @return total price
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {
        int basePrice = 5;
        if (hasWhippedCream) {
            basePrice += 1;
        }
        if (hasChocolate) {
            basePrice += 2;
        }
        return quantity * (basePrice);
    }

    /**
     * This method is called when the order button is clicked, and sends order summary to email.
     */
    public void submitOrder(View view) {
        String name = nameField.getText().toString();
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        boolean hasChocolate = chocolateCheckBox.isChecked();
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, String.format(getString(R.string.order_summary_email_subject), name));
        intent.putExtra(Intent.EXTRA_TEXT, String.format("%s", priceMessage));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method creates an order summary
     *
     * @param price of the order
     * @param addWhippedCream is whether person wants whipped cream
     * @param addChocolate is whether they want chocolate topping
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = String.format(getString(R.string.order_summary_name), name);

        priceMessage += String.format(getString(R.string.order_summary_whipped_cream), addWhippedCream);

        priceMessage += String.format(getString(R.string.order_summary_chocolate), addChocolate);

        priceMessage += String.format(getString(R.string.order_summary_quantity), quantity);

        priceMessage += String.format(getString(R.string.order_summary_price), NumberFormat.getCurrencyInstance().format(price));

        priceMessage += getString(R.string.thank_you);
        return priceMessage;
    }

    /**
     * This method displayQuantity the given quantity value on the screen.
     */
    private void displayQuantity(int quantity) {
        quantityTextView.setText(String.valueOf(quantity));
    }
}