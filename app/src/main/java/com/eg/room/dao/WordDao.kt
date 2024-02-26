package com.eg.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.eg.room.entity.Word
import kotlinx.coroutines.flow.Flow

//In the DAO (data access object), you specify SQL queries and associate them with method calls.
// The compiler checks the SQL and generates queries from convenience annotations for common queries,
// such as @Insert. Room uses the DAO to create a clean API for your code.
//
//The DAO must be an interface or abstract class.
//Let's write a DAO that provides queries for:
//
//Getting all words ordered alphabetically
//Inserting a word
//Deleting all words
@Dao
interface WordDao {
@Query("SELECT * FROM word_table ORDER BY word ASC")
fun getAlphabetizedWords() : Flow<List<Word>>

@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun insert(word : Word)

@Query("DELETE FROM word_table")
suspend fun deleteAll()
}

//Understanding:-
//WordDao is an interface; DAOs must either be interfaces or abstract classes.
//The @Insert annotation is a special DAO method annotation where you don't have to provide any SQL!
// (There are also @Delete and @Update annotations for deleting and updating rows, but you are not
// using them in this app.)
//suspend fun deleteAll(): Declares a suspend function to delete all the words.
//There is no convenience annotation for deleting multiple entities, so it's annotated with the generic @Query.
//@Query("DELETE FROM word_table"): @Query requires that you provide a SQL query as a string parameter
// to the annotation, allowing for complex read queries and other operations.
//fun getAlphabetizedWords(): List<Word>: A method to get all the words and have it return a List of Words.
//@Query("SELECT * FROM word_table ORDER BY word ASC"): Query that returns a list of words sorted in ascending order.
//To observe data changes you will use Flow from kotlinx-coroutines.
// Use a return value of type Flow in your method description, and Room generates all necessary code
// to update the Flow when the database is updated.