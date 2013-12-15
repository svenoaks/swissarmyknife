package com.smp.swissarmyknife.global;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

	public class UtilityPagerAdapter extends FragmentPagerAdapter {

		
		public UtilityPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return Tool.values()[position].toString();
		}

		@Override
		public int getCount() {
			return Tool.values().length;
		}

		@Override
		public Fragment getItem(int position) {
			return BaseUtilityCardFragment.newInstance(position);
		}

	}


