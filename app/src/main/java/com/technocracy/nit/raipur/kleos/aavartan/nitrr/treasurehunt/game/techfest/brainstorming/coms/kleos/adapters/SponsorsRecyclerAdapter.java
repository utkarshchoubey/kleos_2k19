package com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.R;
import com.technocracy.nit.raipur.kleos.aavartan.nitrr.treasurehunt.game.techfest.brainstorming.coms.kleos.models.Sponsor;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.github.mthli.slice.Slice;
import worldline.com.foldablelayout.FoldableLayout;

public class SponsorsRecyclerAdapter extends RecyclerView.Adapter<SponsorsRecyclerAdapter.SponsorViewHolder>{
    Context ct;
    List<Sponsor> sponsors;
    private Map<Integer, Boolean> mFoldStates = new HashMap<>();
    public SponsorsRecyclerAdapter(Context ct, List<Sponsor> sponsors){
        this.ct = ct;
        this.sponsors = sponsors;
    }

    @NonNull
    @Override
    public SponsorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SponsorViewHolder(new FoldableLayout(ct));
    }

    @Override
    public void onBindViewHolder(@NonNull SponsorViewHolder holder, int position) {
        Slice slice = new Slice(holder.mFoldableLayout);
        slice.setRipple(1);
        slice.setRadius(4.0f);
        slice.setElevation(4.0f);

        holder.name.setText(sponsors.get(position).name);
        holder.desc.setText(sponsors.get(position).description);
        Picasso.get().load(sponsors.get(position).image).into(holder.iv1);
        Picasso.get().load(sponsors.get(position).image).into(holder.iv2);
        holder.b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(sponsors.get(position).url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                ct.startActivity(intent);
            }
        });


        if (mFoldStates.containsKey(position)) {
            if (mFoldStates.get(position) == Boolean.TRUE) {
                if (!holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.foldWithoutAnimation();
                }
            } else if (mFoldStates.get(position) == Boolean.FALSE) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithoutAnimation();
                }
            }
        } else {
            holder.mFoldableLayout.foldWithoutAnimation();
        }
        holder.mFoldableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.mFoldableLayout.isFolded()) {
                    holder.mFoldableLayout.unfoldWithAnimation();
                } else {
                    holder.mFoldableLayout.foldWithAnimation();
                }
            }
        });

        holder.mFoldableLayout.setFoldListener(new FoldableLayout.FoldListener() {
            @Override
            public void onUnFoldStart() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
            }

            @Override
            public void onUnFoldEnd() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
                mFoldStates.put(holder.getAdapterPosition(), false);
            }

            @Override
            public void onFoldStart() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
            }

            @Override
            public void onFoldEnd() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mFoldableLayout.setElevation(0);
                }
                mFoldStates.put(holder.getAdapterPosition(), true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return sponsors.size();
    }

    public class SponsorViewHolder extends RecyclerView.ViewHolder {
        public FoldableLayout mFoldableLayout;
        public TextView name,desc;
        public ImageView iv1,iv2;
        public Button b1;
        public CardView main,detail;
        public SponsorViewHolder(FoldableLayout foldableLayout) {
            super(foldableLayout);
            mFoldableLayout = foldableLayout;
            foldableLayout.setupViews(R.layout.sponsor_card, R.layout.sponsor_card_detail, R.dimen.card_cover_height, ct);
            name = (TextView) foldableLayout.findViewById(R.id.name);
            desc = (TextView) foldableLayout.findViewById(R.id.desc);
            b1 = (Button) foldableLayout.findViewById(R.id.link);
            iv1 = (ImageView)foldableLayout.findViewById(R.id.imageview_cover);
            iv2 = (ImageView)foldableLayout.findViewById(R.id.imageview_detail);
            main = (CardView)foldableLayout.findViewById(R.id.main_card);
            detail = (CardView)foldableLayout.findViewById(R.id.detail);
        }
    }
}
