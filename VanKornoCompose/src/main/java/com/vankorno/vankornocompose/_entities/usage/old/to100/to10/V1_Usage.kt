package com.vankorno.vankornocompose._entities.usage.old.to100.to10

import com.vankorno.vankornodb.api.OldEntity
import com.vankorno.vankornodb.dbManagement.data.OldSchemaBundle
import com.vankorno.vankornodb.misc.getBoolean

data class V1_Usage(
                             val subjTable: String = "",
                                val subjId: Int = -1,

                              val objTable: String = "",
                                 val objId: Int = -1,

                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",

                              val position: Int = 0,
                           override val id: Int = -1,
) : OldEntity





object _V1_Usage : OldSchemaBundle<V1_Usage>(
    clazz = V1_Usage::class,

    getter = { cursor ->
        var idx = 0

        V1_Usage(
            subjTable = cursor.getString(idx++),
            subjId = cursor.getInt(idx++),
            objTable = cursor.getString(idx++),
            objId = cursor.getInt(idx++),
            int1 = cursor.getInt(idx++),
            bool1 = cursor.getBoolean(idx++),
            str1 = cursor.getString(idx++),
            id = cursor.getInt(idx++)
        )
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)