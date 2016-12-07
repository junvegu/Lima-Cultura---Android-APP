package com.bixspace.livesculture.data.repository.source;

import android.support.annotation.NonNull;

import com.bixspace.livesculture.data.model.MainResponse;

import java.util.List;

/**
 * Created by junior on 27/08/16.
 */
public interface MainDataSource {

    interface LoadMainCallback {

        void onMainModelLoaded(List<MainResponse> tasks);

        void onDataNotAvailable();
    }

    interface GetMainModelCallback {

        void onMainModelLoaded(MainResponse mainModel);

        void onDataNotAvailable();
    }

    void getMainModel(@NonNull LoadMainCallback callback);

    void getMainModel(@NonNull String id, @NonNull GetMainModelCallback callback);

    void saveMainModel(@NonNull MainResponse mainModel);

    void refreshMainModel();

    void deleteAllMainModel();

    void deleteMainModel(@NonNull String id);
}
