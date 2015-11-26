package com.iShamrock.iMuseum.acvitity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.iShamrock.iMuseum.R;

/**
 * Created by lifengshuang on 11/22/15.
 */
public class Exhibit extends Activity{
    //wx start here
    private LinearLayout likeLayout;
    private ImageButton back;
    private ImageButton like;
    private ImageButton go;
    /* the boolean isLiked is true when the user have liked it */
    private boolean isLiked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exhibit);
        Activity activity = this;
        likeLayout = (LinearLayout)this.findViewById(R.id.likeLayout);
        back = (ImageButton)this.findViewById(R.id.Back);
        like = (ImageButton)this.findViewById(R.id.like);
        go = (ImageButton)this.findViewById(R.id.go);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(activity, Vision.class);
                startActivity(intent);
            }
        });
        /* when the user click the like button, change the image it shows */
        likeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isLiked) {
                    like.setImageDrawable(getResources().getDrawable(R.drawable.liked));
                    isLiked = true;
                }
                else {
                    like.setImageDrawable(getResources().getDrawable(R.drawable.likeit));
                    isLiked = false;
                }
            }
        });
    }
}
