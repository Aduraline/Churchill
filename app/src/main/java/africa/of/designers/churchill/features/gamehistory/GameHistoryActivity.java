package africa.of.designers.churchill.features.gamehistory;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import java.util.List;

import javax.inject.Inject;

import africa.of.designers.churchill.R;
import africa.of.designers.churchill.WordSearchApp;
import africa.of.designers.churchill.easyadapter.MultiTypeAdapter;
import africa.of.designers.churchill.features.FullscreenActivity;
import africa.of.designers.churchill.features.ViewModelFactory;
import africa.of.designers.churchill.features.gameplay.GamePlayActivity;
import africa.of.designers.churchill.model.GameDataInfo;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameHistoryActivity extends FullscreenActivity {

    @BindView(R.id.textEmpty)
    TextView mTextEmpty;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Inject
    ViewModelFactory mViewModelFactory;
    private GameHistoryViewModel mViewModel;

    private MultiTypeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_history);

        ((WordSearchApp) getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        initRecyclerView();

        mViewModel = ViewModelProviders.of(this, mViewModelFactory).get(GameHistoryViewModel.class);
        mViewModel.getOnGameDataInfoLoaded().observe(this, this::onGameDataInfoLoaded);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mViewModel.loadGameHistory();
    }

    @OnClick(R.id.btnClear)
    public void onButtonClearClick() {
        mViewModel.clear();
    }

    private void onGameDataInfoLoaded(List<GameDataInfo> gameDataInfos) {
        if (gameDataInfos.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            mTextEmpty.setVisibility(View.VISIBLE);
        } else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mTextEmpty.setVisibility(View.GONE);
            mAdapter.setItems(gameDataInfos);
        }
    }

    private void initRecyclerView() {
        GameDataAdapterDelegate gameDataAdapterDelegate = new GameDataAdapterDelegate();
        gameDataAdapterDelegate.setOnClickListener(new GameDataAdapterDelegate.OnClickListener() {
            @Override
            public void onClick(GameDataInfo gameDataInfo) {
                Intent intent = new Intent(GameHistoryActivity.this, GamePlayActivity.class);
                intent.putExtra(GamePlayActivity.EXTRA_GAME_ROUND_ID, gameDataInfo.getId());
                startActivity(intent);
            }

            @Override
            public void onDeleteClick(GameDataInfo gameDataInfo) {
                mViewModel.deleteGameData(gameDataInfo);
            }
        });

        mAdapter = new MultiTypeAdapter();
        mAdapter.addDelegate(gameDataAdapterDelegate);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
