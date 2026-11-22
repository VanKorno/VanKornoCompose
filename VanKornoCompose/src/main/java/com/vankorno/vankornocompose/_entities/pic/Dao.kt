package com.vankorno.vankornocompose._entities.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornodb.get.byId.getEntityById

fun SQLiteDatabase.getPic(id: Long) = getEntityById(id, TT_Pic, Pic())








