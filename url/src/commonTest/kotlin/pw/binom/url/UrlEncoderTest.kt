package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals

class UrlEncoderTest {

    @Test
    fun testEncode() {
        assertEquals(
            "http%3A%2F%2Fexample.com%2Ftest%3Fff%3Dffff",
            UrlEncoder.encode("http://example.com/test?ff=ffff")
        )
    }

    @Test
    fun spaceTest() {
        val uri =
            URL.new(
                schema = "https",
                user = null,
                password = null,
                host = "example.com",
                port = 3301,
                path = "/123 456".toPath,
                query = Query.new(mapOf("gender" to "m w")),
                fragment = Fragment.create("ff nn"),
            )
        assertEquals("https://example.com:3301/123%20456?gender=m%20w#ff%20nn", uri)
    }

    @Test
    fun decodeSpaceInOldStyle() {
        val query1 = "http://127.0.0.1/?value=aa%20bb".toURL().query!!.find("value").single()
//        val query2 = "http://127.0.0.1/?value=aa+bb".toURL().query!!.find("value").single()
        assertEquals("aa bb", query1)
//        assertEquals("aa bb", query2)
    }
}