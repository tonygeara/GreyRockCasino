package com.example.tony.greycasino;
import android.view.View;

import org.jetbrains.annotations.NotNull;

interface IBackButtonCallback {
//    fun onToolbarBackPressed(view: View)

    void onToolbarBackPressed(@NotNull View view);
}

interface IMoreOptionsCallback {
//    fun onToolbarMoreOptionClicked(view: View)
}

interface IFilterOptionCallback {
//    fun onFilterOptionClicked(view: View)
}

interface IFilterSelectedDoneTextCallback {
//    fun onFilterSaved(view: View)
}