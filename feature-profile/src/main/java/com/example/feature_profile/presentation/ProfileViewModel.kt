package com.example.feature_profile.presentation

import androidx.lifecycle.viewModelScope
import com.example.core.data.session.SessionRepository
import com.example.core.presentation.BaseViewModel
import com.example.core.util.runCatchingCancellable
import com.example.feature_mainscreen.MainScreenFragmentDirections
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
) : BaseViewModel<ProfileViewState>(
    initialState = ProfileViewState(
        userName = "Mick Wick",
        userMail = "examle@mail.com"
    )
) {

    fun onLogoutButtonClick() {
        viewModelScope.launch {
            runCatchingCancellable(
                action = { sessionRepository.deleteSession() },
                onSuccess = {
                    parentNavigateTo(MainScreenFragmentDirections.actionMainScreenToAuth())
                }
            )
        }
    }
}
