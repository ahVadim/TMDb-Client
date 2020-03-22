package com.example.feaure_authorization.domain

import com.example.core.data.session.RefreshSessionRepository
import com.example.core.prefs.UserPrefs
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthInteractorTest : Spek(
    {
        val mockSessionId = "123"
        val testLogin = "testlogin"
        val testPassword = "testPassword"
        val repositoryMock: RefreshSessionRepository = mock {
            on { refreshSessionId(eq(testLogin), eq(testPassword)) } doReturn Single.just(
                mockSessionId
            )
        }
        val prefsMock: UserPrefs = mock()

        val authInteractor = AuthInteractor(
            repositoryMock,
            prefsMock
        )

        Feature("AuthInteractor") {
            Scenario("success authorization") {
                When("authorize call with proper user data") {
                    authInteractor.authorize(testLogin, testPassword)
                        .subscribe()
                }
                Then("refreshSessionId called") {
                    verify(repositoryMock).refreshSessionId(testLogin, testPassword)
                }
                And("new login is saved to userPrefs") {
                    verify(prefsMock).userLogin = testLogin
                }
                And("new password is saved to userPrefs") {
                    verify(prefsMock).userPassword = testPassword
                }
            }
        }
    }
)
