package xyz.theprogramsrc.translationsmodule

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import xyz.theprogramsrc.translationsmodule.objects.Translation

internal class TranslationManagerTest {
    private val tm = TranslationManager()
    private val translations = listOf(
        Translation(
            id = "id",
            defaultValue = "My **colorful** translation!",
            language = "en",
            colors = arrayOf("&a"),
        ),
        Translation(
            id = "id2",
            defaultValue = "My **{text}** translation!",
            language = "en",
            colors = arrayOf("&a"),
        ),
        Translation(
            id = "id3",
            defaultValue = "My **{text}** translation!",
            language = "en",
        ),
    )

    @Test
    fun countTest(){
        tm.registerTranslations(translations = translations)
        assertEquals(3, tm.countTranslations())
    }

    @Test
    fun translationId1Test(){
        tm.registerTranslations(translations = translations)
        assertEquals("My &acolorful translation!", tm.translate("id", "en"))
    }

    @Test
    fun translationId2Test() {
        tm.registerTranslations(translations = translations)
        assertEquals("My &acolorful translation!", tm.translate("id2", "en", placeholders = mapOf("text" to "colorful")))
    }

    @Test
    fun translationId3Test() {
        tm.registerTranslations(translations = translations)
        assertEquals("My **colorful** translation!", tm.translate("id3", "en", placeholders = mapOf("text" to "colorful")))
    }
}