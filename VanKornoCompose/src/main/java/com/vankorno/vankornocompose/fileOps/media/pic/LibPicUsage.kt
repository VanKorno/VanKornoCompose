package com.vankorno.vankornocompose.fileOps.media.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.pic.TT_Pic
import com.vankorno.vankornocompose._entities.pic.TT_PicUsage
import com.vankorno.vankornocompose._entities.usage.SimpleUsageDsl
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.delete.deleteRows
import com.vankorno.vankornodb.get.getColLongs
import com.vankorno.vankornodb.get.hasRows
import com.vankorno.vankornodb.set.setLong
import com.vankorno.vankornohelpers.getCurrTime

object LibPicUsage {
    
    fun deletePicUsages(                                                           picId: Long
    ) {
        dbh.write("deletePicUsages") { db ->
            db.deleteRows(TT_PicUsage) { ObjId equal picId }
            
            setBecameUnused(db, picId)
        }
    }
    
    
    fun deletePicUsagesBySubj(                                                     table: String,
                                                                                      id: Long,
    ) {
        deleteUsages {
            SubjTable equal table
            and { SubjId equal id }
        }
    }
    
    fun deletePicUsagesByTable(table: String) = deleteUsages { SubjTable equal table }
    
    fun deletePicUsagesByTables(vararg tables: String) = deleteUsages { SubjTable.equalAnyOf(*tables) }
    
    
    
    
    
    private fun deleteUsages(                                         where: SimpleUsageDsl.()->Unit
    ) {
        dbh.write("clearSecPicUsages") { db ->
            val usedPicIDs = db.getColLongs(TT_PicUsage.col { ObjId }, where = where) 
            
            db.deleteRows(TT_PicUsage, where = where)
            
            for (picId in usedPicIDs) {
                val used = db.hasRows(TT_PicUsage) { ObjId equal picId }
                if (!used)
                    setBecameUnused(db, picId)
            }
        }
    }
    
    private fun setBecameUnused(                                                db: SQLiteDatabase,
                                                                             picId: Long,
    ) {
        val dbPicExists = db.hasRows(TT_Pic) { Id equal picId }
        
        if (dbPicExists)
            db.setLong(getCurrTime(), TT_Pic.col { BecameUnused }) { Id equal picId }
    }
    
}
