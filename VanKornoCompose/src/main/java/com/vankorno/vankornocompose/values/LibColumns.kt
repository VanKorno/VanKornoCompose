package com.vankorno.vankornocompose.values

import com.vankorno.vankornodb.dbManagement.data.bCol
import com.vankorno.vankornodb.dbManagement.data.fCol
import com.vankorno.vankornodb.dbManagement.data.iCol
import com.vankorno.vankornodb.dbManagement.data.lCol
import com.vankorno.vankornodb.dbManagement.data.sCol

/**
 * Just some commonly used columns
 */
object LibColumns {
    val cInfo by lazy { sCol("info") }
    
    val cInt by lazy { iCol("cInt") }
    val cStr by lazy { sCol("cStr") }
    val cBool by lazy { bCol("cBool") }
    val cLong by lazy { lCol("cLong") }
    val cFloat by lazy { fCol("cFloat") }
}
