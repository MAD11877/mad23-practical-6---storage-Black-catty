package sg.edu.np.mad.practical3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class customAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User> UserList;
    private OnItemClickListener itemClickListener;
    private static final int VIEW_TYPE_DEFAULT = 0;
    private static final int VIEW_TYPE_SPECIAL = 1;
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setUserList(List<User> userList) {
        this.UserList = userList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        itemClickListener = listener;
    }
    public customAdapter(ArrayList<User>UserList){
        this.UserList=UserList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_SPECIAL) {
            View view = inflater.inflate(R.layout.slicelayout, parent, false);
            return new SpecialViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.customlayout, parent, false);
            return new DefaultViewHolder(view);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        User user = UserList.get(position);

        if (viewHolder instanceof SpecialViewHolder) {
            SpecialViewHolder holder = (SpecialViewHolder) viewHolder;
            // Bind data to views in the special layout
            holder.imageViewProfile.setImageResource(R.mipmap.ic_launcher_round);
            holder.textViewName.setText(user.getName());
            holder.textViewDescription.setText(user.getDescription());
        } else if (viewHolder instanceof DefaultViewHolder) {
            DefaultViewHolder holder = (DefaultViewHolder) viewHolder;
            // Bind data to views in the default layout
            holder.imageView.setImageResource(R.mipmap.ic_launcher_round);
            holder.textViewName.setText(user.getName());
            holder.textViewDescription.setText(user.getDescription());
        }
    }

    public int getItemCount(){
        return UserList.size();
    }
    public class DefaultViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName;
        TextView textViewDescription;

        public DefaultViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textView2);
            textViewDescription = itemView.findViewById(R.id.textView3);

            // Set click listener for the profile image
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    public class SpecialViewHolder extends RecyclerView.ViewHolder {
        ImageView imageViewProfile;
        TextView textViewName;
        TextView textViewDescription;

        public SpecialViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewProfile = itemView.findViewById(R.id.imageViewProfile);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            // Set click listener for the profile image
            imageViewProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && itemClickListener != null) {
                        itemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }

    private int generatedOTP() {
        Random ran = new Random();
        int myNumber = ran.nextInt(999999999);
        return myNumber;
    }

    private boolean generateBool() {
        Random rand = new Random();
        boolean Truth = rand.nextBoolean();
        return Truth;
    }
    public int getItemViewType(int position) {
        User user = UserList.get(position);
        int idLastDigit = user.getId() % 10;
        if (idLastDigit == 7) {
            return VIEW_TYPE_SPECIAL;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

}
