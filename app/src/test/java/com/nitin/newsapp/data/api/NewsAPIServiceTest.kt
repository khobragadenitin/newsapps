package com.nitin.newsapp.data.api

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {

    private lateinit var service : NewsAPIService
    private lateinit var server : MockWebServer

    @Before
    fun setUp() {
        server = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(server.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    @Test
    fun getTopHeadline_sentRequest_receivedExpected(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val  response = service.getTopHeadline("us",1).body()
            val request = server.takeRequest()
            assertThat(response).isNotNull()
            assertThat(request.path).isEqualTo("/v2/top-headlines?country=us&page=1&apiKey=c2f39fe02d4a410589ab5e1d069b5fbb")

        }
    }


    @Test
    fun getTopHeadline_recivedResponse_correctPageSize(){
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val  response = service.getTopHeadline("us",1).body()
            val articlesList = response!!.articles
            assertThat(articlesList.size).isEqualTo(20)

        }
    }



    private fun enqueueMockResponse(fileName: String){
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        server.enqueue(mockResponse)
    }




    @After
    fun tearDown() {
        server.shutdown()
    }
}