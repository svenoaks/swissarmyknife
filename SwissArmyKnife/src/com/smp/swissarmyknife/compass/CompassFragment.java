package com.smp.swissarmyknife.compass;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import android.app.Activity;
import android.graphics.Paint;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.smp.swissarmyknife.R;
import com.smp.swissarmyknife.global.BaseUtilityCardFragment;

//needs major cleanup

public class CompassFragment extends BaseUtilityCardFragment implements SensorEventListener
{
	private Float azimut;

	private SensorManager mSensorManager;
	private Sensor accel;
	private Sensor magnetic;

	private static final float ALPHA = 0.03F;
	private static final String PREFERENCES_NAME = "pref";
	private static final String PREFERENCES_THEME = "theme";

	private TextView angle;
	private float azimuth;
	private float currentDegree;
	private String direction = "";
	private ImageView image;
	private boolean isLight;
	private boolean isTablet;
	private MenuItem item;
	private float[] mGeomagnetic;
	private float[] mGravity;

	private SharedPreferences preferences;
	private TextSwitcher sideCenter;
	private String tempDir = "N";

	@Override
	public void onAttach(Activity activity)
	{
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		mSensorManager = (SensorManager)
				getActivity().getSystemService(Context.SENSOR_SERVICE);
		accel = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		magnetic = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View child = inflater.inflate(R.layout.fragment_compass, null);
		DisplayMetrics localDisplayMetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
		int i = localDisplayMetrics.widthPixels;
		final double d = Math.sqrt(Math.pow(localDisplayMetrics.widthPixels / localDisplayMetrics.xdpi, 2.0D) 
				+ Math.pow(localDisplayMetrics.heightPixels / localDisplayMetrics.ydpi, 2.0D));
		image = ((ImageView) child.findViewById(R.id.image));
		sideCenter = (TextSwitcher) child.findViewById(R.id.sideCenter);
		sideCenter.setFactory(new ViewSwitcher.ViewFactory()
	    {
	      public View makeView()
	      {
	        TextView localTextView = new TextView(getActivity());
	        localTextView.setGravity(1);
	        if (d > 7.0D)
	        {
	          localTextView.setTextSize(30.0F);
	          return localTextView;
	        }
	        localTextView.setTextSize(20.0F);
	        return localTextView;
	      }
	    });
		angle = ((TextView) child.findViewById(R.id.angle));
		

		InputStream localInputStream;
		try
		{
			localInputStream = getActivity().getAssets().open("light_circle.png");
			Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
			image.setImageBitmap(Bitmap.createScaledBitmap(localBitmap, i, i, false));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return attachLayout(child);
	}

	@Override
	public void onPause()
	{
		super.onPause();
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onResume()
	{
		super.onResume();
		mSensorManager.registerListener(this, accel,
				SensorManager.SENSOR_DELAY_GAME);
		mSensorManager.registerListener(this, magnetic,
				SensorManager.SENSOR_DELAY_GAME);
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{

	}

	private float[] lowPass(float[] paramArrayOfFloat1, float[] paramArrayOfFloat2)
	{
		if (paramArrayOfFloat2 == null)
			return paramArrayOfFloat1;
		for (int i = 0;; i++)
		{
			if (i >= paramArrayOfFloat1.length)
				return paramArrayOfFloat2;
			paramArrayOfFloat2[i] += 0.03F * (paramArrayOfFloat1[i] - paramArrayOfFloat2[i]);
		}
	}

	@Override
	public void onSensorChanged(SensorEvent paramSensorEvent)
	{
		if (paramSensorEvent.sensor.getType() == 1)
			this.mGravity = lowPass((float[]) paramSensorEvent.values.clone(), this.mGravity);
		if (paramSensorEvent.sensor.getType() == 2)
			this.mGeomagnetic = lowPass((float[]) paramSensorEvent.values.clone(), this.mGeomagnetic);
		if ((this.mGravity != null) && (this.mGeomagnetic != null))
		{
			float[] arrayOfFloat1 = new float[9];
			if (SensorManager.getRotationMatrix(arrayOfFloat1, new float[9], this.mGravity, this.mGeomagnetic))
			{
				float[] arrayOfFloat2 = new float[3];
				SensorManager.getOrientation(arrayOfFloat1, arrayOfFloat2);
				this.currentDegree = (360.0F * -arrayOfFloat2[0] / 6.28318F);
				if (this.currentDegree < 0.0F)
					this.azimuth = (-this.currentDegree);
				this.azimuth = (360.0F - this.currentDegree);
				if ((this.azimuth <= 337.0F) && (this.azimuth >= 23.0F))
					this.direction = "N";
				if ((this.azimuth >= 23.0F) && (this.azimuth <= 67.0F))
					this.direction = "NE";
				else if ((this.azimuth > 67.0F) && (this.azimuth < 113.0F))
					this.direction = "E";
				else if ((this.azimuth >= 113.0F) && (this.azimuth <= 157.0F))
					this.direction = "SE";
				else if ((this.azimuth > 157.0F) && (this.azimuth < 203.0F))
					this.direction = "S";
				else if ((this.azimuth >= 203.0F) && (this.azimuth <= 247.0F))
					this.direction = "SW";
				else if ((this.azimuth > 247.0F) && (this.azimuth < 293.0F))
					this.direction = "W";
				else if ((this.azimuth >= 293.0F) && (this.azimuth <= 337.0F))
					this.direction = "NW";
			}
		}

		if (!tempDir.equals(this.direction))
		{
			this.sideCenter.setText(this.direction);
			this.tempDir = this.direction;
		}
		this.angle.setText(Integer.toString((int) this.azimuth) + "°");
		this.image.setRotation(this.currentDegree);

	}

}
