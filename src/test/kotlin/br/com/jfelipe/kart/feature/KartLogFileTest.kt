package br.com.jfelipe.kart.feature

import br.com.jfelipe.kart.feature.stage.KartLogFileStage
import com.tngtech.jgiven.junit.SimpleScenarioTest
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(JUnitParamsRunner::class)
class KartLogFileTest : SimpleScenarioTest<KartLogFileStage>() {

    @Test
    @Parameters(method = "parametersValid")
    fun `load kart log file with success`(file: String) {
        given().`a kart log file`(file)
        `when`().`the file is processed`()
        then().`the race rank must be returned`()
                .and()
                .`f massa is champions`()
    }

    @Test
    @Parameters(method = "parametersNotValid")
    fun `not load kart log file`(variation: String, file: String) {
        given().`a kart log file`(file)
        `when`().`the file is processed`()
        then().`an error must occur`()
    }

    private fun parametersValid() = arrayOf(filePathFromResource("kart-valid.log"))

    private fun parametersNotValid() = arrayOf(
            arrayOf("exceeded the maximum limit of laps", filePathFromResource("exceeded-max-laps.log")),
            arrayOf("log format invalid", filePathFromResource("format-invalid.log"))
    )

    private fun filePathFromResource(fileName: String) = KartLogFileTest::class.java.classLoader.getResource(fileName).file
}