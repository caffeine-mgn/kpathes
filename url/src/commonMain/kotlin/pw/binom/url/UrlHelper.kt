package pw.binom.url

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

object UrlHelper {

    @OptIn(ExperimentalContracts::class)
    inline fun parseUrl(
        url: String,
        schema: (String) -> Unit = {},
        domain: (String) -> Unit = {},
        port: (Int?) -> Unit = {},
        auth: (Auth?) -> Unit = {},
        path: (Path) -> Unit = {},
        query: (Query) -> Unit = {},
        fragment: (Fragment?) -> Unit = {},
    ) {
        contract {
            callsInPlace(schema, InvocationKind.EXACTLY_ONCE)
            callsInPlace(domain, InvocationKind.EXACTLY_ONCE)
            callsInPlace(port, InvocationKind.EXACTLY_ONCE)
            callsInPlace(auth, InvocationKind.EXACTLY_ONCE)
            callsInPlace(path, InvocationKind.EXACTLY_ONCE)
            callsInPlace(query, InvocationKind.UNKNOWN)
            callsInPlace(fragment, InvocationKind.EXACTLY_ONCE)
        }
        val schemaIndex = url.indexOf("://")
        if (schemaIndex == -1) {
            throw IllegalArgumentException("Missing \"://\" in url \"$url\"")
        }
        schema(url.substring(0, schemaIndex))
        val domainEndIndex = url.indexOf("/", schemaIndex + 3)
        var domainFull = url.substring(
            startIndex = schemaIndex + 3,
            endIndex = if (domainEndIndex == -1) url.length else domainEndIndex,
        )
        val authIndex = domainFull.indexOf('@')
        val authValue = if (authIndex == -1) {
            null
        } else {
            val a = domainFull.substring(0, authIndex)
            domainFull = domainFull.substring(authIndex + 1)
            a
        }
        auth(authValue?.let { Auth(it) })
        val portIndex = domainFull.indexOf(":")
        val portValue = if (portIndex == -1) {
            null
        } else {
            val v = domainFull.substring(portIndex + 1)
            val r = v.toIntOrNull() ?: throw IllegalArgumentException("Can't parse \"$v\" to integer")
            domainFull = domainFull.substring(0, portIndex)
            r
        }
        port(portValue)
        domain(domainFull)
        if (domainEndIndex == -1) {
            path(Path.EMPTY)
        } else {
            val queryIndex = url.indexOf('?', domainEndIndex)
            val fragmentIndex = url.indexOf('#', if (queryIndex == -1) domainEndIndex else queryIndex)

            when {
                queryIndex != -1 -> {
                    path(url.substring(domainEndIndex, queryIndex).toPath)

                    var next = queryIndex + 1
                    do {
                        val nextQuery = url.indexOf('&', next)
                        if (nextQuery != -1) {
                            if (nextQuery < fragmentIndex) {
                                query(Query(url.substring(next, nextQuery)))
                                next = nextQuery + 1
                                continue
                            } else {
                                query(Query(url.substring(next, fragmentIndex)))
                                break
                            }
                        } else {
                            if (fragmentIndex == -1) {
                                break
                            }
                            query(Query(url.substring(next, fragmentIndex)))
                            break
                        }
                    } while (next != -1)

                    if (fragmentIndex == -1) {
                        fragment(null)
                    } else {
                        fragment(Fragment(url.substring(fragmentIndex + 1)))
                    }
                }

                fragmentIndex != -1 -> {
                    path(url.substring(domainEndIndex, fragmentIndex).toPath)
                    fragment(Fragment(url.substring(fragmentIndex + 1)))
                }

                else -> {
                    path(url.substring(domainEndIndex).toPath)
                    fragment(null)
                }
            }
        }
    }
}
