package me.developersancho.prefstore.moshi

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

val getMoshi: Moshi = Moshi.Builder()
    .addLast(KotlinJsonAdapterFactory())
    .build()

inline fun <reified T> String.deserialize(): T? {
    val jsonAdapter = getMoshi.adapter(T::class.java)
    return jsonAdapter.fromJson(this)
}

inline fun <reified T> String.deserializeList(): List<T>? {
    val type = Types.newParameterizedType(MutableList::class.java, T::class.java)
    val jsonAdapter: JsonAdapter<List<T>> = getMoshi.adapter(type)
    return jsonAdapter.fromJson(this)
}

@Suppress("CheckResult")
fun String.canConvertTo(type: Class<*>): Boolean {
    return try {
        val jsonAdapter = getMoshi.adapter(type)
        jsonAdapter.fromJson(this)
        true
    } catch (exception: Exception) {
        exception.printStackTrace()
        false
    }
}

inline fun <reified T> T.serialize(): String {
    val jsonAdapter = getMoshi.adapter(T::class.java)
    return jsonAdapter.toJson(this)
}

/* EXAMPLE
data class Person(var name: String)

### Deserialize Json object
private val personJson = "{\"name\":\"Mr.Sanchez\"}"
val person: Person? = personJson.deserialize<Person>()

### Deserialize Json array
private val personsJson = "[{\"name\":\"Mr.Sanchez\"},{\"name\":\"Mr.Wick\"}]"
val persons: List<Person>? = personsJson.deserializeList()

### Serialize Json
private val person: Person = Person("Mr.Sanchez")
val serializedObject = person.serialize()

### Check if you can convert json to a particular type
val canConvertPersonJsonToPerson: Boolean = personJson.canConvertTo(Person::class.java) //true


 */
