package com.bixspace.livesculture.presentation.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.presentation.activities.ScannerActivity;
import com.bixspace.livesculture.presentation.utils.GlideUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by junior on 27/08/16.
 */
public class DetailEventFragment extends BaseFragment {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.btn_value)
    Button btnValue;
    @BindView(R.id.btn_scanner)
    Button btnScanner;
    @BindView(R.id.iv_event)
    ImageView ivEvent;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.tv_place)
    TextView tvPlace;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tv_hours)
    TextView tvHours;
    @BindView(R.id.tv_title_event)
    TextView tvTitleEvent;
    @BindView(R.id.tv_detail)
    TextView tvDetail;


    private Unbinder unbinder;
    private EventModel eventModel;

    public DetailEventFragment() {
        // Requires empty public constructor
    }

    public static DetailEventFragment newInstance(EventModel eventModel) {
        DetailEventFragment fragment = new DetailEventFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("event", eventModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        eventModel = (EventModel) bundle.getSerializable("event");

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_detail_event, container, false);
        ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvTitle.setText(eventModel.getName());
        tvHours.setText(eventModel.getStart_hour() + " - " + eventModel.getEnd_hour());
        tvPrice.setText("Desde S/. " + eventModel.getMin_price());
        GlideUtils.loadImage(ivEvent, eventModel.getImage_1(), getContext());
        GlideUtils.loadImage(imageView2, eventModel.getImage_category(), getContext());

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.btn_value, R.id.btn_scanner})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_value:
                break;
            case R.id.btn_scanner:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    nextActivity(getActivity(),null, ScannerActivity.class,false);
                } else {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{Manifest.permission.CAMERA}, 0);
                }

                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                nextActivity(getActivity(),null, ScannerActivity.class,false);
            }
        }
    }
}
