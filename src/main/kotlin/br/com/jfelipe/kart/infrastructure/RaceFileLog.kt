package br.com.jfelipe.kart.infrastructure

import br.com.jfelipe.kart.domain.race.Lap
import br.com.jfelipe.kart.domain.race.Race
import br.com.jfelipe.kart.domain.shared.Pilot
import java.nio.charset.StandardCharsets.UTF_8
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

class RaceFileLog {

    companion object {
        private val ENCODING = UTF_8

        private val pilots = mutableSetOf<Pilot>()

        @JvmStatic
        fun read(fileName: String): Race {
            val path = Paths.get(fileName)

            Files.newBufferedReader(path, ENCODING).use {
                // skip 1 eh para pular o cabecalho
                val laps = it.lines().skip(1).map { line ->
                    val regex = RaceRegexPatterns.FULL.toRegex()
                    val matchResult = regex.find(line) ?: throw IllegalArgumentException("Invalid log format")

                    val (hour, idPilot, namePilot, lap, time, averageSpeed) = matchResult.destructured

                    val pilot = buildPilot(idPilot, namePilot)

                    Lap.Builder()
                            .hour(hour)
                            .number(lap)
                            .time(time)
                            .averageSpeed(averageSpeed)
                            .pilot(pilot)
                            .build()
                }.toList()

                return Race(laps)
            }
        }

        private fun buildPilot(idPilot: String, namePilot: String): Pilot {
            val pilot = Pilot.Builder()
                    .id(idPilot)
                    .name(namePilot)
                    .build()

            if (pilots.add(pilot)) {
                return pilot
            } else {
                return pilots.stream()
                        .filter { it == pilot }
                        .findFirst()
                        .get()
            }
        }
    }
}