package com.comapny.greendaodemo

import android.content.Context
import android.util.Log
import org.greenrobot.greendao.database.Database

/**
 *  升级
 */
class DbOpenHelper(context: Context?, name: String?) : DaoMaster.DevOpenHelper(context, name) {


    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        //super.onUpgrade(db, oldVersion, newVersion)
        Log.e("oldVersion",oldVersion.toString())
        Log.e("newVersion",newVersion.toString())
        when (newVersion) {
            //新增字段
            2 -> {
                db?.execSQL("alter table student_info add column sex integer")
            }
            //新增表
            3 -> {
               //XXXDao.create(db,true)
            }
        }

    }
}