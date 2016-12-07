package com.bixspace.livesculture.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.data.CuponModel;
import com.bixspace.livesculture.presentation.adapters.listeners.OnClickCuponListener;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateCuponPresenter;
import com.bixspace.livesculture.presentation.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CuponAdapter extends RecyclerView.Adapter<CuponAdapter.ViewHolder>
        implements OnClickCuponListener {



    private ArrayList<CuponModel> contactModelResponses;
    private Context context;
    public CommunicateCuponPresenter communicateEventsPresenter;

    public CuponAdapter(Context context, ArrayList<CuponModel> contactModelResponses,
                        CommunicateCuponPresenter communicateEventsPresenter) {
        this.contactModelResponses = contactModelResponses;
        this.context = context;
        this.communicateEventsPresenter = communicateEventsPresenter;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_coupon,
                parent, false);
        return new ViewHolder(row, this);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CuponModel model = contactModelResponses.get(position);


        GlideUtils.loadImage(holder.ivCoupon, model.getCoupon().getImage(), context);
        holder.tvTitle.setText(model.getCoupon().getCompany());
        holder.tvDuration.setText(model.getCoupon().getValid());
        //  GlideUtils.loadImage(holder.imageView3,model.getImage_1(),context);

   /*     holder.tvTitle.setText(model.getName());
        holder.tvPrice.setText("Desde S/. "+model.getMin_price());
        holder.tvHour.setText(model.getStartC_hour()+" - "+model.getEnd_hour());
*/
    }

    public void setItems(ArrayList<CuponModel> eventModels) {
        this.contactModelResponses = eventModels;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return contactModelResponses.size();
    }

    @Override
    public void onEventClick(int position) {
       // communicateEventsPresenter.clickItem(contactModelResponses.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_coupon)
        ImageView ivCoupon;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_duration)
        TextView tvDuration;

        OnClickCuponListener onClickContactListener;

        public ViewHolder(View view, OnClickCuponListener onClickContactListener) {
            super(view);
            this.onClickContactListener = onClickContactListener;
            ButterKnife.bind(this, view);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickContactListener.onEventClick(getAdapterPosition());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}