package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class UrlHelperTest {
    @Test
    fun testFragment() {
        UrlHelper.parseUrl("http://123/#anton", fragment = { assertEquals(Fragment("anton"), it) })
    }

    @Test
    fun testQueryWithFragment() {
        val queries = ArrayList<Query>()
        UrlHelper.parseUrl(
            url = "http://login:pass@123/dd?aa=bb&cc=dd#anton%20caffeine",
            schema = { assertEquals("http", it) },
            domain = { assertEquals("123", it) },
            port = { assertNull(it) },
            auth = { assertEquals(Auth("login", "pass"), it) },
            query = {
                queries += it
            },
            path = { assertEquals("/dd".toPath, it) },
            fragment = { assertEquals(Fragment("anton%20caffeine"), it) })
        assertEquals(2, queries.size)
        assertEquals(Query("aa=bb"), queries[0])
        assertEquals(Query("cc=dd"), queries[1])
    }
}