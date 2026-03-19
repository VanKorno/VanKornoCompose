package com.vankorno.vankornocompose._entities.usage

import com.vankorno.vankornodb.api.defineMigrations

fun migrationsUsage() = defineMigrations(UsageMeta) {
    version(1, _Usage)
}