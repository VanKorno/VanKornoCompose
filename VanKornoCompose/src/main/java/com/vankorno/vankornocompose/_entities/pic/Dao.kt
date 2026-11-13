package com.vankorno.vankornocompose._entities.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornodb.get.getEntity

fun SQLiteDatabase.getPic(id: Long) = getEntity(_TTTPic, PicEntt()) { ID = id }








