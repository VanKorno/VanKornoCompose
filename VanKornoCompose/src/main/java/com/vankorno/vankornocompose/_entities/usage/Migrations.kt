package com.vankorno.vankornocompose._entities.usage

import com.vankorno.vankornocompose._entities.usage.old.to100.to10._V1_Usage
import com.vankorno.vankornodb.api.defineMigrations

fun migrationsUsage() = defineMigrations(UsageMeta) {
    version(1, _V1_Usage)
}