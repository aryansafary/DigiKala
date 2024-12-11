package com.arysapp.digikala.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import com.arysapp.digikala.data.model.product_detail.Comment
import com.arysapp.digikala.data.model.product_detail.NewComment
import com.arysapp.digikala.data.remote.NetworkResult
import com.arysapp.digikala.data.source.ProductCommentsDataSource
import com.arysapp.digikala.data.source.UserCommentsDataSource
import com.arysapp.digikala.repository.CommentRepository
import com.arysapp.digikala.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val repository: CommentRepository) :
    ViewModel() {


    val newCommentResult =
        MutableStateFlow<NetworkResult<String>>(NetworkResult.Loading())


    var productCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }


    var UserCommentsList: Flow<PagingData<Comment>> = flow { emit(PagingData.empty()) }


    fun setNewComment(newComment: NewComment) {
        viewModelScope.launch {
            newCommentResult.emit(repository.setNewComment(newComment))
        }
    }


    fun getCommentList(productId: String) {
        productCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            ProductCommentsDataSource(repository, productId)
        }.flow.cachedIn(viewModelScope)
    }


    fun getUserComments() {
        UserCommentsList = Pager(
            PagingConfig(pageSize = 5)
        ) {
            UserCommentsDataSource(repository, Constants.USER_TOKEN)
        }.flow.cachedIn(viewModelScope)
    }



}