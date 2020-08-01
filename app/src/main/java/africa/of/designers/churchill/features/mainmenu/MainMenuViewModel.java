package africa.of.designers.churchill.features.mainmenu;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;


import java.util.List;

import africa.of.designers.churchill.data.GameThemeRepository;
import africa.of.designers.churchill.model.GameTheme;

public class MainMenuViewModel extends ViewModel {

    private GameThemeRepository mGameThemeRepository;

    private MutableLiveData<List<GameTheme>> mOnGameThemeLoaded;

    public MainMenuViewModel(GameThemeRepository gameThemeRepository) {
        mGameThemeRepository = gameThemeRepository;
        mOnGameThemeLoaded = new MutableLiveData<>();
    }

    public void loadData() {
        mOnGameThemeLoaded.setValue(mGameThemeRepository.getGameThemes());
    }

    public LiveData<List<GameTheme>> getOnGameThemeLoaded() {
        return mOnGameThemeLoaded;
    }
}
