package com.smp.swissarmyknife.flashlight;

import com.smp.swissarmyknife.R;
import com.smp.swissarmyknife.global.BaseUtilityCardFragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class FlashlightFragment extends BaseUtilityCardFragment
{

	@Override
	public void onAttach(Activity activity)
	{
		// TODO Auto-generated method stub
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View child = inflater.inflate(R.layout.fragment_flashlight, null);
		return attachLayout(child);
	}

	@Override
	public void onPause()
	{
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}
	
}
