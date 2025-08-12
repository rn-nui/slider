#include "NUISliderMeasurementsManager.h"

#include <fbjni/fbjni.h>
#include <react/jni/ReadableNativeMap.h>
#include <react/renderer/core/conversions.h>

using namespace facebook::jni;

namespace facebook::react {

Size NUISliderMeasurementsManager::measure(
    SurfaceId surfaceId,
    LayoutConstraints layoutConstraints) const {
  const auto& fabricUIManager =
      contextContainer_->at<jni::global_ref<jobject>>("FabricUIManager");

  static const auto cls = jni::findClassStatic("com/facebook/react/fabric/FabricUIManager");
  static const auto measureMethod =
      cls->getMethod<jlong(
          jint,
          jstring,
          ReadableMap::javaobject,
          ReadableMap::javaobject,
          ReadableMap::javaobject,
          jfloat,
          jfloat,
          jfloat,
          jfloat)>("measure");

  auto minSize = layoutConstraints.minimumSize;
  auto maxSize = layoutConstraints.maximumSize;

  local_ref<JString> componentName = make_jstring("NUISlider");

  return yogaMeassureToSize(measureMethod(
      fabricUIManager,
      static_cast<jint>(surfaceId),
      componentName.get(),
      nullptr,
      nullptr,
      nullptr,
      minSize.width,
      maxSize.width,
      minSize.height,
      maxSize.height));
}

} // namespace facebook::react
