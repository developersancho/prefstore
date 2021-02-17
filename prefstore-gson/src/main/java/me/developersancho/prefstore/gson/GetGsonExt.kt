package me.developersancho.prefstore.gson

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

val getGson: Gson
    get() {
        val builder = GsonBuilder()
        builder.serializeNulls().setLenient()
        return builder.setPrettyPrinting().create()
    }


inline fun <reified T> T.convertJson(): String = getGson.toJson(this)

inline fun <reified T> Any.convertModel(): T {
    return if (this is String) {
        try {
            getGson.fromJson(this, T::class.java)
        } catch (ex: Exception) {
            this as T
        }
    } else {
        this as T
    }
}

inline fun <reified T> String.convertList(): T? =
    getGson.fromJson<T>(this, object : TypeToken<T>() {}.type)

/***
 * convert an object of type I to O
 */
inline fun <I, reified O> I.convert(): O {
    val json = getGson.toJson(this)
    return getGson.fromJson(json, object : TypeToken<O>() {}.type)
}


/***
 * convert a map to a data class
 */
inline fun <reified T> Map<String, Any>.toDataClass(): T = convert()

/***
 * convert a data class to a map
 */
fun <T> T.serializeToMap(): Map<String, Any> = convert()