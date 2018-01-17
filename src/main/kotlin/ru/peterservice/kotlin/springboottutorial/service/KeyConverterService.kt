package ru.peterservice.kotlin.springboottutorial.service

interface KeyConverterService {
    fun keyToId(key: String): Long
    fun idToKey(id: Long): String
}