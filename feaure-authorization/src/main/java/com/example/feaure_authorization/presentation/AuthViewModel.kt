package com.example.feaure_authorization.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.exceptions.AuthException
import com.example.core.presentation.BaseViewModel
import com.example.core.presentation.events.HideKeyboard
import com.example.core.util.delegate
import com.example.core.util.runCatchingCancellable
import com.example.feaure_authorization.domain.AuthInteractor
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
) : BaseViewModel() {

    val liveState = MutableLiveData(createInitialState())
    private var state by liveState.delegate()

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
        viewModelScope.launch {
            runCatchingCancellable(
                action = { authInteractor.authorize(login, password) },
                onSuccess = {
                    state = state.copy(errorState = AuthErrorState.None)
                    eventsQueue.offer(HideKeyboard)
                    navigateTo(AuthFragmentDirections.actionAuthToPincode())
                },
                onError = { error ->
                    state = getErrorState(error)
                }
            )
        }
    }

    private fun getErrorState(error: Throwable): AuthViewState {
        return when (error) {
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
    }
}
