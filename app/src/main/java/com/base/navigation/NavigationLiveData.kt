package com.pempem.utils.navigation

import androidx.lifecycle.LiveData
import java.util.*

/**
 * Created by kapilvij on 25/05/20.
 */
class NavigationLiveData(val navigation: Navigation = Navigation.None): LiveData<Navigation>() {
    init {
        value = navigation
    }
    private val navigationPool: Queue<Navigation> = LinkedList<Navigation>()

    fun navigateTo(distance: Navigation){
        navigationPool.offer(distance)
        navigateNext()
    }

    private fun navigateNext() {
        if (value?.screen== Navigation.Screen.None){
            val next = navigationPool.poll()
            if (next!=null)
                value = next
        }
    }

    fun navigateTo(screen: Navigation.Screen){
        navigateTo(Navigation(screen))
    }

    fun navigateTo(fromScreen: Navigation.Screen?, screen: Navigation.Screen){
        navigateTo(Navigation(fromScreen,screen))
    }

    fun navigated(){
        value = Navigation(Navigation.Screen.None)
        navigateNext()
    }
}