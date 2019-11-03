package com.example.example;

import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;


import android.os.Bundle;

public class Example1 extends AppCompatActivity {

    private LinearLayout llSingleChoice;
    private LinearLayout llMultipleChoice;
    private LinearLayout llJudgement;

    private TextView tvStem;

    private RadioGroup rgSingleChoice;
    private RadioButton rbSingleA;
    private RadioButton rbSingleB;
    private RadioButton rbSingleC;
    private RadioButton rbSingleD;

    private RadioGroup rgJudgement;
    private RadioButton rbJudgementT;
    private RadioButton rbJudgementF;

    private CheckBox cbMultipleA;
    private CheckBox cbMultipleB;
    private CheckBox cbMultipleC;
    private CheckBox cbMultipleD;

    private ArrayList<Question> questions;

    private int questionIndex;

    private String[] answers;

    private String answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 利用布局资源文件设置用户界面
        setContentView(R.layout.activity_example1);

        // 通过资源标识获得控件实例
        llSingleChoice = findViewById(R.id.ll_single_choice);
        llMultipleChoice = findViewById(R.id.ll_multiple_choice);
        llJudgement = findViewById(R.id.ll_judgement);

        tvStem = findViewById(R.id.tv_stem);
        rgSingleChoice = findViewById(R.id.rg_single_choice);
        rbSingleA = findViewById(R.id.rb_single_a);
        rbSingleB = findViewById(R.id.rb_single_b);
        rbSingleC = findViewById(R.id.rb_single_c);
        rbSingleD = findViewById(R.id.rb_single_d);
        rgJudgement = findViewById(R.id.rg_judgement);
        rbJudgementT = findViewById(R.id.rb_judgement_t);
        rbJudgementF = findViewById(R.id.rb_judgement_f);
        cbMultipleA = findViewById(R.id.cb_multiple_a);
        cbMultipleB = findViewById(R.id.cb_multiple_b);
        cbMultipleC = findViewById(R.id.cb_multiple_c);
        cbMultipleD = findViewById(R.id.cb_multiple_d);

        // 初始化试题库
        questions = getQuestions();

        // 实例化答案数组
        answers = new String[questions.size()];


        //Log.i("test", String.valueOf(questions.size()));
        // 取出第1题
        Question question = questions.get(questionIndex);
        // 根据题型设置界面
        setInterfaceByType(question);
    }

    private ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();

        // 创建第1题
        Question question = new Question();
        question.setId(1);
        question.setStem("驾驶机动车在道路上违反道路交通安全法的行为，属于什么行为？");
        question.setA("违章行为");
        question.setB("违法行为");
        question.setC("过失行为");
        question.setD("违规行为");
        question.setType(1);
        question.setAnswer("B");
        // 将第1题添加到试题库
        questions.add(question);

        // 创建第2题
        question = new Question();
        question.setId(2);
        question.setStem("交通信号包括交通信号灯、交通标志、交通标线和交通警察的指挥。");
        question.setA("正确");
        question.setB("错误");
        question.setC("");
        question.setD("");
        question.setType(3);
        question.setAnswer("A");
        // 将第2题添加到试题库
        questions.add(question);


        // 创建第3题
        question = new Question();
        question.setId(3);
        question.setStem("机动车驾驶人违法驾驶造成重大交通事故构成犯罪的，依法追究什么责任？");
        question.setA("刑事责任");
        question.setB("民事责任");
        question.setC("经济责任");
        question.setD("直接责任");
        question.setType(1);
        question.setAnswer("A");
        // 将第3题添加到试题库
        questions.add(question);

        // 创建第4题
        question = new Question();
        question.setId(4);
        question.setStem("钱某驾驶大型卧铺客车，乘载45人（核载40人），保持40公里/小时以上的车速行至八宿县境内连续下坡急转弯路段处，翻下100米深的山崖，造成17人死亡、20人受伤。钱某的主要违法行为是什么？");
        question.setA("驾驶时接听手持电话");
        question.setB("超速行驶");
        question.setC("客车超员");
        question.setD("疲劳驾驶");
        question.setType(2);
        question.setAnswer("BC");
        // 将第4题添加到试题库
        questions.add(question);

        // 创建第5题
        question = new Question();
        question.setId(5);
        question.setStem("邹某驾驶大型卧铺客车（核载35人，实载47人），行至京港澳高速公路938公里时，因乘车人携带的大量危险化学品在车厢内突然发生爆燃，造成41人死亡，6人受伤。此事故中的主要违法行为是什么？");
        question.setA("客车超员");
        question.setB("乘车人携带易燃易爆危险物品");
        question.setC("超速行驶");
        question.setD("不按规定停车");
        question.setType(2);
        question.setAnswer("AB");
        // 将第5题添加到试题库
        questions.add(question);

        return questions;
    }

    /**
     * 根据题型设置界面
     *
     * @param question
     */
    private void setInterfaceByType(Question question) {
        // 判断题型，选择界面
        switch (question.getType()) {
            case 1:
                llSingleChoice.setVisibility(View.VISIBLE);
                llMultipleChoice.setVisibility(View.INVISIBLE);
                llJudgement.setVisibility(View.INVISIBLE);
                tvStem.setText("[单选] " + question.getId() + ". " + question.getStem());
                tvStem.setTextColor(Color.RED);
                rbSingleA.setText(question.getA());
                rbSingleB.setText(question.getB());
                rbSingleC.setText(question.getC());
                rbSingleD.setText(question.getD());

                // 清空控件选中状态
                rbSingleA.setChecked(false);
                rbSingleB.setChecked(false);
                rbSingleC.setChecked(false);
                rbSingleD.setChecked(false);
                rgSingleChoice.check(-1);

                answer = answers[questionIndex];
                if (answer != null) {
                    // 根据用户答案来设定单选按钮状态
                    switch (answer) {
                        case "A":
                            rbSingleA.setChecked(true);
                            break;
                        case "B":
                            rbSingleB.setChecked(true);
                            break;
                        case "C":
                            rbSingleC.setChecked(true);
                            break;
                        case "D":
                            rbSingleD.setChecked(true);
                            break;
                    }
                }
                break;
            case 2:
                llMultipleChoice.setVisibility(View.VISIBLE);
                llSingleChoice.setVisibility(View.INVISIBLE);
                llJudgement.setVisibility(View.INVISIBLE);
                tvStem.setText("[多选] " + question.getId() + ". " + question.getStem());
                tvStem.setTextColor(Color.BLACK);
                cbMultipleA.setText(question.getA());
                cbMultipleB.setText(question.getB());
                cbMultipleC.setText(question.getC());
                cbMultipleD.setText(question.getD());

                // 清空控件选中状态
                cbMultipleA.setChecked(false);
                cbMultipleB.setChecked(false);
                cbMultipleC.setChecked(false);
                cbMultipleD.setChecked(false);

                answer = answers[questionIndex];
                if (answer != null) {
                    for (int i = 0; i < answer.length(); i++) {
                        switch (answer.charAt(i)) {
                            case 'A':
                                cbMultipleA.setChecked(true);
                                break;
                            case 'B':
                                cbMultipleB.setChecked(true);
                                break;
                            case 'C':
                                cbMultipleC.setChecked(true);
                                break;
                            case 'D':
                                cbMultipleD.setChecked(true);
                                break;
                        }
                    }
                }

                break;
            case 3:
                llJudgement.setVisibility(View.VISIBLE);
                llSingleChoice.setVisibility(View.INVISIBLE);
                llMultipleChoice.setVisibility(View.INVISIBLE);
                tvStem.setText("[判断] " + question.getId() + ". " + question.getStem());
                tvStem.setTextColor(Color.BLUE);

                // 清空控件选中状态
                rbJudgementT.setChecked(false);
                rbJudgementF.setChecked(false);
                rgJudgement.check(-1);

                // 根据用户答案来设定单选按钮状态
                answer = answers[questionIndex];
                if (answer != null) {
                    switch (answer) {
                        case "A":
                            rbJudgementT.setChecked(true);
                            break;
                        case "B":
                            rbJudgementF.setChecked(true);
                            break;
                    }
                }
        }
    }


    /**
     * 退出
     *
     * @param view
     */

    public void doExit(View view) {
        finish();
    }

    /**
     * 上一题
     *
     * @param view
     */
    public void doPrevious(View view) {
        // 判断用户是否做了本题
        switch (questions.get(questionIndex).getType()) {
            case 1:
                if (rbSingleA.isChecked() || rbSingleB.isChecked() || rbSingleC.isChecked() || rbSingleD.isChecked()) {
                    saveAnswer();
                }
                break;
            case 2:
                if (cbMultipleA.isChecked() || cbMultipleB.isChecked() || cbMultipleC.isChecked() || cbMultipleD.isChecked()) {
                    saveAnswer();
                }
                break;
            case 3:
                if (rbJudgementT.isChecked() || rbJudgementF.isChecked()) {
                    saveAnswer();
                }
                break;
        }

        if (questionIndex > 0) {
            questionIndex--;
        } else {
            questionIndex = questions.size() - 1;
        }

        // 根据题型设置界面
        setInterfaceByType(questions.get(questionIndex));

    }

    /**
     * 下一题
     *
     * @param view
     */
    public void doNext(View view) {
        // 判断用户是否做了本题
        switch (questions.get(questionIndex).getType()) {
            case 1:
                if (rbSingleA.isChecked() || rbSingleB.isChecked() || rbSingleC.isChecked() || rbSingleD.isChecked()) {
                    saveAnswer();
                }
                break;
            case 2:
                if (cbMultipleA.isChecked() || cbMultipleB.isChecked() || cbMultipleC.isChecked() || cbMultipleD.isChecked()) {
                    saveAnswer();
                }
                break;
            case 3:
                if (rbJudgementT.isChecked() || rbJudgementF.isChecked()) {
                    saveAnswer();
                }
                break;
        }


        Log.i("test", (questionIndex + 1) + answers[questionIndex]);

        if (questionIndex < questions.size() - 1) {
            questionIndex++;
        } else {
            questionIndex = 0;
        }
        // 根据题型设置界面
        setInterfaceByType(questions.get(questionIndex));

        //Log.i("test","questionIndex原本值"+questionIndex);
        //Log.i("test","question中的id值"+questions.get(questionIndex).getId());
    }

    /**
     * 提交按钮单击事件处理方法
     *
     * @param view
     */
    public void doSubmit(View view) {
        // 保存当前题目答案
        saveAnswer();

        // 计算成绩点
        int points = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (null != answers[i]) {
                if (answers[i].equals(questions.get(i).getAnswer())) {
                    points++;
                }
            }
        }

        Log.i("test","次数="+points);

        // 按百分制来折算分数
        double score = points * 100 / questions.size();

        // 根据分数给出评语
        String comment = "";
        if (score >= 90) {
            comment = "分数：" + score + "\n评语：棒棒哒~";
        } else if (score >= 60) {
            comment = "分数：" + score + "\n评语：还行吧！";
        } else {
            comment = "分数：" + score + "\n评语：好好干吧！";
        }

        // 利用警告对话框来显示测试结果
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher_round)
                .setTitle("测试结果")
                .setMessage(comment)
                .setPositiveButton("确定", null)
                .create()
                .show();
    }

    /**
     * 保存用户选择的答案
     */
    private void saveAnswer() {
        switch (questions.get(questionIndex).getType()) {
            case 1: // 单选题
                if (rbSingleA.isChecked()) {
                    answers[questionIndex] = "A";
                } else if (rbSingleB.isChecked()) {
                    answers[questionIndex] = "B";
                } else if (rbSingleC.isChecked()) {
                    answers[questionIndex] = "C";
                } else {
                    answers[questionIndex] = "D";
                }
                break;
            case 2: // 多选题
                StringBuilder builder = new StringBuilder();
                if (cbMultipleA.isChecked()) {
                    builder.append("A");
                }
                if (cbMultipleB.isChecked()) {
                    builder.append("B");
                }
                if (cbMultipleC.isChecked()) {
                    builder.append("C");
                }
                if (cbMultipleD.isChecked()) {
                    builder.append("D");
                }
                answers[questionIndex] = builder.toString();
                break;
            case 3: // 判断题
                if (rbJudgementT.isChecked()) {
                    answers[questionIndex] = "A";
                } else {
                    answers[questionIndex] = "B";
                }
                break;
        }
    }

}