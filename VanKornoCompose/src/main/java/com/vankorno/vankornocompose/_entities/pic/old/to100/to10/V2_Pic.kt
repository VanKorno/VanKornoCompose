package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.set.dsl.SetDsl

data class V2_Pic(
                                  val path: String = "",
                           val variantPath: String = "",
                             val thumbBlob: ByteArray = byteArrayOf(),
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                val usages: Int = 0,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
                           override val id: Long = -1L,
) : DbEntity





object _V2_Pic : SchemaBundle<V2_Pic>(
    clazz = V2_Pic::class,

    getter = { cursor ->
        var idx = 0

        V2_Pic(
            path = cursor.getString(idx++),
            variantPath = cursor.getString(idx++),
            thumbBlob = cursor.getBlob(idx++),
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
        cv.put("variantPath", e.variantPath)
        cv.put("thumbBlob", e.thumbBlob)
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
        +V2_PicFields.Path
        +V2_PicFields.VariantPath
        +V2_PicFields.ThumbBlob
        +V2_PicFields.CreatedAt
        +V2_PicFields.UpdatedAt
        +V2_PicFields.Usages
        +V2_PicFields.BecameUnused
        +V2_PicFields.Notes
        +V2_PicFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::V2_PicDsl, ::V2_PicOrderDsl, ::V2_PicExtraDsl, ::V2_PicSetDsl)
}


interface V2_PicDslColumns {
    val Path: Field<V2_PicDsl, StrCol>
    val VariantPath: Field<V2_PicDsl, StrCol>
    val ThumbBlob: Field<V2_PicDsl, BlobCol>
    val CreatedAt: Field<V2_PicDsl, LongCol>
    val UpdatedAt: Field<V2_PicDsl, LongCol>
    val Usages: Field<V2_PicDsl, IntCol>
    val BecameUnused: Field<V2_PicDsl, LongCol>
    val Notes: Field<V2_PicDsl, StrCol>
    val Id: Field<V2_PicDsl, LongCol>
}


class V2_PicOrderDsl internal constructor(columns: V2_PicDslColumns) : OrderDsl<V2_PicDsl, V2_PicOrderDsl>(), V2_PicDslColumns by columns

class V2_PicExtraDsl internal constructor(columns: V2_PicDslColumns) : ExtraDsl<V2_PicDsl>(), V2_PicDslColumns by columns

class V2_PicSetDsl(columns: V2_PicDslColumns) : SetDsl<V2_PicDsl>(), V2_PicDslColumns by columns

typealias V2_PicT = FullDslTable<TableInfo<V2_Pic>, V2_PicDsl, V2_PicOrderDsl, V2_PicExtraDsl, V2_PicSetDsl>


class V2_PicDsl internal constructor(                     context: DslContext<V2_PicDsl>
) : WhereDsl<V2_PicDsl>(context), V2_PicDslColumns {
    override val Path get() = column(V2_PicFields.Path)
    override val VariantPath get() = column(V2_PicFields.VariantPath)
    override val ThumbBlob get() = column(V2_PicFields.ThumbBlob)
    override val CreatedAt get() = column(V2_PicFields.CreatedAt)
    override val UpdatedAt get() = column(V2_PicFields.UpdatedAt)
    override val Usages get() = column(V2_PicFields.Usages)
    override val BecameUnused get() = column(V2_PicFields.BecameUnused)
    override val Notes get() = column(V2_PicFields.Notes)
    override val Id get() = column(V2_PicFields.Id)
}

private object V2_PicFields {
    val Path = sCol("path", "")
    val VariantPath = sCol("variantPath", "")
    val ThumbBlob = pCol("thumbBlob", byteArrayOf())
    val CreatedAt = lCol("createdAt", 0L)
    val UpdatedAt = lCol("updatedAt", 0L)
    val Usages = iCol("usages", 0)
    val BecameUnused = lCol("becameUnused", 0L)
    val Notes = sCol("notes", "")
    val Id = lCol("id", -1L)
}
