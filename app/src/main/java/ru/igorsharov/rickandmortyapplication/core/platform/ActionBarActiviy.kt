package ru.igorsharov.rickandmortyapplication.core.platform

import androidx.annotation.StringRes

interface ActionBarActivity {
    fun setActionBarTitle(@StringRes stringResId: Int)
}