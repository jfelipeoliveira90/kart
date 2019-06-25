package br.com.jfelipe.kart.domain.shared

import org.junit.Assert.assertEquals
import org.junit.Test

class PilotBuilderTest {

    @Test(expected = IllegalStateException::class)
    fun `build pilot without id`() {
        Pilot.Builder()
                .name("FELIPE")
                .build()
    }

    @Test(expected = IllegalStateException::class)
    fun `build pilot without name`() {
        Pilot.Builder()
                .id("123")
                .build()
    }

    @Test
    fun `comparing with another type class`() {
        val pilot = Pilot.Builder()
                .id("123")
                .name("FELIPE")
                .build()

        assertEquals(false, pilot.equals(0L))
    }
}