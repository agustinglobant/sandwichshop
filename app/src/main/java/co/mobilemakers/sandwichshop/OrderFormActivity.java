package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;


public class OrderFormActivity extends ActionBarActivity {

    Button mButtonPlaceOrder;
    RadioButton mRadioButtonWheat, mRadioButtonWhite, mRadioButtonRye;
    CheckBox mCheckBoxMozzarella, mCheckBoxCheddar, mCheckBoxProvolone, mCheckBoxJalapenos,
           mCheckBoxOlives, mCheckBoxPickles, mCheckBoxMustard, mCheckBoxVinegar, mCheckBoxBarbecue;
    RadioGroup mRadioGroup;
    ArrayList<String> mOptions = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWidgets();
        mRadioButtonWhite.setChecked(true);
        setupCheckBoxs();

        mButtonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderFormActivity.this, OrderConfirmationActivity.class);
                intent.putExtra("Bread", ((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText());
                intent.putExtra("Options", mOptions);
                startActivity(intent);
            }
        });
    }

    private void setupCheckBoxs() {
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mOptions.add(buttonView.getText().toString());
                }else{
                    mOptions.remove(mOptions.indexOf(buttonView.getText().toString()));
                }
            }
        };
        mCheckBoxBarbecue.setOnCheckedChangeListener(listener);
        mCheckBoxCheddar.setOnCheckedChangeListener(listener);
        mCheckBoxJalapenos.setOnCheckedChangeListener(listener);
        mCheckBoxMozzarella.setOnCheckedChangeListener(listener);
        mCheckBoxMustard.setOnCheckedChangeListener(listener);
        mCheckBoxOlives.setOnCheckedChangeListener(listener);
        mCheckBoxPickles.setOnCheckedChangeListener(listener);
        mCheckBoxProvolone.setOnCheckedChangeListener(listener);
        mCheckBoxVinegar.setOnCheckedChangeListener(listener);
    }

    private void findWidgets() {
        mButtonPlaceOrder = (Button) findViewById(R.id.button_place_order);
        mCheckBoxMozzarella = (CheckBox) findViewById(R.id.cb_mozzarella);
        mCheckBoxCheddar = (CheckBox) findViewById(R.id.cb_cheddar);
        mCheckBoxProvolone = (CheckBox) findViewById(R.id.cb_provolone);
        mCheckBoxJalapenos = (CheckBox) findViewById(R.id.cb_jalapenos);
        mCheckBoxOlives = (CheckBox) findViewById(R.id.cb_olives);
        mCheckBoxPickles = (CheckBox) findViewById(R.id.cb_pickles);
        mCheckBoxMustard = (CheckBox) findViewById(R.id.cb_mustard);
        mCheckBoxVinegar = (CheckBox) findViewById(R.id.cb_vinegar);
        mCheckBoxBarbecue = (CheckBox) findViewById(R.id.cb_barbecue);
        mRadioButtonWheat = (RadioButton) findViewById(R.id.rb_wheat);
        mRadioButtonWhite = (RadioButton) findViewById(R.id.rb_white);
        mRadioButtonRye = (RadioButton) findViewById(R.id.rb_rye);
        mRadioGroup = (RadioGroup) findViewById(R.id.radio_group_bread);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
