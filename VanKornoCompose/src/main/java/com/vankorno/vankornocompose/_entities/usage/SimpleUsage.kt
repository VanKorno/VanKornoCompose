package com.vankorno.vankornocompose._entities.usage

import android.os.Parcelable
import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.misc.getBoolean
import com.vankorno.vankornodb.set.dsl.SetDsl
import kotlinx.parcelize.Parcelize

@Parcelize
data class SimpleUsage(
                             val subjTable: String = "",
                                val subjId: Long = -1L,

                              val objTable: String = "",
                                 val objId: Long = -1L,

                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",

                              val position: Long = 0L,
                           override val id: Long = -1L,
) : DbEntity, Parcelable





object _SimpleUsage : SchemaBundle<SimpleUsage>(
    clazz = SimpleUsage::class,

    getter = { cursor ->
        var idx = 0

        SimpleUsage(
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
        +SimpleUsageFields.SubjTable
        +SimpleUsageFields.SubjId
        +SimpleUsageFields.ObjTable
        +SimpleUsageFields.ObjId
        +SimpleUsageFields.Int1
        +SimpleUsageFields.Bool1
        +SimpleUsageFields.Str1
        +SimpleUsageFields.Position
        +SimpleUsageFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::SimpleUsageDsl, ::SimpleUsageOrderDsl, ::SimpleUsageExtraDsl, ::SimpleUsageSetDsl)
}


interface SimpleUsageDslColumns {
    val SubjTable: Field<SimpleUsageDsl, StrCol>
    val SubjId: Field<SimpleUsageDsl, LongCol>
    val ObjTable: Field<SimpleUsageDsl, StrCol>
    val ObjId: Field<SimpleUsageDsl, LongCol>
    val Int1: Field<SimpleUsageDsl, IntCol>
    val Bool1: Field<SimpleUsageDsl, BoolCol>
    val Str1: Field<SimpleUsageDsl, StrCol>
    val Position: Field<SimpleUsageDsl, LongCol>
}


class SimpleUsageOrderDsl internal constructor(columns: SimpleUsageDslColumns) : OrderDsl<SimpleUsageDsl, SimpleUsageOrderDsl>(), SimpleUsageDslColumns by columns

class SimpleUsageExtraDsl internal constructor(columns: SimpleUsageDslColumns) : ExtraDsl<SimpleUsageDsl>(), SimpleUsageDslColumns by columns

class SimpleUsageSetDsl(columns: SimpleUsageDslColumns) : SetDsl<SimpleUsageDsl>(), SimpleUsageDslColumns by columns

typealias SimpleUsageT = FullDslTable<TableInfo<SimpleUsage>, SimpleUsageDsl, SimpleUsageOrderDsl, SimpleUsageExtraDsl, SimpleUsageSetDsl>


class SimpleUsageDsl internal constructor(                     context: DslContext<SimpleUsageDsl>
) : WhereDsl<SimpleUsageDsl>(context), SimpleUsageDslColumns {
    override val SubjTable get() = column(SimpleUsageFields.SubjTable)
    override val SubjId get() = column(SimpleUsageFields.SubjId)
    override val ObjTable get() = column(SimpleUsageFields.ObjTable)
    override val ObjId get() = column(SimpleUsageFields.ObjId)
    override val Int1 get() = column(SimpleUsageFields.Int1)
    override val Bool1 get() = column(SimpleUsageFields.Bool1)
    override val Str1 get() = column(SimpleUsageFields.Str1)
    override val Position get() = column(SimpleUsageFields.Position)
}

private object SimpleUsageFields {
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
