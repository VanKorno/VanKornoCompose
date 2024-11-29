package com.vankorno.vankornocompose.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.vankorno.vankornocompose.values.MOD_DefaultSpacer

@Composable
fun Spa_______________cer() { Spacer(MOD_DefaultSpacer) }

@Composable
fun Spa_______________cer(modifier: Modifier) { Spacer(modifier) }

@Composable
fun Spa_______________cer(heightDP: Dp) { Spacer(Modifier.height(heightDP)) }

@Composable
fun Spa_______________cer(height: Int) { Spacer(Modifier.height(height.dp)) }










