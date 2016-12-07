package com.bixspace.livesculture.presentation.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.base.BaseFragment;
import com.bixspace.livesculture.data.ResponseQRModel;
import com.bixspace.livesculture.presentation.activities.CouponActivity;
import com.bixspace.livesculture.presentation.activities.CuponActivity;
import com.bixspace.livesculture.presentation.contracts.QuestonaryContract;
import com.bixspace.livesculture.presentation.utils.ProgressDialogCustom;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by junior on 27/08/16.
 */
public class QuestionaryFragment extends BaseFragment implements QuestonaryContract.View {

    @BindView(R.id.content_text)
    ImageView contentText;
    @BindView(R.id.a)
    LinearLayout a;
    @BindView(R.id.b)
    LinearLayout b;
    @BindView(R.id.c)
    LinearLayout c;
    @BindView(R.id.d)
    LinearLayout d;
    @BindView(R.id.et_a)
    TextView etA;
    @BindView(R.id.et_b)
    TextView etB;
    @BindView(R.id.et_c)
    TextView etC;
    @BindView(R.id.et_d)
    TextView etD;
    @BindView(R.id.et_answer)
    TextView etAnswer;
    private QuestonaryContract.Presenter mPresenter;
    private Unbinder unbinder;
    private ResponseQRModel responseQRModel;
    private ProgressDialogCustom progressDialogCustom;

    public QuestionaryFragment() {
        // Requires empty public constructor
    }

    public static QuestionaryFragment newInstance(ResponseQRModel responseQRModel) {

        QuestionaryFragment questionaryFragment = new QuestionaryFragment();

        Bundle bundle = new Bundle();
        bundle.putSerializable("qr", responseQRModel);
        questionaryFragment.setArguments(bundle);
        return questionaryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        responseQRModel = (ResponseQRModel) bundle.getSerializable("qr");


    }

    @Override
    public void onResume() {
        super.onResume();
        // mPresenter.start();
    }

    @Override
    public void setPresenter(@NonNull QuestonaryContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_layout_cuestonary, container, false);

        ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressDialogCustom = new ProgressDialogCustom(getContext(), "Validando");
        etA.setText(responseQRModel.getQ_1());
        etB.setText(responseQRModel.getQ_2());
        etC.setText(responseQRModel.getQ_3());
        etD.setText(responseQRModel.getQ_4());
        etAnswer.setText(responseQRModel.getQuestion());

    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }
        if (active) {
            progressDialogCustom.show();
        } else {
            if (progressDialogCustom.isShowing()) {
                progressDialogCustom.dismiss();
            }
        }
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void showmessage() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Has ganado un cupon en: " + responseQRModel.getCompany() + " revisa tus cupones en el menú principal")
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getContext(), CuponActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });

        builder.show();


    }

    @Override
    public void showerrormessage(String msg) {
        showRedMessage(getActivity(), msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick({R.id.a, R.id.b, R.id.c, R.id.d})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a:
                if (responseQRModel.getA_1().equals("1")) {
                    mPresenter.califyQuestionary(responseQRModel.getId());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Oops, te equivocaste, vuelve a intentarlo en otro momento.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                }
                            });
                    builder.show();

                }

                break;
            case R.id.b:
                if (responseQRModel.getA_1().equals("2")) {
                    mPresenter.califyQuestionary(responseQRModel.getId());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Oops, te equivocaste, vuelve a intentarlo en otro momento.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                }
                            });
                    builder.show();
                }
                break;
            case R.id.c:
                if (responseQRModel.getA_1().equals("3")) {
                    mPresenter.califyQuestionary(responseQRModel.getId());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Oops, te equivocaste, vuelve a intentarlo en otro momento.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                }
                            });
                    builder.show();
                }
                break;
            case R.id.d:
                if (responseQRModel.getA_1().equals("4")) {
                    mPresenter.califyQuestionary(responseQRModel.getId());
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Oops, te equivocaste, vuelve a intentarlo en otro momento.")
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    getActivity().finish();
                                }
                            });
                    builder.show();
                }
                break;
        }
    }
}
