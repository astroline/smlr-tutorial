package ru.peterservice.kotlin.springboottutorial.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import ru.peterservice.kotlin.springboottutorial.service.KeyMapperService

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class RedirectControllerTest {
    @Autowired lateinit var  webApplicationContext: WebApplicationContext
    lateinit var mockMvc: MockMvc

    @Mock
    lateinit var service: KeyMapperService

    @Autowired
    @InjectMocks
    lateinit var controller: RedirectController

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
        Mockito.`when`(service.getLink(PATH)).thenReturn(KeyMapperService.Get.Link(HEADER_VALUE))
        Mockito.`when`(service.getLink(BAD_PATH)).thenReturn(KeyMapperService.Get.NotFound(BAD_PATH))
    }

    private val PATH = "aAbBcCdD"
    private val HEADER_NAME = "Location"
    private val HEADER_VALUE = "http://www.google.com"

    @Test
    fun controllerMustRedirectUsWhenRequestInSuccessfull() {
        mockMvc.perform(get("/$PATH"))
                .andExpect(status().is3xxRedirection)
                .andExpect(header().string(HEADER_NAME, HEADER_VALUE))
    }

    private val BAD_PATH = "olololo"

    @Test
    fun controllerMustReturn404IfBadKey() {
        mockMvc.perform(get("/$BAD_PATH"))
                .andExpect(status().isNotFound)
    }

    @Test
    fun homeWorksFine() {
        mockMvc.perform(get("/"))
                .andExpect(MockMvcResultMatchers.view().name("home"))
    }
}