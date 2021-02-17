package me.developersancho.prefstore.gson

import android.content.Context
import me.developersancho.prefstore.CacheManager

/**
 * Manages Shared Preferences and provides utility functions
 * Can read and write simple values or objects
 * Uses Gson as the backend to serialize and deserialize objects
 * @property context Required to access SharedPreferences
 * @property prefFileName Parent name of the SharedPreferences space
 */
class GsonCacheManager(
    context: Context,
    prefFileName: String? = null
) : CacheManager(context, prefFileName) {

    /**
     * Reads json from SharedPreferences and casts it to requested type using Gson
     * @param T Type parameter to cast gson to
     * @param key Key to read from
     * @return An object of requested type
     */
    inline fun <reified T> readObject(key: String): T? {
        val readValue = read(key, "")
        return readValue.convertModel<T>()
    }

    /**
     * Stores an object under given key or class name.
     * @param key Key to write object to. If not given, class name will be used
     * @param value Object to store.
     */
    fun writeObject(key: String, value: Any) {
        write(key, value.convertJson())
    }

    /**
     * Read a list object from json string  and casts it to requested type using Gson
     * @param key Key to read from
     * @return List Object
     */
    inline fun <reified T> readListObject(key: String): T? {
        return try {
            val value = read(key, "")
            value.convertList<T>()
        } catch (ex: Exception) {
            null
        }
    }
}