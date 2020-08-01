package africa.of.designers.churchill.data;


import java.util.List;

import africa.of.designers.churchill.data.entity.GameDataEntity;
import africa.of.designers.churchill.model.GameDataInfo;
import africa.of.designers.churchill.model.UsedWord;

/**
 * Created by abdularis on 18/07/17.
 */

public interface GameDataSource {

    void markWordAsAnswered(UsedWord usedWord);

    long saveGameData(GameDataEntity gameDataEntity);

    interface GameRoundCallback {

        void onLoaded(GameDataEntity gameRound);

    }

    interface InfosCallback {

        void onLoaded(List<GameDataInfo> infoList);
    }

    interface StatCallback {

        void onLoaded(GameDataInfo gameDataInfo);

    }

    void getGameData(int gid, GameRoundCallback callback);

    void getGameDataInfos(InfosCallback callback);

    void getGameDataInfo(int gid, StatCallback callback);


    void deleteGameData(int gid);

    void deleteGameDatas();

    void saveGameDataDuration(int gid, int newDuration);
}
