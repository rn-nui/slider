package com.rnnui.slider

import android.graphics.Color
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.NUISliderManagerInterface
import com.facebook.react.viewmanagers.NUISliderManagerDelegate

@ReactModule(name = NUISliderManager.NAME)
class NUISliderManager : SimpleViewManager<SliderView>(),
  NUISliderManagerInterface<SliderView> {
  private val mDelegate: ViewManagerDelegate<SliderView> = NUISliderManagerDelegate(this)

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
    const val NAME = "NUISlider"
  }
}
