package com.vankorno.appforcomposelib.fileOps

import androidx.test.filters.MediumTest
import com.vankorno.vankornocompose.fileOps.LibFileOps.createDummyFile
import com.vankorno.vankornocompose.fileOps.LibFileOps.deleteAllFiles
import com.vankorno.vankornocompose.fileOps.LibFileOps.fileExists
import com.vankorno.vankornocompose.fileOps.LibFileOps.getUriForFile
import com.vankorno.vankornocompose.fileOps.LibFileOps.readTextFromFile
import com.vankorno.vankornocompose.fileOps.LibFileOps.renameFile
import com.vankorno.vankornocompose.fileOps.LibFileOps.saveFileFromUri
import com.vankorno.vankornocompose.fileOps.LibFileOps.writeTextToFile
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@MediumTest
class DirectoryHandlingTest {
    private val dir = "test_dir"
    
    @Before
    fun setup() {
        deleteAllFiles(dir)
    }
    
    
    @Test
    fun writeText_createsMissingDir() {
        val nestedDir = "test_dir_nested/a/b/c"
        
        val path = writeTextToFile(nestedDir, "file.txt", "hello")
        
        assertTrue(fileExists(path))
        
        val text = readTextFromFile(nestedDir, "file.txt")
        assertEquals("hello", text)
    }
    
    @Test
    fun saveFileFromUri_createsMissingDir() {
        val nestedDir = "test_dir_nested_uri/x/y/z"
        
        val srcPath = createDummyFile(dir, "src.txt")
        val uri = getUriForFile(srcPath)
        
        val newPath = saveFileFromUri(nestedDir, uri)
        
        assertNotNull(newPath)
        assertTrue(fileExists(newPath!!))
    }
    
    @Test
    fun renameFile_createsDirIfMissing() { // TODO Check
        val nestedDir = "test_dir_nested_rename/q/w/e"
        
        createDummyFile(nestedDir, "old.txt")
        
        val newPath = renameFile(nestedDir, "old.txt", "new")
        
        assertNotNull(newPath)
        assertTrue(fileExists(newPath!!))
    }
    
    @Test
    fun createDummyFile_createsDeepNestedDir() {
        val nestedDir = "test_dir_nested_dummy/1/2/3/4"
        
        val path = createDummyFile(nestedDir, "dummy.txt")
        
        assertTrue(fileExists(path))
    }
    
    @Test
    fun deleteAllFiles_onNonExistingDir_returnsZero() {
        val nestedDir = "test_dir_non_existing/a/b/c"
        
        val deletedCount = deleteAllFiles(nestedDir)
        
        assertEquals(0, deletedCount)
    }
}