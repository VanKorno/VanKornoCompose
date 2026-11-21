package com.vankorno.vankornocompose._entities.usage.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity

data class V1_Usage(
                             val subjTable: String = "",
                                val subjId: Long = -1L,

                              val objTable: String = "",
                                 val objId: Long = -1L,

                                  val int1: Int = 0,
                                 val bool1: Boolean = false,
                                  val str1: String = "",

                              val position: Long = 0L,
                           override val id: Long = -1L,
) : DbEntity
