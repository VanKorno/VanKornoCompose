package com.vankorno.vankornocompose._entities.usage.old.to100.to10

import com.vankorno.vankornodb.api.OldEntity
import com.vankorno.vankornodb.dbManagement.data.OldSchemaBundle
import com.vankorno.vankornodb.misc.getBoolean

data class V1_Usage(
                             val subjTable: String = "",
                                val subjId: Long = -1L,

                              val objTable: String = "",
                                 val objId: Long = -1L,

                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",

                              val position: Long = 0L,
                           override val id: Long = -1L,
) : OldEntity





object _V1_Usage : OldSchemaBundle<V1_Usage>(
    clazz = V1_Usage::class,

    getter = { cursor ->
        var idx = 0

        V1_Usage(
            subjTable = cursor.getString(idx++),
            subjId = cursor.getLong(idx++),
            objTable = cursor.getString(idx++),
            objId = cursor.getLong(idx++),
            int1 = cursor.getInt(idx++),
            bool1 = cursor.getBoolean(idx++),
            str1 = cursor.getString(idx++),
            position = cursor.getLong(idx++),
            id = cursor.getLong(idx++)
        )
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)