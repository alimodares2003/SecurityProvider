package ir.adp.securityproviderlibrary

import android.content.Context
import android.os.Build
import android.util.Base64
import java.security.NoSuchAlgorithmException
import kotlin.random.Random


object SPUtil {

    fun isDeviceRooted(): Boolean {
        return RootUtil.isDeviceRooted
    }

    fun isClone(context: Context, packageNameMD5: String): Boolean {
        val encode = context.packageName.trim()
        return encode != packageNameMD5
    }

    fun isEmulator(): Boolean {
        return (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86")
                || Build.MANUFACTURER.contains("Genymotion")
                || Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")
                || "google_sdk" == Build.PRODUCT)
    }

    fun String.toMD5(): String {
        try {
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(this.toByte())
            val messageDigest = digest.digest()
            val hexString = StringBuffer()
            for (i in messageDigest.indices)
                hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))
            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return ""
    }

    fun decodeBase64(s: String): String {
        return Base64.decode(s, Base64.DEFAULT).toString(charset("UTF-8"))
    }

    fun encodeBase64(s: String): String {
        return Base64.encodeToString(s.toByteArray(charset("UTF-8")), Base64.DEFAULT)
    }

    fun hashGenerate(): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789=+-"
        var key = ""
        for (i in 0 until 32) {
            Random.nextInt(1, characters.length).apply {
                characters[this].apply {
                    key += this
                }
            }
        }
        return key
    }

    fun hashGenerate(hashLength: Int): String {
        val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789=+-"
        var key = ""
        for (i in 0 until hashLength) {
            Random.nextInt(1, characters.length).apply {
                characters[this].apply {
                    key += this
                }
            }
        }
        return key
    }
}