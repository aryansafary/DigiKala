package com.arysapp.digikala.data.remote

import com.arysapp.digikala.data.model.ResponseResult
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.data.model.product_detail.NewComment
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface CommentApiInterface {

    @POST("v1/setNewComment")
    suspend fun setNewComment(
        @Body newComment: NewComment
    ): Response<ResponseResult<String>>

    @GET("v1/getAllProductComments")
    suspend fun getAllProductComments(
        @Query("id") id: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<Comment>>>

    @GET("v1/getUserComments")
    suspend fun getUserComments(
        @Query("token") token: String,
        @Query("pageSize") pageSize: String,
        @Query("pageNumber") pageNumber: String,
    ): Response<ResponseResult<List<Comment>>>

}