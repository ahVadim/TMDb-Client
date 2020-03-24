package com.example.feaure_authorization

import com.example.core.data.session.RefreshSessionRepository
import com.example.core.prefs.UserPrefs
import com.example.core.rxjava.TestSchedulersProvider
import com.example.feaure_authorization.domain.AuthInteractor
import com.example.feaure_authorization.network.AuthMockDispatcher
import com.example.feaure_authorization.network.SessionApiFactory
import com.example.feaure_authorization.presentation.presenter.AuthPresenter
import com.example.feaure_authorization.presentation.view.AuthView
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import okhttp3.mockwebserver.MockWebServer
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class AuthIntegrationTest : Spek(
    {
        Feature("Auth integration test") {

            val server = MockWebServer().apply {
                dispatcher = AuthMockDispatcher()
            }
            val userPrefs = mock<UserPrefs>()
            val refreshSessionRepository = RefreshSessionRepository(
                SessionApiFactory.getSessionApi(server.url("")),
                userPrefs
            )
            val authInteractor = AuthInteractor(
                refreshSessionRepository,
                userPrefs
            )

            val authView = mock<AuthView>()
            val authPresenter = AuthPresenter(
                authInteractor,
                TestSchedulersProvider()
            ).apply {
                attachView(authView)
            }

            Scenario("login with proper user data") {

                When("on login button click") {
                    authPresenter.onLoginButtonClick(
                        AuthMockDispatcher.PROPER_LOGIN,
                        AuthMockDispatcher.PROPER_PASSWORD
                    )
                }

                Then("view show success message") {
                    verify(authView).showSuccessAuthorizationMessage()
                }
            }
        }
    }
)
