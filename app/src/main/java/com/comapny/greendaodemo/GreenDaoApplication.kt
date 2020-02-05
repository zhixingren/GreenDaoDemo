package com.comapny.greendaodemo

import android.app.Application
import android.content.Context

/**
 * Created by xingzhi on 2020-02-03.
 */
class GreenDaoApplication : Application() {

    companion object {
        private lateinit var instance: Context
        fun getInstance() = instance

        private lateinit var daoSession: DaoSession
        fun getDaoSession() = daoSession
    }

    override fun onCreate() {
        super.onCreate()
        instance = GreenDaoApplication()
        val openHelper = DaoMaster.DevOpenHelper(this, "student-info.db")//数据库名称
        val readableDb = openHelper.writableDb

        daoSession = DaoMaster(readableDb).newSession()

    }



}