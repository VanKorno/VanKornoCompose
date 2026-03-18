package com.vankorno.vankornocompose._entities.usage

import com.vankorno.vankornodb.dbManagement.data.BaseEntityMeta


object UsageMeta : BaseEntityMeta {
    override val currVersion = 1
    
    override val dbRowName = "Usage"
    
    override val schemaBundle = _Usage
    
    override val migrationBundle = lazy { migrationsUsage() }
}




