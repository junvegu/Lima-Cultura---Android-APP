package com.bixspace.livesculture.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.data.PlaceModel;
import com.bixspace.livesculture.presentation.adapters.listeners.OnClickPlaceListener;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateContactsPresenter;
import com.bixspace.livesculture.presentation.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder>
        implements OnClickPlaceListener {


    private ArrayList<PlaceModel> contactModelResponses;
    private Context context;
    public CommunicateContactsPresenter communicateEventsPresenter;

    public PlaceAdapter(Context context, ArrayList<PlaceModel> contactModelResponses,
                        CommunicateContactsPresenter communicateEventsPresenter) {
        this.contactModelResponses = contactModelResponses;
        this.context = context;
        this.communicateEventsPresenter = communicateEventsPresenter;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place_event,
                parent, false);
        return new ViewHolder(row, this);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final PlaceModel model = contactModelResponses.get(position);

        GlideUtils.loadImage(holder.ivEvent, model.getFile_1(), context);
        GlideUtils.loadImage(holder.imageView2, model.getFile_2(), context);
        holder.tvTitle.setText(model.getName());
        holder.tvAddress.setText(model.getAddress());
        holder.tvDescription.setText(model.getDescription());
        //  GlideUtils.loadImage(holder.imageView3,model.getImage_1(),context);

   /*     holder.tvTitle.setText(model.getName());
        holder.tvPrice.setText("Desde S/. "+model.getMin_price());
        holder.tvHour.setText(model.getStart_hour()+" - "+model.getEnd_hour());
*/
    }

    public void setItems(ArrayList<PlaceModel> eventModels) {
        this.contactModelResponses = eventModels;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return contactModelResponses.size();
    }

    @Override
    public void onEventClick(int position) {
        communicateEventsPresenter.clickItem(contactModelResponses.get(position));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.iv_event)
        ImageView ivEvent;
        @BindView(R.id.imageView2)
        ImageView imageView2;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_address)
        TextView tvAddress;
        @BindView(R.id.tv_description)
        TextView tvDescription;
        @BindView(R.id.imageView3)
        ImageView imageView3;
        OnClickPlaceListener onClickContactListener;

        public ViewHolder(View view, OnClickPlaceListener onClickContactListener) {
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