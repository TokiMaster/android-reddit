package com.example.reddit_clone_android.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.ListFragment;

import com.example.reddit_clone_android.DetailActivity;
import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.Adapters.PostAdapter;
import com.example.reddit_clone_android.model.Mockup;
import com.example.reddit_clone_android.model.Post;

public class PostFragments extends ListFragment {

    public static PostFragments newInstance() {
        return new PostFragments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.post_map, container, false);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        PostAdapter postAdapter = new PostAdapter(getActivity());
        setListAdapter(postAdapter);
    }
}