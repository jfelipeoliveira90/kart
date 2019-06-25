package br.com.jfelipe.kart.domain.podium

import br.com.jfelipe.kart.domain.shared.Pilot
import java.time.LocalTime

data class Rank(
        val place: Int,
        val pilot: Pilot,
        val totalLaps: Int,
        val totalTime: LocalTime
) {

    class Builder {
        private var place: Int? = null
        private var pilot: Pilot? = null
        private var totalLaps: Int? = null
        private var totalTime: LocalTime? = null

        fun place(place: Int) = apply { this.place = place }
        fun pilot(pilot: Pilot) = apply { this.pilot = pilot }
        fun totalLaps(totalLaps: Int) = apply { this.totalLaps = totalLaps }
        fun totalTime(totalTime: LocalTime) = apply { this.totalTime = totalTime }

        fun build() = Rank(
                place ?: error("Place is required"),
                pilot ?: error("Pilot is required"),
                totalLaps ?: error("Total laps is required"),
                totalTime ?: error("Total time is required")
        )
    }
}