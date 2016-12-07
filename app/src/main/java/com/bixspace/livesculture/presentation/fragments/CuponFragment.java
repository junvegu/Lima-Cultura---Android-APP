package com.bixspace.livesculture.presentation.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.base.RecyclerViewScrollListener;
import com.bixspace.livesculture.base.ScrollChildSwipeRefreshLayout;
import com.bixspace.livesculture.data.CuponModel;
import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.presentation.activities.EventDetailActivity;
import com.bixspace.livesculture.presentation.adapters.CuponAdapter;
import com.bixspace.livesculture.presentation.adapters.EventsAdapter;
import com.bixspace.livesculture.presentation.contracts.CouponContract;
import com.bixspace.livesculture.presentation.contracts.CuponContract;
import com.bixspace.livesculture.presentation.contracts.EventContract;
import com.bixspace.livesculture.presentation.presenters.CouponPresenter;
import com.bixspace.livesculture.presentation.presenters.CuponPresenter;
import com.bixspace.livesculture.presentation.presenters.EventPresenter;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateContactsPresenter;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateCuponPresenter;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateEventsPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 27/08/16.
 */
public class CuponFragment extends BaseFragment implements CuponContract.View{

    @BindView(R.id.rv_events)
    RecyclerView rvEvents;
    private CuponContract.Presenter mPresenter;
    private Unbinder unbinder;
    private CuponAdapter eventsAdapter;
    private LinearLayoutManager linearLayoutManager;


    public CuponFragment() {
        // Requires empty public constructor
    }

    public static CuponFragment newInstance() {
        return new CuponFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new CuponPresenter(getContext(),this);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull CuponContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_events, container, false);
        unbinder = ButterKnife.bind(this, root);

        // Set up progress indicator
        final ScrollChildSwipeRefreshLayout swipeRefreshLayout =
                (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );
        // Set the scrolling view in the custom SwipeRefreshLayout.
        swipeRefreshLayout.setScrollUpChild(rvEvents);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.loadEvents();
            }
        });

        setHasOptionsMenu(true);

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        linearLayoutManager= new LinearLayoutManager(getContext());
        eventsAdapter  = new CuponAdapter(getContext(), new ArrayList<CuponModel>(), (CommunicateCuponPresenter) mPresenter);
        rvEvents.setLayoutManager(linearLayoutManager);
        rvEvents.setAdapter(eventsAdapter);

    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        if (getView() == null) {
            return;
        }
        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showEvents(ArrayList<CuponModel> eventModels) {
        if (this.rvEvents != null && eventsAdapter != null) {

            eventsAdapter = new CuponAdapter(getContext(), eventModels, (CommunicateCuponPresenter) mPresenter);
            rvEvents.setAdapter(eventsAdapter);


            if (eventModels.size() > 0) {
                //numCoupon.setText(wearModels.getCouponNum()+ " CUPONES");
                // noComplatins.setVisibility(View.GONE);
            } else {
                //numCoupon.setText("No tienes cupones guardados");
                // noComplatins.setVisibility(View.VISIBLE);
            }


            this.rvEvents.addOnScrollListener(new RecyclerViewScrollListener() {
                @Override
                public void onScrollUp() {

                }

                @Override
                public void onScrollDown() {

                }

                @Override
                public void onLoadMore() {

                  //  mPresenter.loadMoreCoupon();
                   /*  if (!isLoading)
                       if(sessionManager.isLogin())
                            presenter.loadMore(sessionManager.getUserToken(),"");
                        else
                            presenter.loadMore("");*/

                }
            });


        } else {
           // numCoupon.setText("No hay cupones disponibles");
            // noComplatins.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showDetailEvent(CuponModel eventModel) {
        Intent intent = new Intent(getContext(), EventDetailActivity.class);
        intent.putExtra("event",eventModel);
        startActivity(intent);
    }

    @Override
    public void showErroMessage(String msg) {
        showRedMessage(getActivity(),msg);
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }


}
