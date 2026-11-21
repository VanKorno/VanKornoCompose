package com.vankorno.vankornocompose._entities.misc

import android.os.Parcelable
import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.misc.getBoolean
import com.vankorno.vankornodb.set.dsl.SetDsl
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiscData(
                                  val name: String = "",
                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",
                                 val long1: Long = 0L,
                                val float1: Float = 0F,
                           override val id: Long = -1L,
) : DbEntity, Parcelable





object _MiscData : SchemaBundle<MiscData>(
    clazz = MiscData::class,

    getter = { cursor ->
        var idx = 0

        MiscData(
            name = cursor.getString(idx++),
            int1 = cursor.getInt(idx++),
            bool1 = cursor.getBoolean(idx++),
            str1 = cursor.getString(idx++),
            long1 = cursor.getLong(idx++),
            float1 = cursor.getFloat(idx++),
            id = cursor.getLong(idx++)
        )
    },

    setter = { e, cv ->
        cv.put("name", e.name)
        cv.put("int1", e.int1)
        cv.put("bool1", e.bool1)
        cv.put("str1", e.str1)
        cv.put("long1", e.long1)
        cv.put("float1", e.float1)
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) },

    columns = buildColList {
        +MiscDataFields.Name
        +MiscDataFields.Int1
        +MiscDataFields.Bool1
        +MiscDataFields.Str1
        +MiscDataFields.Long1
        +MiscDataFields.Float1
        +MiscDataFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::MiscDataDsl, ::MiscDataOrderDsl, ::MiscDataExtraDsl, ::MiscDataSetDsl)
}


interface MiscDataDslColumns {
    val Name: Field<MiscDataDsl, StrCol>
    val Int1: Field<MiscDataDsl, IntCol>
    val Bool1: Field<MiscDataDsl, BoolCol>
    val Str1: Field<MiscDataDsl, StrCol>
    val Long1: Field<MiscDataDsl, LongCol>
    val Float1: Field<MiscDataDsl, FloatCol>
    val Id: Field<MiscDataDsl, LongCol>
}


class MiscDataOrderDsl internal constructor(columns: MiscDataDslColumns) : OrderDsl<MiscDataDsl, MiscDataOrderDsl>(), MiscDataDslColumns by columns

class MiscDataExtraDsl internal constructor(columns: MiscDataDslColumns) : ExtraDsl<MiscDataDsl>(), MiscDataDslColumns by columns

class MiscDataSetDsl(columns: MiscDataDslColumns) : SetDsl<MiscDataDsl>(), MiscDataDslColumns by columns

typealias MiscDataT = FullDslTable<TableInfo<MiscData>, MiscDataDsl, MiscDataOrderDsl, MiscDataExtraDsl, MiscDataSetDsl>


class MiscDataDsl internal constructor(                     context: DslContext<MiscDataDsl>
) : WhereDsl<MiscDataDsl>(context), MiscDataDslColumns {
    override val Name get() = column(MiscDataFields.Name)
    override val Int1 get() = column(MiscDataFields.Int1)
    override val Bool1 get() = column(MiscDataFields.Bool1)
    override val Str1 get() = column(MiscDataFields.Str1)
    override val Long1 get() = column(MiscDataFields.Long1)
    override val Float1 get() = column(MiscDataFields.Float1)
    override val Id get() = column(MiscDataFields.Id)
}

private object MiscDataFields {
    val Name = sCol("name", "")
    val Int1 = iCol("int1", 0)
    val Bool1 = bCol("bool1", false)
    val Str1 = sCol("str1", "")
    val Long1 = lCol("long1", 0L)
    val Float1 = fCol("float1", 0F)
    val Id = lCol("id", -1L)
}
