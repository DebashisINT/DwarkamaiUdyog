package com.breezedwarkamaiudyog.features.taskManagement.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.breezedwarkamaiudyog.app.AppConstant
import com.breezedwarkamaiudyog.app.domain.ProductRateEntity
import com.breezedwarkamaiudyog.app.domain.ShopActivityEntity

@Dao
interface TaskManagementDao {

    @Insert
    fun insertAll(vararg obj: TaskManagmentEntity)


    @Query("SELECT * FROM " + AppConstant.TASK_ACTIVITY +" where task_next_date=:task_next_date")
    fun getAll(task_next_date:String): List<TaskManagmentEntity>

    @Query("update "+AppConstant.TASK_ACTIVITY+" set task_next_date=:task_next_date where task_status_id=:task_status_id ")
    fun trash2(task_status_id: String, task_next_date: String)
}