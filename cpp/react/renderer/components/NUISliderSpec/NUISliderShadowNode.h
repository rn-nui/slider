#pragma once

#include "NUISliderMeasurementsManager.h"

#include <react/renderer/components/NUISliderSpec/EventEmitters.h>
#include <react/renderer/components/NUISliderSpec/Props.h>
#include <react/renderer/components/view/ConcreteViewShadowNode.h>

namespace facebook::react {

extern const char NUISliderComponentName[];

class NUISliderShadowNode final : public ConcreteViewShadowNode<
    NUISliderComponentName,
    NUISliderProps,
    NUISliderEventEmitter> {
 public:
  using ConcreteViewShadowNode::ConcreteViewShadowNode;

  static ShadowNodeTraits BaseTraits() {
    auto traits = ConcreteViewShadowNode::BaseTraits();
    traits.set(ShadowNodeTraits::Trait::LeafYogaNode);
    traits.set(ShadowNodeTraits::Trait::MeasurableYogaNode);
    return traits;
  }

  void setNUISliderMeasurementsManager(
      const std::shared_ptr<NUISliderMeasurementsManager>& manager);

  Size measureContent(
      const LayoutContext& layoutContext,
      const LayoutConstraints& layoutConstraints) const override;

 private:
  std::shared_ptr<NUISliderMeasurementsManager> measurementsManager_;
};

} // namespace facebook::react
