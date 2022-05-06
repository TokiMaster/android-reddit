package Adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.reddit_clone_android.R;

import model.Mockup;
import model.Post;

public class PostAdapter extends BaseAdapter {
    private Activity activity;
    public PostAdapter(Activity activity){
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return Mockup.getPosts().size();
    }

    @Override
    public Object getItem(int position) {
        return Mockup.getPosts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View vi = view;
        Post post = Mockup.getPosts().get(position);

        if(view == null){
            vi = activity.getLayoutInflater().inflate(R.layout.post_list, null);
        }

        ImageView avatar = vi.findViewById(R.id.avatar);
        TextView username = vi.findViewById(R.id.username);
        TextView description = vi.findViewById(R.id.description);
        ImageView photo = vi.findViewById(R.id.photo);

        if(post.getAvatar() != -1){
            avatar.setImageResource(post.getAvatar());
        }

        username.setText(post.getUsername());
        description.setText(post.getDescription());

        if(post.getImg() != -1){
            photo.setImageResource(post.getImg());
        }

        return vi;
    }
}
