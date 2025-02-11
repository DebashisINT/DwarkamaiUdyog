package com.breezedwarkamaiudyog.app.domain

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.breezedwarkamaiudyog.app.AppConstant

@Entity(tableName = AppConstant.QUESTION_TABLE_MASTER)
class QuestionEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @ColumnInfo(name = "question_id")
    var question_id: String? = null

    @ColumnInfo(name = "question")
    var question: String? = null
}