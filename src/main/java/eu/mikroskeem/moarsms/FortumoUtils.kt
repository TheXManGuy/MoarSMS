package eu.mikroskeem.moarsms

/**
 * @author Mark Vainomaa
 */
internal class FortumoUtils {
    internal lateinit var allowedIPs: List<String>

    fun checkSignature(params: Map<String, String>, secret: String): Boolean = StringBuilder().run {
        params.keys.toSortedSet().forEach { if(it != "sig") append("$it=${params[it]}") }
        append(secret)
        return@run Hash.md5(this.toString()) == params["sig"]
    }

    fun checkIP(ip: String?): Boolean = allowedIPs.contains(ip)
}