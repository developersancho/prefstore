package me.developersancho.prefstore.moshi

import android.content.Context
import me.developersancho.prefstore.PrefStore

/**
 * Manages Shared Preferences and provides utility functions
 * Can read and write simple values or objects
 * Uses Moshi as the backend to serialize and deserialize objects
 * @property context Required to access SharedPreferences
 * @property prefFileName Parent name of the SharedPreferences space
 */
class MoshiPrefStore(
    context: Context,
    prefFileName: String? = null
) : PrefStore(context, prefFileName) {

    /**
     * Reads json from SharedPreferences and casts it to requested type using Moshi
     * @param T Type parameter to cast Moshi to
     * @param key Key to read from
     * @return An object of requested type
     */
    inline fun <reified T> readObject(key: String): T? {
        val readValue = read(key, "")
        return readValue.deserialize()
    }

    /**
     * Stores an object under given key or class name.
     * @param key Key to write object to. If not given, class name will be used
     * @param value Object to store.
     */
    fun writeObject(key: String, value: Any) {
        write(key, value.serialize())
    }

    /**
     * Read a list object from json string and casts it to requested type using Moshi
     * @param key Key to read from
     * @return List Object
     */
    inline fun <reified T> readListObject(key: String): List<T>? {
        return try {
            val value = read(key, "")
            value.deserializeList()
        } catch (ex: Exception) {
            null
        }
    }

}