package com.vankorno.vankornocompose._entities.pic

import android.os.Parcelable
import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.set.dsl.SetDsl
import kotlinx.parcelize.Parcelize

@Parcelize
data class Pic(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
    
                           override val id: Long = -1L,
) : DbEntity, Parcelable





object _Pic : SchemaBundle<Pic>(
    clazz = Pic::class,

    getter = { cursor ->
        var idx = 0

        Pic(
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
        +PicFields.Path
        +PicFields.PreviewPath
        +PicFields.CreatedAt
        +PicFields.UpdatedAt
        +PicFields.BecameUnused
        +PicFields.Notes
        +PicFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::PicDsl, ::PicOrderDsl, ::PicExtraDsl, ::PicSetDsl)
}


interface PicDslColumns {
    val Path: Field<PicDsl, StrCol>
    val PreviewPath: Field<PicDsl, StrCol>
    val CreatedAt: Field<PicDsl, LongCol>
    val UpdatedAt: Field<PicDsl, LongCol>
    val BecameUnused: Field<PicDsl, LongCol>
    val Notes: Field<PicDsl, StrCol>
    val Id: Field<PicDsl, LongCol>
}


class PicOrderDsl internal constructor(columns: PicDslColumns) : OrderDsl<PicDsl, PicOrderDsl>(), PicDslColumns by columns

class PicExtraDsl internal constructor(columns: PicDslColumns) : ExtraDsl<PicDsl>(), PicDslColumns by columns

class PicSetDsl(columns: PicDslColumns) : SetDsl<PicDsl>(), PicDslColumns by columns

typealias PicT = FullDslTable<TableInfo<Pic>, PicDsl, PicOrderDsl, PicExtraDsl, PicSetDsl>


class PicDsl internal constructor(                     context: DslContext<PicDsl>
) : WhereDsl<PicDsl>(context), PicDslColumns {
    override val Path get() = column(PicFields.Path)
    override val PreviewPath get() = column(PicFields.PreviewPath)
    override val CreatedAt get() = column(PicFields.CreatedAt)
    override val UpdatedAt get() = column(PicFields.UpdatedAt)
    override val BecameUnused get() = column(PicFields.BecameUnused)
    override val Notes get() = column(PicFields.Notes)
    override val Id get() = column(PicFields.Id)
}

private object PicFields {
    val Path = sCol("path", "")
    val PreviewPath = sCol("previewPath", "")
    val CreatedAt = lCol("createdAt", 0L)
    val UpdatedAt = lCol("updatedAt", 0L)
    val BecameUnused = lCol("becameUnused", 0L)
    val Notes = sCol("notes", "")
    val Id = lCol("id", -1L)
}
