package ru.finntrolle

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner

@TestPropertySource(locations = arrayOf("classpath:repositories-test.properties"))
@RunWith(SpringRunner::class)
@SpringBootTest
class GohomeApplicationTests {

    @Test
    fun contextLoads() {
    }

}
