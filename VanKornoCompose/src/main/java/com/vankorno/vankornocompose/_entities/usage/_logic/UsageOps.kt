package com.vankorno.vankornocompose._entities.usage._logic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.usage.SimpleUsageT
import com.vankorno.vankornodb.add.addEntities
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.getColLongs
import com.vankorno.vankornodb.get.getEntities
import com.vankorno.vankornodb.get.id.getLastId
import com.vankorno.vankornodb.set.set

object UsageOps {
    
    fun copyUsagesWithSubj(                                               usageTable: SimpleUsageT,
                                                                            oldTable: String,
                                                                            newTable: String,
                                                                               oldId: Long,
                                                                               newId: Long,
    ) = dbh.write("copyUsagesWithSubj") { db ->
        copyUsagesWithSubj(db, usageTable, oldTable, newTable, oldId, newId)
    }
    
    fun copyUsagesWithSubj(                                                     db: SQLiteDatabase,
                                                                        usageTable: SimpleUsageT,
                                                                          oldTable: String,
                                                                          newTable: String,
                                                                             oldId: Long,
                                                                             newId: Long,
    ) {
        val oldObjects = db.getEntities(usageTable) {
            SubjId equal oldId
            SubjTable equal oldTable
        }
        
        if (oldObjects.isEmpty()) return //\/\/\/\/\/\
        
        val lastId = db.getLastId(usageTable)
        
        val newObjects = buildList {
            oldObjects.forEachIndexed { idx, obj ->
                add(
                    obj.copy(
                        id = lastId + idx + 1,
                        subjTable = newTable,
                        subjId = newId
                    )
                )
            }
        }
        db.addEntities(usageTable, newObjects)
    }
    
    
    fun handleUsagesOnSubjMove(                                           usageTable: SimpleUsageT,
                                                                            oldTable: String,
                                                                            newTable: String,
                                                                               oldId: Long,
                                                                               newId: Long,
    ) = dbh.write("handleUsagesOnSubjMove") { db ->
        handleUsagesOnSubjMove(db, usageTable, oldTable, newTable, oldId, newId)
    }
    
    fun handleUsagesOnSubjMove(                                                 db: SQLiteDatabase,
                                                                        usageTable: SimpleUsageT,
                                                                          oldTable: String,
                                                                          newTable: String,
                                                                             oldId: Long,
                                                                             newId: Long,
    ) {
        db.set(
            usageTable,
            where = {
                SubjId equal oldId
                SubjTable equal oldTable
            }
        ) {
            SubjId setTo newId
            SubjTable setTo newTable
        }
    }
    
    
    /**
     * Gets all IDs of objects belonging to the same subject(s)
     */
    fun getUsageObjIDs(                                                   usageTable: SimpleUsageT,
                                                                           subjTable: String,
                                                                              subjId: Long? = null,
    ): List<Long> = dbh.read(emptyList(), "getUsageObjIDs") { db ->
        getUsageObjIDs(db, usageTable, subjTable, subjId)
    }
    
    fun getUsageObjIDs(                                                         db: SQLiteDatabase,
                                                                        usageTable: SimpleUsageT,
                                                                         subjTable: String,
                                                                            subjId: Long? = null,
    ): List<Long> = getUsageObjIDsP(db, usageTable, subjTable, subjId)
    
    /**
     * Gets all IDs of objects belonging to the same subject(s)
     */
    fun getUsageObjIDs(                                                 usageTable: SimpleUsageT,
                                                                         subjTable: String? = null,
                                                                            subjId: Long,
    ): List<Long> = dbh.read(emptyList(), "getUsageObjIDs") { db ->
        getUsageObjIDs(db, usageTable, subjTable, subjId)
    }
    
    fun getUsageObjIDs(                                                         db: SQLiteDatabase,
                                                                        usageTable: SimpleUsageT,
                                                                         subjTable: String? = null,
                                                                            subjId: Long,
    ): List<Long> = getUsageObjIDsP(db, usageTable, subjTable, subjId)
    
    private fun getUsageObjIDsP(                                                db: SQLiteDatabase,
                                                                        usageTable: SimpleUsageT,
                                                                         subjTable: String?,
                                                                            subjId: Long?,
    ): List<Long> = db.getColLongs(usageTable.col { ObjId }) {
        if (subjId != null)
            SubjId equal subjId
        
        if (subjTable != null)
            SubjTable equal subjTable
    }
    
}
