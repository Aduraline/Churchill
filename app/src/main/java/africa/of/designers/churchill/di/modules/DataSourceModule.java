package africa.of.designers.churchill.di.modules;

import android.content.Context;


import javax.inject.Singleton;

import africa.of.designers.churchill.data.GameDataSource;
import africa.of.designers.churchill.data.WordDataSource;
import africa.of.designers.churchill.data.sqlite.DbHelper;
import africa.of.designers.churchill.data.sqlite.GameDataSQLiteDataSource;
import africa.of.designers.churchill.data.xml.WordXmlDataSource;
import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class DataSourceModule {

    @Provides
    @Singleton
    DbHelper provideDbHelper(Context context) {
        return new DbHelper(context);
    }

    @Provides
    @Singleton
    GameDataSource provideGameRoundDataSource(DbHelper dbHelper) {
        return new GameDataSQLiteDataSource(dbHelper);
    }

//    @Provides
//    @Singleton
//    WordDataSource provideWordDataSource(DbHelper dbHelper) {
//        return new WordSQLiteDataSource(dbHelper);
//    }

    @Provides
    @Singleton
    WordDataSource provideWordDataSource(Context context) {
        return new WordXmlDataSource(context);
    }

}
