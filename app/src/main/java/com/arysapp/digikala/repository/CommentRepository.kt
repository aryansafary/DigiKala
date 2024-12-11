package com.arysapp.digikala.repository

import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.data.model.product_detail.NewComment
import com.arysapp.digikala.data.remote.BaseApiResponse
import com.arysapp.digikala.data.remote.CommentApiInterface
import com.arysapp.digikala.data.remote.NetworkResult
import javax.inject.Inject

class CommentRepository @Inject constructor(private val api: CommentApiInterface) :
    BaseApiResponse() {

    suspend fun setNewComment(newComment: NewComment): NetworkResult<String> =
        safeApiCall {
            api.setNewComment(newComment)
        }


    suspend fun getAllProductComments(
        id: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getAllProductComments(id, pageSize, pageNumber)
        }


    suspend fun getUserComments(
        token: String,
        pageSize: String,
        pageNumber: String,
    ): NetworkResult<List<Comment>> =
        safeApiCall {
            api.getUserComments(token, pageSize, pageNumber)
        }


}