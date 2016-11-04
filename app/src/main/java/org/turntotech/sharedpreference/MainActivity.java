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
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import static org.turntotech.sharedpreference.R.id.radioColor;

public class MainActivity extends Activity {

	private float x1, x2;
	private float y1, y2;

	private float minDistance = 200;

	private boolean isDark;
	private boolean isNormal;


	private RadioGroup radioColorGroup;
	private RadioButton radioColorButton;
	public static final String prefs_name = "MyPreferencesFile";

	private GestureDetectorCompat gestureDetectorCompat;



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
				SharedPreferences settings = getSharedPreferences(prefs_name, 0);

				// SharedPreferences.Editor - Interface used for modifying values in a SharedPreferences object. 
				SharedPreferences.Editor editor = settings.edit();

				// Set a String value in the preferences editor, to be written back once commit() or apply() are called.
				editor.putString("col", radioColorButton.getText().toString());

				editor.commit();
				setBackground();

			}
		});

		setBackground();

		gestureDetectorCompat = new GestureDetectorCompat(this, new GestureDetector.OnGestureListener() {
			@Override
			public boolean onDown(MotionEvent motionEvent) {

				return false;
			}

			@Override
			public void onShowPress(MotionEvent motionEvent) {

			}

			@Override
			public boolean onSingleTapUp(MotionEvent motionEvent) {
				return false;
			}

			@Override
			public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
				return false;
			}

			@Override
			public void onLongPress(MotionEvent motionEvent) {

			}

			@Override
			public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

				x1 = motionEvent.getX();
				y1 = motionEvent.getY();

				x2 = motionEvent1.getX();
				y2 = motionEvent1.getY();

				//this represents a swipe to the right
				if((x1 + 100) < x2 ){
					isDark = false;
					Toast.makeText(getApplicationContext(), "left to right swipe", Toast.LENGTH_SHORT).show();
				}

				if((x2 - x1) > 100){
					isDark = true;
					Toast.makeText(getApplicationContext(), "right to left swipe", Toast.LENGTH_SHORT).show();
				}
//
//				float flingMin = 100;
//				float velocityMin = 100;
//
//				//user will move forward through messages on fling up or left
//				boolean forward = false;
//                //user will move backward through messages on fling down or right
//				boolean backward = false;
//
//
//				//calculate the change in X position within the fling gesture
//				float horizontalDiff = event2.getX() - event1.getX();
//				//calculate the change in Y position within the fling gesture
//				float verticalDiff = event2.getY() - event1.getY();
//

				setBackground();
				return false;
			}
		});

	}



		//radioColorGroup.

//		radioColorGroup.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View view, MotionEvent swipeEvent) {
//
//
//				switch (swipeEvent.getAction()) {
//
//					case MotionEvent.ACTION_DOWN:
//						x1 = swipeEvent.getX();
//						y1 = swipeEvent.getY();
//
//						Toast toast2 = Toast.makeText(getApplicationContext(), "x1 " + Float.toString(x1), Toast.LENGTH_SHORT);
//						toast2.show();
//						break;
//
//					case MotionEvent.ACTION_UP:
//						x2 = swipeEvent.getX();
//						y2 = swipeEvent.getY();
//
//						Toast toast1 = Toast.makeText(getApplicationContext(), "x2 " + Float.toString(x2), Toast.LENGTH_SHORT);
//						toast1.show();
//						break;
//				}
//
////				Toast toast1 = Toast.makeText(getApplicationContext(), "x2 " + Float.toString(x2), Toast.LENGTH_SHORT);
////				toast1.show();
////
////				Toast toast2 = Toast.makeText(getApplicationContext(), "x1 " + Float.toString(x1), Toast.LENGTH_SHORT);
////				toast2.show();
//
//				float deltaX = x2 - x1;
//
//				if(deltaX < minDistance){
//				//if (Math.abs(x2 - x1) > minDistance) {
//			//		Toast toast3 = Toast.makeText(getApplicationContext(), "left to right swipe", Toast.LENGTH_SHORT);
//			//		toast3.show();
//					isDark = true;
//				}
//
//				else{
//			//		Toast toast4 = Toast.makeText(getApplicationContext(), "right to left swipe", Toast.LENGTH_SHORT);
//			//		toast4.show();
//					isDark = false;
//				}
//
////				if (y1 > y2) {
////					Toast toast3 = Toast.makeText(getApplicationContext(), "up to down swipe", Toast.LENGTH_SHORT);
////					toast3.show();
////				}else if (y2 > y1){
////					Toast toast4 = Toast.makeText(getApplicationContext(), "down to up swipe", Toast.LENGTH_SHORT);
////					toast4.show();
////				}
//
//				changeBackGround();
//
//				return false;
//			}
//		});





//		radioColorGroup.setOnTouchListener(new View.OnTouchListener()nTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				int x = (int)event.getRawX();
//				int y = (int)event.getRawY();
//				if(event.getAction() == MotionEvent.ACTION_UP){
//					if(isViewInBounds(viewA, x, y))
//						viewA.dispatchTouchEvent(event);
//					else if(isViewInBounds(viewB, x, y)){
//						Log.d(TAG, "onTouch ViewB");
//						//Here goes code to execute on onTouch ViewB
//					}
//				}
//				// Further touch is not handled
//				return false;
//			}
//		});
//
//	}

//#39c639  dark green
//		//#00ff00 light green
//
//		//dark red  #ffb3b3
//		//light red #b30000
//
//		//dark blue  #0000ff
//		//light blue  #00bfff


//changes the background
	private void setBackground(){
		    
			SharedPreferences settings = getSharedPreferences(prefs_name, 0);
			
			// Retrieve a String value from the preferences. default "Green"
		    String color_pref = settings.getString("col", "Green");
		    
		    // After retrieving change background color accordingly. 
		    if(color_pref.equals("Green")){
		    	radioColorGroup.setBackgroundColor(Color.parseColor("#99FFCC")); //green
		    	radioColorGroup.check(R.id.radioGreen);
				if(isDark == true){
					radioColorGroup.setBackgroundColor(Color.parseColor("#39c639")); //dark green
				}
				else{
					radioColorGroup.setBackgroundColor(Color.parseColor("#00ff00")); //light green
				}
		    }
		    if(color_pref.equals("Red")){
		    	radioColorGroup.setBackgroundColor(Color.parseColor("#FF6666")); //red
		    	radioColorGroup.check(R.id.radioRed);
				if(isDark == true){
					radioColorGroup.setBackgroundColor(Color.parseColor("#ffb3b3")); //dark red
				}
				else{
					radioColorGroup.setBackgroundColor(Color.parseColor("#b30000")); //light red
				}
		    }
		    if(color_pref.equals("Blue")){
		    	radioColorGroup.setBackgroundColor(Color.parseColor("#66CCFF")); //blue
		    	radioColorGroup.check(R.id.radioBlue);
				if(isDark == true){
					radioColorGroup.setBackgroundColor(Color.parseColor("#0000ff")); //dark blue
				}
				else{
					radioColorGroup.setBackgroundColor(Color.parseColor("#00bfff")); //light blue
				}

		    }


	}


}





//
//	@Override
//	public boolean onTouchEvent(MotionEvent swipeEvent){
//
//		switch(swipeEvent.getAction()){
//
//			case MotionEvent.ACTION_DOWN:
//				x1 = swipeEvent.getX();
//				y1 = swipeEvent.getY();
//				break;
//
//			case MotionEvent.ACTION_UP:
//				x2 = swipeEvent.getX();
//				y2 = swipeEvent.getY();
//				break;
//		}
//
//		if (x1 < (x2 - 10)){
//			Toast toast1 = Toast.makeText(this, "left to right swipe", Toast.LENGTH_SHORT);
//			toast1.show();
//		}
//
//		if (x1 + 10 < x2){
//			Toast toast2 = Toast.makeText(this, "right to left swipe", Toast.LENGTH_SHORT);
//			toast2.show();
//		}
//
//
//
//
//	}


