package com.app.prevention.configuration

import com.app.prevention.dao.PatientDao
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils.create
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseConfiguration {

    companion object {
        fun initDatabase() {
            Database.connect("jdbc:sqlite:database.db", driver = "org.sqlite.JDBC")
            transaction { create(PatientDao) }
        }
    }

}