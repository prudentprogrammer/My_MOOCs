package course.labs.activitylab;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class ActivityTwo extends Activity {

	private static final String RESTART_KEY = "restart";
	private static final String RESUME_KEY = "resume";
	private static final String START_KEY = "start";
	private static final String CREATE_KEY = "create";

	// String for LogCat documentation
	private final static String TAG = "Lab-ActivityTwo";

	// Lifecycle counters
    private int[] mCounter;
    private String COUNTERS_KEY = "counters";
    private static HashMap<String, Integer> mapping;

    static  {
        mapping = new HashMap<String, Integer>();
        mapping.put("onCreate",  0);
        mapping.put("onStart",   1);
        mapping.put("onResume",  2);
        mapping.put("onRestart", 3);
    }

    private TextView mTvCreate;
    private TextView mTvStart;
    private TextView mTvResume;
    private TextView mTvRestart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_two);

        mTvCreate =  (TextView) findViewById(R.id.create);
        mTvStart =   (TextView) findViewById(R.id.start);
        mTvResume =  (TextView) findViewById(R.id.resume);
        mTvRestart = (TextView) findViewById(R.id.restart);
        mCounter = new int[4];

		Button closeButton = (Button) findViewById(R.id.bClose); 
		closeButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			    finish();

			}
		});

        // Check for previously saved state
        if (savedInstanceState != null) {
            mCounter = savedInstanceState.getIntArray(COUNTERS_KEY);
        }
        Log.d(TAG, CREATE_KEY + " called");
        Integer index = ActivityTwo.mapping.get(new Object(){}.getClass().getEnclosingMethod().getName());
        mCounter[index] += 1;
        // Update the user interface via the displayCounts() method
        displayCounts();
	}

	// Lifecycle callback methods overrides
	@Override
	public void onStart() {
		super.onStart();

        Log.d(TAG, START_KEY + " called");

        // Update the appropriate count variable
        Integer index = ActivityTwo.mapping.get(new Object(){}.getClass().getEnclosingMethod().getName());
        mCounter[index] += 1;
        // Update the user interface
        displayCounts();
	}

	@Override
	public void onResume() {
		super.onResume();
        Log.d(TAG, RESUME_KEY + " called");

        // Update the appropriate count variable
        Integer index = ActivityTwo.mapping.get(new Object(){}.getClass().getEnclosingMethod().getName());
        mCounter[index] += 1;
        // Update the user interface
        displayCounts();
	}

	@Override
	public void onPause() {
		super.onPause();
        Log.d(TAG, "OnPause called");
	}

	@Override
	public void onStop() {
		super.onStop();
        Log.d(TAG, "OnStop called");
	}

	@Override
	public void onRestart() {
		super.onRestart();
        Log.d(TAG, RESTART_KEY + " called");

        // Update the appropriate count variable
        Integer index = ActivityTwo.mapping.get(new Object(){}.getClass().getEnclosingMethod().getName());
        mCounter[index] += 1;
        // Update the user interface
        displayCounts();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
        Log.d(TAG, "OnDestroy called");
	}

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putIntArray(COUNTERS_KEY, mCounter);
	}

    // Updates the displayed counters
    public void displayCounts() {
        mTvCreate.setText("onCreate() calls: " + mCounter[0]);
        mTvStart.setText("onStart() calls: " + mCounter[1]);
        mTvResume.setText("onResume() calls: " + mCounter[2]);
        mTvRestart.setText("onRestart() calls: " + mCounter[3]);
    }
}
