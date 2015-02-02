package co.mobilemakers.sandwichshop;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class OrderConfirmationActivity extends ActionBarActivity {

    TextView mTextViewBread;
    TextView mTextViewOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.setRequestedOrientation(OrderConfirmationActivity.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_order_confirmation);
        mTextViewBread = (TextView) findViewById(R.id.text_bread_selected);
        mTextViewOptions = (TextView) findViewById(R.id.text_options_selected);
        mTextViewBread.setText(getIntent().getStringExtra("Bread"));
        mTextViewOptions.setText(getIntent().getStringArrayListExtra("Options").toString());
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
