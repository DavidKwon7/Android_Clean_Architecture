package com.example.data

import app.cash.turbine.test
import com.example.common.extensions.Resource
import com.example.data.mapper.CommentDataDomainMapper
import com.example.data.mapper.PostDataDomainMapper
import com.example.data.repository.RemoteDataSource
import com.example.data.repository.RepositoryImpl
import com.example.domain.repository.Repository
import com.google.common.truth.Truth
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.lang.Exception
import kotlin.time.ExperimentalTime

@ExperimentalTime
@ExperimentalCoroutinesApi
class RepositoryImpTest {

    @MockK
    private lateinit var remoteDataSource: RemoteDataSource

    private val postMapper = PostDataDomainMapper()
    private val commentMapper = CommentDataDomainMapper()

    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = RepositoryImpl(
            remoteDataSource = remoteDataSource,
            postMapper = postMapper,
            commentMapper = commentMapper
        )
    }

    @Test
    fun test_get_posts_remote_success() = runBlocking {
        val posts = TestDataGenerator.generatePosts()
        val affectedIds = MutableList(posts.size) {index -> index.toLong() }

        coEvery { remoteDataSource.getPosts() } returns posts

       val flow = repository.getPosts()
        flow.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).containsExactlyElementsIn(postMapper.fromList(posts))
            expectComplete()
        }
        coVerify { remoteDataSource.getPosts() }
    }

    @Test
    fun test_get_posts_remote_fail_local_success() = runBlocking {
        val posts = TestDataGenerator.generatePosts()

        coEvery { remoteDataSource.getPosts() } throws Exception()

        val flow = repository.getPosts()
        flow.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).containsExactlyElementsIn(postMapper.fromList(posts))
            expectComplete()
        }
        coVerify { remoteDataSource.getPosts() }
    }

    @Test
    fun test_get_posts_remote_fail_local_fail() = runBlocking {
        coEvery { remoteDataSource.getPosts() } throws Exception()
        val flow = repository.getPosts()
        flow.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }
        coEvery { remoteDataSource.getPosts() }
    }

    @Test
    fun test_get_comments_success() = runBlocking {
        val comments = TestDataGenerator.generatePostComments()
        coEvery { remoteDataSource.getPostComments(any()) } returns comments

        val flow = repository.getPostComments(1)
        flow.test {
            val expected = expectItem()
            val expectedData = (expected as Resource.Success).data
            Truth.assertThat(expected).isInstanceOf(Resource.Success::class.java)
            Truth.assertThat(expectedData).containsExactlyElementsIn(commentMapper.fromList(comments))
            expectComplete()
        }

        // any() 메서드 사용을 통해, para 안의 private val을 임의로 채워줄 수 있다.
        coEvery { remoteDataSource.getPostComments(any()) }
    }

    @Test
    fun test_get_comments_fail() = runBlocking {
        coEvery { remoteDataSource.getPostComments(any()) } throws Exception()

        val flow = repository.getPostComments(1)
        flow.test {
            Truth.assertThat(expectItem()).isInstanceOf(Resource.Error::class.java)
            expectComplete()
        }
        coVerify { remoteDataSource.getPostComments(any()) }
    }
}