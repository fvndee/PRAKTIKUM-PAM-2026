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
