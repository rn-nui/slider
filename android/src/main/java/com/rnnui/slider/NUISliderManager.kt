package com.rnnui.slider

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.TypedValue
import android.view.View
import com.facebook.react.bridge.ReactContext
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.module.annotations.ReactModule
import com.facebook.react.uimanager.PixelUtil
import com.facebook.react.uimanager.PixelUtil.dpToPx
import com.facebook.react.uimanager.PixelUtil.pxToDp
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.UIManagerHelper
import com.facebook.react.uimanager.ViewManagerDelegate
import com.facebook.react.viewmanagers.NUISliderManagerDelegate
import com.facebook.react.viewmanagers.NUISliderManagerInterface
import com.facebook.yoga.YogaMeasureMode
import com.facebook.yoga.YogaMeasureOutput
import com.google.android.material.slider.LabelFormatter
import com.google.android.material.slider.Slider
import com.google.android.material.slider.TickVisibilityMode


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
      trackHeight = getHeightFromProps(props)
      thumbHeight = getHeightFromProps(props)
      labelBehavior = when (props?.getString("labelBehavior")) {
        "always-visible" -> LabelFormatter.LABEL_VISIBLE
        "floating" -> LabelFormatter.LABEL_FLOATING
        "never-visible" -> LabelFormatter.LABEL_GONE
        else -> LabelFormatter.LABEL_VISIBLE
      }
    }
    val measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
    view.measure(measureSpec, measureSpec)
    return YogaMeasureOutput.make(
      PixelUtil.toDIPFromPixel(view.measuredWidth.toFloat()),
      PixelUtil.toDIPFromPixel(view.measuredHeight.toFloat())
    )
  }

  override fun setSize(view: Slider?, value: String?) {
    if (value == null || view == null) return

    val size = getSize(value)

    view.apply {
      trackHeight = size.trackHeight
      thumbHeight = size.thumbHeight
      thumbWidth = 4.dpToPx().toInt()
      trackCornerSize = size.trackCornerSize
      trackIconSize = size.trackIconSize
    }
  }

  override fun setMinValue(view: Slider?, value: Float) {
    if (view == null) return
    view.valueFrom = value
  }

  override fun setMaxValue(view: Slider?, value: Float) {
    if (view == null) return
    view.valueTo = value
  }

  override fun setStepSize(view: Slider?, value: Float) {
    if (view == null) return
    view.stepSize = value
  }

  override fun setValue(
    view: Slider?,
    value: Float
  ) {
    if (view == null) return

    view.clearOnSliderTouchListeners()
    view.clearOnChangeListeners()

    view.value = value

    view.addOnChangeListener(VALUE_CHANGE_LISTENER)
    view.addOnSliderTouchListener(SLIDING_TOUCH_LISTENER)
  }

  override fun setHeight(view: Slider?, value: Int) {
    if (view == null) return
    if (value == 0) return

    view.trackHeight = value.dpToPx().toInt()

  }

  override fun setTrackColor(view: Slider?, value: Int?) {
    TODO("Not yet implemented")
  }

  override fun setActiveTrackColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return

    view.trackActiveTintList = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        androidx.appcompat.R.attr.colorPrimary,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.BLUE) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setInactiveTrackColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return

    view.trackInactiveTintList = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        com.google.android.material.R.attr.colorSurfaceContainerHighest,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.GRAY) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setTrackCornerSize(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.trackCornerSize = if (value == null) {
      (view.trackHeight / 2.dpToPx().toInt())
    } else {
      value.dpToPx().toInt()
    }
  }

  override fun setTrackInsideCornerSize(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.trackInsideCornerSize = if (value == null) {
      2.dpToPx().toInt()
    } else {
      value.dpToPx().toInt()
    }
  }

  override fun setTrackStopIndicatorSize(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.trackStopIndicatorSize = if (value == null) {
      4.dpToPx().toInt()
    } else {
      value.dpToPx().toInt()
    }
  }

  override fun setTrackIconActiveStart(
    view: Slider?,
    value: String?
  ) {
    if (view == null) return
    if (value == null) {
      view.trackIconActiveStart = null
      return
    }

    val iconFinder = IconFinder(view.context)
    val iconId = iconFinder.getId(value)

    if (iconId == null) {
      view.trackIconActiveStart = null
    } else {
      view.trackIconActiveStart = iconFinder.getDrawable(iconId)
    }
  }

  override fun setTrackIconActiveEnd(
    view: Slider?,
    value: String?
  ) {
    if (view == null) return
    if (value == null) {
      view.trackIconActiveEnd = null
      return
    }

    val iconFinder = IconFinder(view.context)
    val iconId = iconFinder.getId(value)

    if (iconId == null) {
      view.trackIconActiveEnd = null
    } else {
      view.trackIconActiveEnd = iconFinder.getDrawable(iconId)
    }
  }

  override fun setTrackIconInactiveStart(
    view: Slider?,
    value: String?
  ) {
    if (view == null) return
    if (value == null) {
      view.trackIconInactiveStart = null
      return
    }

    val iconFinder = IconFinder(view.context)
    val iconId = iconFinder.getId(value)

    if (iconId == null) {
      view.trackIconInactiveStart = null
    } else {
      view.trackIconInactiveStart = iconFinder.getDrawable(iconId)
    }
  }

  override fun setTrackIconInactiveEnd(
    view: Slider?,
    value: String?
  ) {
    if (view == null) return
    if (value == null) {
      view.trackIconInactiveEnd = null
      return
    }

    val iconFinder = IconFinder(view.context)
    val iconId = iconFinder.getId(value)

    if (iconId == null) {
      view.trackIconInactiveEnd = null
    } else {
      view.trackIconInactiveEnd = iconFinder.getDrawable(iconId)
    }
  }

  override fun setTrackIconActiveColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return

    view.trackIconActiveColor = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        com.google.android.material.R.attr.colorOnPrimary,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.GRAY) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setTrackIconInactiveColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return

    view.trackIconInactiveColor = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        com.google.android.material.R.attr.colorOnSurfaceVariant,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.GRAY) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setTrackIconSize(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.trackIconSize = value.dpToPx().toInt()
  }

  override fun setThumbColor(view: Slider?, value: Int?) {
    if (view == null) return

    view.thumbTintList = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        androidx.appcompat.R.attr.colorPrimary,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.BLUE) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setThumbWidth(view: Slider?, value: Int) {
    if (view == null) return
    view.thumbWidth = if (value == null) 4.dpToPx().toInt() else value.dpToPx().toInt()
  }

  override fun setThumbHeight(view: Slider?, value: Int) {
    if (view == null) return
    view.thumbHeight = if (value == null) 44.dpToPx().toInt() else value.dpToPx().toInt()
  }

  override fun setThumbElevation(
    view: Slider?,
    value: Float
  ) {
    if (view == null) return
    view.thumbElevation = if (value == null) 2F else value.dpToPx()
  }

  override fun setThumbBorderColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return
    view.thumbStrokeColor =
      if (value == null) ColorStateList.valueOf(Color.TRANSPARENT) else ColorStateList.valueOf(
        value
      )
  }

  override fun setThumbBorderWidth(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.thumbStrokeWidth = if (value == null) 0F else value.dpToPx()

  }

  override fun setThumbTrackGapSize(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.thumbTrackGapSize = if (value == null) 6.dpToPx().toInt() else value.dpToPx().toInt()
  }

  override fun setLabelBehavior(
    view: Slider?,
    value: String?
  ) {
    if (view == null) return
    view.labelBehavior = when (value) {
      "always-visible" -> LabelFormatter.LABEL_VISIBLE
      "floating" -> LabelFormatter.LABEL_FLOATING
      "never-visible" -> LabelFormatter.LABEL_GONE
      else -> LabelFormatter.LABEL_VISIBLE
    }
  }

  override fun setActiveTickColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return

    view.tickActiveTintList = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        com.google.android.material.R.attr.colorSurfaceContainerHighest,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.GRAY) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setInactiveTickColor(
    view: Slider?,
    value: Int?
  ) {
    if (view == null) return

    view.tickInactiveTintList = if (value == null) {
      val typedValue = TypedValue()
      val theme = view.context.theme
      val got = theme.resolveAttribute(
        androidx.appcompat.R.attr.colorPrimary,
        typedValue,
        true
      )

      if (got) ColorStateList.valueOf(typedValue.data) else ColorStateList.valueOf(Color.BLUE) // fallback fallback
    } else {
      ColorStateList.valueOf(value)
    }
  }

  override fun setActiveTickRadius(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.tickActiveRadius =
      if (value == null) (view.trackStopIndicatorSize / 2.dpToPx().toInt()) else value.dpToPx()
        .toInt()
  }

  override fun setInactiveTickRadius(
    view: Slider?,
    value: Int
  ) {
    if (view == null) return
    view.tickInactiveRadius =
      if (value == null) (view.trackStopIndicatorSize / 2.dpToPx().toInt()) else value.dpToPx()
        .toInt()
  }

  override fun setTickVisibilityMode(
    view: Slider?,
    value: String?
  ) {
    if (view == null) return
    view.tickVisibilityMode = when (value) {
      "auto-hide" -> TickVisibilityMode.TICK_VISIBILITY_AUTO_HIDE
      "auto-limit" -> TickVisibilityMode.TICK_VISIBILITY_AUTO_LIMIT
      "hidden" -> TickVisibilityMode.TICK_VISIBILITY_HIDDEN
      else -> TickVisibilityMode.TICK_VISIBILITY_AUTO_HIDE
    }
  }

  override fun setDisabled(
    view: Slider?,
    value: Boolean
  ) {
    if (view == null) return
    view.isEnabled = !value
  }

  override fun setCentered(
    view: Slider?,
    value: Boolean
  ) {
    if (view == null) return
    view.isCentered = value
  }

  private fun getSize(sizeString: String?): TrackSize {
    return when (sizeString) {
      "xs" -> XS
      "sm" -> S
      "md" -> M
      "lg" -> L
      "xl" -> XL
      else -> XS
    }
  }

  private fun getHeightFromProps(props: ReadableMap?): Int {
    if (props == null) return XS.thumbHeight


    val propHeight = if (props.hasKey("height")) {
      props.getInt("height").coerceAtLeast(0).dpToPx().toInt()
    } else {
      0
    }

    val height = if (propHeight > 0) {
      propHeight
    } else if (props.hasKey("size")) {
      val size = getSize(props.getString("size"))
      size.trackHeight
    } else {
      XS.trackHeight
    }

    val propThumbHeight = if (props.hasKey("thumbHeight")) {
      props.getInt("thumbHeight").coerceAtLeast(0).dpToPx().toInt()
    } else {
      0
    }
    val thumbHeight = if (propThumbHeight > 0) {
      propThumbHeight
    } else if (props.hasKey("size")) {
      val size = getSize(props.getString("size"))
      size.thumbHeight
    } else {
      XS.thumbHeight
    }

    return height.coerceAtLeast(thumbHeight)
  }

  override fun addEventEmitters(reactContext: ThemedReactContext, view: Slider) {
    view.addOnChangeListener(VALUE_CHANGE_LISTENER)
    view.addOnSliderTouchListener(SLIDING_TOUCH_LISTENER)
  }

  override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any> =
    mapOf(
      ValueChangeEvent.EVENT_NAME to
        mapOf(
          "registrationName" to ValueChangeEvent.EVENT_NAME
        )
    )


  override fun getExportedCustomDirectEventTypeConstants(): Map<String, Any> =
    mapOf(
      SlidingStartEvent.EVENT_NAME to
        mapOf(
          "registrationName" to SlidingStartEvent.EVENT_NAME
        ),
      SlidingStopEvent.EVENT_NAME to
        mapOf(
          "registrationName" to SlidingStopEvent.EVENT_NAME
        )
    )


  companion object {
    const val NAME = "NUISlider"
    val XS = TrackSize(
      trackHeight = 16.dpToPx().toInt(),
      thumbHeight = 44.dpToPx().toInt(),
      trackCornerSize = 8.dpToPx().toInt(),
      trackIconSize = 0
    )
    val S = TrackSize(
      trackHeight = 24.dpToPx().toInt(),
      thumbHeight = 44.dpToPx().toInt(),
      trackCornerSize = 8.dpToPx().toInt(),
      trackIconSize = 0
    )
    val M = TrackSize(
      trackHeight = 40.dpToPx().toInt(),
      thumbHeight = 52.dpToPx().toInt(),
      trackCornerSize = 12.dpToPx().toInt(),
      trackIconSize = 24.dpToPx().toInt()
    )
    val L = TrackSize(
      trackHeight = 56.dpToPx().toInt(),
      thumbHeight = 68.dpToPx().toInt(),
      trackCornerSize = 16.dpToPx().toInt(),
      trackIconSize = 24.dpToPx().toInt()
    )
    val XL = TrackSize(
      trackHeight = 96.dpToPx().toInt(),
      thumbHeight = 108.dpToPx().toInt(),
      trackCornerSize = 28.dpToPx().toInt(),
      trackIconSize = 32.dpToPx().toInt()
    )

    private val VALUE_CHANGE_LISTENER =
      Slider.OnChangeListener { sliderView, value, fromUser ->
        if (!fromUser) return@OnChangeListener

        val reactContext = sliderView.context as ReactContext
        val reactTag = sliderView.id
        UIManagerHelper.getEventDispatcherForReactTag(reactContext, reactTag)
          ?.dispatchEvent(
            ValueChangeEvent(UIManagerHelper.getSurfaceId(reactContext), reactTag, value)
          )
      }

    private val SLIDING_TOUCH_LISTENER =
      object : Slider.OnSliderTouchListener {
        override fun onStartTrackingTouch(sliderView: Slider) {
          val reactContext = sliderView.context as ReactContext
          val reactTag = sliderView.id
          UIManagerHelper.getEventDispatcherForReactTag(reactContext, reactTag)
            ?.dispatchEvent(
              SlidingStartEvent(
                UIManagerHelper.getSurfaceId(reactContext),
                reactTag,
                sliderView.value
              )
            )
        }

        override fun onStopTrackingTouch(sliderView: Slider) {
          val reactContext = sliderView.context as ReactContext
          val reactTag = sliderView.id
          UIManagerHelper.getEventDispatcherForReactTag(reactContext, reactTag)
            ?.dispatchEvent(
              SlidingStopEvent(
                UIManagerHelper.getSurfaceId(reactContext),
                reactTag,
                sliderView.value
              )
            )
        }
      }
  }
}

data class TrackSize(
  val trackHeight: Int, val thumbHeight: Int, val trackCornerSize: Int, val trackIconSize: Int
)
