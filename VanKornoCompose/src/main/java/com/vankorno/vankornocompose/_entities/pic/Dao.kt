package com.vankorno.vankornocompose._entities.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornodb.get.id.getEntityById

fun SQLiteDatabase.getPic(id: Long) = getEntityById(id, TT_Pic, Pic())








