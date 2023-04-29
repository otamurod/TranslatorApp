package com.ml.translator.ui.digitalink

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslatorOptions
import com.ml.translator.base.use
import com.ml.translator.data.model.ModelLanguage
import com.ml.translator.ui.theme.DigitalInkColors
import com.ml.translator.ui.theme.Purple700
import java.util.*

@Composable
fun DigitalInkScreen(
    modifier: Modifier = Modifier
) {

    val lifecycleOwner = LocalLifecycleOwner.current

    val (state, event) = use(LocalDigitalInkViewModel.current, DigitalInkViewModel.State())

    DisposableEffect(Unit) {
        val lifecycleObserver = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_STOP) event(DigitalInkViewModel.Event.OnStop)
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose { lifecycleOwner.lifecycle.removeObserver(lifecycleObserver) }
    }

    Box(modifier = modifier) {
        if (state.showModelStatusProgress) { // check status
            ModelStatusProgress(
                statusText = "Checking models...", modifier = Modifier.align(Alignment.Center)
            )
        } else {

            Column(modifier = Modifier.fillMaxSize()) {

                Card(elevation = 4.dp) {
                    Row {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                LanguageMenu("Source Language", true)
                                LanguageMenu("Target Language", false)
                            }
                            TextField(
                                value = state.finalText,
                                onValueChange = { event(DigitalInkViewModel.Event.TextChanged(it)) },
                                modifier = Modifier.fillMaxWidth(),
                                maxLines = 2,
                                textStyle = TextStyle(
                                    color = DigitalInkColors.Text,
                                    fontSize = 24.sp
                                ),
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = Color.White,
                                    placeholderColor = DigitalInkColors.PredictionPlaceholder,
                                    focusedIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    cursorColor = DigitalInkColors.PredictionText
                                ),
                                placeholder = {
                                    Text(
                                        text = "Enter text",
                                        fontSize = 24.sp,
                                    )
                                },
                                shape = RoundedCornerShape(0.dp)
                            )

                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(Color.LightGray)
                            )

                            SelectionContainer {
                                Text(
                                    text = state.translation,
                                    fontSize = 24.sp,
                                    color = Purple700,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f, fill = true))

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(DigitalInkColors.PredictionBackground)
                ) {
                    items(state.predictions) { prediction ->
                        Prediction(text = prediction,
                            onClick = { DigitalInkViewModel.Event.PredictionSelected(it) })
                    }
                }

                DrawSpace(
                    reset = state.resetCanvas,
                    onDrawEvent = { event(DigitalInkViewModel.Event.Pointer(it)) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true)
                )
            }
        }
    }
}

@Composable
fun ModelStatusProgress(
    statusText: String, modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {

        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 24.dp)
        )

        Text(
            text = statusText, textAlign = TextAlign.Center, fontSize = 18.sp
        )
    }
}

private var languageArrayList: ArrayList<ModelLanguage>? = null
private var sourceLanguageCode = "ko"
private var sourceLanguageTitle = "KOREAN"
private var targetLanguageCode = "en"
private var targetLanguageTitle = "ENGLISH"
private const val TAG = "DigitalInkScreen"

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LanguageMenu(label: String, isForSource: Boolean) {
    loadAvailableLanguages()
    val options = mutableListOf<String>()
    for (language in languageArrayList!!) {
        options.add(language.languageTitle.toUpperCase())
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(if (isForSource) sourceLanguageTitle else targetLanguageTitle) }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = {
            expanded = !expanded
        }, modifier = Modifier.width(180.dp)
    ) {
        TextField(
            readOnly = true,
            value = selectedOptionText,
            onValueChange = { },
            label = { Text(label) },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {
            options.forEach { selectionOption ->
                DropdownMenuItem(onClick = {
                    selectedOptionText = selectionOption
                    expanded = false
                    if (isForSource) {
                        val selectedLanguage =
                            languageArrayList!!.filter { language -> language.languageTitle == selectionOption.toLowerCase() }
                        sourceLanguageCode = selectedLanguage[0].languageCode
                        sourceLanguageTitle = selectedLanguage[0].languageTitle
                        Log.d(
                            TAG,
                            "LanguageMenu: source: $sourceLanguageCode :: $sourceLanguageTitle"
                        )
                    } else {
                        val selectedLanguage =
                            languageArrayList!!.filter { language -> language.languageTitle == selectionOption.toLowerCase() }
                        targetLanguageCode = selectedLanguage[0].languageCode
                        targetLanguageTitle = selectedLanguage[0].languageTitle
                        Log.d(
                            TAG,
                            "LanguageMenu: target: $targetLanguageCode :: $targetLanguageTitle"
                        )
                    }

                    //TODO: we must send source and target language to TranslatorProvider here
                    val translatorOptions = TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.fromLanguageTag(sourceLanguageCode)!!) //TODO Lang
                        .setTargetLanguage(TranslateLanguage.fromLanguageTag(targetLanguageCode)!!)
                        .build()


                }) {
                    Text(text = selectionOption)
                }
            }
        }
    }
}

fun loadAvailableLanguages() {
    languageArrayList = ArrayList()
    val languageCodeList = TranslateLanguage.getAllLanguages()
    for (languageCode in languageCodeList) {
        val languageTitle = Locale(languageCode).displayLanguage
        val modelLanguage = ModelLanguage(languageCode, languageTitle)
        languageArrayList!!.add(modelLanguage)
    }
}

@Composable
fun Prediction(
    text: String, onClick: (String) -> Unit, modifier: Modifier = Modifier
) {

    Row(modifier = modifier) {
        Text(
            text = text,
            color = DigitalInkColors.PredictionText,
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onClick.invoke(text) },
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .background(DigitalInkColors.PredictionDivider)
        )
    }
}