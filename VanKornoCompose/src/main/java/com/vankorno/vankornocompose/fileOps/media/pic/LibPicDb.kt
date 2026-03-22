package com.vankorno.vankornocompose.fileOps.media.pic

import com.vankorno.vankornocompose._entities.pic.PicEntt
import com.vankorno.vankornocompose._entities.pic.getPic
import com.vankorno.vankornodb.api.DbRuntime.dbh

object LibPicDb {
    
    fun getPic(id: Long) = dbh.read(PicEntt(), "getPic") { db -> db.getPic(id) }
    
}