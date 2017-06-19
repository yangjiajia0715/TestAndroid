package com.testandroid.yang.db;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.provider.SearchRecentSuggestions;

/**
 * SearchRecentSuggestionsProviderDemo
 * Created by yangjiajia on 2017/6/18 0018.
 */

public class SearchRecentSuggestionsProviderDemo extends SearchRecentSuggestions {
    /**
     * Although provider utility classes are typically static, this one must be constructed
     * because it needs to be initialized using the same values that you provided in your
     * {@link SearchRecentSuggestionsProvider}.
     *
     * @param context
     * @param authority This must match the authority that you've declared in your manifest.
     * @param mode      You can use mode flags here to determine certain functional aspects of your
     *                  database.  Note, this value should not change from run to run, because when it does change,
     *                  your suggestions database may be wiped.
     * @see SearchRecentSuggestionsProvider
     * @see SearchRecentSuggestionsProvider#setupSuggestions
     */
    public SearchRecentSuggestionsProviderDemo(Context context, String authority, int mode) {
        super(context, authority, mode);
    }
}
