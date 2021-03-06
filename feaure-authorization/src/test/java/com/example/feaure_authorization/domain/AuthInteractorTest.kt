package com.example.feaure_authorization.domain

import com.example.core.data.session.SessionRepository
import com.example.core.prefs.UserPrefs
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.only
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthInteractorTest : Spek(
    {
        Feature("AuthInteractor") {

            // region values
            val mockSessionId = "123"
            val testLogin = "testLogin"
            val invalidTestLogin = "invalidTestLogin"
            val testPassword = "testPassword"
            val invalidTestPassword = "invalidTestPassword"

            lateinit var repositoryMock: SessionRepository

            val prefsMock by memoized { mock<UserPrefs>() }

            val authInteractor by memoized {
                AuthInteractor(
                    repositoryMock,
                    prefsMock
                )
            }
            //endregion

            Scenario("success authorization") {

                When("authorize call with proper user data") {
                    repositoryMock = mock {
                        on { refreshSessionId(testLogin, testPassword) } doReturn Single.just(
                            mockSessionId
                        )
                    }
                    authInteractor.authorize(testLogin, testPassword)
                        .blockingGet()
                }
                Then("refreshSessionId called") {
                    verify(repositoryMock, only()).refreshSessionId(testLogin, testPassword)
                }
                And("new login is saved to userPrefs") {
                    verify(prefsMock).userLogin = testLogin
                }
                And("new password is saved to userPrefs") {
                    verify(prefsMock).userPassword = testPassword
                }
            }

            Scenario("failed authorization") {
                When("authorize call with invalid user data") {
                    repositoryMock = mock {
                        on {
                            refreshSessionId(
                                invalidTestLogin,
                                invalidTestPassword
                            )
                        } doReturn Single.error<String>(Exception())
                    }
                    authInteractor.authorize(invalidTestLogin, invalidTestPassword)
                        .blockingGet()
                }
                Then("refreshSessionId called") {
                    verify(repositoryMock, only()).refreshSessionId(
                        invalidTestLogin,
                        invalidTestPassword
                    )
                }
                And("new login is not saved to userPrefs") {
                    verify(prefsMock, never()).userLogin = testLogin
                }
                And("new password is not saved to userPrefs") {
                    verify(prefsMock, never()).userPassword = testPassword
                }
            }
        }
    }
)
