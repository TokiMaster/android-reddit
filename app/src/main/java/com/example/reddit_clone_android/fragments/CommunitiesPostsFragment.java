package com.example.reddit_clone_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.Adapters.PostAdapter;
import com.example.reddit_clone_android.http.BackendHttpRequests;
import com.example.reddit_clone_android.model.Post;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunitiesPostsFragment extends ListFragment {

    public static CommunitiesPostsFragment newInstance() {
        return new CommunitiesPostsFragment();
    }
    List<Post> posts = new ArrayList<>();
    PostAdapter postAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.post_map, container, false);
        Long id = getActivity().getIntent().getExtras().getLong("id");
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Call<List<Post>> posts = BackendHttpRequests.getInstance().getCommunityService().getCommunitiesPosts(id);
                posts.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if(response.isSuccessful()){
                            postAdapter.setPosts(response.body());
                            postAdapter.notifyDataSetChanged();
                        }
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
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
        postAdapter = new PostAdapter(getActivity(), this.posts);
        setListAdapter(postAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Long id = getActivity().getIntent().getExtras().getLong("id");
        Call<List<Post>> posts = BackendHttpRequests.getInstance().getCommunityService().getCommunitiesPosts(id);
        posts.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()){
                    postAdapter.setPosts(response.body());
                    postAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }
}
