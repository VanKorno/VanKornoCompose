package com.vankorno.vankornocompose.values.internal

import com.vankorno.vankornodb.dbManagement.data.BaseEntityMeta
import com.vankorno.vankornodb.dbManagement.data.SchemaBundle
import com.vankorno.vankornodb.dbManagement.migration.data.MigrationBundle

enum class DummyEntityMeta(                         override val currVersion: Int,
                                                      override val dbRowName: String,
                                                   override val schemaBundle: SchemaBundle<*>,
                                                override val migrationBundle: Lazy<MigrationBundle>,
                                                 override val limitedToTable: String? = null,
): BaseEntityMeta {
    
}
