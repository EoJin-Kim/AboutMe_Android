package com.ej.aboutme.viewmodel

import androidx.lifecycle.ViewModel
import com.ej.aboutme.api.AboutMeFetchr

class GroupViweModel : ViewModel() {
    private val aboutMeFetchr : AboutMeFetchr by lazy { AboutMeFetchr() }
}