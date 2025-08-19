/*
 * Copyright (c) Meta Platforms, Inc. and affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

package com.rnnui.slider

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.WritableMap
import com.facebook.react.uimanager.events.Event

internal class SlidingStartEvent(surfaceId: Int, viewId: Int, private val value: Float) :
  Event<SlidingStartEvent>(surfaceId, viewId) {

  override fun getEventName(): String = EVENT_NAME

  override fun getEventData(): WritableMap =
    Arguments.createMap().apply {
      putInt("target", viewTag)
      putDouble("value", value.toDouble())
    }

  companion object {
    const val EVENT_NAME = "onSlidingStart"
  }
}
