package com.smp.swissarmyknife.global;

public enum Tool
{
	FLASHLIGHT, COMPASS, GPS, MAG_GLASS, MIRROR, RULER, TIMER, STOPWATCH, UNDEF1, UNDEF2, UNDEF3;
	
	@Override 
	public String toString()
	{
		switch(this)
		{
			case FLASHLIGHT:
				return "Flashlight";
			case COMPASS:
				return "Compass";
			case GPS:
				return "GPS";
			case MAG_GLASS:
				return "Magnifying Glass";
			case MIRROR:
				return "Mirror";
			case RULER:
				return "Ruler";
			case TIMER:
				return "Timer";
			case STOPWATCH:
				return "Stopwatch";
			default:
				return "?";	
		}
	}
}
