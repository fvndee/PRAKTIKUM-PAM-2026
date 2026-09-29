<<<<<<< HEAD
# Praktikum 2 — News Feed Simulator

Simulator feed berita menggunakan Kotlin Coroutines, Flow, dan StateFlow.
## Kode Program

```kotlin
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

data class News(
    val id: Int,
    val title: String,
    val category: String
)

// Flow yang mensimulasikan data berita baru setiap 2 detik
fun newsFlow(): Flow<News> = flow {
    val newsList = listOf(
        News(1, "AI Mengubah Dunia Teknologi", "Teknologi"),
        News(2, "Tim Nasional Juara Turnamen", "Olahraga"),
        News(3, "Ekonomi Tumbuh 5 Persen", "Ekonomi")
    )
    
    newsList.forEach { news ->
        emit(news)
        delay(2000)
    }
}

// Filter berita berdasarkan kategori tertentu
fun filterNewsByCategory(category: String): Flow<News> {
    return newsFlow().filter { news ->
        news.category == category
    }
}

// Transform data menjadi format yang ditampilkan
fun transformNewsToDisplay(): Flow<String> {
    return newsFlow().map { news ->
        "[${news.category}] ${news.title} (ID: ${news.id})"
    }
}

// StateFlow untuk menyimpan jumlah berita yang sudah dibaca
class NewsCounter {
    private val _readCount = MutableStateFlow(0)
    val readCount: StateFlow<Int> = _readCount.asStateFlow()
    
    fun markAsRead() {
        _readCount.value++
    }
}

// Coroutines untuk mengambil detail berita secara async
suspend fun fetchNewsDetails(newsId: Int): Pair<String, Int> = coroutineScope {
    val authorDeferred = async {
        delay(500)
        listOf("Ahmad", "Budi", "Citra").random()
    }
    
    val viewsDeferred = async {
        delay(300)
        Random.nextInt(100, 1000)
    }
    
    Pair(authorDeferred.await(), viewsDeferred.await())
}

fun main() = runBlocking {
    println("NEWS FEED SIMULATOR")
    
    val counter = NewsCounter()
    
    // Monitor jumlah berita yang dibaca
    val counterJob = launch {
        counter.readCount.collect { count ->
            if (count > 0) {
                println("Total berita dibaca: $count\n")
            }
        }
    }
    
    // Flow + Transform (semua 3 berita)
    println("Transform Berita")
    transformNewsToDisplay()
        .collect { displayText ->
            println(displayText)
            counter.markAsRead()
        }
    
    println()
    
    // Filter berita kategori Teknologi
    println("Filter Kategori 'Teknologi'")
    filterNewsByCategory("Teknologi")
        .collect { news ->
            println("${news.title}")
            counter.markAsRead()
        }
    
    println()
    
    // Async fetch detail untuk 1 berita
    println("Fetch Detail Async")
    newsFlow()
        .take(1)
        .collect { news ->
            println("Mengambil detail untuk: ${news.title}")
            val startTime = System.currentTimeMillis()
            
            val (author, views) = fetchNewsDetails(news.id)
            
            val duration = System.currentTimeMillis() - startTime
            println("Penulis: $author | Views: $views | Waktu: ${duration}ms")
            counter.markAsRead()
        }
    
    delay(500)
    counterJob.cancel()
}
```

## Fitur yang Diimplementasikan

- **Flow**: Simulasi stream berita dengan `newsFlow()`
- **Filter**: Filter berita berdasarkan kategori dengan `filterNewsByCategory()`
- **Transform**: Ubah format berita dengan `transformNewsToDisplay()`
- **StateFlow**: Counter berita yang dibaca dengan `NewsCounter`
- **Async/Await**: Ambil detail berita paralel dengan `fetchNewsDetails()`
=======
This is a Kotlin Multiplatform project targeting Android, iOS, Desktop (JVM).

* [/iosApp](./iosApp/iosApp) contains an iOS application. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

* [/shared](./shared/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./shared/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./shared/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./shared/src/jvmMain/kotlin)
    folder is the appropriate location.

### Running the apps

Use the run configurations provided by the run widget in your IDE's toolbar. You can also use these commands and options:

- Android app: `./gradlew :androidApp:assembleDebug`
- Desktop app:
  - Hot reload: `./gradlew :desktopApp:hotRun --auto`
  - Standard run: `./gradlew :desktopApp:run`
- iOS app: open the [/iosApp](./iosApp) directory in Xcode and run it from there.

### Running tests

Use the run button in your IDE's editor gutter, or run tests using Gradle tasks:

- Android tests: `./gradlew :shared:testAndroidHostTest`
- Desktop tests: `./gradlew :shared:jvmTest`
- iOS tests: `./gradlew :shared:iosSimulatorArm64Test`

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
>>>>>>> 599120b (Praktikum ke 3)
