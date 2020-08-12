package com.pempem.utils.navigation

import java.util.*

/**
 * Created by kapilvij on 25/05/20.
 */
class Navigation(val fromScreen: Screen?, val screen: Screen) {

    constructor(screen: Screen):this(null,screen)

    enum class Screen {
        None,
        SPLASH,
        LOGIN,
        DASHBOARD,
        PROFILE
    }
    enum class Param(val value:String){
//        Phone("phone"),
    }
    private val params:MutableMap<String, Any?> = HashMap()

    fun addParam(name: Param, value:Any?): Navigation {
        params[name.value] = value
        return this
    }
    fun getParam(name: Param):Any?{
        return params[name.value]
    }
    companion object {
        val None = Navigation(Screen.None)
    }
}