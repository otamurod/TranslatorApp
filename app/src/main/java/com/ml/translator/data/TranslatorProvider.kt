package com.ml.translator.data

import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.ml.translator.ui.digitalink.MLKitModelStatus
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class TranslatorProviderImpl @Inject constructor(): TranslatorProvider {

    override val translation = Channel<String>(4)

    private val translatorOptions = TranslatorOptions.Builder()
        .setSourceLanguage(TranslateLanguage.KOREAN) //TODO Lang
        .setTargetLanguage(TranslateLanguage.ENGLISH)
        .build()

    private val translator = Translation.getClient(this.translatorOptions)

    override fun checkIfModelIsDownloaded(): Flow<MLKitModelStatus> = callbackFlow {
        trySend(MLKitModelStatus.CheckingDownload)
        val downloadConditions = DownloadConditions.Builder()
            .build()

        this@TranslatorProviderImpl.translator.downloadModelIfNeeded(downloadConditions)
            .addOnSuccessListener {
                trySend(MLKitModelStatus.Downloaded)
            }
            .addOnCompleteListener { close() }
            .addOnFailureListener {
                it.printStackTrace()
                close(it)
            }

        awaitClose { cancel()  }
    }

    override fun translate(text: String) {
        this.translator.translate(text)
            .addOnSuccessListener { this.translation.trySend(it) }
            .addOnFailureListener { it.printStackTrace() }
    }

    override fun close() {
        this.translator.close()
    }
}

interface TranslatorProvider {

    val translation: Channel<String>

    fun checkIfModelIsDownloaded(): Flow<MLKitModelStatus>
    fun translate(text: String)

    fun close()
}