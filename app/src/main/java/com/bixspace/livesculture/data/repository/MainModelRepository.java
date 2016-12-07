/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bixspace.livesculture.data.repository;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bixspace.livesculture.data.model.MainResponse;
import com.bixspace.livesculture.data.repository.source.MainDataSource;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation to load tasks from the data sources into a cache.
 * <p>
 * For simplicity, this implements a dumb synchronisation between locally persisted data and data
 * obtained from the server, by using the remote data source only if the local database doesn't
 * exist or is empty.
 */
public class MainModelRepository implements MainDataSource {

    private static MainModelRepository INSTANCE = null;

    private final MainDataSource mainRemoteDataSource;

    private final MainDataSource mainLocalDotaSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, MainResponse> mCachedTasks;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    // Prevent direct instantiation.
    private MainModelRepository(@NonNull MainDataSource tasksRemoteDataSource,
                            @NonNull MainDataSource tasksLocalDataSource) {
        mainRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mainLocalDotaSource = checkNotNull(tasksLocalDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link MainModelRepository} instance
     */
    public static MainModelRepository getInstance(MainDataSource tasksRemoteDataSource,
                                              MainDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new MainModelRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }



    private void getTasksFromRemoteDataSource(@NonNull final LoadMainCallback callback) {
        mainRemoteDataSource.getMainModel(new LoadMainCallback() {
            @Override
            public void onMainModelLoaded(List<MainResponse> tasks) {
                refreshCache(tasks);
                refreshLocalDataSource(tasks);
                callback.onMainModelLoaded(new ArrayList<>(mCachedTasks.values()));
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    private void refreshCache(List<MainResponse> models) {
        if (mCachedTasks == null) {
            mCachedTasks = new LinkedHashMap<>();
        }
        mCachedTasks.clear();
        for (MainResponse model : models) {
           // mCachedTasks.put(model.getId(), model);
        }
        mCacheIsDirty = false;
    }

    private void refreshLocalDataSource(List<MainResponse> models) {
        mainLocalDotaSource.deleteAllMainModel();
        for (MainResponse model : models) {
            mainLocalDotaSource.saveMainModel(model);
        }
    }

    @Nullable
    private MainResponse getMainWithId(@NonNull String id) {
        checkNotNull(id);
        if (mCachedTasks == null || mCachedTasks.isEmpty()) {
            return null;
        } else {
            return mCachedTasks.get(id);
        }
    }

    @Override
    public void getMainModel(@NonNull LoadMainCallback callback) {

    }

    @Override
    public void getMainModel(@NonNull String id, @NonNull GetMainModelCallback callback) {

    }

    @Override
    public void saveMainModel(@NonNull MainResponse mainModel) {

    }

    @Override
    public void refreshMainModel() {

    }

    @Override
    public void deleteAllMainModel() {

    }

    @Override
    public void deleteMainModel(@NonNull String id) {

    }
}
