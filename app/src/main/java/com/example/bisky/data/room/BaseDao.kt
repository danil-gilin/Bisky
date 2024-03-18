package com.example.bisky.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

@Dao
interface BaseDao<T> {
    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg obj: T)

    /**
     * Insert an array of objects in the database.
     *
     * @param obj the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(obj: List<T>)

    /**
     * Delete an array of objects in the database.
     *
     * @param obj the objects to be deleted.
     */
    @Delete
    fun delete(vararg obj: T)

    /**
     * Update an array of objects in the database.
     *
     * @param obj the objects to be updated.
     */
    @Update
    fun update(vararg obj: T)
}
