# prefstore
Security SharedPreferences support min sdk 21+

[![](https://jitpack.io/v/developersancho/prefstore.svg)](https://jitpack.io/#developersancho/prefstore)

# 🏃 Library Setup
## 1. Add the repository
`build.gradle`
```groovy
allprojects {
    repositories {
        maven { url "https://jitpack.io" }
    }
}
```

## 2. Add the dependency
`build.gradle`
* Read and Write key-value
````kotlin
dependencies {
    implementation("com.github.developersancho.prefstore:prefstore:1.0.0-alpha01")
}
````
* Read and Write key-value, Support `Moshi Serialize-Deserialize`
````kotlin
dependencies {
    implementation("com.github.developersancho.prefstore:prefstore-moshi:1.0.0-alpha01")
}
````
* Read and Write key-value, Support `Gson Serialize-Deserialize`
````kotlin
dependencies {
    implementation("com.github.developersancho.prefstore:prefstore-gson:1.0.0-alpha01")
}
````
* Read and Write key-value, Support `Both of Moshi-Gson Serialize-Deserialize`
````kotlin
dependencies {
    implementation("com.github.developersancho.prefstore:prefstore-core:1.0.0-alpha01")
}
````
# 💻 Usage
## Example
```kotlin
data class Student(
        val studentId: Int,
        val studentName: String,
)
```
#### Just read and write value
```kotlin
val pref = CacheManager(context = this, prefFileName = "prefFileName")
pref.write(key = "KEY", "default")
pref.read(key = "KEY", defaultValue = "")
```

#### Just read and write value via moshi
```kotlin
val prefMoshi = MoshiCacheManager(context = this, prefFileName = "prefFileName_moshi")
prefMoshi.write(key = "KEY_MOSHI", "developersancho")
prefMoshi.read(key = "KEY_MOSHI", defaultValue = "")
prefMoshi.writeObject(key = "KEY_MOSHI_OBJECT", value = Student(id = 1, name = "moshi"))
prefMoshi.readObject<Student>(key = "KEY_MOSHI_OBJECT")
```

#### Just read and write value via gson
```kotlin
val prefGson = GsonCacheManager(context = this, prefFileName = "prefFileName_gson")
prefGson.write(key = "KEY_GSON", "gson")
prefGson.read(key = "KEY_GSON", defaultValue = "")
prefGson.writeObject(key = "KEY_GSON_OBJECT", value = Student(id = 1, name = "gson"))
prefGson.readObject<Student>(key = "KEY_GSON_OBJECT")
```
#### Also you can use extension methods
* <a href="https://github.com/developersancho/prefstore/blob/master/prefstore-moshi/src/main/java/me/developersancho/prefstore/moshi/GetMoshiExt.kt" target="_blank">Moshi Serialize-Deserialize Extension Methods</a>
* <a href="https://github.com/developersancho/prefstore/blob/master/prefstore-gson/src/main/java/me/developersancho/prefstore/gson/GetGsonExt.kt" target="_blank">Gson Serialize-Deserialize Extension Methods</a>

## ✍️ Author

👤 **Mr.Sanchez**

* Github: <a href="https://github.com/developersancho" target="_blank">@developersancho</a>
* Email: developersanchez1903@gmail.com

Feel free to ping me 😉

## 🤝 Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Open an issue first to discuss what you would like to change.
1. Fork the Project
1. Create your feature branch (`git checkout -b feature/amazing-feature`)
1. Commit your changes (`git commit -m 'Add some amazing feature'`)
1. Push to the branch (`git push origin feature/amazing-feature`)
1. Open a pull request

Please make sure to update tests as appropriate.

## ☑️ TODO

- [ ] Unit Testing
- [ ] Demo Sample App
- [ ] Support more libraries

## 📝 License

```
Copyright © 2021 - theapache64

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
