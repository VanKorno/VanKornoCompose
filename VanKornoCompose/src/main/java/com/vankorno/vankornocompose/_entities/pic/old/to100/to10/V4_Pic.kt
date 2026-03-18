package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.OldEntity
import com.vankorno.vankornodb.dbManagement.data.OldSchemaBundle

data class V4_Pic(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
    
                           override val id: Int = -1,
) : OldEntity





object _V4_Pic : OldSchemaBundle<V4_Pic>(
    clazz = V4_Pic::class,

    getter = { cursor ->
        var idx = 0

        V4_Pic(
            path = cursor.getString(idx++),
            previewPath = cursor.getString(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
            becameUnused = cursor.getLong(idx++),
            notes = cursor.getString(idx++),
            id = cursor.getInt(idx++)
        )
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)