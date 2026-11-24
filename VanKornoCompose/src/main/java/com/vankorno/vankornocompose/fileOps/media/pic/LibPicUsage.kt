package com.vankorno.vankornocompose.fileOps.media.pic

import android.database.sqlite.SQLiteDatabase
import com.vankorno.vankornocompose._entities.pic.TT_Pic
import com.vankorno.vankornocompose._entities.pic.TT_PicUsage
import com.vankorno.vankornocompose._entities.usage.SimpleUsageDsl
import com.vankorno.vankornodb.api.DbRuntime.dbh
import com.vankorno.vankornodb.delete.deleteRows
import com.vankorno.vankornodb.get.getColLongs
import com.vankorno.vankornodb.get.hasRows
import com.vankorno.vankornodb.get.id.hasId
import com.vankorno.vankornodb.set.byId.setLongById
import com.vankorno.vankornohelpers.getCurrTime

object LibPicUsage {
    
    fun deletePicUsages(                                                         picId: Long
    ) = dbh.write("deletePicUsages") { db ->
        deletePicUsages(db, picId)
    }
    
    fun deletePicUsages(                                                        db: SQLiteDatabase,
                                                                             picId: Long,
    ) {
        db.deleteRows(TT_PicUsage) { ObjId equal picId }
        
        setBecameUnused(db, picId)
    }
    
    
    fun deletePicUsagesBySubj(                                                   table: String,
                                                                                    id: Long,
    ) = dbh.write("deletePicUsagesBySubj") { db ->
        deletePicUsagesBySubj(db, table, id)
    }
    
    fun deletePicUsagesBySubj(                                                  db: SQLiteDatabase,
                                                                             table: String,
                                                                                id: Long,
    ) {
        deleteUsages(db) {
            SubjTable equal table
            SubjId equal id
        }
    }
    
    
    fun deletePicUsagesByTable(                                                  table: String
    ) = dbh.write("deletePicUsagesByTable") { db ->
        deletePicUsagesByTable(db, table)
    }
    fun deletePicUsagesByTable(                                                 db: SQLiteDatabase,
                                                                             table: String,
    ) = deleteUsages(db) { SubjTable equal table }
    
    
    fun deletePicUsagesByTables(                                         vararg tables: String
    ) = dbh.write("deletePicUsagesByTables") { db ->
        deletePicUsagesByTables(db, *tables)
    }
    fun deletePicUsagesByTables(                                                db: SQLiteDatabase,
                                                                     vararg tables: String,
    ) = deleteUsages(db) { SubjTable.equalAnyOf(*tables) }
    
    
    private fun deleteUsages(                                          db: SQLiteDatabase,
                                                                    where: SimpleUsageDsl.()->Unit,
    ) {
        val usedPicIDs = db.getColLongs(TT_PicUsage.col{ ObjId }, where = where)
        
        db.deleteRows(TT_PicUsage, where = where)
        
        for (picId in usedPicIDs) {
            val used = db.hasRows(TT_PicUsage) { ObjId equal picId }
            if (!used)
                setBecameUnused(db, picId)
        }
    }
    
    private fun setBecameUnused(                                                db: SQLiteDatabase,
                                                                             picId: Long,
    ) {
        val dbPicExists = db.hasId(picId, TT_Pic)
        
        if (dbPicExists)
            db.setLongById(picId, getCurrTime(), TT_Pic.col{ BecameUnused })
    }
    
}
