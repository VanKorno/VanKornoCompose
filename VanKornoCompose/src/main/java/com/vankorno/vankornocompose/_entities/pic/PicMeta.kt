package com.vankorno.vankornocompose._entities.pic

import com.vankorno.vankornocompose._entities.usage._SimpleUsage
import com.vankorno.vankornodb.dbManagement.data.BaseEntityMeta

const val TTTPicName = "TTTPic"
val TTTPic = _Pic.tableNamed(TTTPicName)

const val TTTPicUsageName = "TTTPicUsage"
val TTTPicUsage = _SimpleUsage.tableNamed(TTTPicUsageName)



object PicMeta : BaseEntityMeta {
    override val currVersion = 4
    
    override val dbRowName = "Pic"
    
    override val schemaBundle = _Pic
    
    override val migrationBundle = lazy { migrationsPic() }
    
    override val limitedToTable = TTTPicName
}
