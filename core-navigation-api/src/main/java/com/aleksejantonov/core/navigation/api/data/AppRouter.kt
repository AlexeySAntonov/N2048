package com.aleksejantonov.core.navigation.api.data

import android.os.Bundle

interface AppRouter {
    fun navigateTo(screen: Screens, args: Bundle? = null)
}