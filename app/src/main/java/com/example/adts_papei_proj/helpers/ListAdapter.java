package com.example.adts_papei_proj.helpers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.model.UserTestResult;

import java.util.List;

public class ListAdapter extends ArrayAdapter<UserTestResult> {

    private int resourceLayout;
    private Context mContext;

    public ListAdapter(Context context, int resource, List<UserTestResult> items) {
        super(context, resource, items);
        this.resourceLayout = resource;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        TextView tvScore = null/*= (TextView) v.findViewById(R.id.score)*/;
        TextView tvDate= null /*= (TextView) v.findViewById(R.id.date)*/;
        TextView tvLevel= null /*= (TextView) v.findViewById(R.id.testLevel)*/;
        TextView tvCorrect= null /*= (TextView) v.findViewById(R.id.correctAnswers)*/;
        TextView tvWrong = null/*= (TextView) v.findViewById(R.id.wrongAnswers)*/;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(mContext);
            v = vi.inflate(resourceLayout, null);

            tvScore = (TextView) v.findViewById(R.id.score);
            tvDate = (TextView) v.findViewById(R.id.date);
            tvLevel = (TextView) v.findViewById(R.id.testLevel);
            tvCorrect = (TextView) v.findViewById(R.id.correctAnswers);
            tvWrong = (TextView) v.findViewById(R.id.wrongAnswers);
        }


        UserTestResult p = getItem(position);

        if (p != null) {


            if (tvScore != null) {
                tvScore.setText(p.getScorePercentage() + "%");
            }

            if (tvDate != null) {
                tvDate.setText(String.valueOf(p.getDate()));
            }

            if (tvLevel != null) {
                tvLevel.setText(tvLevel.getText()+" B1");
                if(p.getLevel()>0)
                {
                    tvLevel.setText(tvLevel.getText()+" B2");
                }
            }
            String correctAnswers = "";
            String wrongAnswers = "";
            if (tvCorrect != null) {
                if(p.getCorrectAnsweredQuestions()!=null &&
                        p.getCorrectAnsweredQuestions().size()>0)
                {
                    for(int i = 0; i<p.getCorrectAnsweredQuestions().size();i++)
                    {
                        correctAnswers = tvCorrect.getText() + String.valueOf(p.getCorrectAnsweredQuestions().get(i).getQuestionOriginalNumber());

                        if(i==p.getCorrectAnsweredQuestions().size()-1)
                        {
                            tvCorrect.setText(correctAnswers);
                        }
                        else
                        {
                            tvCorrect.setText(correctAnswers + ",");

                        }
                    }

                }

            }
            if (tvWrong != null) {
                if(p.getWrongAnsweredQuestions()!=null &&
                        p.getWrongAnsweredQuestions().size()>0)
                {
                    for(int i = 0; i<p.getWrongAnsweredQuestions().size();i++)
                    {
                        wrongAnswers = tvWrong.getText() +String.valueOf(p.getWrongAnsweredQuestions().get(i).getQuestionOriginalNumber());

                        if(i==p.getWrongAnsweredQuestions().size()-1)
                        {
                            tvWrong.setText(wrongAnswers);
                        }
                        else
                        {
                            tvWrong.setText(wrongAnswers + ",");

                        }
                    }

                }

            }
        }

        return v;
    }

}