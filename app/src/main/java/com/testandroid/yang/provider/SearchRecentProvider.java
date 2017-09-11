package com.testandroid.yang.provider;

import android.content.SearchRecentSuggestionsProvider;

/**
 * SearchRecentProvider
 * Created by yangjiajia on 2017/9/11.
 */

public class SearchRecentProvider extends SearchRecentSuggestionsProvider {
    public final static String AUTHORITY = "com.yang.SearchRecentProvider";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public SearchRecentProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
