package com.tomash.boxquest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tomash.boxquest.сontroller.BoxPresenter;
import com.tomash.boxquest.сontroller.Randomizer;
import com.tomash.boxquest.model.Box;
import com.tomash.boxquest.R;
import com.tomash.boxquest.util.GridLayoutItemDecoration;
import com.tomash.boxquest.сontroller.BoxRVAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BoxPresenter {
    private RecyclerView mRecyclerView;
    private TextView mScoreTextView;
    private Button mNewGameButton;
    private ArrayList<Box> mBoxList = new ArrayList<>();
    private BoxRVAdapter mBoxRVAdapter;
    private double mScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonSetup();
        rvSetup();


    }

    private void rvSetup() {
        mRecyclerView = (RecyclerView) findViewById(R.id.game_rv);
//        mBoxRVAdapter = new BoxRVAdapter(mBoxList,this);
        //grid layout manager with 4 places for boxes
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //decoration with space 2px
        mRecyclerView.addItemDecoration(new GridLayoutItemDecoration(2));
        //adapter setup
        mBoxRVAdapter = new BoxRVAdapter(mBoxList, MainActivity.this);
        mRecyclerView.setAdapter(new BoxRVAdapter(mBoxList, this));
    }

    private void buttonSetup() {
        mNewGameButton = (Button) findViewById(R.id.start_game_button);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set score to 0 for every new game
                mScore = 0;
                String scoreText = getString(R.string.default_score_text) + mScore;
                mScoreTextView.setText(scoreText);
                //hide button
                view.setVisibility(View.GONE);
                mNewGameButton.setText(R.string.try_again);
                //game init
                mBoxList = Randomizer.getStartingBoxList();
                mBoxRVAdapter = new BoxRVAdapter(mBoxList, MainActivity.this);
                mRecyclerView.setAdapter(mBoxRVAdapter);

            }
        });

        mScoreTextView = (TextView) findViewById(R.id.score_text_view);
    }

    @Override
    public void onBoxClick(int size) {

        //score = chance to lose * 100 * lvl number
        double level = 11 - size;
        double loseChance = 1d / (double) size * 100;
        mScore += Math.round(level * loseChance);
        mScoreTextView.setText(getScoreText());
    }

    @Override
    public void onLose() {
        mNewGameButton.setVisibility(View.VISIBLE);
        String loseText = "Sorry , but you lose."+getScoreText();
        mScoreTextView.setText(loseText);
    }

    @Override
    public void onWin() {

        mNewGameButton.setVisibility(View.VISIBLE);
        String winText = "Congratulations , you win!"+getScoreText();
        mScoreTextView.setText(winText);
    }



    private String getScoreText() {
        return getString(R.string.default_score_text) + mScore;

    }
}
