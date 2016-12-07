package com.bixspace.livesculture.presentation.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.data.ResponseQRModel;
import com.bixspace.livesculture.data.repository.local.SessionManager;
import com.bixspace.livesculture.presentation.activities.QuestionaryActivity;
import com.bixspace.livesculture.presentation.contracts.ValidateQRContract;
import com.google.zxing.Result;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 27/08/16.
 */
public class ScannerFragment extends BaseFragment implements ValidateQRContract.View,
        ZXingScannerView.ResultHandler{

    private ZXingScannerView mScannerView;
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 42;
    private ValidateQRContract.Presenter mPresenter;
    private Unbinder unbinder;
    ProgressDialog progressDialog;
    public ScannerFragment() {
        // Requires empty public constructor
    }

    public static ScannerFragment newInstance() {
        return new ScannerFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Validando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.circle_progress));


    }



    @Override
    public void setPresenter(@NonNull ValidateQRContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_scanner, container, false);
        unbinder = ButterKnife.bind(this, root);



        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mScannerView = new ZXingScannerView(getActivity());
        FrameLayout contentFrame = (FrameLayout) view.findViewById(R.id.content_frame);
        contentFrame.addView(mScannerView);
    }
    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    public void showinfo(){
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if(active){
            progressDialog.show();
        }else{
            if(progressDialog!=null)
                progressDialog.dismiss();
        }
    }

    @Override
    public void showMessageError(String msg) {
        showRedMessage(getActivity(),msg);
    }

    @Override
    public void validateQRSuccess(ResponseQRModel couponDetail) {
        Intent intent = new Intent(getContext(), QuestionaryActivity.class);
        intent.putExtra("qr", couponDetail);
        startActivity(intent);
        getActivity().finish();

    }


    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }




    @Override
    public void handleResult(Result result) {
        String r = result.getText();
        SessionManager authLocalData = new SessionManager(getContext());
        mPresenter.validateCode(authLocalData.getUserToken(),r);

    }
}
