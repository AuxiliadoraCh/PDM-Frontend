package com.andriod17.upbudget.data.repository.Learning

import com.andriod17.upbudget.data.model.Learning.LearningItem

interface LearningRepository {
    suspend fun getLearningItemsFromDB(): List<LearningItem>
}


