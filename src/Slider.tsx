import { useCallback } from 'react';
import SliderViewNativeComponent from './SliderViewNativeComponent';
import {
  type ColorValue,
  type ViewProps,
  type NativeSyntheticEvent,
} from 'react-native';

export default function Slider({
  size,
  minValue,
  maxValue,
  stepSize,
  value,
  height,
  trackColor,
  activeTrackColor,
  inactiveTrackColor,
  trackCornerSize,
  trackInsideCornerSize,
  trackIconActiveStart,
  trackIconActiveEnd,
  trackIconInactiveStart,
  trackIconInactiveEnd,
  trackIconActiveColor,
  trackIconInactiveColor,
  trackIconSize,
  thumbColor,
  thumbWidth,
  thumbHeight,
  thumbElevation,
  thumbBorderColor,
  thumbBorderWidth,
  thumbTrackGapSize,
  labelBehavior,
  activeTickColor,
  inactiveTickColor,
  activeTickRadius,
  inactiveTickRadius,
  tickVisibilityMode,
  trackStopIndicatorSize,
  disabled,
  onChange,
  onValueChange,
  onSlidingStart,
  onSlidingStop,
  centered,
  ...props
}: SliderProps) {
  const handleChange = useCallback(
    (e: SliderValueChangeEvent) => {
      onChange?.(e);
      onValueChange?.(e.nativeEvent.value);
    },
    [onChange, onValueChange]
  );

  return (
    <SliderViewNativeComponent
      {...props}
      size={size}
      minValue={minValue}
      maxValue={maxValue}
      stepSize={stepSize}
      value={value}
      height={height}
      trackColor={trackColor}
      activeTrackColor={activeTrackColor}
      inactiveTrackColor={inactiveTrackColor}
      trackCornerSize={trackCornerSize}
      trackInsideCornerSize={trackInsideCornerSize}
      trackIconActiveStart={trackIconActiveStart}
      trackIconActiveEnd={trackIconActiveEnd}
      trackIconInactiveStart={trackIconInactiveStart}
      trackIconInactiveEnd={trackIconInactiveEnd}
      trackIconActiveColor={trackIconActiveColor}
      trackIconInactiveColor={trackIconInactiveColor}
      trackStopIndicatorSize={trackStopIndicatorSize}
      trackIconSize={trackIconSize}
      thumbColor={thumbColor}
      thumbWidth={thumbWidth}
      thumbHeight={thumbHeight}
      thumbElevation={thumbElevation}
      thumbBorderColor={thumbBorderColor}
      thumbBorderWidth={thumbBorderWidth}
      thumbTrackGapSize={thumbTrackGapSize}
      labelBehavior={labelBehavior}
      activeTickColor={activeTickColor}
      inactiveTickColor={inactiveTickColor}
      activeTickRadius={activeTickRadius}
      inactiveTickRadius={inactiveTickRadius}
      tickVisibilityMode={tickVisibilityMode}
      disabled={disabled}
      onChange={handleChange}
      centered={centered}
      onSlidingStart={(e) => onSlidingStart?.(e.nativeEvent.value)}
      onSlidingStop={(e) => onSlidingStop?.(e.nativeEvent.value)}
    />
  );
}

export type SliderProps = ViewProps & {
  size?: SliderSize | undefined | null;

  /** Track props */
  minValue?: number | undefined;
  maxValue?: number | undefined;
  stepSize?: number | undefined | null;
  value?: number | undefined | null;
  height?: number | undefined | null;
  trackColor?: ColorValue | undefined | null;
  activeTrackColor?: ColorValue | undefined | null;
  inactiveTrackColor?: ColorValue | undefined | null;
  trackCornerSize?: number | undefined | null;
  trackInsideCornerSize?: number | undefined | null;
  trackIconActiveStart?: string | undefined | null;
  trackIconActiveEnd?: string | undefined | null;
  trackIconInactiveStart?: string | undefined | null;
  trackIconInactiveEnd?: string | undefined | null;
  trackIconActiveColor?: ColorValue | undefined | null;
  trackIconInactiveColor?: ColorValue | undefined | null;
  trackIconSize?: number | undefined | null;
  trackStopIndicatorSize?: number | undefined | null;

  /** Thumb props */
  thumbColor?: ColorValue | undefined | null;
  thumbWidth?: number | undefined | null;
  thumbHeight?: number | undefined | null;
  thumbElevation?: number | undefined | null;
  thumbBorderColor?: ColorValue | undefined | null;
  thumbBorderWidth?: number | undefined | null;
  thumbTrackGapSize?: number | undefined | null;

  /** Value props */
  labelBehavior?: SliderLabelBehavior | undefined | null;

  /** Tick props */
  tickColor?: ColorValue | undefined | null;
  activeTickColor?: ColorValue | undefined | null;
  inactiveTickColor?: ColorValue | undefined | null;
  activeTickRadius?: number | undefined | null;
  inactiveTickRadius?: number | undefined | null;
  tickVisibilityMode?: SliderTickVisibility | undefined | null;

  disabled?: boolean | undefined | null;

  centered?: boolean | undefined | null;

  onChange?: ((event: SliderValueChangeEvent) => void) | undefined | null;
  onValueChange?: ((value: number) => void) | undefined | null;
  onSlidingStart?: ((value: number) => void) | undefined | null;
  onSlidingStop?: ((value: number) => void) | undefined | null;
};

export type SliderSize = 'xs' | 'sm' | 'md' | 'lg' | 'xl';
export type SliderLabelBehavior =
  | 'always-visible'
  | 'floating'
  | 'never-visible';

export type SliderTickVisibility = 'auto-limit' | 'auto-hide' | 'hidden';

type SliderValueChangeEventData = {
  target: number;
  value: number;
};

export type SliderValueChangeEvent =
  NativeSyntheticEvent<SliderValueChangeEventData>;
