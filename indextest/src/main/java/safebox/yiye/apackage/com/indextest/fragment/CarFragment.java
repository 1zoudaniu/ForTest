package safebox.yiye.apackage.com.indextest.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import safebox.yiye.apackage.com.indextest.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CarFragment extends BaseFragment {
    public static final String TAG = "CarFragment";

    public CarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_car, container, false);
    }

    @Override
    public String getFragmentName() {
        return TAG;
    }

}
