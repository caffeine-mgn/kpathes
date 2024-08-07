package pw.binom.url

import kotlin.test.Test
import kotlin.test.assertEquals

class GotoTest {

    @Test
    fun otherUrl(){
        val result = "http://localhost:8080/aaa".toURL().goto("http://localhost:8080/anton".toURI())
        assertEquals("http://localhost:8080/anton".toURL(), result)
    }

    @Test
    fun changeSearch(){
        val result = "http://localhost:8080/aaa".toURL().goto("?aa=bb".toURI())
        assertEquals("http://localhost:8080/aaa?aa=bb".toURL(), result)
    }

    @Test
    fun changeHashSearch(){
        val result = "http://localhost:8080/aaa".toURL().goto("#aa=bb".toURI())
        assertEquals("http://localhost:8080/aaa#aa=bb".toURL(), result)
    }

    @Test
    fun otherWithoutSchema() {
        val result = "https://google.com".toURL().goto("://test.com/123".toURI())
        assertEquals("https://test.com/123".toURL(), result)
    }

    @Test
    fun selfRelativeAbsolute() {
        val result = "https://google.com/aa/bb".toURL().goto("/aaa".toURI())
        assertEquals("https://google.com/aaa".toURL(), result)
    }

    @Test
    fun selfRelativeParent() {
        val result = "https://google.com/aa/bb".toURL().goto("../aaa".toURI())
        assertEquals("https://google.com/aaa".toURL(), result)
    }

    @Test
    fun selfRelativeCurrent() {
        val result = "https://google.com/aa/bb".toURL().goto("./aaa".toURI())
        assertEquals("https://google.com/aa/aaa".toURL(), result)
    }

    @Test
    fun selfRelativeReplaceCurrent() {
        val result = "https://google.com/aa/bb".toURL().goto("aaa".toURI())
        assertEquals("https://google.com/aa/bb/aaa".toURL(), result)
    }
}