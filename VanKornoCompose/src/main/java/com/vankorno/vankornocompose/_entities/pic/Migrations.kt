package com.vankorno.vankornocompose._entities.pic

import com.vankorno.vankornocompose._entities.pic.old.to100.to10.V1_Pic
import com.vankorno.vankornocompose._entities.pic.old.to100.to10.V2_Pic
import com.vankorno.vankornocompose._entities.pic.old.to100.to10.V3_Pic
import com.vankorno.vankornodb.api.defineMigrations

fun migrationsPic() = defineMigrations(PicMeta) {
    version(1, V1_Pic::class)
    version(2, V2_Pic::class)
    version(3, V3_Pic::class)
    version(4, Pic::class)
}
