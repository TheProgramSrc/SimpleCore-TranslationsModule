package xyz.theprogramsrc.translationsmodule

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class TranslationManagerTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun setUp() {
            TranslationManager()
        }
    }

    @BeforeEach
    fun beforeEach() {
        File("translations").deleteRecursively()
    }

    @Test
    fun testAutoRegister() {
        val translation = Translation(
            id = "identifier",
            defaultValue = "defaultValue",
        )
        val folder = File("translations/${translation.group}")
        val file = File(folder, "${translation.language}.lang")
        assertTrue(file.exists())
    }

    @Test
    fun testManualRegistration() {
        val translation = Translation(
            id = "identifier2",
            defaultValue = "defaultValue",
            autoRegister = false,
        )
        val folder = File("translations/${translation.group}")
        val file = File(folder, "${translation.language}.lang")
        assertFalse(file.exists())
        TranslationManager.instance.registerTranslation(translation = translation)
        assertTrue(file.exists())
    }
}