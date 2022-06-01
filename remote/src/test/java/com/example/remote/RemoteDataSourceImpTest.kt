package com.example.remote

import com.example.data.repository.RemoteDataSource
import com.example.remote.api.ApiService
import com.example.remote.mapper.CommentNetworkDataMapper
import com.example.remote.mapper.PostNetworkDataMapper
import com.example.remote.source.RemoteDataSourceImpl
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.Exception

@ExperimentalCoroutinesApi
// @SmallTest
class RemoteDataSourceImpTest {

    @MockK
    private lateinit var apiService: ApiService
    private val postNetworkDataMapper = PostNetworkDataMapper()
    private val commentNetworkDataMapper = CommentNetworkDataMapper()

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        remoteDataSource = RemoteDataSourceImpl(
            apiService = apiService,
            postMapper = postNetworkDataMapper,
            commentMapper = commentNetworkDataMapper
        )
    }

    @Test
    fun test_get_post_success() = runBlocking {
        val postsNetwork = TestDataGenerator.generatePosts()
        coEvery { apiService.getPosts() } returns postsNetwork
        val result = remoteDataSource.getPosts()
        coVerify { apiService.getPosts() }
        val expected = postNetworkDataMapper.fromList(postsNetwork)
        Truth.assertThat(result).containsExactlyElementsIn(expected)
    }

    @Test(expected = Exception::class)
    fun test_get_post_fail() = runBlocking {
        coEvery { apiService.getPosts() } throws Exception()
        remoteDataSource.getPosts()
        coVerify { apiService.getPosts() }
    }
}