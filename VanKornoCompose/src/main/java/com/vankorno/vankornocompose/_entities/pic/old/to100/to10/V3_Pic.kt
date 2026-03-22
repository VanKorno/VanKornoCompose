package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.OldEntity
import com.vankorno.vankornodb.dbManagement.data.OldSchemaBundle

data class V3_Pic(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                val usages: Int = 0,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
                           override val id: Long = -1L,
) : OldEntity





object _V3_Pic : OldSchemaBundle<V3_Pic>(
    clazz = V3_Pic::class,

    getter = { cursor ->
        var idx = 0

        V3_Pic(
            path = cursor.getString(idx++),
            previewPath = cursor.getString(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
            usages = cursor.getInt(idx++),
            becameUnused = cursor.getLong(idx++),
            notes = cursor.getString(idx++),
            id = cursor.getLong(idx++)
        )
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)