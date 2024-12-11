package com.arysapp.digikala.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.repository.CommentRepository

class ProductCommentsDataSource(
    private val repository: CommentRepository,
    val productId: String
) : PagingSource<Int, Comment>() {

    override fun getRefreshKey(state: PagingState<Int, Comment>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comment> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response = repository.getAllProductComments(
                pageNumber = nextPageNumber.toString(),
                pageSize = "5",
                id = productId
            ).data

            LoadResult.Page(
                data = response!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )

        } catch (e: Exception) {
            Log.d("3636", "error:$e ")
            LoadResult.Error(e)
        }
    }


}