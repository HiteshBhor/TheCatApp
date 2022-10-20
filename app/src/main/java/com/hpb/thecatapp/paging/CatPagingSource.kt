package com.hpb.thecatapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hpb.thecatapp.models.CatModelItem
import com.hpb.thecatapp.retrofit.CatAPI

class CatPagingSource(private val catAPI: CatAPI): PagingSource<Int, CatModelItem>() {
    override fun getRefreshKey(state: PagingState<Int, CatModelItem>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatModelItem> {

        return try {
            val position = params.key ?: 1
            val response = catAPI.getCats(position)

            return LoadResult.Page(
                data = response,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.size) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }
}