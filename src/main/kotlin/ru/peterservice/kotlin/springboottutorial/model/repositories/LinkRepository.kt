package ru.peterservice.kotlin.springboottutorial.model.repositories

import org.springframework.data.repository.Repository
import ru.peterservice.kotlin.springboottutorial.model.Link
import java.util.*

interface LinkRepository: Repository<Link, Long> {
    fun findOne(id: Long?): Optional<Link>
    fun save(toBeSaved: Link): Link
    fun findAll(): List<Link>

}