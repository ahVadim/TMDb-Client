package com.example.core.di

import android.content.Context
import com.example.core.consts.TinkConsts
import com.example.core.prefs.UserPrefs
import com.google.crypto.tink.Aead
import com.google.crypto.tink.DeterministicAead
import com.google.crypto.tink.aead.AeadKeyTemplates
import com.google.crypto.tink.daead.DeterministicAeadKeyTemplates
import com.google.crypto.tink.integration.android.AndroidKeysetManager
import dagger.Module
import dagger.Provides

@Module
class SecurePrefsModule {

    @Provides
    @AppScope
    fun provideAead(context: Context): Aead {
        val keysetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(context, TinkConsts.KEYSET_NAME, TinkConsts.PREFERENCE_FILE)
            .withKeyTemplate(AeadKeyTemplates.AES256_GCM)
            .withMasterKeyUri(TinkConsts.MASTER_KEY_URI)
            .build()
            .keysetHandle

        return keysetHandle.getPrimitive(Aead::class.java)
    }

    @Provides
    @AppScope
    fun provideDaead(context: Context): DeterministicAead {
        val keysetHandle = AndroidKeysetManager.Builder()
            .withSharedPref(context, TinkConsts.DKEYSET_NAME, TinkConsts.DPREFERENCE_FILE)
            .withKeyTemplate(DeterministicAeadKeyTemplates.AES256_SIV)
            .withMasterKeyUri(TinkConsts.DMASTER_KEY_URI)
            .build()
            .keysetHandle

        return keysetHandle.getPrimitive(DeterministicAead::class.java)
    }

    @Provides
    @AppScope
    fun provideUserPrefs(
        context: Context,
        aead: Aead,
        daead: DeterministicAead
    ) = UserPrefs(context, aead, daead)
}
