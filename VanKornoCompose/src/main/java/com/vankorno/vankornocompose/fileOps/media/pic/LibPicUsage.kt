package com.vankorno.vankornocompose.fileOps.media.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.pic.CPic.BecameUnused
import com.vankorno.vankornocompose._entities.pic.TTTPic
import com.vankorno.vankornocompose._entities.pic.TTTPicUsage
import com.vankorno.vankornocompose._entities.usage.CUsage.ObjId
import com.vankorno.vankornocompose._entities.usage.CUsage.SubjId
import com.vankorno.vankornocompose._entities.usage.CUsage.SubjTable
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.api.WhereDsl
import com.vankorno.vankornodb.delete.deleteRows
import com.vankorno.vankornodb.get.getColInts
import com.vankorno.vankornodb.get.hasRows
import com.vankorno.vankornodb.set.setLong
import com.vankorno.vankornohelpers.getCurrTime

object LibPicUsage {
    
    fun deletePicUsages(                                                           picId: Int
    ) {
        dbh.write("deletePicUsages") { db ->
            db.deleteRows(TTTPicUsage) { ObjId equal picId }
            
            setBecameUnused(db, picId)
        }
    }
    
    
    fun deletePicUsagesBySubj(                                                     table: String,
                                                                                      id: Int,
    ) {
        deleteUsages {
            SubjTable equal table
            and { SubjId equal id }
        }
    }
    
    fun deletePicUsagesByTable(table: String) = deleteUsages { SubjTable equal table }
    
    fun deletePicUsagesByTables(vararg tables: String) = deleteUsages { SubjTable.equalAnyOf(*tables) }
    
    
    
    
    
    private fun deleteUsages(                                               where: WhereDsl.()->Unit
    ) {
        dbh.write("clearSecPicUsages") { db ->
            val usedPicIDs = db.getColInts(TTTPicUsage, ObjId, where) 
            
            db.deleteRows(TTTPicUsage, where = where)
            
            for (picId in usedPicIDs) {
                val used = db.hasRows(TTTPicUsage) { ObjId equal picId }
                if (!used)
                    setBecameUnused(db, picId)
            }
        }
    }
    
    private fun setBecameUnused(                                                db: SQLiteDatabase,
                                                                             picId: Int,
    ) {
        val dbPicExists = db.hasRows(TTTPic) { ID = picId }
        
        if (dbPicExists)
            db.setLong(getCurrTime(), TTTPic, BecameUnused) { ID = picId }
    }
    
}





