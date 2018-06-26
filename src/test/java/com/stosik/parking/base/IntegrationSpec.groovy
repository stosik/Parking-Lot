package com.stosik.parking.base

import com.stosik.ParkingLotApplication
import com.stosik.infrastructure.config.Profiles
import groovy.transform.TypeChecked
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.transaction.Transactional
import java.nio.charset.Charset

@TypeChecked
@SpringBootTest(classes = [ParkingLotApplication])
@ActiveProfiles([Profiles.TEST])
@Transactional
@Rollback
abstract class IntegrationSpec extends Specification
{
    @Autowired
    protected WebApplicationContext webApplicationContext

    MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
                                          MediaType.APPLICATION_JSON.getSubtype(),
                                          Charset.forName("utf8"))

    MockMvc mockMvc

    @Before
    void setupMockMvc()
    {
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .build()
    }
}
