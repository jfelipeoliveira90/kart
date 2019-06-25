package br.com.jfelipe.kart.infrastructure

import java.util.regex.Pattern

class RaceRegexPatterns {
    companion object {
        private val HOUR = "([\\d]{0,2}:[\\d]{0,2}:[\\d]{0,2}.[\\d]{3})"
        private val PILOT = "([0-9]{3}) – ([A-Z.]{0,34})"
        private val TURN = "(\\d)"
        private val TIME = "([\\d]{0,2}:[\\d]{0,2}.[\\d]{3})"
        private val AVERAGE_SPEED = "([0-9]*,?[0-9]+)"
        private val WHITESPACE = "\\s*"

        @JvmStatic
        val FULL: Pattern = Pattern.compile(HOUR +
                WHITESPACE +
                PILOT +
                WHITESPACE +
                TURN +
                WHITESPACE +
                TIME +
                WHITESPACE +
                AVERAGE_SPEED)
    }
}