package com.example.ps5;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.text.Editable;
public class MainActivity extends Activity implements OnItemSelectedListener{
	
	EditText investment;
	EditText Rate;
	TextView fv;
	Spinner yrs;
	Object totalyrs;
	Button button;
	Integer[] nums = new Integer [75];
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		investment = (EditText) findViewById(R.id.editText1);
		Rate = (EditText) findViewById(R.id.editText2);
		fv = (TextView) findViewById(R.id.textView4);
		
		investment.addTextChangedListener(
				new TextWatcher(){
					@Override
					public void afterTextChanged(Editable editable) {fv.setText(" "); }
					@Override
					public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3){}
					@Override
					public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
					});
					Rate.addTextChangedListener(
							new TextWatcher(){
							@Override
							public void afterTextChanged(Editable editable) {
								fv.setText(" ");
							}
							@Override
							 public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
				            @Override
				            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
				        });
					 
						for (int i = 0; i < 75; i++) {
				            nums[i] = i + 1;
							}
						ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, nums);
				        yrs = (Spinner) findViewById(R.id.spinner1);
				        yrs.setAdapter(adapter);
				        yrs.setOnItemSelectedListener(this);
				        Button button1 = (Button)findViewById(R.id.button1);
				        button1.setOnClickListener(new View.OnClickListener(){			        
	@Override
	public void onClick(View v) {
		   double init;
	        double rate;
	        Editable initial = investment.getText();
	        Editable rt = Rate.getText();

	        if (initial.length() != 0 && rt.length() != 0) {

	            init = Double.parseDouble(initial.toString());
	            rate = Double.parseDouble(rt.toString());

	            // Main formula
	            double result = (double) (init * Math.pow((1 + rate / 100), (Integer) totalyrs));
	            result = Math.round(result);

	            // Final value
	            fv.setText("Future Value is $ " + ((Double) result).toString());

	            // For debug
	            System.out.println("result: " + result);
	        }
	    }
	});}

	 public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	        int position = yrs.getSelectedItemPosition();
	        totalyrs = yrs.getItemAtPosition(position);
	        fv.setText(" "); 
	    }

	    public void onNothingSelected(AdapterView<?> arg0) {


	    }

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        // Inflate the menu; this adds items to the action bar if it is present.
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }

	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle action bar item clicks here. The action bar will
	        // automatically handle clicks on the Home/Up button, so long
	        // as you specify a parent activity in AndroidManifest.xml.
	        int id = item.getItemId();
	        if (id == R.id.action_settings) {
	            return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	}