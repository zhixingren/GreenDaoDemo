package com.comapny.greendaodemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comapny.greendaodemo.entity.StudentInfo
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 1.列表查询。
 * 2.单个元素查询。
 * 1.分页查询。
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var daoSession: DaoSession
    private lateinit var studentInfoDao: StudentInfoDao
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        daoSession = GreenDaoApplication.getDaoSession()
        studentInfoDao = daoSession.studentInfoDao

        initListener()

    }

    private fun initListener() {
        btn_add.setOnClickListener {
            addData()
        }

        btn_repeat.setOnClickListener {

        }

        btn_delete.setOnClickListener {
            delete()
        }

        btn_query.setOnClickListener {
            queryDataList()
        }

        btn_update.setOnClickListener {
            update("5","刘备6")
        }

    }

    /**
     * 更新数据数据,替换旧的数据
     */
    private fun update(no: String,name:String) {
        var queryData = queryData(no)
        if (queryData == null) {
            queryData = StudentInfo()
        }
        queryData.perNo = no
        queryData.name = name
        queryData.updateTime = (System.currentTimeMillis()/1000).toString()


        studentInfoDao.insertOrReplace(queryData)
    }

    /**
     * 增加数据
     */
    private fun addData() {
        val studentInfo = StudentInfo()
        studentInfo.perNo = count++.toString()
        studentInfo.name = "刘备$count"
        studentInfo.updateTime = (System.currentTimeMillis()/1000).toString()
        studentInfoDao.insert(studentInfo)
    }

    /**
     * 查询列表
     *
     *
     */
    private fun queryDataList() {
        val queryBuilder = studentInfoDao.queryBuilder().orderDesc(StudentInfoDao.Properties.UpdateTime).limit(10).list()
        queryBuilder.forEach {
            Log.e("no",it.perNo)
            Log.e("name",it.name)
            Log.e("time",it.updateTime)
        }

    }

    /**
     * 根据编号查询具体元素对象
     */
    private fun queryData(no: String): StudentInfo? {
        return studentInfoDao.queryBuilder().where(StudentInfoDao.Properties.PerNo.eq(no)).unique()
    }

    private fun delete() {
        studentInfoDao.deleteAll()
    }
}
