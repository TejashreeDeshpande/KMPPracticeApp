package com.example.kmppracticeapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform