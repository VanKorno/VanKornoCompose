package com.vankorno.vankornocompose._entities.pic

import android.os.Parcelable
import com.vankorno.vankornodb.api.CurrEntity
import com.vankorno.vankornodb.api.EntityColumns
import com.vankorno.vankornodb.dbManagement.data.CurrSchemaBundle
import com.vankorno.vankornodb.dbManagement.data.iCol
import com.vankorno.vankornodb.dbManagement.data.lCol
import com.vankorno.vankornodb.dbManagement.data.sCol
import kotlinx.parcelize.Parcelize

@Parcelize
data class PicEntt(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                                val usages: Int = 0,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
                           override val id: Int = -1,
) : CurrEntity, Parcelable





object _Pic : CurrSchemaBundle<PicEntt>(
    clazz = PicEntt::class,

    columns = CPic,

    getter = { cursor ->
        var idx = 0

        PicEntt(
            path = cursor.getString(idx++),
            previewPath = cursor.getString(idx++),
            createdAt = cursor.getLong(idx++),
            updatedAt = cursor.getLong(idx++),
            usages = cursor.getInt(idx++),
            becameUnused = cursor.getLong(idx++),
            notes = cursor.getString(idx++),
            id = cursor.getInt(idx++)
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

    withId = { obj, newId -> obj.copy(id = newId) }
)





object CPic : EntityColumns {
    val Path = sCol("path", "")
    val PreviewPath = sCol("previewPath", "")
    val CreatedAt = lCol("createdAt", 0L)
    val UpdatedAt = lCol("updatedAt", 0L)
    val Usages = iCol("usages", 0)
    val BecameUnused = lCol("becameUnused", 0L)
    val Notes = sCol("notes", "")
    val Id = iCol("id", -1)

    override val columns = buildColList {
        +Path
        +PreviewPath
        +CreatedAt
        +UpdatedAt
        +Usages
        +BecameUnused
        +Notes
        +Id
    }
}
