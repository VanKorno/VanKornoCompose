package com.vankorno.vankornocompose._entities.usage

import android.os.Parcelable
import com.vankorno.vankornodb.api.CurrEntity
import com.vankorno.vankornodb.api.EntityColumns
import com.vankorno.vankornodb.dbManagement.data.CurrSchemaBundle
import com.vankorno.vankornodb.dbManagement.data.bCol
import com.vankorno.vankornodb.dbManagement.data.iCol
import com.vankorno.vankornodb.dbManagement.data.sCol
import com.vankorno.vankornodb.misc.getBoolean
import kotlinx.parcelize.Parcelize

@Parcelize
data class UsageEntt(
                             val subjTable: String = "",
                                val subjId: Int = -1,

                              val objTable: String = "",
                                 val objId: Int = -1,

                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",

                           override val id: Int = -1,
) : CurrEntity, Parcelable





object _Usage : CurrSchemaBundle<UsageEntt>(
    clazz = UsageEntt::class,

    columns = CUsage,

    getter = { cursor ->
        var idx = 0

        UsageEntt(
            subjTable = cursor.getString(idx++),
            subjId = cursor.getInt(idx++),
            objTable = cursor.getString(idx++),
            objId = cursor.getInt(idx++),
            int1 = cursor.getInt(idx++),
            bool1 = cursor.getBoolean(idx++),
            str1 = cursor.getString(idx++),
            id = cursor.getInt(idx++)
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
        cv.put("id", e.id)
        cv
    },

    withId = { obj, newId -> obj.copy(id = newId) }
)





object CUsage : EntityColumns {
    val SubjTable = sCol("subjTable", "")
    val SubjId = iCol("subjId", -1)
    val ObjTable = sCol("objTable", "")
    val ObjId = iCol("objId", -1)
    val Int1 = iCol("int1", 0)
    val Bool1 = bCol("bool1", false)
    val Str1 = sCol("str1", "")
    val Id = iCol("id", -1)

    override val columns = buildColList {
        +SubjTable
        +SubjId
        +ObjTable
        +ObjId
        +Int1
        +Bool1
        +Str1
        +Id
    }
}