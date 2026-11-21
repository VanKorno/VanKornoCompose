package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity
import com.vankorno.vankornodb.core.dsl.*
import com.vankorno.vankornodb.dbManagement.data.*
import com.vankorno.vankornodb.set.dsl.SetDsl

data class V1_Pic(
                                  val path: String = "",
                           val variantPath: String = "",
                             val thumbBlob: ByteArray = byteArrayOf(),
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                 val notes: String = "",

                           override val id: Long = -1L,

) : DbEntity





object _V1_Pic : SchemaBundle<V1_Pic>(
    clazz = V1_Pic::class,

    getter = { cursor ->
        var idx = 0

        V1_Pic(
            path = cursor.getString(idx++),
            variantPath = cursor.getString(idx++),
            thumbBlob = cursor.getBlob(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
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
        cv.put("notes", e.notes)
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) },

    columns = buildColList {
        +V1_PicFields.Path
        +V1_PicFields.VariantPath
        +V1_PicFields.ThumbBlob
        +V1_PicFields.CreatedAt
        +V1_PicFields.UpdatedAt
        +V1_PicFields.Notes
        +V1_PicFields.Id
    },
) {
    fun tableNamed(name: String) = (name using this).withDsl(::V1_PicDsl, ::V1_PicOrderDsl, ::V1_PicExtraDsl, ::V1_PicSetDsl)
}


interface V1_PicDslColumns {
    val Path: Field<V1_PicDsl, StrCol>
    val VariantPath: Field<V1_PicDsl, StrCol>
    val ThumbBlob: Field<V1_PicDsl, BlobCol>
    val CreatedAt: Field<V1_PicDsl, LongCol>
    val UpdatedAt: Field<V1_PicDsl, LongCol>
    val Notes: Field<V1_PicDsl, StrCol>
    val Id: Field<V1_PicDsl, LongCol>
}


class V1_PicOrderDsl internal constructor(columns: V1_PicDslColumns) : OrderDsl<V1_PicDsl, V1_PicOrderDsl>(), V1_PicDslColumns by columns

class V1_PicExtraDsl internal constructor(columns: V1_PicDslColumns) : ExtraDsl<V1_PicDsl>(), V1_PicDslColumns by columns

class V1_PicSetDsl(columns: V1_PicDslColumns) : SetDsl<V1_PicDsl>(), V1_PicDslColumns by columns

typealias V1_PicT = FullDslTable<TableInfo<V1_Pic>, V1_PicDsl, V1_PicOrderDsl, V1_PicExtraDsl, V1_PicSetDsl>


class V1_PicDsl internal constructor(                     context: DslContext<V1_PicDsl>
) : WhereDsl<V1_PicDsl>(context), V1_PicDslColumns {
    override val Path get() = column(V1_PicFields.Path)
    override val VariantPath get() = column(V1_PicFields.VariantPath)
    override val ThumbBlob get() = column(V1_PicFields.ThumbBlob)
    override val CreatedAt get() = column(V1_PicFields.CreatedAt)
    override val UpdatedAt get() = column(V1_PicFields.UpdatedAt)
    override val Notes get() = column(V1_PicFields.Notes)
    override val Id get() = column(V1_PicFields.Id)
}

private object V1_PicFields {
    val Path = sCol("path", "")
    val VariantPath = sCol("variantPath", "")
    val ThumbBlob = pCol("thumbBlob", byteArrayOf())
    val CreatedAt = lCol("createdAt", 0L)
    val UpdatedAt = lCol("updatedAt", 0L)
    val Notes = sCol("notes", "")
    val Id = lCol("id", -1L)
}
