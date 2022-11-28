package fr.free.nrw.commons.upload

import fr.free.nrw.commons.utils.ImageUtils
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Test

class ReadFBMDTest {

    @Test
    fun shouldFindFBMDInFile(){
        val reader = ReadFBMD()

        assertEquals(
            ImageUtils.FILE_FBMD,
            reader.processMetadata("src/test/resources/ImageTest/fromFB.jpg").blockingGet()
        )
    }

    @Test
    fun shouldNotFindFBMDInFile(){
        val reader = ReadFBMD()

        assertEquals(
            ImageUtils.IMAGE_OK,
            reader.processMetadata("src/test/resources/ImageTest/ok1.jpg").blockingGet()
        )
    }
}
