/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smp.swissarmyknife.global;

import com.smp.swissarmyknife.R;
import static com.smp.swissarmyknife.global.Constants.*;

import com.smp.swissarmyknife.compass.CompassFragment;
import com.smp.swissarmyknife.flashlight.FlashlightFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

public class BaseUtilityCardFragment extends Fragment
{

	private Tool type;

	public static BaseUtilityCardFragment newInstance(int position)
	{
		BaseUtilityCardFragment frag;
		Tool type = Tool.values()[position];
		switch (type)
		{
			case FLASHLIGHT:
				frag = new FlashlightFragment();
				break;
			case COMPASS:
				frag = new CompassFragment();
				break;
			default:
				frag = new BaseUtilityCardFragment();
		}

		Bundle b = new Bundle();
		b.putInt(EXTRA_POSITION, position);
		frag.setArguments(b);
		return frag;
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		type = Tool.values()[getArguments().getInt(EXTRA_POSITION)];
	}

	public Tool getType()
	{
		return type;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return attachLayout(null);
	}

	protected View attachLayout(View child)
	{
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		FrameLayout grandFather = new FrameLayout(getActivity());
		grandFather.setLayoutParams(params);

		final int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources()
				.getDisplayMetrics());

		FrameLayout parent = new FrameLayout(getActivity());
		params.setMargins(margin, margin, margin, margin);
		parent.setLayoutParams(params);
		parent.setLayoutParams(params);

		parent.setBackgroundResource(R.drawable.background_card);
		
		if (child != null)
			parent.addView(child);
		
		grandFather.addView(parent);

		return grandFather;
	}

}