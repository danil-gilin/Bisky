// Generated by Dagger (https://dagger.dev).
package com.example.bisky.ui.screen.searchpage.quicksearch.mapper;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class QuickSearchAnimeMapper_Factory implements Factory<QuickSearchAnimeMapper> {
  private final Provider<Context> contextProvider;

  public QuickSearchAnimeMapper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public QuickSearchAnimeMapper get() {
    return newInstance(contextProvider.get());
  }

  public static QuickSearchAnimeMapper_Factory create(Provider<Context> contextProvider) {
    return new QuickSearchAnimeMapper_Factory(contextProvider);
  }

  public static QuickSearchAnimeMapper newInstance(Context context) {
    return new QuickSearchAnimeMapper(context);
  }
}
