package com.vankorno.vankornocompose._entities.simpleRef

import android.os.Parcelable
import com.vankorno.vankornodb.api.CurrEntity
import com.vankorno.vankornodb.api.EntityColumns
import com.vankorno.vankornodb.dbManagement.data.CurrSchemaBundle
import com.vankorno.vankornodb.dbManagement.data.lCol
import com.vankorno.vankornodb.dbManagement.data.sCol
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleRef(
                                 val refId: Long = 0L,
                              val refTable: String = "",

                           override val id: Long = -1L,
) : CurrEntity, Parcelable





object _SimpleRef : CurrSchemaBundle<SimpleRef>(
    clazz = SimpleRef::class,

    columns = CSimpleRef,

    getter = { cursor ->
        var idx = 0

        SimpleRef(
            refId = cursor.getLong(idx++),
            refTable = cursor.getString(idx++),
            id = cursor.getLong(idx++)
        )
    },

    setter = { e, cv ->
        cv.put("refId", e.refId)
        cv.put("refTable", e.refTable)
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)





object CSimpleRef : EntityColumns {
    val RefId = lCol("refId", 0L)
    val RefTable = sCol("refTable", "")
    val Id = lCol("id", -1L)

    override val columns = buildColList {
        +RefId
        +RefTable
        +Id
    }
}
