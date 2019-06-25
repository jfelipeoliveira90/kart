package br.com.jfelipe.kart.domain.race

import br.com.jfelipe.kart.domain.shared.Pilot
import org.junit.Test

class LapBuilderTest {

    @Test(expected = IllegalStateException::class)
    fun `build lap without hour`() {
        Lap.Builder()
                .number("4")
                .time("1:59.999")
                .averageSpeed("43,6")
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .build()
    }

    @Test(expected = IllegalStateException::class)
    fun `build lap without number`() {
        Lap.Builder()
                .hour("23:49:08.277")
                .time("1:59.999")
                .averageSpeed("43,6")
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .build()
    }

    @Test(expected = IllegalStateException::class)
    fun `build lap without time`() {
        Lap.Builder()
                .hour("23:49:08.277")
                .number("4")
                .averageSpeed("43,6")
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .build()
    }

    @Test(expected = IllegalStateException::class)
    fun `build lap without average speed`() {
        Lap.Builder()
                .hour("23:49:08.277")
                .number("4")
                .time("1:59.999")
                .pilot(Pilot.Builder().id("123").name("FELIPE").build())
                .build()
    }

    @Test(expected = IllegalStateException::class)
    fun `build lap without pilot`() {
        Lap.Builder()
                .hour("23:49:08.277")
                .number("4")
                .time("1:59.999")
                .averageSpeed("43,6")
                .build()
    }
}