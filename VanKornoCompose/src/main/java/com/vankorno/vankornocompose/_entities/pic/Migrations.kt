package com.vankorno.vankornocompose._entities.pic

import com.vankorno.vankornocompose._entities.pic.old.to100.to10._V1_Pic
import com.vankorno.vankornocompose._entities.pic.old.to100.to10._V2_Pic
import com.vankorno.vankornocompose._entities.pic.old.to100.to10._V3_Pic
import com.vankorno.vankornocompose._entities.pic.old.to100.to10._V4_Pic
import com.vankorno.vankornodb.api.defineMigrations

fun migrationsPic() = defineMigrations(PicMeta) {
    version(1, _V1_Pic)
    version(2, _V2_Pic)
    version(3, _V3_Pic)
    version(4, _V4_Pic)
}