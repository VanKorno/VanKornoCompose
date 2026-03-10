package com.vankorno.vankornocompose.fileOps.media

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.core.graphics.scale
import com.vankorno.vankornocompose.fileOps.LibFileGetter
import com.vankorno.vankornocompose.fileOps.generateUniqueFilename
import com.vankorno.vankornocompose.values.LibGlobals2.appStorage
import com.vankorno.vankornohelpers.SizeWH
import com.vankorno.vankornohelpers.dLog
import com.vankorno.vankornohelpers.eLog
import com.vankorno.vankornohelpers.getRealScreenSizePx
import java.io.File
import java.io.FileOutputStream

private const val TAG = "LibPic"

class LibPic(                                             private val context: Context,
                                                   private val fileNamePrefix: String = "pic",
                                                    private val picFolderName: String = "user_pics",
) {
    private val fileGetter = LibFileGetter(context)
    
    fun saveImageFromUri(                                                      uri: Uri,
                                                                         extension: String = "png",
    ): String {
        // region LOG
            dLog(TAG, "saveImageFromUri()")
        // endregion
        val filename = generateUniqueFilename(fileNamePrefix, extension)
        
        var result = ""
        
        appStorage.pics {
            result = saveFromUri(uri, filename) ?: ""
        }
        return result
    }
    
    
    
    fun deleteImage(                                                                path: String
    ): Boolean {
        // region LOG
            dLog(TAG, "deleteImage(path = $path)")
        // endregion
        if (path.isBlank()) {
            // region LOG
                eLog(TAG, "deleteImage(): path is blank. Returning...")
            // endregion
            return false //\/\/\/\/\/\ 
        }
        val name = File(path).name
        
        var result = false
        
        appStorage.pics {
            result = delete(name)
        }
        return result
    }
    
    
    fun listImageFiles(): List<File> {
        var paths: List<String> = emptyList()
        
        appStorage.pics {
            paths = list()
        }
        return paths.map { fileGetter.getFileInAppStorage(it) }
    }
    
    
    fun clearAllImages(): Int {
        // region LOG
            dLog(TAG, "clearAllImages()")
        // endregion
        var count = 0
        
        appStorage.pics {
            count = deleteAll()
        }
        return count
    }
    
    
    fun getImageSize(                                                               path: String
    ): SizeWH? {
        return try {
            val opts = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(fileGetter.getFileInAppStorage(path).absolutePath, opts)
            val w = opts.outWidth
            val h = opts.outHeight
            // region LOG
                dLog(TAG, "getImageSize() returns w = $w, h = $h")
            // endregion
            if (w <= 0 || h <= 0) {
                // region LOG
                    eLog(TAG, "getImageSize(): invalid size w=$w h=$h")
                // endregion
                return null //\/\/\/\/\/\
            }
            SizeWH(w = w, h = h)
        } catch (e: Exception) {
            // region LOG
                eLog(TAG, "getImageSize() failed!", e)
            // endregion
            null
        }
    }
    
    
    fun updateImageFromUri(                                                          uri: Uri,
                                                                                filename: String,
    ): Boolean {
        // region LOG
            dLog(TAG, "updateImageFromUri(filename = $filename)")
        // endregion
        var result: String? = null
        
        appStorage.pics {
            result = saveFromUri(uri, filename)
        }
        return result != null
    }
    
    
    fun getBitmapFromPath(                                                          path: String
    ): Bitmap? = BitmapFactory.decodeFile(
        fileGetter.getFileInAppStorage(path).absolutePath
    )
    
    
    fun saveBitmapAsNewFile(              bitmap: Bitmap,
                                          format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
                                         quality: Int = 100
    ): String {
        // region LOG
            dLog(TAG, "saveBitmapAsNewFile(format = ${format.name}, quality = $quality)")
        // endregion
        val extension = when (format) {
            Bitmap.CompressFormat.PNG -> "png"
            Bitmap.CompressFormat.JPEG -> "jpg"
            Bitmap.CompressFormat.WEBP -> "webp"
            else -> "img"
        }
        val filename = generateUniqueFilename(fileNamePrefix, extension)
        val relativePath = "$picFolderName/$filename"
        val ok = saveBitmapAt(relativePath, bitmap, format, quality)
        return if (ok) relativePath else ""
    }
    
    
    fun saveBitmapAt(                       path: String,
                                          bitmap: Bitmap,
                                          format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
                                         quality: Int = 100,
    ): Boolean {
        // region LOG
            dLog(TAG, "saveBitmapAt(path = $path, format = ${format.name}, quality = $quality)")
        // endregion
        if (path.isBlank()) {
            // region LOG
                eLog(TAG, "saveBitmapAt() Failed because path is blank or empty.")
            // endregion
            return false //\/\/\/\/\/\
        }
        return try {
            val file = fileGetter.getFileInAppStorage(path)
            
            file.parentFile?.mkdirs() /*This ensures that
                if someone ever passes a path with subdirectories (e.g., "subfolder/my_image.png"),
                it won’t crash with FileNotFoundException.*/
            
            FileOutputStream(file).use { out ->
                if (!bitmap.compress(format, quality, out)) return false
            }
            // region LOG
                dLog(TAG, "saveBitmapAt(): Saved successfully!")
            // endregion
            true
        } catch (e: Exception) {
            // region LOG
                eLog(TAG, "saveBitmapAt() Failed!", e)
            // endregion
            false
        }
    }
    
    
    
    
    
    // ------------------------------------  R E S I Z E  ------------------------------------ \\
    
    /** fraction: 1f = 100% */
    fun resizePicToScr(                     path: String,
                                        fraction: Float = 1f,
                                         quality: Int = 100,
                                          format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
                                 longestSideOnly: Boolean = true,
    ): Boolean {
        // region LOG
            dLog(TAG, "resizePicToScr(path = $path, fraction = $fraction, quality = $quality, format = ${format.name}, byLongestSideOnly = $longestSideOnly)")
        // endregion
        val (maxW, maxH) = getMaxWHToFitScr(fraction, longestSideOnly)
        return resizeImagePreserveAspect(path, maxW, maxH, format, quality)
    }
    
    
    fun resizeBitmapToScr(                                                original: Bitmap,
                                                                          fraction: Float = 1f,
                                                                   longestSideOnly: Boolean = true,
    ): Bitmap {
        // region LOG
            dLog(TAG, "resizeBitmapToScr(fraction = $fraction, longestSideOnly = $longestSideOnly)")
        // endregion
        val (maxW, maxH) = getMaxWHToFitScr(fraction, longestSideOnly)
        val result = resizeBitmapPreserveAspect(original, maxW, maxH)
        return if (result.resized) result.bitmap else original
    }
    
    
    private fun getMaxWHToFitScr(                                               fraction: Float,
                                                                         longestSideOnly: Boolean,
    ): Pair<Int, Int> {
        val safeFraction = fraction.coerceIn(0.1f, 1f)
        val screen = getRealScreenSizePx(context)
        var maxW = (screen.w * safeFraction).toInt()
        var maxH = (screen.h * safeFraction).toInt()
        
        if (longestSideOnly) {
            val longest = maxOf(maxW, maxH)
            maxW = longest
            maxH = longest
        }
        return Pair(maxW, maxH)
    }
    
    
    /**
     * Returns true if resized successfully or already small enough.
     */
    fun resizeImagePreserveAspect(          path: String,
                                        maxWidth: Int,
                                       maxHeight: Int,
                                          format: Bitmap.CompressFormat = Bitmap.CompressFormat.PNG,
                                         quality: Int = 100,
    ): Boolean {
        // region LOG
            dLog(TAG, "resizeImagePreserveAspect(path = $path, maxW = $maxWidth, maxH = $maxHeight, format = ${format.name}, quality = $quality)")
        // endregion
        val original = getBitmapFromPath(path) ?: return false
        val result = resizeBitmapPreserveAspect(original, maxWidth, maxHeight)
        if (!result.resized) return true //\/\/\/\/\/\
        
        return saveBitmapAt(path, result.bitmap, format, quality)
    }
    
    private fun resizeBitmapPreserveAspect(                                     original: Bitmap,
                                                                                maxWidth: Int,
                                                                               maxHeight: Int,
    ): ResizeBitmapResult {
        val width = original.width
        val height = original.height
        
        if (width <= maxWidth && height <= maxHeight) {
            // region LOG
            dLog(TAG, "resizeBitmapPreserveAspect(): The image is already small enough. Resizing isn't needed.")
            // endregion
            return ResizeBitmapResult(false, original) //\/\/\/\/\/\ 
        }
        val ratio = minOf(maxWidth / width.toFloat(), maxHeight / height.toFloat())
        val newW = (width * ratio).toInt()
        val newH = (height * ratio).toInt()
        val resized = original.scale(newW, newH)
        return ResizeBitmapResult(true, resized)
    }
    
    internal data class ResizeBitmapResult(val resized: Boolean, val bitmap: Bitmap)
    
    
    
    
    
    // ------------------------------------  N A M I N G  ------------------------------------ \\
    
    fun renameImage(                                                             oldPath: String,
                                                                                 newName: String,
    ): String? {
        // region LOG
            dLog(TAG, "renameImage(oldPath = $oldPath, newName = $newName)")
        // endregion
        if (oldPath.isBlank()) {
            // region LOG
                eLog(TAG, "renameImage(): oldPath is blank. Returning null...")
            // endregion
            return null //\/\/\/\/\/\
        } else if (newName.isBlank()) {
            // region LOG
                eLog(TAG, "renameImage(): newName is blank. Returning null...")
            // endregion
            return null //\/\/\/\/\/\
        }
        val oldName = File(oldPath).name
        
        var result: String? = null
        
        appStorage.pics {
            result = rename(oldName, newName)
        }
        return result
    }
    
    
}



