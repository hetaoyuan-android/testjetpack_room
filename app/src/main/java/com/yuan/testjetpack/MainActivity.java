package com.yuan.testjetpack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordDataBase wordDataBase;
    private Button button1, button2, button3,button4;
    private TextView textView;
    private WordViewModel wordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);
        textView = findViewById(R.id.textView);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        wordDataBase = WordDataBase.getDatabse(getApplicationContext());
        wordViewModel.getAllWOrdsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                String text = "";
                for (int i = 0; i < words.size();i++) {
                    Word word = words.get(i);
                    text += word.getId() + ":" + word.getWord() + "=" + word.getChineseMeaning() + "\n";
                }
                textView.setText(text);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("hello", "你好");
                Word word1 = new Word("world", "世界");
                wordViewModel.insertWords(word,word1);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordViewModel.deleteAllWords();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("hi", "您好啊");
                word.setId(94);
                wordViewModel.updateWords(word);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("hi", "您好啊");
                word.setId(10);
                wordViewModel.deleteWords(word);
            }
        });
    }


}
