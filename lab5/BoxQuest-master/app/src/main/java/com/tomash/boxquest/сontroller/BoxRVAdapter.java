package com.tomash.boxquest.сontroller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tomash.boxquest.model.Box;
import com.tomash.boxquest.R;

import java.util.List;

public class BoxRVAdapter extends RecyclerView.Adapter<BoxRVAdapter.BoxViewHolder> {
    private List<Box> mBoxList;
    private BoxPresenter mPresenter;
    private  boolean mIsGameOver;



    public BoxRVAdapter(List<Box> boxList, BoxPresenter presenter) {
        mBoxList = boxList;
        mPresenter = presenter;
    }

    @Override
    public BoxViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BoxViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.box_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(BoxViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return mBoxList.size();
    }

    public class BoxViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mImageView;


        public BoxViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.bomb_image);
            mImageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //if game over , no callback , else if bomb , change image to bomb , if nor decrease size of list and mix items.
            //if user clicks too fast need to catch array of bounds exception
            try {
                if (!mIsGameOver) {
                    if (mBoxList.get(getAdapterPosition()).hasBomb()) {
                        //change image to bomb and end game
                        mIsGameOver = true;
                        mImageView.setImageResource(R.drawable.bomb_box);
                        mPresenter.onLose();
                    } else {
                        //delete item and mix items one more time
                            mPresenter.onBoxClick(mBoxList.size());
                            mBoxList.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            notifyItemRangeChanged(getAdapterPosition(), mBoxList.size());
                            Randomizer.mixList(mBoxList);
                            //if there is one item means no items remain , and user has won the game.
                            if (getItemCount() == 1) {
                                mPresenter.onWin();
                                mIsGameOver = true;
                            }


                    }


                }
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }


        }
    }
}
