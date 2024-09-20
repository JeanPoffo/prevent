package com.app.prevention.dao

import com.app.prevention.model.Schedule
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.javatime.datetime
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

object ScheduleDao: IntIdTable("schedules") {
    val transporter = varchar("transporter", 100)
    val dateTime = datetime("date_time")
}

fun ScheduleDao.insert(schedule: Schedule) = transaction {
    this@insert.insert {
        it[transporter] = schedule.transporter.get()
        it[dateTime] = schedule.dateTime.get()
    }
}

fun ScheduleDao.update(schedule: Schedule) = transaction {
    if (schedule.id != null) {
        this@update.update({ ScheduleDao.id eq schedule.id }) {
            it[transporter] = schedule.transporter.get()
            it[dateTime] = schedule.dateTime.get()
        }
    } else {
        error("Missing Transporter ID")
    }
}

fun ScheduleDao.delete(schedule: Schedule) = transaction {
    if (schedule.id != null) {
        this@delete.deleteWhere { ScheduleDao.id eq schedule.id }
    } else {
        error("Missing Transporter ID")
    }
}

fun ResultRow.toScheduleModel() = Schedule(
    id = this[ScheduleDao.id].value,
    transporter = this[ScheduleDao.transporter],
    dateTime = this[ScheduleDao.dateTime]
)
