package com.comapny.greendaodemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.comapny.greendaodemo.entity.StudentInfo
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 1.列表查询。
 * 2.单个元素查询。
 * 3.分页查询。
 * 4.新增数据。
 * 5.删除数据。
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var daoSession: DaoSession
    private lateinit var studentInfoDao: StudentInfoDao
    private var count = 0
    private var pageNo = 0
    private val pageSize = 10

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

        btn_delete.setOnClickListener {
            delete()
        }

        btn_query.setOnClickListener {
            queryDataList(pageNo,pageSize)
        }

        btn_update.setOnClickListener {
            update("5","刘备6")
        }
        btn_query_all.setOnClickListener {
            queryAllData()
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
        studentInfo.updateTime = (System.currentTimeMillis()).toString()
        studentInfoDao.insertOrReplace(studentInfo)
    }

    /**
     * #分页查询，pageNo的index是从0开始的
     * offset(x)参数：
     * limit(y)参数：
     * 从x开始查询，查询y条数据
     */
    private fun queryDataList(no: Int,pageSize:Int) {
        val queryBuilder = studentInfoDao.queryBuilder().orderDesc(StudentInfoDao.Properties.UpdateTime).offset(no*pageSize).limit(pageSize).list()
        queryBuilder.forEach {
            Log.e("no",it.perNo)
            Log.e("name",it.name)
            Log.e("time",it.updateTime)
        }
        pageNo ++
    }

    /**
     * 根据编号查询具体元素对象
     */
    private fun queryData(no: String): StudentInfo? {
        return studentInfoDao.queryBuilder().where(StudentInfoDao.Properties.PerNo.eq(no)).unique()
    }

    /**
     * 查询全部
     */
    private fun queryAllData() {
        val list =
            studentInfoDao.queryBuilder().orderDesc(StudentInfoDao.Properties.UpdateTime).list()
        list.forEach {
            Log.e("no",it.perNo)
            Log.e("name",it.name)
            Log.e("time",it.updateTime)
        }
    }


    private fun delete() {
        studentInfoDao.deleteAll()
    }
}
