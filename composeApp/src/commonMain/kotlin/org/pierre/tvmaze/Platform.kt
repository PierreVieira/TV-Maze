package org.pierre.tvmaze

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform