package com.example.p3

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform