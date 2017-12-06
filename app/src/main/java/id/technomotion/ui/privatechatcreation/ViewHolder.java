package id.technomotion.ui.privatechatcreation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.UUID;

import id.technomotion.R;
import id.technomotion.model.Person;


/**
 * Created by omayib on 18/09/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = "ViewHolder";
    private TextView itemName;
    private TextView itemJob;
    private com.qiscus.sdk.ui.view.QiscusCircularImageView picture;
    private Person selectedContact;
    private CheckBox checkBox;
    private final OnContactClickedListener listener;
    private TextView initialText;
    public ViewHolder(View itemView, OnContactClickedListener listener) {
        super(itemView);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        itemName = (TextView) itemView.findViewById(R.id.textViewName);
        itemJob = (TextView) itemView.findViewById(R.id.textViewJob);
        picture = (com.qiscus.sdk.ui.view.QiscusCircularImageView) itemView.findViewById(R.id.imageViewProfile);
        this.listener = listener;
        initialText = (TextView) itemView.findViewById(R.id.textInitial);
        itemView.setOnClickListener(this);
        checkBox.setVisibility(View.GONE);
    }

    public void bindAlumni(Person person,boolean isNewInitial, char newInitial) {
        if (isNewInitial) {
            this.initialText.setVisibility(View.VISIBLE);
            this.initialText.setText(String.valueOf(newInitial).toUpperCase());
        }
        else
        {
            this.initialText.setVisibility(View.INVISIBLE);
        }
        this.selectedContact = person;
        this.itemName.setText(person.getName());
        this.itemJob.setText(person.getJob());
        String avatarUrl = person.getAvatarUrl();
        Picasso.with(this.picture.getContext()).load(avatarUrl).into(picture);
    }

    @Override
    public void onClick(final View v) {
        this.listener.onContactClicked(this.selectedContact.getEmail());
    }

    public interface OnContactClickedListener{
        public void onContactClicked(String userEmail);
    }
}
