package br.com.jfelipe.kart.feature.stage

import br.com.jfelipe.kart.domain.race.Race
import br.com.jfelipe.kart.infrastructure.RaceFileLog
import com.tngtech.jgiven.Stage
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

open class KartLogFileStage : Stage<KartLogFileStage>() {

    var file: String? = null
    var race: Race? = null
    var expectedException: Exception? = null

    // Given
    fun `a kart log file`(file: String) = apply { this.file = file }

    // When
    fun `the file is processed`(): KartLogFileStage {
        try {
            this.race = RaceFileLog.read(file!!)
        } catch (e: IllegalArgumentException) {
            expectedException = e
        }

        return this
    }

    // Then
    fun `the race rank must be returned`(): KartLogFileStage {
        race?.run {
            assertEquals(true, podium().rank.isNotEmpty())
        }

        return this
    }

    fun `f massa is champions`(): KartLogFileStage {
        race?.run {
            val count = podium().rank.filter { it.place == 1 && it.pilot.name == "F.MASSA" }.count()

            assertEquals(1, count)
        }

        return this
    }

    fun `an error must occur`(): KartLogFileStage {
        assertNotNull(expectedException)
        return this
    }
}