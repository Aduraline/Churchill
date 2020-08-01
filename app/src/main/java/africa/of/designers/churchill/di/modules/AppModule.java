package africa.of.designers.churchill.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


import javax.inject.Singleton;

import africa.of.designers.churchill.data.GameDataSource;
import africa.of.designers.churchill.data.GameThemeRepository;
import africa.of.designers.churchill.data.WordDataSource;
import africa.of.designers.churchill.features.ViewModelFactory;
import africa.of.designers.churchill.features.gamehistory.GameHistoryViewModel;
import africa.of.designers.churchill.features.gameover.GameOverViewModel;
import africa.of.designers.churchill.features.gameplay.GamePlayViewModel;
import africa.of.designers.churchill.features.mainmenu.MainMenuViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * Created by abdularis on 18/07/17.
 */

@Module
public class AppModule {

    private Application mApp;

    public AppModule(Application application) {
        mApp = application;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mApp;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    ViewModelFactory provideViewModelFactory(GameDataSource gameDataSource,
                                             WordDataSource wordDataSource) {
        return new ViewModelFactory(
                new GameOverViewModel(gameDataSource),
                new GamePlayViewModel(gameDataSource, wordDataSource),
                new MainMenuViewModel(new GameThemeRepository()),
                new GameHistoryViewModel(gameDataSource)
        );
    }
}
