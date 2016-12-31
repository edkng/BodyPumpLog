package com.example.android.bodypumplog;

import android.icu.text.DecimalFormat;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by eng on 12/29/2016.
 */

public class TrackFragmentActivity extends Fragment{


    public static final String ARG_PAGE = "page";
    private int mPageNumber;

    private ConstraintLayout mWarmup;
    private ClickHandler ch_warmup;
    private ConstraintLayout mSquat;
    private ClickHandler ch_squat;
    private ConstraintLayout mChest;
    private ClickHandler ch_chest;
    private ConstraintLayout mBack;
    private ClickHandler ch_back;
    private ConstraintLayout mTricep;
    private ClickHandler ch_tricep;
    private ConstraintLayout mBicep;
    private ClickHandler ch_bicep;
    private ConstraintLayout mLunge;
    private ClickHandler ch_lunge;
    private ConstraintLayout mShoulder;
    private ClickHandler ch_shoulder;
    private ConstraintLayout mAbs;
    private ClickHandler ch_abs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_track, container, false);

//        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //getView().findViewById(R.id.item_warmup);
        mWarmup = (ConstraintLayout)rootView.findViewById(R.id.item_warmup);
        setTitle(mWarmup, "Warm-Up");
        setWeights(mWarmup);
        setButton(ch_warmup, mWarmup);

        mSquat = (ConstraintLayout)rootView.findViewById(R.id.item_squats);
        setTitle(mSquat,"Squats");
        setWeights(mSquat);
        setButton(ch_squat, mSquat);

        mChest = (ConstraintLayout)rootView.findViewById(R.id.item_chest);
        setTitle(mChest,"Chest");
        setWeights(mChest);
        setButton(ch_chest, mChest);

        mBack = (ConstraintLayout)rootView.findViewById(R.id.item_back);
        setTitle(mBack,"Back");
        setWeights(mBack);
        setButton(ch_back, mBack);

        mTricep = (ConstraintLayout)rootView.findViewById(R.id.item_tricep);
        setTitle(mTricep,"Triceps");
        setWeights(mTricep);
        setButton(ch_tricep, mTricep);

        mBicep = (ConstraintLayout)rootView.findViewById(R.id.item_bicep);
        setTitle(mBicep,"Biceps");
        setWeights(mBicep);
        setButton(ch_bicep, mBicep);

        mLunge = (ConstraintLayout)rootView.findViewById(R.id.item_lunge);
        setTitle(mLunge,"Lunges");
        setWeights(mLunge);
        setButton(ch_lunge, mLunge);

        mShoulder = (ConstraintLayout)rootView.findViewById(R.id.item_shoulder);
        setTitle(mShoulder,"Shoulders");
        setWeights(mShoulder);
        setButton(ch_shoulder, mShoulder);

        mAbs = (ConstraintLayout)rootView.findViewById(R.id.item_abs);
        setTitle(mAbs,"Abs");
        setWeights(mAbs);
        setButton(ch_abs, mAbs);

        return rootView;
    }


    private void setTitle(ConstraintLayout item, String title){
        ((TextView)item.findViewById(R.id.tv_exercise)).setText(title);
    }

    private void setButton(ClickHandler clickHandler, ConstraintLayout item) {
        clickHandler = new ClickHandler(item);
        (item.findViewById(R.id.button_decrement)).setOnClickListener(clickHandler);
        (item.findViewById(R.id.button_increment)).setOnClickListener(clickHandler);

    }

    private void setWeights(ConstraintLayout item) {
        ((TextView)item.findViewById(R.id.tv_lastweek_weights)).setText("0");
        ((TextView)item.findViewById(R.id.tv_this_weights)).setText("0");
        item.findViewById(R.id.button_decrement).setEnabled(false);
    }


    private class ClickHandler implements View.OnClickListener {

        ConstraintLayout mItem;

        public ClickHandler(ConstraintLayout item) {
            mItem = item;
        }

        @Override
        public void onClick(View v) {

            double number = Double.parseDouble(((TextView) mItem.findViewById(R.id.tv_this_weights)).getText().toString());

            switch(v.getId()) {
                case R.id.button_increment:
                    number += 2.5;
                    break;
                case R.id.button_decrement:
                    if(number > 0)
                        number -= 2.5;
                    break;
                default:
                    Toast.makeText(getContext(), "Something's wrong", Toast.LENGTH_SHORT).show();
            }

            java.text.DecimalFormat formatter = new java.text.DecimalFormat("0.#");

            ((TextView)mItem.findViewById(R.id.tv_this_weights)).setText(formatter.format(number));
            if(number <= 0)
                mItem.findViewById(R.id.button_decrement).setEnabled(false);
            else
                mItem.findViewById(R.id.button_decrement).setEnabled(true);
        }
    }
}
