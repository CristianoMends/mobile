package com.example.crudapp

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface  UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM user_table")
    fun getAll() : Flow<List<User>>

    @Update
    suspend fun update(user: User): Int

    @Delete
    suspend fun delete(user: User): Int
}