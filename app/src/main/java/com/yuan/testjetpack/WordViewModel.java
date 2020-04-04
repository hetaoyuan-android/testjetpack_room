package com.yuan.testjetpack;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordDao wordDao;
    private LiveData<List<Word>> allWOrdsLive;
    private WordRepository wordRepository;

    public LiveData<List<Word>> getAllWOrdsLive() {
        return wordRepository.getAllWordsLive();
    }

    public WordViewModel(@NonNull Application application) {
        super(application);
        WordDataBase wordDataBase = WordDataBase.getDatabse(application);
        wordRepository = new WordRepository(application);
    }

    void insertWords(Word...words) {
        wordRepository.insertWords(words);
    }

    void updateWords(Word...words) {
        wordRepository.updateWords(words);
    }

    void deleteWords(Word...words) {
        wordRepository.deleteWords(words);
    }

    void deleteAllWords() {
        wordRepository.deleteAllWords();
    }

}
