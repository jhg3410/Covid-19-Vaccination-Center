package org.jik.retrofit_project

data class Data(
    val currentCount: Int,
    val `data`: List<DataX>,
    val matchCount: Int,
    val page: Int,
    val perPage: Int,
    val totalCount: Int,
)