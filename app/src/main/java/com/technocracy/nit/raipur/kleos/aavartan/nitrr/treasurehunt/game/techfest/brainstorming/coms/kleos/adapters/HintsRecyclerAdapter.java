package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.models.Question;

import io.github.mthli.slice.Slice;

public class HintsRecyclerAdapter extends RecyclerView.Adapter<HintsRecyclerAdapter.HintViewHolder> {
    Context ct;
    Question q;
    int size;

    public HintsRecyclerAdapter(Context ct, Question q, int size){
        this.ct = ct;
        this.q = q;
        this.size = size;
    }
    @NonNull
    @Override
    public HintsRecyclerAdapter.HintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater myInflator = LayoutInflater.from(ct);
        View MyOwnView = myInflator.inflate(R.layout.hint_card,parent,false);
        return new HintsRecyclerAdapter.HintViewHolder(MyOwnView);
    }

    @Override
    public void onBindViewHolder(@NonNull HintsRecyclerAdapter.HintViewHolder holder, int position) {

        Slice slice = new Slice(holder.main);
        slice.setRipple(1);
        slice.setRadius(8.0f);
        slice.setElevation(4.0f);

        switch (position+1){
            case 1:
                if(q.hint1.equals(""))holder.main.setVisibility(View.GONE);
                else{
                    holder.title.setText(q.title);
                    holder.hint.setText(q.hint1);
                }
                break;
            case 2:
                if(q.hint2.equals(""))holder.main.setVisibility(View.GONE);
                else{
                    holder.title.setText(q.title);
                    holder.hint.setText(q.hint2);
                }
                break;
            case 3:
                if(q.hint3.equals(""))holder.main.setVisibility(View.GONE);
                else{
                    holder.title.setText(q.title);
                    holder.hint.setText(q.hint3);
                }
                break;
            case 4:
                if(q.hint4.equals(""))holder.main.setVisibility(View.GONE);
                else{
                    holder.title.setText(q.title);
                    holder.hint.setText(q.hint4);
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class HintViewHolder extends RecyclerView.ViewHolder {
        TextView title,hint;
        RelativeLayout main;
        public HintViewHolder(View itemView) {
            super(itemView);
            main = (RelativeLayout)itemView.findViewById(R.id.hint);
            title=(TextView)itemView.findViewById(R.id.hinttv1);
            hint=(TextView)itemView.findViewById(R.id.hinttv2);
        }
    }
}
