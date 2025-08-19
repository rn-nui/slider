#include "NUISliderMeasurementsManager.h"

#include <fbjni/fbjni.h>
#include <react/jni/ReadableNativeMap.h>
#include <react/renderer/core/conversions.h>
#include <react/renderer/components/NUISliderSpec/Props.h>

using namespace facebook::jni;

namespace facebook::react {

Size NUISliderMeasurementsManager::measure(
    SurfaceId surfaceId,
    const NUISliderProps& props,
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

  folly::dynamic serializedProps = folly::dynamic::object();
  serializedProps["size"] = props.size;
  serializedProps["height"] = props.height;
  serializedProps["thumbHeight"] = props.thumbHeight;
  serializedProps["labelBehavior"] = props.labelBehavior;

  local_ref<ReadableNativeMap::javaobject> propsRNM =
          ReadableNativeMap::newObjectCxxArgs(serializedProps);
  local_ref<ReadableMap::javaobject> propsRM =
          make_local(reinterpret_cast<ReadableMap::javaobject>(propsRNM.get()));

  auto minSize = layoutConstraints.minimumSize;
  auto maxSize = layoutConstraints.maximumSize;

  local_ref<JString> componentName = make_jstring("NUISlider");

  return yogaMeassureToSize(measureMethod(
      fabricUIManager,
      static_cast<jint>(surfaceId),
      componentName.get(),
      nullptr,
      propsRM.get(),
      nullptr,
      minSize.width,
      maxSize.width,
      minSize.height,
      maxSize.height));
}

} // namespace facebook::react
