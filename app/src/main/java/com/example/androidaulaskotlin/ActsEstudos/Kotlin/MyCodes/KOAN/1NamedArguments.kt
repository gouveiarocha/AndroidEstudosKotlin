package com.example.androidaulaskotlin.ActsEstudos.Kotlin.MyCodes.KOAN

fun main() {

    val collection = listOf<String>("Teste1", "Teste2", "Teste3")

    println(joinOptions(collection))

}

fun joinOptions(options: Collection<String>) =
    options.joinToString(prefix = "[", postfix = "]")