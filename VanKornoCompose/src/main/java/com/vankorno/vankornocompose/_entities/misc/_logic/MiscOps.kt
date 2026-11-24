package com.vankorno.vankornocompose._entities.misc._logic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.misc.MiscData
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
import com.vankorno.vankornodb.set.setBool
import com.vankorno.vankornodb.set.setFloat
import com.vankorno.vankornodb.set.setInt
import com.vankorno.vankornodb.set.setLong
import com.vankorno.vankornodb.set.setStr

object MiscOps {
    
    fun getMiscEntity(name: String): MiscData = dbh.read(MiscData(), "getMiscEntity") { db -> getMiscEntity(db, name) }
    fun getMiscEntity(db: SQLiteDatabase, name: String): MiscData = db.getEntity(TT_Misc,
        MiscData()
    ) { Name equal name }
    
    fun getMiscInt(name: String): Int = dbh.read(-1, "getMiscInt") { db -> getMiscInt(db, name) }
    fun getMiscInt(db: SQLiteDatabase, name: String): Int = db.getInt(TT_Misc.col{ Int1 }) { Name equal name }
    
    fun getMiscBool(name: String): Boolean = dbh.read(false, "getMiscBool") { db -> getMiscBool(db, name) }
    fun getMiscBool(db: SQLiteDatabase, name: String): Boolean = db.getBool(TT_Misc.col{ Bool1 }) { Name equal name }
    
    fun getMiscStr(name: String): String = dbh.read("", "getMiscStr") { db -> getMiscStr(db, name) }
    fun getMiscStr(db: SQLiteDatabase, name: String): String = db.getStr(TT_Misc.col{ Str1 }) { Name equal name }
    
    fun getMiscLong(name: String): Long = dbh.read(-1L, "getMiscLong") { db -> getMiscLong(db, name) }
    fun getMiscLong(db: SQLiteDatabase, name: String): Long = db.getLong(TT_Misc.col{ Long1 }) { Name equal name }
    
    fun getMiscFloat(name: String): Float = dbh.read(-1F, "getMiscFloat") { db -> getMiscFloat(db, name) }
    fun getMiscFloat(db: SQLiteDatabase, name: String): Float = db.getFloat(TT_Misc.col{ Float1 }) { Name equal name }
    
    
    fun setMiscInt(value: Int, name: String) = dbh.write("setMiscInt") { db -> setMiscInt(db, value, name) }
    fun setMiscInt(db: SQLiteDatabase, value: Int, name: String) = db.setInt(value, TT_Misc.col{ Int1 }) { Name equal name }
    
    fun setMiscBool(value: Boolean, name: String) = dbh.write("setMiscBool") { db -> setMiscBool(db, value, name) }
    fun setMiscBool(db: SQLiteDatabase, value: Boolean, name: String) = db.setBool(value, TT_Misc.col{ Bool1 }) { Name equal name }
    
    fun setMiscStr(value: String, name: String) = dbh.write("setMiscStr") { db -> setMiscStr(db, value, name) }
    fun setMiscStr(db: SQLiteDatabase, value: String, name: String) = db.setStr(value, TT_Misc.col{ Str1 }) { Name equal name }
    
    fun setMiscLong(value: Long, name: String) = dbh.write("setMiscLong") { db -> setMiscLong(db, value, name) }
    fun setMiscLong(db: SQLiteDatabase, value: Long, name: String) = db.setLong(value, TT_Misc.col{ Long1 }) { Name equal name }
    
    fun setMiscFloat(value: Float, name: String) = dbh.write("setMiscFloat") { db -> setMiscFloat(db, value, name) }
    fun setMiscFloat(db: SQLiteDatabase, value: Float, name: String) = db.setFloat(value, TT_Misc.col{ Float1 }) { Name equal name }
    
    
    fun addMiscWithDefaults(                                              vararg names: String
    ) = dbh.write("addMiscWithDefaults") { db ->
        addMiscWithDefaults(db, *names)
    }
    fun addMiscWithDefaults(                                                    db: SQLiteDatabase,
                                                                      vararg names: String,
    ) {
        val entities = names.map { MiscData(name = it) }
        db.addEntities(TT_Misc, entities)
    }
    
    
    fun addMisc(                                                             name: String,
                                                                             int1: Int = 0,
                                                                            bool1: Boolean = false,
                                                                             str1: String = "",
                                                                            long1: Long = 0L,
                                                                           float1: Float = 0F,
    ) = dbh.write("addMisc") { db ->
        addMisc(db, name, int1, bool1, str1, long1, float1)
    }
    fun addMisc(                                                               db: SQLiteDatabase,
                                                                             name: String,
                                                                             int1: Int = 0,
                                                                            bool1: Boolean = false,
                                                                             str1: String = "",
                                                                            long1: Long = 0L,
                                                                           float1: Float = 0F,
    ) {
        db.addEntity(TT_Misc, MiscData(name, int1, bool1, str1, long1, float1))
    }
    
}
