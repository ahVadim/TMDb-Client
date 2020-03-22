package com.example.core.network

import com.example.core.BuildConfig
import com.example.core.network.AuthInterceptor.Companion.API_TOKEN_QUERY_FIELD_NAME
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthInterceptorTest : Spek(
    {
        Feature("Auth okhttp interceptor") {

            lateinit var server: MockWebServer

            beforeFeature {
                server = MockWebServer()
                server.enqueue(MockResponse())
                server.start()
            }

            afterFeature {
                server.shutdown()
            }

            Scenario("intercept any request") {

                lateinit var httpClient: OkHttpClient
                lateinit var resultRequest: RecordedRequest

                Given("client with authInterceptor") {
                    httpClient = OkHttpClient().newBuilder()
                        .addInterceptor(AuthInterceptor())
                        .build()
                }

                When("call any request") {
                    val callRequest = Request.Builder()
                        .url(server.url("/"))
                        .build()
                    httpClient.newCall(callRequest).execute()
                    resultRequest = server.takeRequest()
                }

                Then("should add query with proper field name") {
                    assertThat(resultRequest.requestUrl?.queryParameterNames)
                        .contains(API_TOKEN_QUERY_FIELD_NAME)
                }

                Then("should add api token from build config to query") {
                    assertThat(resultRequest.requestUrl?.queryParameter("api_key"))
                        .isEqualTo(BuildConfig.API_TOKEN)
                }
            }
        }
    }
)
