package pw.binom.url


actual object UrlEncoder {
    actual fun encode(input: String): String = java.net.URLEncoder.encode(input, Charsets.UTF_8.name()).replace("+", "%20")

    actual fun decode(input: String): String = java.net.URLDecoder.decode(input, Charsets.UTF_8.name())

    actual fun pathEncode(input: String): String =
        input.splitToSequence('/').map { encode(it) }.joinToString(separator = "/")

    actual fun pathDecode(input: String): String =
        input.splitToSequence('/').map { decode(it) }.joinToString(separator = "/")
}
