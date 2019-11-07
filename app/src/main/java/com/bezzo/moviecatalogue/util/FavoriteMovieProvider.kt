package com.bezzo.moviecatalogue.util

import android.content.*
import android.database.Cursor
import android.net.Uri
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.local.LocalStorage
import com.bezzo.moviecatalogue.data.model.Favorite
import java.util.*
import java.util.concurrent.Callable


class FavoriteMovieProvider: ContentProvider() {

    companion object {
        val AUTHORITY = "com.bezzo.moviecatalogue.provider"
        val FAVORITE_URI = Uri.parse(
            "content://" + AUTHORITY + "/" + AppConstant.FAVORITE
        )

        val CONTENT_URI = Uri.Builder().scheme("content")
            .authority(AUTHORITY)
            .appendEncodedPath(AppConstant.FAVORITE)
            .build()
    }

    /** The match code for some items in the Cheese table.  */
    private val CODE_FAVORITE_DIR = 1

    /** The match code for an item in the Cheese table.  */
    private val CODE_FAVORITE_ITEM = 2

    /** The URI matcher.  */
    private val MATCHER = UriMatcher(UriMatcher.NO_MATCH)

    init {
        MATCHER.addURI(
            AUTHORITY,
            AppConstant.FAVORITE, CODE_FAVORITE_DIR);
        MATCHER.addURI(AUTHORITY, AppConstant.FAVORITE + "/*", CODE_FAVORITE_ITEM);
    }

    override fun onCreate(): Boolean {
        return true
    }
    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val code = MATCHER.match(uri)
        if (code == CODE_FAVORITE_DIR || code == CODE_FAVORITE_ITEM) {
            val context = context ?: return null
            val favorite = LocalStorage.getDatabase(context).favoriteDao()
            val cursor: Cursor
            cursor = if (code == CODE_FAVORITE_DIR) {
                favorite.allFavorites()
            } else {
                favorite.getFavorite(ContentUris.parseId(uri).toInt())
            }
            cursor.setNotificationUri(context.contentResolver, uri)
            return cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return when (MATCHER.match(uri)) {
            CODE_FAVORITE_DIR -> "vnd.android.cursor.dir/" + AUTHORITY + "." + AppConstant.FAVORITE
            CODE_FAVORITE_ITEM -> "vnd.android.cursor.item/" + AUTHORITY + "." + AppConstant.FAVORITE
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (MATCHER.match(uri)) {
            CODE_FAVORITE_DIR -> {
                val context = context ?: return null
                val id = LocalStorage.getDatabase(context).favoriteDao()
                    .add(Favorite.fromContentValues(values!!))
                context.contentResolver.notifyChange(uri, null)
                return ContentUris.withAppendedId(uri, id)
            }
            CODE_FAVORITE_ITEM -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(
        uri: Uri, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        when (MATCHER.match(uri)) {
            CODE_FAVORITE_DIR -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            CODE_FAVORITE_ITEM -> {
                val context = context ?: return 0
                val count = LocalStorage.getDatabase(context).favoriteDao()
                    .deleteById(ContentUris.parseId(uri).toInt())
                context.contentResolver.notifyChange(uri, null)
                return count
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        when (MATCHER.match(uri)) {
            CODE_FAVORITE_DIR -> throw IllegalArgumentException("Invalid URI, cannot update without ID$uri")
            CODE_FAVORITE_ITEM -> {
                val context = context ?: return 0
                val cheese = Favorite.fromContentValues(values!!)
                cheese.id = ContentUris.parseId(uri).toInt()
                val count = LocalStorage.getDatabase(context).favoriteDao()
                    .add(cheese).toInt()
                context.contentResolver.notifyChange(uri, null)
                return count
            }
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun applyBatch(operations: ArrayList<ContentProviderOperation>): Array<ContentProviderResult> {
        val context = context ?: return arrayOf()
        val database = LocalStorage.getDatabase(context)
        return database.runInTransaction(Callable<Array<ContentProviderResult>> {
            super@FavoriteMovieProvider.applyBatch(operations)
        })
    }

    override fun bulkInsert(uri: Uri, values: Array<out ContentValues>): Int {
        when (MATCHER.match(uri)) {
            CODE_FAVORITE_DIR -> {
                val context = context ?: return 0
                val database = LocalStorage.getDatabase(context)
                val favorites = arrayOf<Favorite>()
                for (i in values.indices) {
                    favorites[i] = Favorite.fromContentValues(values[i])
                }
                return database.favoriteDao().inserts(favorites).size
            }
            CODE_FAVORITE_ITEM -> throw IllegalArgumentException("Invalid URI, cannot insert with ID: $uri")
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }
}