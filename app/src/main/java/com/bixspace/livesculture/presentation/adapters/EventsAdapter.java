package com.bixspace.livesculture.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bixspace.livesculture.R;
import com.bixspace.livesculture.data.EventModel;
import com.bixspace.livesculture.presentation.adapters.listeners.OnClickEventListener;
import com.bixspace.livesculture.presentation.presenters.commons.CommunicateEventsPresenter;
import com.bixspace.livesculture.presentation.utils.GlideUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder>
        implements OnClickEventListener {


    private ArrayList<EventModel> contactModelResponses;
    private Context context;
    public CommunicateEventsPresenter communicateEventsPresenter;

    public EventsAdapter(Context context, ArrayList<EventModel> contactModelResponses,
                         CommunicateEventsPresenter communicateEventsPresenter) {
        this.contactModelResponses = contactModelResponses;
        this.context = context;
        this.communicateEventsPresenter = communicateEventsPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_place,
                parent, false);
        return new ViewHolder(row, this);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final EventModel model = contactModelResponses.get(position);

        GlideUtils.loadImage(holder.ivEvent,model.getImage_1(),context);
        GlideUtils.loadImage(holder.imageView2,model.getImage_category(),context);
      //  GlideUtils.loadImage(holder.imageView3,model.getImage_1(),context);

        holder.tvTitle.setText(model.getName());
        holder.tvPrice.setText("Desde S/. "+model.getMin_price());
        holder.tvHour.setText(model.getStart_hour()+" - "+model.getEnd_hour());

    }

    public void setItems(ArrayList<EventModel> eventModels) {
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
        @BindView(R.id.tv_hour)
        TextView tvHour;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.imageView3)
        ImageView imageView3;

        OnClickEventListener onClickContactListener;

        public ViewHolder(View view, OnClickEventListener onClickContactListener) {
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