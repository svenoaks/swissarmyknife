package com.smp.swissarmyknife.global;

import com.astuetz.PagerSlidingTabStrip;
import com.smp.swissarmyknife.R;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.Menu;

public class ToolHostActivity extends FragmentActivity
{
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private UtilityPagerAdapter adapter;

	@SuppressLint("NewApi") @Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tool_host);

		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
		pager = (ViewPager) findViewById(R.id.pager);
		adapter = new UtilityPagerAdapter(getSupportFragmentManager());

		pager.setAdapter(adapter);

		final int pageMargin =
				(int) TypedValue.applyDimension
				(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		
		pager.setPageMargin(pageMargin);

		tabs.setViewPager(pager);
		final int colorRed = getResources().getColor(R.color.holo_red);
		tabs.setIndicatorColor(colorRed);
		
		Drawable colorDrawable = new ColorDrawable(colorRed);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			getActionBar().setBackgroundDrawable(colorDrawable);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tool_host, menu);
		return true;
	}

}
