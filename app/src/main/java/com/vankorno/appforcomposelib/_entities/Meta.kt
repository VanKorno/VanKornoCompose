package com.vankorno.appforcomposelib._entities

import com.vankorno.vankornodb.dbManagement.data.BaseEntityMeta
import com.vankorno.vankornodb.dbManagement.data.CurrSchemaBundle
import com.vankorno.vankornodb.dbManagement.migration.data.MigrationBundle

const val DbNAME = "ComposeDemoApp.db"
const val DbVersion = 1


enum class EntityMeta(                              override val currVersion: Int,
                                                      override val dbRowName: String,
                                                   override val schemaBundle: CurrSchemaBundle<*>,
                                                override val migrationBundle: Lazy<MigrationBundle>,
                                                 override val limitedToTable: String? = null,
): BaseEntityMeta {
    
}
