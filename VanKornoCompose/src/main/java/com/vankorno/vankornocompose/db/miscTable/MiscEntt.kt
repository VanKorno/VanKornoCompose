package com.vankorno.vankornocompose.db.miscTable

import android.database.sqlite.SQLiteDatabase
import android.os.Parcelable
import com.vankorno.vankornocompose.db.LibDbVals._TTTMisc
import com.vankorno.vankornodb.add.addObj
import com.vankorno.vankornodb.api.CurrEntity
import com.vankorno.vankornodb.api.EntityColumns
import com.vankorno.vankornodb.dbManagement.data.CurrSchemaBundle
import com.vankorno.vankornodb.dbManagement.data.bCol
import com.vankorno.vankornodb.dbManagement.data.fCol
import com.vankorno.vankornodb.dbManagement.data.iCol
import com.vankorno.vankornodb.dbManagement.data.lCol
import com.vankorno.vankornodb.dbManagement.data.sCol
import com.vankorno.vankornodb.misc.getBoolean
import kotlinx.parcelize.Parcelize

@Parcelize
data class MiscEntt(
                                  val name: String = "",
                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",
                                 val long1: Long = 0L,
                                val float1: Float = 0F,
                           override val id: Int = -1,
) : CurrEntity, Parcelable {
    fun insert(db: SQLiteDatabase) = db.addObj(_TTTMisc, this)
}





object _Misc : CurrSchemaBundle<MiscEntt>(
    clazz = MiscEntt::class,

    columns = CMisc,

    getter = { cursor ->
        var idx = 0

        MiscEntt(
            name = cursor.getString(idx++),
            int1 = cursor.getInt(idx++),
            bool1 = cursor.getBoolean(idx++),
            str1 = cursor.getString(idx++),
            long1 = cursor.getLong(idx++),
            float1 = cursor.getFloat(idx++),
            id = cursor.getInt(idx++)
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

    withId = { obj, newId -> obj.copy(id = newId) }
)





object CMisc : EntityColumns {
    val Name = sCol("name", "")
    val Int1 = iCol("int1", 0)
    val Bool1 = bCol("bool1", false)
    val Str1 = sCol("str1", "")
    val Long1 = lCol("long1", 0L)
    val Float1 = fCol("float1", 0F)
    val Id = iCol("id", -1)

    override val columns = buildColList {
        +Name
        +Int1
        +Bool1
        +Str1
        +Long1
        +Float1
        +Id
    }
}