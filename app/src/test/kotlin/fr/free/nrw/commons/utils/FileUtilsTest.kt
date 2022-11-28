package fr.free.nrw.commons.utils

import android.content.Context
import fr.free.nrw.commons.upload.FileUtils
import fr.free.nrw.commons.upload.FileUtilsWrapper
import fr.free.nrw.commons.upload.ImageCoordinates
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.io.File
import java.util.Collections
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RunWith(PowerMockRunner::class)
@PrepareForTest(ImageCoordinates::class)
class FileUtilsTest {
    @Test
    fun deleteFile() {
        val file = File.createTempFile("testfile", "")
        file.writeText("Hello, World")

        assertEquals(true, file.exists())
        assertEquals(true, FileUtils.deleteFile(file))
        assertEquals(false, file.exists())
    }

    @Test
    fun testSHA1() {
        val fileUtilsWrapper = FileUtilsWrapper()

        assertEquals(
                "907d14fb3af2b0d4f18c2d46abe8aedce17367bd",
                fileUtilsWrapper.getSHA1("Hello, World".byteInputStream())
        )

        assertEquals(
                "8b971da6347bd126872ea2f4f8d394e70c74073a",
                fileUtilsWrapper.getSHA1("apps-android-commons".byteInputStream())
        )

        assertEquals(
                "e9d30f5a3a82792b9d79c258366bd53207ceaeb3",
                fileUtilsWrapper.getSHA1("domdomegg was here".byteInputStream())
        )

        assertEquals(
                "96e733a3e59261c0621ba99be5bd10bb21abe53e",
                fileUtilsWrapper.getSHA1("iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNk+A8AAQUBAScY42YAAAAASUVORK5CYII=".byteInputStream())
        )
    }

    @Test
    fun shouldReturnEmptyStringWhenThereAreNoGeoLocations(){
        val file = File.createTempFile("foo", ".jpg")

        val fileUtilsWrapper = FileUtilsWrapper()

        assertEquals("", fileUtilsWrapper.getGeolocationOfFile(file.absolutePath))
        file.deleteOnExit()
    }


    @Test
    fun shouldReturnCorrectFileExtension(){
        val file = File.createTempFile("foo", ".jpg")
        val file2 = File.createTempFile("foo", ".png")

        val fileUtilsWrapper = FileUtilsWrapper()

        assertEquals("jpg", fileUtilsWrapper.getFileExt(file.absolutePath))
        assertEquals("png", fileUtilsWrapper.getFileExt(file2.absolutePath))
        file.deleteOnExit()
        file2.deleteOnExit()
    }

    @Test
    fun shouldReturnCorrectChunks(){
        val file = File.createTempFile("foo", ".jpg")

        val fileUtilsWrapper = FileUtilsWrapper()

        val c = Mockito.mock(Context::class.java)

        assertEquals(Collections.emptyList<File>(), fileUtilsWrapper.getFileChunks(c, file, 10))
    }
}
