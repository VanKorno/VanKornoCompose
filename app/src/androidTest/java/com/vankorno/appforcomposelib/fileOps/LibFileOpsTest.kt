package com.vankorno.appforcomposelib.fileOps

import androidx.test.filters.MediumTest
import com.vankorno.vankornocompose.fileOps.LibFileOps
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@MediumTest
class LibFileOpsTest {
    private val dir = "test_dir"
    
    @Before
    fun setup() {
        LibFileOps.deleteAllFiles(dir)
    }
    
    @Test
    fun fileCreationExistenceDeletion() {
        val path1 = LibFileOps.createDummyFile(dir, "a.txt")
        val path2 = LibFileOps.createDummyFile(dir, "b.txt")
        
        val exists1 = LibFileOps.fileExists(path1)
        val exists2 = LibFileOps.fileExists(path2)
        
        assertTrue(exists1)
        assertTrue(exists2)
        
        val deleted1 = LibFileOps.deleteFile(dir, "a.txt")
        val deleted2 = LibFileOps.deleteFile(dir, "b.txt")
        
        assertTrue(deleted1)
        assertTrue(deleted2)
        
        assertFalse(LibFileOps.fileExists(path1))
        assertFalse(LibFileOps.fileExists(path2))
    }
    
    @Test
    fun fileCreationExistenceDeletionList() {
        LibFileOps.createDummyFile(dir, "a.txt")
        LibFileOps.createDummyFile(dir, "b.txt")
        
        val filesBefore = LibFileOps.getAllFileNames(dir)
        assertTrue(filesBefore.contains("a.txt"))
        assertTrue(filesBefore.contains("b.txt"))
        
        LibFileOps.deleteAllFiles(dir)
        
        val filesAfter = LibFileOps.getAllFileNames(dir)
        assertTrue(filesAfter.isEmpty())
    }
    
    
    
    @Test
    fun writeText_createsMissingDir() {
        val nestedDir = "test_dir_nested/a/b/c"
        
        val path = LibFileOps.writeTextToFile(nestedDir, "file.txt", "hello")
        
        assertTrue(LibFileOps.fileExists(path))
        
        val text = LibFileOps.readTextFromFile(nestedDir, "file.txt")
        assertEquals("hello", text)
    }
    
    @Test
    fun saveFileFromUri_createsMissingDir() {
        val nestedDir = "test_dir_nested_uri/x/y/z"
        
        val srcPath = LibFileOps.createDummyFile(dir, "src.txt")
        val uri = LibFileOps.getUriForFile(srcPath)
        
        val newPath = LibFileOps.saveFileFromUri(nestedDir, uri)
        
        assertNotNull(newPath)
        assertTrue(LibFileOps.fileExists(newPath!!))
    }
    
    @Test
    fun renameFile_createsDirIfMissing() { // TODO Check
        val nestedDir = "test_dir_nested_rename/q/w/e"
        
        LibFileOps.createDummyFile(nestedDir, "old.txt")
        
        val newPath = LibFileOps.renameFile(nestedDir, "old.txt", "new")
        
        assertNotNull(newPath)
        assertTrue(LibFileOps.fileExists(newPath!!))
    }
    
    @Test
    fun createDummyFile_createsDeepNestedDir() {
        val nestedDir = "test_dir_nested_dummy/1/2/3/4"
        
        val path = LibFileOps.createDummyFile(nestedDir, "dummy.txt")
        
        assertTrue(LibFileOps.fileExists(path))
    }
    
    @Test
    fun deleteAllFiles_onNonExistingDir_returnsZero() {
        val nestedDir = "test_dir_non_existing/a/b/c"
        
        val deletedCount = LibFileOps.deleteAllFiles(nestedDir)
        
        assertEquals(0, deletedCount)
    }
}