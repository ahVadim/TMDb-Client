package com.example.feaure_authorization

import com.example.core.data.session.SessionRepository
import com.example.core.network.refreshsession.SessionApi
import com.example.core.prefs.UserPrefs
import com.example.feaure_authorization.domain.AuthInteractor
import com.example.feaure_authorization.network.AuthMockDispatcher
import com.example.feaure_authorization.network.SessionApiFactory
import com.example.feaure_authorization.presentation.presenter.AuthPresenter
import com.example.feaure_authorization.presentation.view.AuthErrorState
import com.example.feaure_authorization.presentation.view.AuthView
import com.example.test_core.rxjava.TestSchedulersProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import okhttp3.mockwebserver.MockWebServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class AuthIntegrationTest : Spek(
    {
        Feature("Auth integration test") {

            // region values
            val server by memoized {
                MockWebServer().apply {
                    dispatcher = AuthMockDispatcher()
                }
            }
            val userPrefs by memoized { mock<UserPrefs>() }
            lateinit var sessionApi: SessionApi
            val refreshSessionRepository by memoized {
                SessionRepository(
                    sessionApi,
                    userPrefs
                )
            }
            val authInteractor by memoized {
                AuthInteractor(
                    refreshSessionRepository,
                    userPrefs
                )
            }

            val authView by memoized { mock<AuthView>() }
            val authPresenter by memoized {
                AuthPresenter(
                    authInteractor,
                    TestSchedulersProvider()
                ).apply {
                    attachView(authView)
                }
            }
            // endregion

            Scenario("login with proper user data") {

                When("on login button click") {
                    sessionApi = SessionApiFactory.getSessionApi(
                        baseUrl = server.url(""),
                        isNetworkAvailable = true
                    )
                    authPresenter.onLoginButtonClick(
                        AuthMockDispatcher.PROPER_LOGIN,
                        AuthMockDispatcher.PROPER_PASSWORD
                    )
                }

                Then("view show success message") {
                    verify(authView).showSuccessAuthorizationMessage()
                }
            }

            Scenario("login with invalid user data and available network") {

                When("on login button click") {
                    sessionApi = SessionApiFactory.getSessionApi(
                        baseUrl = server.url(""),
                        isNetworkAvailable = true
                    )
                    authPresenter.onLoginButtonClick(
                        "invalid login",
                        "invalid password"
                    )
                }

                Then("view show incorrect data error") {
                    verify(authView).setErrorState(AuthErrorState.IncorrectData)
                }
            }

            Scenario("login with invalid user data and not available network") {

                When("on login button click") {
                    sessionApi = SessionApiFactory.getSessionApi(
                        baseUrl = server.url(""),
                        isNetworkAvailable = false
                    )
                    authPresenter.onLoginButtonClick(
                        "invalid login",
                        "invalid password"
                    )
                }

                Then("view show try later error") {
                    verify(authView).setErrorState(AuthErrorState.TryLater)
                }
            }
        }
    }
)
