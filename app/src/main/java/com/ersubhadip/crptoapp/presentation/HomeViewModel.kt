package com.ersubhadip.crptoapp.presentation

import androidx.lifecycle.ViewModel
import com.ersubhadip.crptoapp.data.IRemoteDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val respository: IRemoteDataRepository
) : ViewModel() {

}