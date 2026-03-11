package com.vankorno.vankornocompose._entities.pic

import com.vankorno.vankornodb.dbManagement.data.BaseEntityMeta
import com.vankorno.vankornodb.dbManagement.data.using

const val TTTPic = "TTTPic"

val _TTTPic = TTTPic using _Pic


object PicMeta : BaseEntityMeta {
    override val currVersion = 3
    
    override val dbRowName = "Pic"
    
    override val schemaBundle = _Pic
    
    override val migrationBundle = lazy { migrationsPic() }
    
    override val limitedToTable = TTTPic
}
