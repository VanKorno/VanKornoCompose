package com.vankorno.vankornocompose._entities.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornodb.get.getObj

fun SQLiteDatabase.getPic(id: Long) = getObj(_TTTPic, PicEntt()) { ID = id }








