package com.vankorno.vankornocompose._entities.pic

import com.vankorno.vankornocompose._entities.usage._SimpleUsage
import com.vankorno.vankornodb.dbManagement.data.BaseEntityMeta

const val PicTableName = "TTTPic"
val TT_Pic = _Pic.tableNamed(PicTableName)

const val PicUsageTableName = "TTTPicUsage"
val TT_PicUsage = _SimpleUsage.tableNamed(PicUsageTableName)



object PicMeta : BaseEntityMeta {
    override val currVersion = 4
    
    override val dbRowName = "Pic"
    
    override val schemaBundle = _Pic
    
    override val migrationBundle = lazy { migrationsPic() }
    
    override val limitedToTable = PicTableName
}
