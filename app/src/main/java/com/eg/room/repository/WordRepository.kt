package com.eg.room.repository

import androidx.annotation.WorkerThread
import com.eg.room.dao.WordDao
import com.eg.room.entity.Word
import kotlinx.coroutines.flow.Flow


//A repository class abstracts access to multiple data sources. The repository
// is not part of the Architecture Components libraries, but is a suggested best practice
// for code separation and architecture. A Repository class provides a clean API for data access to
// the rest of the application.

//A Repository manages queries and allows you to use multiple backends.
// In the most common example,the Repository implements the logic for deciding
// whether to fetch data from a network or use results
// cached in a local database.
// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allWords: Flow<List<Word>> = wordDao.getAlphabetizedWords()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}