package pw.binom.url

import kotlin.jvm.JvmInline

@JvmInline
value class Auth(val raw: String) {
    constructor(login: String, password: String) : this("$login:$password")

    val user: String
        get() {
            val index = raw.indexOf(":")
            return if (index == -1) {
                raw
            } else {
                raw.substring(0, index)
            }
        }

    val password: String
        get() {
            val index = raw.indexOf(":")
            return if (index == -1) {
                ""
            } else {
                raw.substring(index + 1)
            }
        }
}