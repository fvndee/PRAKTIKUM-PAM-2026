import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.random.Random

// Hands-on 2: Flow dengan Operators
// Tugas: Buat Flow yang mensimulasikan sensor suhu, filter suhu di atas 30°C,
// dan tampilkan warning dengan format yang bagus.

fun temperatureSensor(): Flow<Int> = flow {
    repeat(10) {
        delay(500)
        val temp = Random.nextInt(20, 40) // Random 20-39°C
        emit(temp)
    }
}

fun main() = runBlocking {
    temperatureSensor()
        .filter { temp -> temp > 30 }
        .map { temp -> "Suhu tinggi terdeteksi: $temp°C" }
        .onEach { println("Processing warning...") }
        .collect { warning -> println(warning) }
}
