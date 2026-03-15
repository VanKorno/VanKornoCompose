package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.OldEntity
import com.vankorno.vankornodb.dbManagement.data.OldSchemaBundle

data class V2_Pic(
                                  val path: String = "",
                           val variantPath: String = "",
                             val thumbBlob: ByteArray = byteArrayOf(),
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                val usages: Int = 0,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
                           override val id: Int = -1,
) : OldEntity





object _V2_Pic : OldSchemaBundle<V2_Pic>(
    clazz = V2_Pic::class,

    getter = { cursor ->
        var idx = 0

        V2_Pic(
            path = cursor.getString(idx++),
            variantPath = cursor.getString(idx++),
            thumbBlob = cursor.getBlob(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
            usages = cursor.getInt(idx++),
            becameUnused = cursor.getLong(idx++),
            notes = cursor.getString(idx++),
            id = cursor.getInt(idx++)
        )
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)