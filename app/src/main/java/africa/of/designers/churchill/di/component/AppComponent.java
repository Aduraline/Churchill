package africa.of.designers.churchill.di.component;


import javax.inject.Singleton;

import africa.of.designers.churchill.PracticeActivity;
import africa.of.designers.churchill.di.modules.AppModule;
import africa.of.designers.churchill.di.modules.DataSourceModule;
import africa.of.designers.churchill.features.FullscreenActivity;
import africa.of.designers.churchill.features.gamehistory.GameHistoryActivity;
import africa.of.designers.churchill.features.gameover.GameOverActivity;
import africa.of.designers.churchill.features.gameplay.GamePlayActivity;
import africa.of.designers.churchill.features.mainmenu.MainMenuActivity;
import dagger.Component;


/**
 * Created by abdularis on 18/07/17.
 */

@Singleton
@Component(modules = {AppModule.class, DataSourceModule.class})
public interface AppComponent {

    void inject(PracticeActivity activity);

    void inject(GamePlayActivity activity);

    void inject(MainMenuActivity activity);

    void inject(GameOverActivity activity);

    void inject(FullscreenActivity activity);

    void inject(GameHistoryActivity activity);

}
