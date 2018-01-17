package ru.peterservice.kotlin.springboottutorial.model.repositories

import com.github.springtestdbunit.annotation.DatabaseOperation
import com.github.springtestdbunit.annotation.DatabaseSetup
import com.github.springtestdbunit.annotation.DatabaseTearDown
import javafx.application.Application
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import ru.peterservice.kotlin.springboottutorial.model.AbstractRepositoryTest
import ru.peterservice.kotlin.springboottutorial.model.Link
import java.io.File
import java.util.*

@DatabaseSetup(LinkRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = LinkRepositoryTest.DATASET)
open class LinkRepositoryTest: AbstractRepositoryTest() {

    @Autowired
    lateinit var repository: LinkRepository

    @Before
    fun init() {

    }

    @Test
    fun findOneExistitng() {
        var got: Optional<Link> =  repository.findOne(LINK_1_ID)
        MatcherAssert.assertThat(got.isPresent, Matchers.equalTo(true))
        val link = got.get()
        MatcherAssert.assertThat(link, Matchers.equalTo(Link(LINK_1_TEXT, LINK_1_ID)))
    }

    @Test
    fun findOneNotExisting() {
        var got: Optional<Link> =  repository.findOne(LINK_NOT_FOUND_ID)
        MatcherAssert.assertThat(got.isPresent, Matchers.equalTo(false))
    }

    @Test
    fun saveNew() {
        val toBeSaved: Link = Link(LINK_TBS_TEXT)
        val got: Link = repository.save(toBeSaved)
        val list: List<Link> = repository.findAll()

        MatcherAssert.assertThat(list, Matchers.hasSize(4))
        MatcherAssert.assertThat(got.text, Matchers.equalTo(LINK_TBS_TEXT))
    }

    companion object {
        const val DATASET = "classpath:datasets/link-table.xml"
        private val LINK_1_ID: Long = 100500L
        private val LINK_1_TEXT: String = "https://www.google.com"
        private val LINK_NOT_FOUND_ID: Long = 404L
        private val LINK_TBS_TEXT: String = "http://www.billing.ru"
    }
}