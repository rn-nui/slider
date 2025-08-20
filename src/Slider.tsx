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
  /**
   *  Preset sizes controlling track height, thumb height and border radius
   *  Options are 'xs', 'sm', 'md', 'lg', 'xl'
   */
  size?: SliderSize | undefined | null;

  minValue?: number | undefined;
  maxValue?: number | undefined;

  /** Determines the snap points */
  stepSize?: number | undefined | null;

  /** Controlled value */
  value?: number | undefined | null;

  /** Height of the track */
  height?: number | undefined | null;

  trackColor?: ColorValue | undefined | null;

  /** Color of the track to left of the thumb */
  activeTrackColor?: ColorValue | undefined | null;

  /** Color of the track to right of the thumb */
  inactiveTrackColor?: ColorValue | undefined | null;

  /** Border radius of the track */
  trackCornerSize?: number | undefined | null;

  /** Border radius between track and thumb */
  trackInsideCornerSize?: number | undefined | null;

  /** Icon displayed in active section at start of track */
  trackIconActiveStart?: string | undefined | null;

  /** Icon displayed in active section next to thumb */
  trackIconActiveEnd?: string | undefined | null;

  /** Icon display in inactive section next to thumb */
  trackIconInactiveStart?: string | undefined | null;

  /** Icon displayed in inactive section at end of track */
  trackIconInactiveEnd?: string | undefined | null;

  /** Color of icons in active section */
  trackIconActiveColor?: ColorValue | undefined | null;

  /** Color of icons in inactive section */
  trackIconInactiveColor?: ColorValue | undefined | null;

  /** Size of icons */
  trackIconSize?: number | undefined | null;

  /** Size of the stop circle at the end of track */
  trackStopIndicatorSize?: number | undefined | null;

  /** Thumb props */
  thumbColor?: ColorValue | undefined | null;
  thumbWidth?: number | undefined | null;
  thumbHeight?: number | undefined | null;
  thumbElevation?: number | undefined | null;
  thumbBorderColor?: ColorValue | undefined | null;
  thumbBorderWidth?: number | undefined | null;

  /** Space between thumb and track on both sides of thumb */
  thumbTrackGapSize?: number | undefined | null;

  /**
   * Behavior of label displaying current value
   * Options are:
   * - 'always-visible': The label is always visible
   * - 'floating': The label floats above the thumb when sliding
   * - 'never-visible': The label is never visible, even when sliding
   * */
  labelBehavior?: SliderLabelBehavior | undefined | null;

  /** Tick props */

  /** Color of ticks, regardless of active or inactive */
  tickColor?: ColorValue | undefined | null;
  activeTickColor?: ColorValue | undefined | null;
  inactiveTickColor?: ColorValue | undefined | null;

  /** Border radius of ticks in active section */
  activeTickRadius?: number | undefined | null;

  /** Border radius of ticks in inactive section */
  inactiveTickRadius?: number | undefined | null;

  /**
   * Determines how the ticks are visible
   * Options are:
   * - 'auto-limit': Ticks are drawn. If there are too many, the maximum allowed number will be drawn
   * - 'auto-hide': Ticks are drawn. If there are too many, they are all hidden
   * - 'hidden': Ticks are always hidden (allows for snapping to points without showing ticks)
   */
  tickVisibilityMode?: SliderTickVisibility | undefined | null;

  disabled?: boolean | undefined | null;

  /**
   * Controls if the active section is tied to the center of track
   * Example: Panning audio from left to right speakers
   * */
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
