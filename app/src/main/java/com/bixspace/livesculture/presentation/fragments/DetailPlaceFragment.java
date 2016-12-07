package com.bixspace.livesculture.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.data.PlaceModel;
import com.bixspace.livesculture.presentation.activities.EventDetailActivity;
import com.bixspace.livesculture.presentation.adapters.EventsAdapter;
import com.bixspace.livesculture.presentation.contracts.EventContract;
import com.bixspace.livesculture.presentation.presenters.EventPresenter;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateEventsPresenter;
import com.bixspace.livesculture.presentation.utils.GlideUtils;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 27/08/16.
 */
public class DetailPlaceFragment extends BaseFragment implements EventContract.View, OnMapReadyCallback {


    MapView mapView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.btn_value)
    Button btnValue;
    @BindView(R.id.btn_scanner)
    Button btnScanner;
    @BindView(R.id.iv_place)
    ImageView ivPlace;
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
    @BindView(R.id.rv_events)
    RecyclerView rvEvents;
    @BindView(R.id.like)
    ToggleButton like;
    @BindView(R.id.map)
    MapView map;


    private EventContract.Presenter mPresenter;
    private Unbinder unbinder;
    private EventsAdapter eventsAdapter;
    private LinearLayoutManager linearLayoutManager;


    PlaceModel placeModel = new PlaceModel();

    public DetailPlaceFragment() {
        // Requires empty public constructor
    }

    public static DetailPlaceFragment newInstance(PlaceModel placeModel) {
        DetailPlaceFragment detailPlaceFragment = new DetailPlaceFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("place", placeModel);
        detailPlaceFragment.setArguments(bundle);
        return detailPlaceFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        placeModel = (PlaceModel) bundle.getSerializable("place");
        mPresenter = new EventPresenter(getContext(), this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void setPresenter(@NonNull EventContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_detail_place, container, false);
        mapView = (MapView) root.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        ButterKnife.bind(this, root);


        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        GlideUtils.loadImage(ivPlace, placeModel.getFile_1(), getContext());
        tvAddress.setText(placeModel.getAddress());
        tvTitle.setText(placeModel.getName());
        tvDetail.setText(placeModel.getDescription());
        if(placeModel.is_favorite()){
            like.setChecked(true);
        }else{
            like.setChecked(false);
        }

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                // -11.9897286,-77.0161435
                final double latitude = placeModel.getLatitude();
                final double longitude = placeModel.getLongitude();

                // final double latitude = -11.9897286;
                //final double longitude = -77.0161435;

                LatLng latLng = new LatLng(latitude, longitude);
                googleMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title(placeModel.getName())
                        .snippet(placeModel.getAddress())
                        .icon(BitmapDescriptorFactory.defaultMarker()));
                CameraUpdate center =
                        CameraUpdateFactory.newLatLng(latLng);
                CameraUpdate zoom = CameraUpdateFactory.zoomTo(17);
                if (center != null)
                    googleMap.moveCamera(center);
                if (zoom != null)
                    googleMap.animateCamera(zoom);
            }
        });

        eventsAdapter = new EventsAdapter(getContext(), placeModel.getEvents(), (CommunicateEventsPresenter) mPresenter);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvEvents.setLayoutManager(linearLayoutManager);
        rvEvents.setAdapter(eventsAdapter);
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showEvents(ArrayList<EventModel> eventModels) {

    }

    @Override
    public void showDetailEvent(EventModel eventModel) {
        Intent intent = new Intent(getContext(), EventDetailActivity.class);
        intent.putExtra("event", eventModel);
        startActivity(intent);
    }


    @Override
    public void showErroMessage(String msg) {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @OnClick(R.id.like)
    public void onClick() {

        if (like.isChecked()){
            mPresenter.likeEvent(placeModel.getId());
        }else{
            mPresenter.dislike(placeModel.getId());
        }

    }
}
