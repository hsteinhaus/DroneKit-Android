package org.droidplanner.fragments.calibration;

import org.droidplanner.activitys.ConfigurationActivity;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class SetupMainPanel extends Fragment{
 
	protected ConfigurationActivity parentActivity;

    public abstract SetupSidePanel getSidePanel();
    public abstract void setupLocalViews(View v);
    public abstract int getPanelLayout();
    public abstract void doCalibrationStep(int step);
    
    @Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if (!(activity instanceof ConfigurationActivity)) {
			throw new IllegalStateException("Parent activity must be "
					+ ConfigurationActivity.class.getName());
		}

		parentActivity = (ConfigurationActivity) activity;
	}


	@Override
	public void onDetach() {
		super.onDetach();
		parentActivity = null;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstaneState) {
		final View view = inflater.inflate(getPanelLayout(),
				container, false);

		setupLocalViews(view);

		return view;
	}

}
