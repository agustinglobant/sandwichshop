package co.mobilemakers.sandwichshop;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

import static co.mobilemakers.sandwichshop.OrderFormActivity.FORM_STATE;


public class OrderConfirmationActivity extends ActionBarActivity {


    TextView mTextViewBread;
    TextView mTextViewOptions;
    ArrayList<String> mAllBreads = new ArrayList<>();
    ArrayList<String> mAllOptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        findWidgets();
        if (savedInstanceState != null){
            ArrayList<OrderFormActivity.FormState> forms = getIntent().getExtras().getParcelableArrayList(FORM_STATE);
            for (OrderFormActivity.FormState form: forms){
                mAllBreads.add(form.getBread());
            }
        }

        mTextViewBread.setText(mAllBreads.toString());
        mTextViewOptions.setText(mAllOptions.toString());
    }

    private void findWidgets() {
        mTextViewBread   = (TextView) findViewById(R.id.text_bread_selected);
        mTextViewOptions = (TextView) findViewById(R.id.text_options_selected);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order_confirmation, menu);
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
