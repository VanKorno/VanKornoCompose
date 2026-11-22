package com.vankorno.vankornocompose._entities.misc

import com.vankorno.vankornocompose.db.LibDbVals.TT_Misc
import com.vankorno.vankornodb.add.addEntities
import com.vankorno.vankornodb.add.addEntity
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.getBool
import com.vankorno.vankornodb.get.getEntity
import com.vankorno.vankornodb.get.getFloat
import com.vankorno.vankornodb.get.getInt
import com.vankorno.vankornodb.get.getLong
import com.vankorno.vankornodb.get.getStr

object MiscOps {
    fun getMiscEntity(name: String): MiscData = dbh.getEntity(TT_Misc, MiscData()) { Name equal name }
    
    fun getMiscInt(name: String): Int = dbh.getInt(TT_Misc.col{ Int1 }) { Name equal name }
    fun getMiscBool(name: String): Boolean = dbh.getBool(TT_Misc.col{ Bool1 }) { Name equal name }
    fun getMiscStr(name: String): String = dbh.getStr(TT_Misc.col{ Str1 }) { Name equal name }
    fun getMiscLong(name: String): Long = dbh.getLong(TT_Misc.col{ Long1 }) { Name equal name }
    fun getMiscFloat(name: String): Float = dbh.getFloat(TT_Misc.col{ Float1 }) { Name equal name }
    
    
    fun addMiscsWithDefaults(                                                  names: Array<String>
    ) {
        val entities = names.map { MiscData(name = it) }
        dbh.addEntities(TT_Misc, entities)
    }
    
    
    fun addMisc(                                                             name: String,
                                                                             int1: Int = 0,
                                                                            bool1: Boolean = false,
                                                                             str1: String = "",
                                                                            long1: Long = 0L,
                                                                           float1: Float = 0F,
    ) {
        dbh.addEntity(TT_Misc, MiscData(name, int1, bool1, str1, long1, float1))
    }
    
}
