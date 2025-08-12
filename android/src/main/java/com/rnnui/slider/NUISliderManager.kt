package com.rnnui.slider

import android.content.Context
import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.PixelUtil
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.uimanager.annotations.ReactProp
import com.facebook.react.viewmanagers.NUISliderManagerInterface
import com.facebook.react.viewmanagers.NUISliderManagerDelegate
import com.facebook.yoga.YogaMeasureMode
import com.facebook.yoga.YogaMeasureOutput
import com.google.android.material.slider.Slider

@ReactModule(name = NUISliderManager.NAME)
class NUISliderManager : SimpleViewManager<Slider>(),
  NUISliderManagerInterface<Slider> {
  private val mDelegate: ViewManagerDelegate<Slider> = NUISliderManagerDelegate(this)

  override fun getDelegate(): ViewManagerDelegate<Slider>? {
    return mDelegate
  }

  override fun getName(): String {
    return NAME
  }

  public override fun createViewInstance(context: ThemedReactContext): Slider {
    return Slider(context)
  }

  @ReactProp(name = "color")
  override fun setColor(view: Slider?, color: String?) {
    view?.setBackgroundColor(Color.parseColor(color))
    view?.trackHeight = 180
  }

  override fun measure(
    context: Context,
    localData: ReadableMap?,
    props: ReadableMap?,
    state: ReadableMap?,
    width: Float,
    widthMode: YogaMeasureMode,
    height: Float,
    heightMode: YogaMeasureMode,
    attachmentsPositions: FloatArray?
  ): Long {
    val view = Slider(context).apply {
      trackHeight = 180
    }
    val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    view.measure(measureSpec, measureSpec)
    return YogaMeasureOutput.make(
      PixelUtil.toDIPFromPixel(view.measuredWidth.toFloat()),
      PixelUtil.toDIPFromPixel(view.measuredHeight.toFloat())
    )
  }

  companion object {
    const val NAME = "NUISlider"
  }
}
