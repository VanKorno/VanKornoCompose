package com.vankorno.vankornocompose._entities.usage.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.misc.getBoolean
import com.vankorno.vankornodb.set.dsl.SetDsl

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
) : DbEntity





object _V1_Usage : SchemaBundle<V1_Usage>(
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

    setter = { e, cv ->
        cv.put("subjTable", e.subjTable)
        cv.put("subjId", e.subjId)
        cv.put("objTable", e.objTable)
        cv.put("objId", e.objId)
        cv.put("int1", e.int1)
        cv.put("bool1", e.bool1)
        cv.put("str1", e.str1)
        cv.put("position", e.position)
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) },

    columns = buildColList {
        +V1_UsageFields.SubjTable
        +V1_UsageFields.SubjId
        +V1_UsageFields.ObjTable
        +V1_UsageFields.ObjId
        +V1_UsageFields.Int1
        +V1_UsageFields.Bool1
        +V1_UsageFields.Str1
        +V1_UsageFields.Position
        +V1_UsageFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::V1_UsageDsl, ::V1_UsageOrderDsl, ::V1_UsageExtraDsl, ::V1_UsageSetDsl)
}


interface V1_UsageDslColumns {
    val SubjTable: Field<V1_UsageDsl, StrCol>
    val SubjId: Field<V1_UsageDsl, LongCol>
    val ObjTable: Field<V1_UsageDsl, StrCol>
    val ObjId: Field<V1_UsageDsl, LongCol>
    val Int1: Field<V1_UsageDsl, IntCol>
    val Bool1: Field<V1_UsageDsl, BoolCol>
    val Str1: Field<V1_UsageDsl, StrCol>
    val Position: Field<V1_UsageDsl, LongCol>
    val Id: Field<V1_UsageDsl, LongCol>
}


class V1_UsageOrderDsl internal constructor(columns: V1_UsageDslColumns) : OrderDsl<V1_UsageDsl, V1_UsageOrderDsl>(), V1_UsageDslColumns by columns

class V1_UsageExtraDsl internal constructor(columns: V1_UsageDslColumns) : ExtraDsl<V1_UsageDsl>(), V1_UsageDslColumns by columns

class V1_UsageSetDsl(columns: V1_UsageDslColumns) : SetDsl<V1_UsageDsl>(), V1_UsageDslColumns by columns

typealias V1_UsageT = FullDslTable<TableInfo<V1_Usage>, V1_UsageDsl, V1_UsageOrderDsl, V1_UsageExtraDsl, V1_UsageSetDsl>


class V1_UsageDsl internal constructor(                     context: DslContext<V1_UsageDsl>
) : WhereDsl<V1_UsageDsl>(context), V1_UsageDslColumns {
    override val SubjTable get() = column(V1_UsageFields.SubjTable)
    override val SubjId get() = column(V1_UsageFields.SubjId)
    override val ObjTable get() = column(V1_UsageFields.ObjTable)
    override val ObjId get() = column(V1_UsageFields.ObjId)
    override val Int1 get() = column(V1_UsageFields.Int1)
    override val Bool1 get() = column(V1_UsageFields.Bool1)
    override val Str1 get() = column(V1_UsageFields.Str1)
    override val Position get() = column(V1_UsageFields.Position)
    override val Id get() = column(V1_UsageFields.Id)
}

private object V1_UsageFields {
    val SubjTable = sCol("subjTable", "")
    val SubjId = lCol("subjId", -1L)
    val ObjTable = sCol("objTable", "")
    val ObjId = lCol("objId", -1L)
    val Int1 = iCol("int1", 0)
    val Bool1 = bCol("bool1", false)
    val Str1 = sCol("str1", "")
    val Position = lCol("position", 0L)
    val Id = lCol("id", -1L)
}
