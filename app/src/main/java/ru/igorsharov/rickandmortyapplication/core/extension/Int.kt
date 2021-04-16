package ru.igorsharov.rickandmortyapplication.core.extension

fun Int.Companion.empty() = -1

fun Int.isEmpty() = this == Int.empty()

fun Int.isNotEmpty() = this != Int.empty()

fun Int.Companion.zero() = 0

fun Int.isZero() = this == 0

fun Int.isPositive() = this > 0