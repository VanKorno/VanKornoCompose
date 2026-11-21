package com.vankorno.vankornocompose._entities.pic.old.to100.to10

import com.vankorno.vankornodb.api.DbEntity

data class V4_Pic(
                                  val path: String = "",
                           val previewPath: String = "",
                             val createdAt: Long = 0L,
                             val updatedAt: Long = 0L,
                          val becameUnused: Long = 0L,
                                 val notes: String = "",
    
                           override val id: Long = -1L,
) : DbEntity
