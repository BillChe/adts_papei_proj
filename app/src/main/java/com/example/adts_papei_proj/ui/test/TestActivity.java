package com.example.adts_papei_proj.ui.test;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.model.Question;
import com.example.adts_papei_proj.ui.main.HomeActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {

    private TextView questionTV, questionNumberTV;
    private Button button1, button2, button3, button4;
    private ArrayList<Question> questionArrayList ;
    int currentScore, questionAttempted, currentPos, currentAttempt = 0;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_b);

        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        button1 = findViewById(R.id.btnOption1);
        button2 = findViewById(R.id.btnOption2);
        button3 = findViewById(R.id.btnOption3);
        button4 = findViewById(R.id.btnOption4);

        //create questions
        questionArrayList = new ArrayList<>();
        createQuestions(questionArrayList);
        currentPos = 0;
        setDataToViews(currentPos);
        setListeners();

    }

    private void setListeners() {
        //a) button option
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().toLowerCase().equals(button1.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionAttempted++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
        //b) button option
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().toLowerCase().equals(button2.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionAttempted++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
        //c) button option
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().toLowerCase().equals(button3.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionAttempted++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });

        //d) button option
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().toLowerCase().equals(button4.getText().toString().trim().toLowerCase()))
                {
                    currentScore++;
                }
                questionAttempted++;
                currentPos++;
                setDataToViews(currentPos);
            }
        });
    }

    private void setDataToViews(int currentPos){
        if(questionAttempted == 9)
        {
            currentAttempt = questionAttempted+1;
            questionNumberTV.setText("Questions Attempted: " + currentAttempt + "/10");
            showScore();
        }
        else
        {
            currentAttempt = questionAttempted+1;
            questionNumberTV.setText("Questions Attempted: " + currentAttempt + "/10");
            questionTV.setText(questionArrayList.get(currentPos).getQuestionText());

            button1.setText(questionArrayList.get(currentPos).getPossibleAnswer1());
            button2.setText(questionArrayList.get(currentPos).getPossibleAnswer2());
            button3.setText(questionArrayList.get(currentPos).getPossibleAnswer3());
            button4.setText(questionArrayList.get(currentPos).getPossibleAnswer4());
        }

    }

    private void createQuestions(ArrayList<Question> questionArrayList) {
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,2));
        questionArrayList.add(new Question(getString(R.string.question_2),"on account of",
                "due", "because", "owing",
                "on account of",0,1));
        questionArrayList.add(new Question(getString(R.string.question_3),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,1));
    }

    private void showScore(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TestActivity.this);
        View bottomView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_layout,
                (LinearLayout)findViewById(R.id.scoreLayout));
        TextView scoreTV = bottomView.findViewById(R.id.text_view_score);
        Button closeBtn = bottomView.findViewById(R.id.closeBtn);
        scoreTV.setText(scoreTV.getText() + "\n" + currentScore +"/10");
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //restart values
                setDataToViews(currentPos);
                bottomSheetDialog.dismiss();
            }
        });
        currentPos = 0;
        questionAttempted = 0;
        currentScore = 0;
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomView);
        bottomSheetDialog.show();
    }
}
