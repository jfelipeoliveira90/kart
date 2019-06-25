package br.com.jfelipe.kart

import br.com.jfelipe.kart.infrastructure.RaceFileLog

class Application

fun main(args: Array<String>) {
    val file = Application::class.java.classLoader.getResource("kart.log").file

    RaceFileLog.read(file)
            .podium()
            .rank
            .forEach(::println)
}