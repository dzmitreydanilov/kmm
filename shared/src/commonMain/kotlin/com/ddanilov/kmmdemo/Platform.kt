package com.ddanilov.kmmdemo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform