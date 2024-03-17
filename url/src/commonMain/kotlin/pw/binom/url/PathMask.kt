package pw.binom.url

import kotlin.jvm.JvmInline

@JvmInline
value class PathMask(val raw: String) {

  inline fun splitOnElements(crossinline const: (String) -> Unit, crossinline variable: (String) -> Unit) {
    raw.parsePathMask(
      variable = { text, _ -> variable(text) },
      wildcard = { text, _ -> variable(text) },
      text = { text, _ -> const(text) },
    )
  }

  fun toPath(): Path = toPath {
    throw IllegalArgumentException("Can't convert mask \"$raw\" to path: can't replace variable \"$it\"")
  }

  fun toPath(map: Map<String, String>) = toPath { name ->
    map[name] ?: throw IllegalArgumentException("Can't find value for variable \"$name\"")
  }

  fun toPath(vararg values: Pair<String, String>) = toPath(values.toMap())

  fun getVariables(): Set<String> {
    val variables = HashSet<String>()
    raw.parsePathMask(
      variable = { name, _ -> variables += name },
      wildcard = null,
      text = null,
    )
    return variables
  }

  fun toPath(variable: (String) -> String): Path {
    val sb = StringBuilder()
    raw.parsePathMask(
      variable = { text, _ -> sb.append(variable(text)) },
      wildcard = { text, _ -> sb.append(variable(text)) },
      text = { text, _ -> sb.append(text) },
    )
    return sb.toString().toPath
  }

  override fun toString(): String = raw
}
