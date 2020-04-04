package com.yuan.testjetpack;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private final WordDao wordDao;
    private LiveData<List<Word>> allWordsLive;

    public WordRepository(Context context) {
        WordDataBase wordDataBase = WordDataBase.getDatabse(context.getApplicationContext());
        wordDao = wordDataBase.getWordDao();
        allWordsLive =wordDao.getAllWordsLive();
    }

    public LiveData<List<Word>> getAllWordsLive() {
        return allWordsLive;
    }

    public static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public InsertAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insertWords(words);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public UpdateAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.updateWords(words);
            return null;
        }
    }

    static class DeteleAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao wordDao;

        public DeteleAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.deleteWords(words);
            return null;
        }
    }

    static class DeteleAllAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao wordDao;

        public DeteleAllAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAllWords();
            return null;
        }
    }

    void insertWords(Word...words) {
        new InsertAsyncTask(wordDao).execute(words);
    }

    void updateWords(Word...words) {
        new UpdateAsyncTask(wordDao).execute(words);
    }

    void deleteWords(Word...words) {
        new DeteleAsyncTask(wordDao).execute(words);
    }

    void deleteAllWords() {
        new DeteleAllAsyncTask(wordDao).execute();
    }

}
