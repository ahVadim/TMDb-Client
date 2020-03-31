package com.example.feaure_authorization.presentation

import androidx.lifecycle.MutableLiveData
import com.example.core.exceptions.AuthException
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.EventsQueue
import com.example.core.presentation.events.NavEvent
import com.example.core.rxjava.SchedulersProvider
import com.example.core.util.delegate
import com.example.core.util.ioToMain
import com.example.feaure_authorization.domain.AuthInteractor
import timber.log.Timber
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val schedulers: SchedulersProvider
) : BaseViewModel() {

    var liveState = MutableLiveData<AuthViewState>(createInitialState())
    private var state by liveState.delegate()
    val eventsQueue = EventsQueue()

    private fun createInitialState(): AuthViewState {
        return AuthViewState(
            login = null,
            password = null,
            errorState = AuthErrorState.None,
            isLoginButtonEnabled = false
        )
    }

    fun onLoginChange(login: String?) {
        state = state.copy(
            login = login,
            isLoginButtonEnabled = login.isNullOrBlank() && !state.password.isNullOrBlank(),
            errorState = AuthErrorState.None
        )
    }

    fun onPasswordChange(password: String?) {
        state = state.copy(
            password = password,
            isLoginButtonEnabled = !state.login.isNullOrBlank() && !password.isNullOrBlank(),
            errorState = AuthErrorState.None
        )
    }

    fun onLoginButtonClick(login: String, password: String) {
        authInteractor.authorize(login, password)
            .ioToMain(schedulers)
            .subscribe({
                           state = state.copy(errorState = AuthErrorState.None)
                           eventsQueue.offer(
                               NavEvent(AuthFragmentDirections.actionAuthToMainScreen())
                           )
                       }, { error ->
                           Timber.e(error)
                           state = when (error) {
                               is AuthException -> {
                                   state.copy(
                                       errorState = AuthErrorState.IncorrectData,
                                       isLoginButtonEnabled = false
                                   )
                               }
                               else -> {
                                   state.copy(errorState = AuthErrorState.TryLater)
                               }
                           }
                       })
            .let(this::addDisposable)
    }
}
