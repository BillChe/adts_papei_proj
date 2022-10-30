package com.example.adts_papei_proj.ui.test;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adts_papei_proj.R;
import com.example.adts_papei_proj.data.model.Question;
import com.example.adts_papei_proj.data.model.UserTestResult;
import com.example.adts_papei_proj.data.viewmodels.MainViewModel;
import com.example.adts_papei_proj.ui.login.LoginActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TestActivity extends AppCompatActivity {

    private TextView questionTV, questionNumberTV;
    private RadioButton button1, button2, button3, button4;
    private Button  previous, next;
    private ArrayList<Question> questionArrayList, correctQuestions, wrongQuestions, questionListAttempted ;
    private RadioGroup radioGroupQuestions;
    int currentScore, questionAttempted, currentPos,testLevel = 0;
    int currentQuestion = 1;
    String scorePercentage = "";
    double scoreDouble = 0;
    private MainViewModel mainViewModel;
    UserTestResult testResult = null;

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_b);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        questionTV = findViewById(R.id.idTVQuestion);
        questionNumberTV = findViewById(R.id.idTVQuestionAttempted);
        button1 = findViewById(R.id.btnOption1);
        button2 = findViewById(R.id.btnOption2);
        button3 = findViewById(R.id.btnOption3);
        button4 = findViewById(R.id.btnOption4);
        previous = findViewById(R.id.previousQuestion);
        next = findViewById(R.id.nextQuestion);

        radioGroupQuestions = findViewById(R.id.radioGroupQuestions);

        //create questions lists
        questionArrayList = new ArrayList<>();
        //wrong and correct answered questions lists
        correctQuestions = new ArrayList<>();
        wrongQuestions = new ArrayList<>();
        questionListAttempted = new ArrayList<>();
        if(getIntent().getStringExtra("level").equals("b1"))
        {
            getSupportActionBar().setTitle(getString(R.string.app_name) + " B1 TEST");

            createQuestionsB1(questionArrayList);
        }
        else if (getIntent().getStringExtra("level").equals("b2"))
        {
            testLevel = 1;//set level for firebase
            getSupportActionBar().setTitle(getString(R.string.app_name) + " B2 TEST");
            createQuestionsB2(questionArrayList);
        }


        currentPos = 0;

        setDataToViews(currentPos);
        setListeners();
        mainViewModel = new MainViewModel(TestActivity.this);
    }

    private void setListeners() {
        //a) button option
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().equalsIgnoreCase(button1.getText().toString().trim())
                      &&!questionListAttempted.contains(questionArrayList.get(currentPos) ))
                {
                    currentScore++;
                    //update correct answered questions
                    correctQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)) &&
                        !questionArrayList.get(currentPos).getQuestionAnswer().trim().
                                equalsIgnoreCase(button1.getText().toString().trim()))
                {
                    //update wrong answered questions
                    wrongQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)))
                {
                    questionListAttempted.add(questionArrayList.get(currentPos));
                    questionAttempted++;

                }


          /*      questionArrayList.remove(currentPos);*/
                if(currentQuestion<15)
                {

                    currentPos++;
                }
                currentQuestion = currentPos;
                questionArrayList.get(currentPos).setUserChoice(1);
                setDataToViews(currentPos);
            }
        });
        //b) button option
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().equalsIgnoreCase(button2.getText().toString().trim())
                        && !questionListAttempted.contains(questionArrayList.get(currentPos)))
                {
                    currentScore++;
                    //update correct answered questions
                    correctQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)) &&
                        !questionArrayList.get(currentPos).getQuestionAnswer().trim().
                                equalsIgnoreCase(button2.getText().toString().trim()))
                {
                    //update wrong answered questions
                    wrongQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)))
                {
                    questionListAttempted.add(questionArrayList.get(currentPos));
                    questionAttempted++;
                }

             /*   questionArrayList.remove(currentPos);*/
                if(currentQuestion<15)
                {

                    currentPos++;
                }
                currentQuestion = currentPos;
                questionArrayList.get(currentPos).setUserChoice(2);
                setDataToViews(currentPos);
            }
        });
        //c) button option
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().equalsIgnoreCase(button3.getText().toString().trim())
                        && !questionListAttempted.contains(questionArrayList.get(currentPos)))
                {
                    currentScore++;
                    //update correct answered questions
                    correctQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)) &&
                        !questionArrayList.get(currentPos).getQuestionAnswer().trim().
                                equalsIgnoreCase(button3.getText().toString().trim()))
                {
                    //update wrong answered questions
                    wrongQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)))
                {
                    questionListAttempted.add(questionArrayList.get(currentPos));
                    questionAttempted++;

                }


/*
                questionArrayList.remove(currentPos);
*/
                if(currentQuestion<15)
                {

                    currentPos++;
                }
                currentQuestion = currentPos;
                questionArrayList.get(currentPos).setUserChoice(3);
                setDataToViews(currentPos);
            }
        });

        //d) button option
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionArrayList.get(currentPos).getQuestionAnswer().trim().equalsIgnoreCase(button4.getText().toString().trim())
                        && (!questionListAttempted.contains(questionArrayList.get(currentPos))))
                {
                    currentScore++;
                    //update correct answered questions
                    correctQuestions.add(questionArrayList.get(currentPos));

                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)) &&
                        !questionArrayList.get(currentPos).getQuestionAnswer().trim().
                                equalsIgnoreCase(button4.getText().toString().trim()))
                {
                    //update wrong answered questions
                    wrongQuestions.add(questionArrayList.get(currentPos));
                }
                if(!questionListAttempted.contains(questionArrayList.get(currentPos)))
                {
                    questionListAttempted.add(questionArrayList.get(currentPos));
                    questionAttempted++;

                }

/*
                questionArrayList.remove(currentPos);
*/
                if(currentQuestion<15)
                {

                   currentPos++;
                }
                currentQuestion = currentPos;
                questionArrayList.get(currentPos).setUserChoice(4);
                setDataToViews(currentPos);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroupQuestions.clearCheck();
                if(currentPos>0 || currentQuestion<questionArrayList.size())
                {
                    currentPos--;
                }
                currentQuestion = currentPos;

                setDataToViews(currentPos);
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroupQuestions.clearCheck();
                currentPos++;
                if(currentQuestion+1<questionArrayList.size())
                {
                    next.setEnabled(true);
                }
                else
                {
                    next.setEnabled(false);
                }
                currentQuestion = currentPos;

                setDataToViews(currentPos);
            }
        });
    }

    private void setDataToViews(int currentPos){
        radioGroupQuestions.clearCheck();

        if(currentPos > 0)
        {
            previous.setEnabled(true);
        }
        else
        {
            previous.setEnabled(false);
        }
        if(questionAttempted == 15 || currentQuestion == questionArrayList.size())
        {
            next.setEnabled(false);
            //currentQuestion = questionAttempted+1;
            questionNumberTV.setText("Question: " + currentQuestion + "/" + 15);
            if(questionAttempted == 15)
            {
                questionNumberTV.setText("Question: " + questionAttempted + "/" + 15);
                showScore();

            }
            else
            {
                previous.setEnabled(true);

            }
        }
        else
        {

            currentQuestion = currentPos+1;

            if(currentQuestion < 15)
            {
                next.setEnabled(true);
            }
            else {
                previous.setEnabled(true);
                next.setEnabled(false);
            }
            questionNumberTV.setText("Question: " + currentQuestion + "/" + 15);
            questionTV.setText(questionArrayList.get(currentPos).getQuestionText());

            button1.setText(questionArrayList.get(currentPos).getPossibleAnswer1());
            button2.setText(questionArrayList.get(currentPos).getPossibleAnswer2());
            button3.setText(questionArrayList.get(currentPos).getPossibleAnswer3());
            button4.setText(questionArrayList.get(currentPos).getPossibleAnswer4());
            if(currentQuestion+1<questionArrayList.size())
            {
                if(questionArrayList.get(currentQuestion).getUserChoice()>0)
                {
                    if(questionArrayList.get(currentQuestion).getUserChoice()==1)
                    {
                        button1.setChecked(true);
                    }
                    else if(questionArrayList.get(currentQuestion).getUserChoice()==2)
                    {
                        button2.setChecked(true);
                    }
                    else if(questionArrayList.get(currentQuestion).getUserChoice()==3)
                    {
                        button3.setChecked(true);
                    }
                    else if(questionArrayList.get(currentQuestion).getUserChoice()==4)
                    {
                        button4.setChecked(true);
                    }
                }
            }

        }

    }

    private void createQuestionsB2(ArrayList<Question> questionArrayList) {
        questionArrayList.add(new Question(getString(R.string.question_1),"to live",
                "to have lived", "to be lived", "to be living",
                "to have lived",0,2,1,1));
        questionArrayList.add(new Question(getString(R.string.question_2),"on account of",
                "due", "because", "owing",
                "on account of",0,1,1,2));
        questionArrayList.add(new Question(getString(R.string.question_3),
                "not having said", "have never said", "never said", "had never said",
                "have never said",0,4,1,3));
        questionArrayList.add(new Question(getString(R.string.question_4),"to be abducted",
                "to be abducting", "to have been abducted", "to have been abducting",
                "to have been abducted",0,3,1,4));
        questionArrayList.add(new Question(getString(R.string.question_5),"herself",
                "her", "her own", "hers",
                "her",0,2,1,5));
        questionArrayList.add(new Question(getString(R.string.question_6),"Not wanting",
                "As not wanting", "She didn't want", "Because not wanting",
                "Not wanting",0,1,1,6));
        questionArrayList.add(new Question(getString(R.string.question_7),"There's no point",
                "It's no point", "There isn't point", "It's no need",
                "There's no point",0,1,1,7));
        questionArrayList.add(new Question(getString(R.string.question_8),"had written",
                "has written", "had been writing", "wrote",
                "had been writing",0,3,1,8));
        questionArrayList.add(new Question(getString(R.string.question_9),"had",
                "did", "got", "were",
                "got",0,3,1,9));
        questionArrayList.add(new Question(getString(R.string.question_10),"In no way was he",
                "No way he was ", "In any way he was", "In any way was he",
                "In no way was he",0,1,1,10));
        questionArrayList.add(new Question(getString(R.string.question_11),"may not have been",
                "may not be", "might not be", "must not have been",
                "may not have been",0,1,1,11));
        questionArrayList.add(new Question(getString(R.string.question_12),"were made sleeping",
                "were made sleep", "were made to sleep", "made to sleep",
                "were made to sleep",0,3,1,12));
        questionArrayList.add(new Question(getString(R.string.question_13),"if he sent",
                "had he sent", "if he has sent", "did he sent",
                "had he sent",0,2,1,13));
        questionArrayList.add(new Question(getString(R.string.question_14),"is to be achieved",
                "is achieved", "will be achieved", "is due to achieve",
                "is to be achieved",0,1,1,14));
        questionArrayList.add(new Question(getString(R.string.question_15),"It's not allowed offering",
                "It's not permitted to offer", "It's not permitted offering", "It's not allowed to offer",
                "It's not permitted to offer",0,2,1,15));
    }

    private void createQuestionsB1 (ArrayList<Question> questionArrayList) {
        questionArrayList.add(new Question(getString(R.string.question_2_1), "they have travelled",
                "have they travelled to", "they have travelled to", "have they travelled",
                "they have travelled to", 0, 3,0,1));
        questionArrayList.add(new Question(getString(R.string.question_2_2), "I do",
                "I like", "Do i", "I am",
                "I do", 0, 1,0,2));
        questionArrayList.add(new Question(getString(R.string.question_2_3),
                "Have you been seeing / have looked", "Have you seen / 've been looking", "Have you been seen / have been looking",
                "Have you seing / 've looked", "Have you seen / 've been looking", 0, 2,0,3));
        questionArrayList.add(new Question(getString(R.string.question_2_4), "the Japanese",
                "the Japanese people", "the Japaneses", "Japaneses",
                "the Japanese", 0, 1,0,4));
        questionArrayList.add(new Question(getString(R.string.question_2_5), "so",
                "such", "such a", "so much",
                "such", 0, 2,0,5));
        questionArrayList.add(new Question(getString(R.string.question_2_6), "will read",
                "am going to read", "will be reading", "will have read",
                "will have read", 0, 4,0,6));
        questionArrayList.add(new Question(getString(R.string.question_2_7), "wouldn't be",
                "wouldn't have been", "isn't", "weren't",
                "weren't", 0, 4,0,7));
        questionArrayList.add(new Question(getString(R.string.question_2_8), "could",
                "would", "had", "will",
                "could", 0, 1,0,8));
        questionArrayList.add(new Question(getString(R.string.question_2_9), "disappointing",
                "disappointed", "disappoint", "disappointingly",
                "disappointing", 0, 1,0,9));
        questionArrayList.add(new Question(getString(R.string.question_2_10), "get married",
                "to get married ", "having got married", "to have got married",
                "having got married", 0, 3,0,10));
        questionArrayList.add(new Question(getString(R.string.question_2_11), "shouldn't have gone",
                "should have gone", "must have gone", "can’t have gone",
                "should have gone", 0, 2,0,11));
        questionArrayList.add(new Question(getString(R.string.question_2_12), "you waited",
                "you wait", " you to wait", "you waiting",
                "you waited", 0, 1,0,12));
        questionArrayList.add(new Question(getString(R.string.question_2_13), "are thought that they",
                "it’s thought that they", "are thought to", "are thought that",
                "are thought to", 0, 3,0,13));
        questionArrayList.add(new Question(getString(R.string.question_2_14), "test my blood pressure",
                "have my blood pressure tested", "have tested my blood pressure", "get to test my blood pressure",
                "have my blood pressure tested", 0, 2,0,14));
        questionArrayList.add(new Question(getString(R.string.question_2_15), "Despite of",
                "Although", "In spite of", "However",
                "In spite of", 0, 3,0,15));
    }


    private void showScore(){
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(TestActivity.this);
        View bottomView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.score_layout,
                (LinearLayout)findViewById(R.id.scoreLayout));
        TextView scoreTV = bottomView.findViewById(R.id.text_view_score);
        Button closeBtn = bottomView.findViewById(R.id.closeBtn);
        
        String scoreMsg = "" ;
        //for corrected calculation
        //calculate score double value for % view
        scoreDouble = (currentScore * 100 / 15);
        //set score msg to end the test and show to user
        scoreMsg = scoreTV.getText() + "\n" + currentScore +"/"+ 15 + "\n" + scoreDouble + "%";
        scoreTV.setText(scoreMsg);
      
        //todo hold current user score with uid, level, date, score, correct and wrong questions list
        testResult = new UserTestResult(questionArrayList, String.valueOf(currentScore), scoreDouble,
                mainViewModel.getUsername(), correctQuestions, wrongQuestions,null, testLevel);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String dateNow = dateFormat.format(date).toString();
        //Adding values
        testResult.setDate(date);
        testResult.setUid(FirebaseAuth.getInstance().getCurrentUser()!=null?
                FirebaseAuth.getInstance().getCurrentUser().getUid():"");


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //restart values
                setDataToViews(currentPos);
                bottomSheetDialog.dismiss();

                mainViewModel.uploadUserScore(testResult,testLevel,scoreDouble);
                //finish();
            }
        });
        currentPos = 0;
        questionAttempted = 0;
        currentScore = 0;
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(bottomView);
        bottomSheetDialog.show();
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        builder.setMessage(getString(R.string.exitTest))
                .setCancelable(true)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                        dialog.dismiss();
                        finish();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                //do things
                dialog.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
                builder.setMessage(getString(R.string.exitTest))
                        .setCancelable(true)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                dialog.dismiss();
                                finish();
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //do things
                                dialog.dismiss();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
                break;
        }
        return true;
    }
}
