// Generated by Dagger (https://dagger.dev).
package com.example.bisky.ui.screen.searchpage.quicksearch.mapper;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class QuickSearchAnimeMapper_Factory implements Factory<QuickSearchAnimeMapper> {
  @Override
  public QuickSearchAnimeMapper get() {
    return newInstance();
  }

  public static QuickSearchAnimeMapper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static QuickSearchAnimeMapper newInstance() {
    return new QuickSearchAnimeMapper();
  }

  private static final class InstanceHolder {
    private static final QuickSearchAnimeMapper_Factory INSTANCE = new QuickSearchAnimeMapper_Factory();
  }
}