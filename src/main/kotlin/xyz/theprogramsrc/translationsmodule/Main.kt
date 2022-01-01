package xyz.theprogramsrc.translationsmodule

import xyz.theprogramsrc.simplecoreapi.global.module.Module

class Main: Module() {

    override fun onEnable() {
        TranslationManager()
    }
}