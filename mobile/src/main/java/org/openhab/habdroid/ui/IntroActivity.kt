/*
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.openhab.habdroid.ui

import android.os.Bundle
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.github.paolorotolo.appintro.AppIntro
import com.github.paolorotolo.appintro.AppIntroFragment
import org.openhab.habdroid.R
import org.openhab.habdroid.util.Constants
import org.openhab.habdroid.util.getPrefs

class IntroActivity : AppIntro() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Add slides
        addSlide(R.string.intro_welcome,
            R.string.intro_whatis,
            R.drawable.ic_openhab_appicon_340dp)
        addSlide(R.string.intro_themes,
            R.string.intro_themes_description,
            R.drawable.ic_palette_outline_orange_340dp)
        addSlide(R.string.mainmenu_openhab_voice_recognition,
            R.string.intro_voice_description,
            R.drawable.ic_microphone_outline_orange_340dp)
        addSlide(R.string.intro_nfc,
            R.string.intro_nfc_description,
            R.drawable.ic_nfc_orange_340dp)

        // Change bar color
        setBarColor(ContextCompat.getColor(this, R.color.openhab_orange))
        setSeparatorColor(ContextCompat.getColor(this, R.color.openhab_orange_dark))
    }

    /**
     * Must be overridden to ensure that the intro will be closed when clicking on "SKIP"
     * @param currentFragment
     */
    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        getPrefs().edit().putBoolean(Constants.PREFERENCE_FIRST_START, false).apply()
        finish()
    }

    /**
     * Must be overridden to ensure that the intro will be closed when clicking on "DONE"
     * @param currentFragment
     */
    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        getPrefs().edit().putBoolean(Constants.PREFERENCE_FIRST_START, false).apply()
        finish()
    }

    /**
     * Add slide with fixed fonts and colors
     * @param title
     * @param description
     * @param imageDrawable
     */
    private fun addSlide(@StringRes title: Int, @StringRes description: Int, @DrawableRes imageDrawable: Int) {
        @ColorInt val greyColor = ContextCompat.getColor(this, R.color.grey_300)
        @ColorInt val blackColor = ContextCompat.getColor(this, R.color.black)

        addSlide(AppIntroFragment.newInstance(getString(title),
            null, // Title font: null => default
            getString(description), null, // Description font: null => default
            imageDrawable,
            greyColor, // Background color
            blackColor, // Title color
            // Description color
            ContextCompat.getColor(this, R.color.black))) // Description color
    }
}
