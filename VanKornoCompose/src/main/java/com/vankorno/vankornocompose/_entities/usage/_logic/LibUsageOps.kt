package com.vankorno.vankornocompose._entities.usage._logic

import com.vankorno.vankornocompose._entities.usage.SimpleUsageT
import com.vankorno.vankornocompose.values.LibGlobals2.ops
import com.vankorno.vankornodb.add.addEntities
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.get.getColLongs
import com.vankorno.vankornodb.get.getEntities
import com.vankorno.vankornodb.get.id.getLastId
import com.vankorno.vankornodb.set.set

object LibUsageOps {
    
    fun copyUsagesWithSubj(                                               usageTable: SimpleUsageT,
                                                                            oldTable: String,
                                                                            newTable: String,
                                                                               oldId: Long,
                                                                               newId: Long,
    ) {
        val oldObjects = dbh.getEntities(usageTable) {
            SubjId equal oldId
            SubjTable equal oldTable
        }
        
        if (oldObjects.isEmpty()) return //\/\/\/\/\/\
        
        val lastId = dbh.getLastId(usageTable)
        
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
        dbh.addEntities(usageTable, newObjects)
    }
    
    
    fun handleUsagesOnSubjMove(                                           usageTable: SimpleUsageT,
                                                                            oldTable: String,
                                                                            newTable: String,
                                                                               oldId: Long,
                                                                               newId: Long,
    ) {
        dbh.set(
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
    ): List<Long> = ops.get(emptyList(), "getUsageObjIDs") {
        getUsageObjIDsP(usageTable, subjTable, subjId)
    }
    
    /**
     * Gets all IDs of objects belonging to the same subject(s)
     */
    fun getUsageObjIDs(                                                 usageTable: SimpleUsageT,
                                                                         subjTable: String? = null,
                                                                            subjId: Long,
    ): List<Long> = ops.get(emptyList(), "getUsageObjIDs") {
        getUsageObjIDsP(usageTable, subjTable, subjId)
    }
    
    private fun getUsageObjIDsP(                                          usageTable: SimpleUsageT,
                                                                           subjTable: String?,
                                                                              subjId: Long?,
    ): List<Long> = dbh.getColLongs(usageTable.col { ObjId }) {
        if (subjId != null)
            SubjId equal subjId
        
        if (subjTable != null)
            SubjTable equal subjTable
    }
    
}
