package xyz.theprogramsrc.translationsmodule

import xyz.theprogramsrc.simplecoreapi.global.models.module.Module
import xyz.theprogramsrc.simplecoreapi.global.models.module.ModuleDescription

class Main : Module {
    override val description: ModuleDescription =
            ModuleDescription(
                    name = "TranslationsModule",
                    version = "@version@",
                    authors = listOf("Im-Fran"),
            )

    override fun onEnable() {
        TranslationManager()
    }

    override fun onDisable() {

    }


}