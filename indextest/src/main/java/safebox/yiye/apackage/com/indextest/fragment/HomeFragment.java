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
public class HomeFragment extends BaseFragment {

    public static final String TAG = "HomeFragment";
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public String getFragmentName() {
        return TAG;
    }

    TableSelectListener tableSelectListener = null;

    public TableSelectListener getTableSelectListener() {
        return tableSelectListener;
    }

    public void setTableSelectListener(
            TableSelectListener tableSelectListener) {
        this.tableSelectListener = tableSelectListener;
    }

    public interface TableSelectListener {
        void tableSelect(int position,int tag);
    }

}
