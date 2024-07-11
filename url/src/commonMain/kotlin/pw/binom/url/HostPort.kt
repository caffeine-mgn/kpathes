package pw.binom.url

import kotlin.jvm.JvmInline

@JvmInline
value class HostPort(val raw: String) {
    constructor(domain: String, port: Int) : this("$domain:$port")

    init {
        val p = raw.indexOf(':')
        if (p != -1) {
            require(raw.indexOf(':', p + 1) == -1) { "Invalid host \"$raw\": allow define only one port" }
            require(p > 0) { "Invalid host \"$raw\": domain is empty" }
            val port = raw.substring(p + 1)
            require(port.toIntOrNull() != null) { "Invalid host \"$raw\": Invalid port $port" }
        }
    }

    val domain: String
        get() {
            val p = raw.indexOf(':')
            if (p == -1) {
                return raw
            }
            return raw.substring(0, p)
        }
    val port: Int?
        get() {
            val p = raw.indexOf(':')
            return if (p == -1) {
                null
            } else {
                raw.substring(p + 1).toInt()
            }
        }

    override fun toString(): String = raw
}