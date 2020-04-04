package com.yuan.testjetpack;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Word.class}, version = 2, exportSchema = false)
public abstract class WordDataBase extends RoomDatabase {

    private static WordDataBase INSTANCE;
    private static Migration MIGRATION_2_3 = new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("Alter table word add column foo_data integer not null default 1");
        }
    };
    public static WordDataBase getDatabse(Context context) {
        if (INSTANCE == null) {
            synchronized (WordDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), WordDataBase.class, "word_database")
                            .addMigrations(MIGRATION_2_3)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    public abstract WordDao getWordDao();
}
