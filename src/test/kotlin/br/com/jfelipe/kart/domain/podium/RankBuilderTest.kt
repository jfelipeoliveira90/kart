package br.com.jfelipe.kart.domain.podium

import br.com.jfelipe.kart.domain.shared.Pilot
import org.junit.Test
import java.time.LocalTime

class RankBuilderTest {

    @Test(expected = IllegalStateException::class)
    fun `build rank without place`() {
        Rank.Builder()
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .totalTime(LocalTime.now())
                .totalLaps(4)
                .build()

    }

    @Test(expected = IllegalStateException::class)
    fun `build rank without pilot`() {
        Rank.Builder()
                .place(1)
                .totalTime(LocalTime.now())
                .totalLaps(4)
                .build()

    }

    @Test(expected = IllegalStateException::class)
    fun `build rank without total time`() {
        Rank.Builder()
                .place(1)
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .totalLaps(4)
                .build()

    }

    @Test(expected = IllegalStateException::class)
    fun `build rank without total laps`() {
        Rank.Builder()
                .place(1)
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .totalTime(LocalTime.now())
                .build()

    }
}