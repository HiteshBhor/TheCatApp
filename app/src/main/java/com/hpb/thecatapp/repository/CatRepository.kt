package com.hpb.thecatapp.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.hpb.thecatapp.paging.CatPagingSource
import com.hpb.thecatapp.retrofit.CatAPI
import javax.inject.Inject

class CatRepository @Inject constructor(private val catAPI: CatAPI) {

    fun getCats() = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 100),
        pagingSourceFactory = { CatPagingSource(catAPI) }
    ).liveData
}