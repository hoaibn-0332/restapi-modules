package com.example.core.common.response

import com.google.gson.annotations.SerializedName
import org.springframework.data.domain.Page
import java.io.Serializable

class ResultListRes<T>(
    collection: Page<T>
) : Serializable {
    val results: List<T> = collection.content
    @SerializedName("total_items")
    val totalItems: Long = collection.totalElements
    @SerializedName("current_page")
    val currentPage: Int = if (collection.pageable.pageNumber >= 1) collection.pageable.pageNumber + 1 else 1
    @SerializedName("total_pages")
    val totalPages: Int = collection.totalPages
    @SerializedName("has_more")
    val hasMore: Boolean = collection.hasNext()
}
