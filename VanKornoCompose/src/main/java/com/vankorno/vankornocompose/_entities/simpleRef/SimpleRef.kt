package com.vankorno.vankornocompose._entities.simpleRef

import android.os.Parcelable
import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.set.dsl.SetDsl
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleRef(
                                 val refId: Long = 0L,
                              val refTable: String = "",

                           override val id: Long = -1L,
) : DbEntity, Parcelable





object _SimpleRef : SchemaBundle<SimpleRef>(
    clazz = SimpleRef::class,

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

    withId = { obj, newId -> obj.copy(id = newId) },

    columns = buildColList {
        +SimpleRefFields.RefId
        +SimpleRefFields.RefTable
        +SimpleRefFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::SimpleRefDsl, ::SimpleRefOrderDsl, ::SimpleRefExtraDsl, ::SimpleRefSetDsl)
}


interface SimpleRefDslColumns {
    val RefId: Field<SimpleRefDsl, LongCol>
    val RefTable: Field<SimpleRefDsl, StrCol>
}


class SimpleRefOrderDsl internal constructor(columns: SimpleRefDslColumns) : OrderDsl<SimpleRefDsl, SimpleRefOrderDsl>(), SimpleRefDslColumns by columns

class SimpleRefExtraDsl internal constructor(columns: SimpleRefDslColumns) : ExtraDsl<SimpleRefDsl>(), SimpleRefDslColumns by columns

class SimpleRefSetDsl(columns: SimpleRefDslColumns) : SetDsl<SimpleRefDsl>(), SimpleRefDslColumns by columns

typealias SimpleRefT = FullDslTable<TableInfo<SimpleRef>, SimpleRefDsl, SimpleRefOrderDsl, SimpleRefExtraDsl, SimpleRefSetDsl>


class SimpleRefDsl internal constructor(                     context: DslContext<SimpleRefDsl>
) : WhereDsl<SimpleRefDsl>(context), SimpleRefDslColumns {
    override val RefId get() = column(SimpleRefFields.RefId)
    override val RefTable get() = column(SimpleRefFields.RefTable)
}

private object SimpleRefFields {
    val RefId = lCol("refId", 0L)
    val RefTable = sCol("refTable", "")
    val Id = lCol("id", -1L)
}
