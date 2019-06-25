package br.com.jfelipe.kart.domain.race

import br.com.jfelipe.kart.domain.shared.Pilot
import java.time.LocalTime
import java.time.format.DateTimeFormatter.ISO_LOCAL_TIME
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField.HOUR_OF_DAY

data class Lap(
        val hour: LocalTime,
        val number: Int,
        val time: LocalTime,
        val averageSpeed: Float,
        val pilot: Pilot
) {

    companion object {
        private val MAX_LAPS = 4

        private val LOCAL_TIME_WITHOUT_HOURS = DateTimeFormatterBuilder()
                .appendPattern("m:ss.SSS")
                .parseDefaulting(HOUR_OF_DAY, 0)
                .toFormatter()
    }

    class Builder {
        private var hour: LocalTime? = null
        private var number: Int? = null
        private var time: LocalTime? = null
        private var averageSpeed: Float? = null
        private var pilot: Pilot? = null

        fun hour(hour: String) = apply { this.hour = LocalTime.parse(hour, ISO_LOCAL_TIME) }

        fun number(number: String): Builder {
            if (number.toInt() > MAX_LAPS) {
                throw IllegalArgumentException("Number max laps are $MAX_LAPS")
            } else {
                this.number = number.toInt()
            }

            return this
        }

        fun time(time: String) = apply { this.time = LocalTime.parse(time, LOCAL_TIME_WITHOUT_HOURS) }
        fun averageSpeed(averageSpeed: String) = apply { this.averageSpeed = averageSpeed.replace(',', '.').toFloat() }
        fun pilot(pilot: Pilot) = apply { this.pilot = pilot }

        fun build() = Lap(
                hour ?: error("Lap hour is required"),
                number ?: error("Lap number is required"),
                time ?: error("Lap time is required"),
                averageSpeed ?: error("Lap average speed is required"),
                pilot ?: error("Lap shared is required")
        )
    }
}