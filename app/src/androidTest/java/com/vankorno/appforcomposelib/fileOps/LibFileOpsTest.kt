package com.vankorno.appforcomposelib.fileOps

import androidx.test.filters.MediumTest
import com.vankorno.vankornocompose.fileOps.LibFileOps.createDummyFile
import com.vankorno.vankornocompose.fileOps.LibFileOps.deleteAllFiles
import com.vankorno.vankornocompose.fileOps.LibFileOps.deleteFile
import com.vankorno.vankornocompose.fileOps.LibFileOps.fileExists
import com.vankorno.vankornocompose.fileOps.LibFileOps.getAllFileNames
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@MediumTest
class LibFileOpsTest {
    private val dir = "test_dir"
    
    @Before
    fun setup() {
        deleteAllFiles(dir)
    }
    
    @Test
    fun fileCreationExistenceDeletion() {
        val path1 = createDummyFile(dir, "a.txt")
        val path2 = createDummyFile(dir, "b.txt")
        
        val exists1 = fileExists(path1)
        val exists2 = fileExists(path2)
        
        assertTrue(exists1)
        assertTrue(exists2)
        
        val deleted1 = deleteFile(dir, "a.txt")
        val deleted2 = deleteFile(dir, "b.txt")
        
        assertTrue(deleted1)
        assertTrue(deleted2)
        
        assertFalse(fileExists(path1))
        assertFalse(fileExists(path2))
    }
    
    @Test
    fun fileCreationExistenceDeletionList() {
        createDummyFile(dir, "a.txt")
        createDummyFile(dir, "b.txt")
        
        val filesBefore = getAllFileNames(dir)
        assertTrue(filesBefore.contains("a.txt"))
        assertTrue(filesBefore.contains("b.txt"))
        
        deleteAllFiles(dir)
        
        val filesAfter = getAllFileNames(dir)
        assertTrue(filesAfter.isEmpty())
    }
    
    
    
    
}