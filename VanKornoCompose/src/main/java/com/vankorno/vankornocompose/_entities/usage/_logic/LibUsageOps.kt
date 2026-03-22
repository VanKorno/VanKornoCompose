package com.vankorno.vankornocompose._entities.usage._logic

import com.vankorno.vankornocompose._entities.usage.CUsage.SubjId
import com.vankorno.vankornocompose._entities.usage.CUsage.SubjTable
import com.vankorno.vankornocompose._entities.usage._Usage
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.dbManagement.data.using

object LibUsageOps {
    private const val TAG = "LibUsageOps"
    
    
    fun copyUsagesWithSubj(                                                   usageTable: String,
                                                                               tableFrom: String,
                                                                                  idFrom: Long,
                                                                                 tableTo: String,
                                                                                    idTo: Long,
    ) {
        val tableInfo = usageTable using _Usage
        
        val oldObjects = dbh.getObjects(tableInfo) {
            SubjTable equal tableFrom
            and { SubjId equal idFrom }
        }
        
        if (oldObjects.isEmpty()) return //\/\/\/\/\/\
        
        val lastId = dbh.getLastId(usageTable)
        
        val newObjects = buildList {
            oldObjects.forEachIndexed { idx, obj ->
                add(
                    obj.copy(
                        id = lastId + idx + 1,
                        subjTable = tableTo,
                        subjId = idTo
                    )
                )
            }
        }
        dbh.addObjects(tableInfo, newObjects)
    }
    
    
    fun handleUsagesOnSubjMove(                                               usageTable: String,
                                                                               tableFrom: String,
                                                                                  idFrom: Long,
                                                                                 tableTo: String,
                                                                                    idTo: Long,
    ) {
        dbh.set(
            usageTable,
            where = {
                SubjTable equal tableFrom
                and { SubjId equal idFrom }
            }
        ) {
            SubjTable setTo tableTo
            SubjId setTo idTo
        }
    }
}