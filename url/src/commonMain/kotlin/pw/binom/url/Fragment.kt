package pw.binom.url

import kotlin.jvm.JvmInline

@JvmInline
value class Fragment(val raw: String) {
    companion object {
        fun create(raw: String) = Fragment(UrlEncoder.encode(raw))
    }

    val value
        get() = UrlEncoder.decode(raw)

    override fun toString(): String = raw
}