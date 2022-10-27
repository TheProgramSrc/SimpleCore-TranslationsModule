package xyz.theprogramsrc.translationsmodule

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

internal class TranslationColorTest {

    companion object {
        @BeforeAll
        @JvmStatic
        fun setUp() {
            TranslationManager()
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            File("translations").deleteRecursively()
            File("plugins").deleteRecursively()
        }
    }

    @BeforeEach
    fun beforeEach() {
        File("translations").deleteRecursively()
    }

    @Test
    fun testMainColorTranslation(){
        val translation = Translation(
            id = "identifier",
            defaultValue = "defaultValue",
            mainColor = "&7"
        )
        assertEquals("&7defaultValue", translation.translate(colorize=false))
    }

    @Test
    fun testMainColorWithColorizedText(){
        val translation = Translation(
            id = "identifier2",
            defaultValue = "defaultValue is **colorized**.",
            colors = arrayOf("&c"),
            mainColor = "&7"
        )
        assertEquals("&7defaultValue is &ccolorized&7.", translation.translate(colorize=false))
    }

    @Test
    fun testMainColorWithMultipleColorizedTexts(){
        val translation = Translation(
            id = "identifier3",
            defaultValue = "defaultValue is **colorized** and **colorized**.",
            colors = arrayOf("&c", "&a"),
            mainColor = "&7"
        )
        assertEquals("&7defaultValue is &ccolorized&7 and &acolorized&7.", translation.translate(colorize=false))

    }
}