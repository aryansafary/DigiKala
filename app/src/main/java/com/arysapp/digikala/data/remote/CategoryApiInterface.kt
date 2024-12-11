package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.category.SubCategory
import com.arysapp.digikala.data.model.home.StoreProduct
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryApiInterface {

    @GET("v1/getSubCategories")
    suspend fun getSubCategories() : Response<ResponseResult<SubCategory>>


    @GET("v1/getProductByCategory")
    suspend fun getProductByCategory(
        @Query("categoryName") categoryName: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<StoreProduct>>>



    @GET("v1/getProductBySubCategory")
    suspend fun getProductBySubCategory(
        @Query("subCategoryId") subCategoryId: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<StoreProduct>>>


}