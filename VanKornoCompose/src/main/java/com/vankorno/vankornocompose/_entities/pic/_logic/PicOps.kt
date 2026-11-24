package com.vankorno.vankornocompose._entities.pic._logic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.pic.Pic
import com.vankorno.vankornocompose._entities.pic.TT_Pic
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.id.getEntityById

fun getPic(id: Long) = dbh.read(Pic(), "getPic") { db -> getPic(db, id) }

fun getPic(db: SQLiteDatabase, id: Long) = db.getEntityById(id, TT_Pic, Pic())







