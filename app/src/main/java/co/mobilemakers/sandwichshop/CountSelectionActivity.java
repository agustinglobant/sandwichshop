package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CountSelectionActivity extends ActionBarActivity {
    public static final String COUNT_ORDERS = "orders";
    Button mButtonStartOrders;
    EditText mEditTextCountOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_selection);

        mButtonStartOrders = (Button)findViewById(R.id.button_start_orders);
        mEditTextCountOrders = (EditText)findViewById(R.id.edit_count_orders);

        mButtonStartOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = mEditTextCountOrders.getText().toString();
                if (!val.isEmpty() && (Integer.parseInt(val) <= 5)){
                    Intent intent = new Intent(CountSelectionActivity.this, OrderFormActivity.class);
                    intent.putExtra(COUNT_ORDERS, Integer.parseInt(mEditTextCountOrders.getText().toString()));
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(),"You need to enter a valid number", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_count_selection, menu);
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
