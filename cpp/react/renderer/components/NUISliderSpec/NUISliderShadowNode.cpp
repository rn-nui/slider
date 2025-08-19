#include "NUISliderShadowNode.h"
#include <react/renderer/core/LayoutContext.h>

namespace facebook::react {

extern const char NUISliderComponentName[] = "NUISlider";

void NUISliderShadowNode::setNUISliderMeasurementsManager(
    const std::shared_ptr<NUISliderMeasurementsManager>& manager) {
  ensureUnsealed();
  measurementsManager_ = manager;
}

Size NUISliderShadowNode::measureContent(
    const LayoutContext& /*layoutContext*/,
    const LayoutConstraints& layoutConstraints) const {
  return measurementsManager_->measure(getSurfaceId(), getConcreteProps(), layoutConstraints);
}

} // namespace facebook::react
