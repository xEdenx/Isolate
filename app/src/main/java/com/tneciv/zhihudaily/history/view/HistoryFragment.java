package com.tneciv.zhihudaily.history.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.squareup.leakcanary.RefWatcher;
import com.tneciv.zhihudaily.MyApplication;
import com.tneciv.zhihudaily.R;
import com.tneciv.zhihudaily.api.ZhihuApi;
import com.tneciv.zhihudaily.home.view.NewsFragmnt;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {


    @Bind(R.id.datePicker)
    DatePicker datePicker;
    @Bind(R.id.btn_showTime)
    Button btnShowTime;
    @Bind(R.id.coordinator_fragment)
    LinearLayout coordinatorFragment;

    public HistoryFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_showTime)
    void click(View view) {
        int year = datePicker.getYear();
        int month = datePicker.getMonth() + 1;
        int day = datePicker.getDayOfMonth();
        String s = new StringBuilder().append(year).append(month > 10 ? month : "0" + month).append(day > 10 ? day : "0" + day).toString();
        NewsFragmnt newsFragmnt = new NewsFragmnt();
        Bundle bundle = new Bundle();
        String url = ZhihuApi.getHistoryNewsUrl(s);
        bundle.putString("historyUrl", url);
        newsFragmnt.setArguments(bundle);
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_base, newsFragmnt);
        transaction.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = MyApplication.getRefWatcher(getActivity());
        watcher.watch(this);
    }

}
