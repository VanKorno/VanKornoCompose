package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.OldEntity
import com.vankorno.vankornodb.dbManagement.data.OldSchemaBundle

data class V1_Pic(
                                  val path: String = "",
                           val variantPath: String = "",
                             val thumbBlob: ByteArray = byteArrayOf(),
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                 val notes: String = "",

                                    val id: Int = -1,

) : OldEntity





object _V1_Pic : OldSchemaBundle<V1_Pic>(
    clazz = V1_Pic::class,

    getter = { cursor ->
        var idx = 0

        V1_Pic(
            path = cursor.getString(idx++),
            variantPath = cursor.getString(idx++),
            thumbBlob = cursor.getBlob(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
            notes = cursor.getString(idx++),
            id = cursor.getInt(idx++)
        )
    },
)
