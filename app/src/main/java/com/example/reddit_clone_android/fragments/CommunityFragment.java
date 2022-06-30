package com.example.reddit_clone_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.reddit_clone_android.Adapters.CommunityAdapter;
import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.CommunityDTO;
import com.example.reddit_clone_android.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityFragment extends ListFragment {

    public static CommunityFragment newInstance() {
        return new CommunityFragment();
    }

    List<CommunityDTO> communityDTOS = new ArrayList<>();
    CommunityAdapter communityAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.community_map, container, false);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshC);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<CommunityDTO>> communities = BackendHttpRequests.getInstance().getCommunityService().getAllCommunities();
                communities.enqueue(new Callback<List<CommunityDTO>>() {
                    @Override
                    public void onResponse(Call<List<CommunityDTO>> call, Response<List<CommunityDTO>> response) {
                        if(response.isSuccessful()){
                            communityAdapter.setCommunityDTOS(response.body());
                            communityAdapter.notifyDataSetChanged();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<CommunityDTO>> call, Throwable t) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        communityAdapter = new CommunityAdapter(getActivity(), this.communityDTOS);
        setListAdapter(communityAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Call<List<CommunityDTO>> communities = BackendHttpRequests.getInstance().getCommunityService().getAllCommunities();
        communities.enqueue(new Callback<List<CommunityDTO>>() {
            @Override
            public void onResponse(Call<List<CommunityDTO>> call, Response<List<CommunityDTO>> response) {
                if(response.isSuccessful()){
                    communityAdapter.setCommunityDTOS(response.body());
                    communityAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<CommunityDTO>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
