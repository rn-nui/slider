#pragma once

#include <react/renderer/core/LayoutConstraints.h>
#include <react/renderer/core/LayoutContext.h>
#include <react/renderer/core/ConcreteComponentDescriptor.h>
#include <react/utils/ContextContainer.h>

namespace facebook::react {

class NUISliderMeasurementsManager {
 public:
  explicit NUISliderMeasurementsManager(
      const std::shared_ptr<const ContextContainer>& contextContainer)
      : contextContainer_(contextContainer) {}

  Size measure(SurfaceId surfaceId, LayoutConstraints layoutConstraints) const;

 private:
  const std::shared_ptr<const ContextContainer> contextContainer_;
};

} // namespace facebook::react
