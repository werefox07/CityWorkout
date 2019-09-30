package com.example.cityworkout

import android.app.Application
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

class SampleApplication: Application() {
    companion object {
        lateinit var INSTANCE: SampleApplication
    }

    private var cicerone: Cicerone<Router>? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        initCicerone()
    }

    private fun initCicerone() {
        cicerone = Cicerone.create()
    }

    fun getNavigatorHolder(): NavigatorHolder {
        return cicerone!!.navigatorHolder
    }

    fun getRouter(): Router {
        return cicerone!!.router
    }
}