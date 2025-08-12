#pragma once

#include "NUISliderMeasurementsManager.h"
#include "NUISliderShadowNode.h"

#include <react/renderer/core/ConcreteComponentDescriptor.h>

namespace facebook::react {

/*
 * ComponentDescriptor for <NUISlider /> component.
 * Connects the ShadowNode with the rendering system and measurement manager.
 */
class NUISliderComponentDescriptor final
    : public ConcreteComponentDescriptor<NUISliderShadowNode> {
 public:
  NUISliderComponentDescriptor(
      const ComponentDescriptorParameters& parameters)
      : ConcreteComponentDescriptor(parameters),
        measurementsManager_(
            std::make_shared<NUISliderMeasurementsManager>(contextContainer_)) {}

  void adopt(ShadowNode& shadowNode) const override {
    ConcreteComponentDescriptor::adopt(shadowNode);

    auto& nuiSliderShadowNode =
        static_cast<NUISliderShadowNode&>(shadowNode);

    nuiSliderShadowNode.setNUISliderMeasurementsManager(measurementsManager_);
  }

 private:
  const std::shared_ptr<NUISliderMeasurementsManager> measurementsManager_;
};

} // namespace facebook::react
