import codegenNativeComponent from 'react-native/Libraries/Utilities/codegenNativeComponent';
import type { ViewProps, ColorValue } from 'react-native';
import type {
  BubblingEventHandler,
  DirectEventHandler,
  Float,
  Int32,
} from 'react-native/Libraries/Types/CodegenTypes';

interface NativeProps extends ViewProps {
  size?: string | undefined | null;

  /** Track props */
  minValue: Float | undefined | null;
  maxValue: Float | undefined | null;
  stepSize: Float | undefined | null;
  value: Float | undefined | null;
  height: Int32 | undefined | null;

  /** Don't use on Android */
  trackColor: ColorValue | undefined | null;

  activeTrackColor: ColorValue | undefined | null;
  inactiveTrackColor: ColorValue | undefined | null;
  trackCornerSize: Int32 | undefined | null;
  trackInsideCornerSize: Int32 | undefined | null;
  trackStopIndicatorSize: Int32 | undefined | null;
  trackIconActiveStart: string | undefined | null;
  trackIconActiveEnd: string | undefined | null;
  trackIconInactiveStart: string | undefined | null;
  trackIconInactiveEnd: string | undefined | null;
  trackIconActiveColor: ColorValue | undefined | null;
  trackIconInactiveColor: ColorValue | undefined | null;
  trackIconSize: Int32 | undefined | null;

  /** Thumb props */
  thumbColor: ColorValue | undefined | null;
  thumbWidth: Int32 | undefined | null;
  thumbHeight: Int32 | undefined | null;
  thumbElevation: Float | undefined | null;
  thumbBorderColor: ColorValue | undefined | null;
  thumbBorderWidth: Int32 | undefined | null;
  thumbTrackGapSize: Int32 | undefined | null;

  /** Value props */
  labelBehavior: string | undefined | null;

  /** Tick props */
  activeTickColor: ColorValue | undefined | null;
  inactiveTickColor: ColorValue | undefined | null;
  activeTickRadius: Int32 | undefined | null;
  inactiveTickRadius: Int32 | undefined | null;
  tickVisibilityMode: string | undefined | null;

  disabled: boolean | undefined | null;

  onSlidingStart?: DirectEventHandler<Event>;
  onSlidingStop?: DirectEventHandler<Event>;
  onChange?: BubblingEventHandler<Event>;

  centered?: boolean | undefined | null;
}

export default codegenNativeComponent<NativeProps>('NUISlider', {
  interfaceOnly: true,
});

type Event = {
  value: Float;
  target: Int32;
};
