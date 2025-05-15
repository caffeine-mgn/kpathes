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
    fun testQuery() {
        UrlHelper.parseUrl("http://123/?ant=111&fff=222", query = { assertEquals(Query("ant=111&fff=222"), it) })
    }

    @Test
    fun testQueryAndFragment() {
        UrlHelper.parseUrl("http://123/?ant=111&fff=222#anton", fragment = { assertEquals(Fragment("anton"), it) }, query = { assertEquals(Query("ant=111&fff=222"), it) })
    }

    @Test
    fun testPort() {
        UrlHelper.parseUrl(
            url = "https://api.telegram.org:800/aaaa:bbbb/sendVoice?chat_id=dddd",
            port = { assertEquals(800,it) }
        )
    }

    @Test
    fun testQueryWithFragment() {
        val queries:Query?
        UrlHelper.parseUrl(
            url = "http://login:pass@123/dd?aa=bb&cc=dd#anton%20caffeine",
            schema = { assertEquals("http", it) },
            domain = { assertEquals("123", it) },
            port = { assertNull(it) },
            auth = { assertEquals(Auth("login", "pass"), it) },
            query = {
                queries = it
            },
            path = { assertEquals("/dd".toPath, it) },
            fragment = { assertEquals(Fragment("anton%20caffeine"), it) })
        assertEquals(Query("aa=bb&cc=dd"),queries)
//        assertEquals(2, queries.size)
//        assertEquals(Query("aa=bb"), queries[0])
//        assertEquals(Query("cc=dd"), queries[1])
    }
}