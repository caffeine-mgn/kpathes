# Kpath
[![GitHub license](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![Kotlin 2.0.10](https://img.shields.io/badge/Kotlin-2.0.10-blue.svg?style=flat&logo=kotlin)](http://kotlinlang.org)

Library for work with pathes

# How to use
Add to your `build.gradle.kts` file:
```kotlin
dependencies{
    api("pw.binom:url:0.0.7")
}
```

# Examples
```kotlin
val url = "https://test.com/users/42/info".toURL()
println(url.port) // null
println(url.domain) // test.com
println(url.path) // /users/42/info
println(url.goto("../11/info")) // https://test.com/users/11/info
```