package com.vankorno.vankornocompose._entities.usage._logic

import com.vankorno.vankornocompose._entities.usage.CUsage
import com.vankorno.vankornocompose._entities.usage.CUsage.SubjId
import com.vankorno.vankornocompose._entities.usage.CUsage.SubjTable
import com.vankorno.vankornocompose._entities.usage._Usage
import com.vankorno.vankornocompose.values.LibGlobals2.ops
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.dbManagement.data.using

object LibUsageOps {
    
    fun copyUsagesWithSubj(                                                   usageTable: String,
                                                                                oldTable: String,
                                                                                newTable: String,
                                                                                   oldId: Long,
                                                                                   newId: Long,
    ) {
        val tableInfo = usageTable using _Usage
        
        val oldObjects = dbh.getObjects(tableInfo) {
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
        dbh.addObjects(tableInfo, newObjects)
    }
    
    
    fun handleUsagesOnSubjMove(                                               usageTable: String,
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
    fun getUsageObjIDs(                                                   usageTable: String,
                                                                           subjTable: String,
                                                                              subjId: Long? = null,
    ): List<Long> = ops.get(emptyList(), "getUsageObjIDs") {
        getUsageObjIDsP(usageTable, subjTable, subjId)
    }
    
    /**
     * Gets all IDs of objects belonging to the same subject(s)
     */
    fun getUsageObjIDs(                                                 usageTable: String,
                                                                         subjTable: String? = null,
                                                                            subjId: Long,
    ): List<Long> = ops.get(emptyList(), "getUsageObjIDs") {
        getUsageObjIDsP(usageTable, subjTable, subjId)
    }
    
    private fun getUsageObjIDsP(                                              usageTable: String,
                                                                               subjTable: String?,
                                                                                  subjId: Long?,
    ): List<Long> = dbh.getColLongs(usageTable, CUsage.ObjId) {
        if (subjId != null)
            SubjId equal subjId
        
        if (subjTable != null)
            SubjTable equal subjTable
    }
    
}
