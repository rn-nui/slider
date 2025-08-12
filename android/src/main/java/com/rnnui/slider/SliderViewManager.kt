package com.rnnui.slider

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.SliderViewManagerInterface
import com.facebook.react.viewmanagers.SliderViewManagerDelegate

@ReactModule(name = SliderViewManager.NAME)
class SliderViewManager : SimpleViewManager<SliderView>(),
  SliderViewManagerInterface<SliderView> {
  private val mDelegate: ViewManagerDelegate<SliderView>

  init {
    mDelegate = SliderViewManagerDelegate(this)
  }

  override fun getDelegate(): ViewManagerDelegate<SliderView>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): SliderView {
    return SliderView(context)
  }

  @ReactProp(name = "color")
  override fun setColor(view: SliderView?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
  }

  companion object {
    const val NAME = "SliderView"
  }
}
