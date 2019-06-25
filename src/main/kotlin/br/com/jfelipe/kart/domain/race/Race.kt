package br.com.jfelipe.kart.domain.race

import br.com.jfelipe.kart.domain.podium.Podium
import br.com.jfelipe.kart.domain.podium.Rank
import br.com.jfelipe.kart.domain.shared.Pilot
import java.time.LocalTime

data class Race(val laps: List<Lap>) {

    fun podium(): Podium {
        val ranks = arrayListOf<Rank>()

        pilotsByRanking().forEachIndexed { index, pilot ->
            val rank = Rank.Builder()
                    .place(index + 1)
                    .pilot(pilot)
                    .totalTime(totalTimeByPilot(pilot))
                    .totalLaps(totalLapsByPilot(pilot)!!)
                    .build()

            ranks.add(rank)
        }

        return Podium(ranks)
    }

    private fun pilotsByRanking() = laps
            .sortedWith(compareByDescending(Lap::number).thenComparing(compareBy(Lap::hour)))
            .map(Lap::pilot)
            .toCollection(mutableSetOf())

    private fun totalLapsByPilot(pilot: Pilot) = laps
            .filter { it.pilot == pilot }
            .maxWith(compareBy(Lap::number))
            ?.number

    private fun totalTimeByPilot(pilot: Pilot): LocalTime {
        val duration = laps
                .filter { it.pilot == pilot }
                .map { it.time.toNanoOfDay() }
                .sum()

        return LocalTime.ofNanoOfDay(duration)
    }
}