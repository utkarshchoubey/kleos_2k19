package com.technocracy.nitraipur.kleos2k18.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.florent37.depth.Depth;
import com.github.florent37.depth.DepthProvider;
import com.technocracy.nitraipur.kleos2k18.R;
import com.technocracy.nitraipur.kleos2k18.adapters.HintsRecyclerAdapter;
import com.technocracy.nitraipur.kleos2k18.models.Question;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiBase;
import com.technocracy.nitraipur.kleos2k18.restapi.ApiEndpoints;
import com.technocracy.nitraipur.kleos2k18.utils.UserPreferences;
import com.wang.avi.AVLoadingIndicatorView;

import am.appwise.components.ni.NoInternetDialog;
import io.github.mthli.slice.Slice;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HintsFragment extends Fragment {

    private Depth depth;
    public HintsFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        UserPreferences userPreferences = new UserPreferences(getContext());
        View view = inflater.inflate(R.layout.fragment_hints_page, container, false);
        AVLoadingIndicatorView indicatorView =(AVLoadingIndicatorView)view.findViewById(R.id.avi1);
        indicatorView.show();
        TextView def = (TextView)view.findViewById(R.id.defaultTV);
        def.setVisibility(View.INVISIBLE);
        RecyclerView rv = (RecyclerView)view.findViewById(R.id.rv);
        rv.setVisibility(View.INVISIBLE);
        ApiEndpoints apiBase= ApiBase.getClient().create(ApiEndpoints.class);
        String id = userPreferences.getLevel();
        if(!id.equals("12")){
            id = String.valueOf(Integer.parseInt(id)+1);
        }
        Call<Question> call = apiBase.getHints(id);
        call.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(@NonNull Call<Question> call, @NonNull Response<Question> response) {
                indicatorView.hide();
                    if(!response.body().hint1.equals("")
                            || !response.body().hint2.equals("")
                            || !response.body().hint3.equals("")
                            || !response.body().hint4.equals("")){
                        int c = 0;
                        if(!response.body().hint1.equals(""))c++;
                        if(!response.body().hint2.equals(""))c++;
                        if(!response.body().hint3.equals(""))c++;
                        if(!response.body().hint4.equals(""))c++;
                        def.setVisibility(View.GONE);
                        rv.setVisibility(View.VISIBLE);
                        LinearLayoutManager layout = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                        rv.setLayoutManager(layout);
                        HintsRecyclerAdapter adapter = new HintsRecyclerAdapter(getContext(),response.body(),c);
                        rv.setAdapter(adapter);
                    }
                    else{
                        def.setVisibility(View.VISIBLE);
                        rv.setVisibility(View.GONE);
                    }


            }

            @Override
            public void onFailure(@NonNull Call<Question> call, @NonNull Throwable t) {
                Log.i("on Fail","on fail");
                NoInternetDialog noInternetDialog = new NoInternetDialog.Builder(getContext()).build();
            }
        });
        this.depth = DepthProvider.getDepth(view);
        return  depth.setupFragment(10f, 10f, view);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        depth.onFragmentReady(this);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


}
