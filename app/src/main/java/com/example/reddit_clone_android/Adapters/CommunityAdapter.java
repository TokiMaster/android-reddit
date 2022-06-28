package com.example.reddit_clone_android.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.reddit_clone_android.MainActivity;
import com.example.reddit_clone_android.R;

import com.example.reddit_clone_android.SecondActivity;
import com.example.reddit_clone_android.model.CommunityDTO;

import java.util.List;

public class CommunityAdapter extends BaseAdapter {
    private Activity activity;

    public Activity getActivity() {
        return activity;
    }

    private List<CommunityDTO> communityDTOS;

    public List<CommunityDTO> getCommunityDTOS() {
        return communityDTOS;
    }

    public void setCommunityDTOS(List<CommunityDTO> communityDTOS) {
        this.communityDTOS = communityDTOS;
    }

    public CommunityAdapter(Activity activity, List<CommunityDTO> communityDTOS) {
        this.activity = activity;
        this.communityDTOS = communityDTOS;
    }

    @Override
    public int getCount() {
        return this.communityDTOS.size();
    }

    @Override
    public Object getItem(int position) {
        return this.communityDTOS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vi = view;
        CommunityDTO communityDTO = this.communityDTOS.get(position);

        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.community_list, null);
        }

        LinearLayout card = vi.findViewById(R.id.communityCard);

        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("id", communityDTO.getId());
                activity.startActivity(intent);
            }
        });

        TextView name = vi.findViewById(R.id.nameC);
        TextView description = vi.findViewById(R.id.descriptionC);

        name.setText(communityDTO.getName());
        description.setText(communityDTO.getDescription());

        return vi;
    }
}
