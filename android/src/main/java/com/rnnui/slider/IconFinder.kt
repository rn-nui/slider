package com.rnnui.slider

import android.content.Context
import android.graphics.drawable.Drawable

class IconFinder(val context: Context) {
  fun getId(iconName: String): Int {
    val resourceId = context.resources.getIdentifier(iconName, "drawable", context.packageName)
    return resourceId
  }

  fun getDrawable(iconId: Int): Drawable? {
    return context.getDrawable(iconId)
  }
}
