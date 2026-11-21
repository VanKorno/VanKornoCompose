package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.set.dsl.SetDsl

data class V3_Pic(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                val usages: Int = 0,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
                           override val id: Long = -1L,
) : DbEntity





object _V3_Pic : SchemaBundle<V3_Pic>(
    clazz = V3_Pic::class,

    getter = { cursor ->
        var idx = 0

        V3_Pic(
            path = cursor.getString(idx++),
            previewPath = cursor.getString(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
            usages = cursor.getInt(idx++),
            becameUnused = cursor.getLong(idx++),
            notes = cursor.getString(idx++),
            id = cursor.getLong(idx++)
        )
    },

    setter = { e, cv ->
        cv.put("path", e.path)
        cv.put("previewPath", e.previewPath)
        cv.put("createdAt", e.createdAt)
        cv.put("updatedAt", e.updatedAt)
        cv.put("usages", e.usages)
        cv.put("becameUnused", e.becameUnused)
        cv.put("notes", e.notes)
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) },

    columns = buildColList {
        +V3_PicFields.Path
        +V3_PicFields.PreviewPath
        +V3_PicFields.CreatedAt
        +V3_PicFields.UpdatedAt
        +V3_PicFields.Usages
        +V3_PicFields.BecameUnused
        +V3_PicFields.Notes
        +V3_PicFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::V3_PicDsl, ::V3_PicOrderDsl, ::V3_PicExtraDsl, ::V3_PicSetDsl)
}


interface V3_PicDslColumns {
    val Path: Field<V3_PicDsl, StrCol>
    val PreviewPath: Field<V3_PicDsl, StrCol>
    val CreatedAt: Field<V3_PicDsl, LongCol>
    val UpdatedAt: Field<V3_PicDsl, LongCol>
    val Usages: Field<V3_PicDsl, IntCol>
    val BecameUnused: Field<V3_PicDsl, LongCol>
    val Notes: Field<V3_PicDsl, StrCol>
    val Id: Field<V3_PicDsl, LongCol>
}


class V3_PicOrderDsl internal constructor(columns: V3_PicDslColumns) : OrderDsl<V3_PicDsl, V3_PicOrderDsl>(), V3_PicDslColumns by columns

class V3_PicExtraDsl internal constructor(columns: V3_PicDslColumns) : ExtraDsl<V3_PicDsl>(), V3_PicDslColumns by columns

class V3_PicSetDsl(columns: V3_PicDslColumns) : SetDsl<V3_PicDsl>(), V3_PicDslColumns by columns

typealias V3_PicT = FullDslTable<TableInfo<V3_Pic>, V3_PicDsl, V3_PicOrderDsl, V3_PicExtraDsl, V3_PicSetDsl>


class V3_PicDsl internal constructor(                     context: DslContext<V3_PicDsl>
) : WhereDsl<V3_PicDsl>(context), V3_PicDslColumns {
    override val Path get() = column(V3_PicFields.Path)
    override val PreviewPath get() = column(V3_PicFields.PreviewPath)
    override val CreatedAt get() = column(V3_PicFields.CreatedAt)
    override val UpdatedAt get() = column(V3_PicFields.UpdatedAt)
    override val Usages get() = column(V3_PicFields.Usages)
    override val BecameUnused get() = column(V3_PicFields.BecameUnused)
    override val Notes get() = column(V3_PicFields.Notes)
    override val Id get() = column(V3_PicFields.Id)
}

private object V3_PicFields {
    val Path = sCol("path", "")
    val PreviewPath = sCol("previewPath", "")
    val CreatedAt = lCol("createdAt", 0L)
    val UpdatedAt = lCol("updatedAt", 0L)
    val Usages = iCol("usages", 0)
    val BecameUnused = lCol("becameUnused", 0L)
    val Notes = sCol("notes", "")
    val Id = lCol("id", -1L)
}
