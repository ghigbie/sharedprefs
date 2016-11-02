/*
 * Android provides many ways of storing the data of an application. 
 * One of these ways is by using the Shared Preferences class.
 * Shared Preferences allow you to save and retrieve data in the form of key,value pair.
 * This a simple example of Shared Preferences which allows us to save changes
 * to the background color of the app.
 */

package org.turntotech.sharedpreference;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import static org.turntotech.sharedpreference.R.id.radioColor;

public class MainActivity extends Activity {

	float x1, x2;
	float y1, y2;

	private RadioGroup radioColorGroup;
	private RadioButton radioColorButton;
	public static final String prefs_name = "MyPreferencesFile";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        Log.i("TurnToTech", "Project Name - SharedPreference");

		radioColorGroup = (RadioGroup) findViewById(radioColor);
		radioColorGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			// Called when the checked state of a compound button has changed.
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				int selectedId = radioColorGroup.getCheckedRadioButtonId();
			    radioColorButton = (RadioButton) findViewById(selectedId);    

			    /*
			     * getSharedPreferences() - Retrieve and hold the contents of the 
			     * preferences file 'name', returning a SharedPreferences through 
			     * which you can retrieve and modify its values. 
			     */
				SharedPreferences settings = getSharedPreferences(prefs_name,0);
				
				// SharedPreferences.Editor - Interface used for modifying values in a SharedPreferences object. 
				SharedPreferences.Editor editor = settings.edit();
				
				// Set a String value in the preferences editor, to be written back once commit() or apply() are called.
				editor.putString("col",radioColorButton.getText().toString());
				
				editor.commit();
				setBackground();
				
			}
		});
		setBackground();


		radioColorGroup.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {


				return false;
			}
		});
	}
	
	/*
	 * Change the background.
	 */
	private void setBackground(){
		    
			SharedPreferences settings = getSharedPreferences(prefs_name, 0);
			
			// Retrieve a String value from the preferences. default "Green"
		    String color_pref = settings.getString("col", "Green");
		    
		    // After retrieving change background color accordingly. 
		    if(color_pref.equals("Green")){
		    	radioColorGroup.setBackgroundColor(Color.parseColor("#99FFCC"));
		    	radioColorGroup.check(R.id.radioGreen);
		    }
		    if(color_pref.equals("Red")){
		    	radioColorGroup.setBackgroundColor(Color.parseColor("#FF6666"));
		    	radioColorGroup.check(R.id.radioRed);
		    }
		    if(color_pref.equals("Blue")){
		    	radioColorGroup.setBackgroundColor(Color.parseColor("#66CCFF"));
		    	radioColorGroup.check(R.id.radioBlue);
		    }


	}


	public boolean onSwipeEvent(MotionEvent swipeEvent){

		switch(swipeEvent.getAction()){

			case MotionEvent.ACTION_DOWN:
				x1 = swipeEvent.getX();
				y1 = swipeEvent.getY();
				break;

			case MotionEvent.ACTION_UP:
				x2 = swipeEvent.getX();
				y2 = swipeEvent.getY();
				break:
		}

		if (x1 < (x2 - 10)){
			Toast toast1 = Toast.makeText(this, "left to right swipe", Toast.LENGTH_SHORT);
			toast1.show();
		}

		if (x1 + 10 < x2){
			Toast toast2 = Toast.makeText(this, "right to left swipe", Toast.LENGTH_SHORT);
			toast2.show();
		}




	}


	private void swipeColors(){

		radioColorGroup.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this){

		}

		//#39c639  dark green
		//#00ff00 light green

		//dark red  #ffb3b3
		//light red #b30000

		//dark blue  #0000ff
		//light blue  #00bfff




	}





	
}
