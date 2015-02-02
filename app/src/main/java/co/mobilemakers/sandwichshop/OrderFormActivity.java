package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import static co.mobilemakers.sandwichshop.CountSelectionActivity.COUNT_ORDERS;

import java.util.ArrayList;


public class OrderFormActivity extends ActionBarActivity {
    public final static String LOG_TAG = "OrderFormActivity";
    final static String FORM_STATE = "FORM_STATE";

    Button mButtonPlaceOrder;
    RadioButton mRadioButtonWheat, mRadioButtonWhite, mRadioButtonRye;
    CheckBox mCheckBoxMozzarella, mCheckBoxCheddar, mCheckBoxProvolone, mCheckBoxJalapenos,
           mCheckBoxOlives, mCheckBoxPickles, mCheckBoxMustard, mCheckBoxVinegar, mCheckBoxBarbecue;
    RadioGroup mRadioGroup;
    ArrayList<String> mOptions = new ArrayList<>();
    ArrayList<FormState> mArr;
    int mOrderCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findWidgets();
        setupWidgets();
        setupAndShowOrderCount();

        if (savedInstanceState != null){
            mArr = savedInstanceState.getParcelableArrayList(FORM_STATE);
        } else {
            mArr = new ArrayList<>();
        }

    }

    private void setupAndShowOrderCount() {
        mOrderCount = getIntent().getIntExtra(COUNT_ORDERS,0);
        Toast.makeText(getApplicationContext(), String.valueOf(mOrderCount), Toast.LENGTH_SHORT).show();
    }

    public static class FormState implements Parcelable {
        String bread;
        ArrayList<String> options = new ArrayList<>();

        public static final Creator<FormState> CREATOR = new Creator<FormState>() {
            @Override
            public FormState createFromParcel(Parcel source) {
                return null;
            }

            @Override
            public FormState[] newArray(int size) {
                return new FormState[size];
            }
        };

        public void setBread(String bread) {
            this.bread = bread;
        }

        public void setOptions(String option) {
            this.options.add(option);
        }


        public String getBread() {
            return bread;
        }

        public ArrayList<String> getOptions() {
            return options;
        }

        private FormState() {
        }

        private FormState(Parcel source) {
            this.bread = source.readString();
            source.readStringList(this.options);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(bread);
            dest.writeString(options.toString());
        }
    }

    private void setupWidgets() {
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
        mRadioButtonWhite.setChecked(true);

        mButtonPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                FormState formState = new FormState();
                formState.setBread(((RadioButton) findViewById(mRadioGroup.getCheckedRadioButtonId())).getText().toString());
                formState.setOptions(mOptions.toString());
                mArr.add(formState);

                if (mOrderCount == 1){
                    intent = new Intent(OrderFormActivity.this, OrderConfirmationActivity.class);
                } else {
                    intent = new Intent(OrderFormActivity.this, OrderFormActivity.class);
                    intent.putExtra(COUNT_ORDERS,mOrderCount-1);
                }
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(FORM_STATE, mArr);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
