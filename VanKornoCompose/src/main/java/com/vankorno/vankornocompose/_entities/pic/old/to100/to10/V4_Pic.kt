package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.set.dsl.SetDsl

data class V4_Pic(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
    
                           override val id: Long = -1L,
) : DbEntity





object _V4_Pic : SchemaBundle<V4_Pic>(
    clazz = V4_Pic::class,

    getter = { cursor ->
        var idx = 0

        V4_Pic(
            path = cursor.getString(idx++),
            previewPath = cursor.getString(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
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
        cv.put("becameUnused", e.becameUnused)
        cv.put("notes", e.notes)
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) },

    columns = buildColList {
        +V4_PicFields.Path
        +V4_PicFields.PreviewPath
        +V4_PicFields.CreatedAt
        +V4_PicFields.UpdatedAt
        +V4_PicFields.BecameUnused
        +V4_PicFields.Notes
        +V4_PicFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::V4_PicDsl, ::V4_PicOrderDsl, ::V4_PicExtraDsl, ::V4_PicSetDsl)
}


interface V4_PicDslColumns {
    val Path: Field<V4_PicDsl, StrCol>
    val PreviewPath: Field<V4_PicDsl, StrCol>
    val CreatedAt: Field<V4_PicDsl, LongCol>
    val UpdatedAt: Field<V4_PicDsl, LongCol>
    val BecameUnused: Field<V4_PicDsl, LongCol>
    val Notes: Field<V4_PicDsl, StrCol>
    val Id: Field<V4_PicDsl, LongCol>
}


class V4_PicOrderDsl internal constructor(columns: V4_PicDslColumns) : OrderDsl<V4_PicDsl, V4_PicOrderDsl>(), V4_PicDslColumns by columns

class V4_PicExtraDsl internal constructor(columns: V4_PicDslColumns) : ExtraDsl<V4_PicDsl>(), V4_PicDslColumns by columns

class V4_PicSetDsl(columns: V4_PicDslColumns) : SetDsl<V4_PicDsl>(), V4_PicDslColumns by columns

typealias V4_PicT = FullDslTable<TableInfo<V4_Pic>, V4_PicDsl, V4_PicOrderDsl, V4_PicExtraDsl, V4_PicSetDsl>


class V4_PicDsl internal constructor(                     context: DslContext<V4_PicDsl>
) : WhereDsl<V4_PicDsl>(context), V4_PicDslColumns {
    override val Path get() = column(V4_PicFields.Path)
    override val PreviewPath get() = column(V4_PicFields.PreviewPath)
    override val CreatedAt get() = column(V4_PicFields.CreatedAt)
    override val UpdatedAt get() = column(V4_PicFields.UpdatedAt)
    override val BecameUnused get() = column(V4_PicFields.BecameUnused)
    override val Notes get() = column(V4_PicFields.Notes)
    override val Id get() = column(V4_PicFields.Id)
}

private object V4_PicFields {
    val Path = sCol("path", "")
    val PreviewPath = sCol("previewPath", "")
    val CreatedAt = lCol("createdAt", 0L)
    val UpdatedAt = lCol("updatedAt", 0L)
    val BecameUnused = lCol("becameUnused", 0L)
    val Notes = sCol("notes", "")
    val Id = lCol("id", -1L)
}
