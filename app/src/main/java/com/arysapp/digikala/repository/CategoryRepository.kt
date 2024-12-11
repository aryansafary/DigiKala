package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.category.SubCategory
import com.arysapp.digikala.data.model.home.StoreProduct
import com.arysapp.digikala.data.remote.BaseApiResponse
import com.arysapp.digikala.data.remote.CategoryApiInterface
import com.arysapp.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val api: CategoryApiInterface) : BaseApiResponse() {

    suspend fun getSubCategories(): NetworkResult<SubCategory> =
        safeApiCall {
            api.getSubCategories()
        }


    suspend fun getProductByCategory(
        categoryName: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductByCategory(categoryName, pageSize, pageNumber)
        }


    suspend fun getProductBySubCategory(
        subCategoryId: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<StoreProduct>> =
        safeApiCall {
            api.getProductBySubCategory(subCategoryId, pageSize, pageNumber)
        }

}