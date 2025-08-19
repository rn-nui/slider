import { StyleSheet, Text, ScrollView, PlatformColor } from 'react-native';
import { Slider } from '@rn-nui/slider';

export default function App() {
  return (
    <ScrollView
      style={styles.container}
      contentContainerStyle={styles.contentContainer}
    >
      <Text style={styles.text}>Steps</Text>
      <Slider maxValue={10} minValue={0} stepSize={1} />

      <Text style={styles.text}>Centered</Text>
      <Slider maxValue={100} minValue={-100} value={0} centered />

      <Text style={styles.text}>Icons</Text>
      <Slider
        size="lg"
        trackIconActiveStart="outline_music_note_24"
        trackIconActiveEnd="outline_music_note_24"
        trackIconActiveColor="white"
        trackIconInactiveStart="outline_music_note_24"
        trackIconInactiveEnd="outline_music_note_24"
        trackIconInactiveColor="blue"
        style={{ marginBottom: 20 }}
      />

      <Text style={styles.text}>Stop indicator</Text>
      <Slider size="md" trackStopIndicatorSize={20} />

      <Text style={styles.text}>Label visibility - always</Text>
      <Slider size="sm" labelBehavior="always-visible" />

      <Text style={styles.text}>Label visibility - never</Text>
      <Slider size="sm" labelBehavior="never-visible" />

      <Text style={styles.text}>Label visibility - when active</Text>
      <Slider size="sm" labelBehavior="floating" style={{ marginBottom: 20 }} />

      <Text style={styles.text}>Disabled</Text>
      <Slider size="sm" disabled style={{ marginBottom: 20 }} />

      <Text style={styles.text}>Tick visibility - hidden</Text>
      <Slider size="sm" tickVisibilityMode="hidden" stepSize={0.05} />

      <Text style={styles.text}>Tick visibility - auto-hide</Text>
      <Slider size="sm" tickVisibilityMode="auto-hide" stepSize={0.02} />

      <Text style={styles.text}>Tick visibility - auto-limit</Text>
      <Slider size="sm" tickVisibilityMode="auto-limit" stepSize={0.02} />

      <Text style={styles.text}>xsmall slider</Text>
      <Slider size="xs" style={styles.slider} />
      <Text style={styles.text}>small slider</Text>
      <Slider size="sm" style={styles.slider} />
      <Text style={styles.text}>medium slider</Text>
      <Slider size="md" style={styles.slider} />
      <Text style={styles.text}>large slider</Text>
      <Slider size="lg" style={styles.slider} />
      <Text style={styles.text}>xlarge slider</Text>
      <Slider size="xl" style={styles.slider} />

      <Text style={styles.text}>Custom thumb border</Text>
      <Slider
        thumbBorderColor="red"
        thumbBorderWidth={2}
        thumbWidth={15}
        thumbColor="transparent"
      />

      <Text style={styles.text}>Custom step colors</Text>
      <Slider
        inactiveTickColor="black"
        stepSize={0.1}
        value={0.5}
        activeTickColor="blue"
      />

      <Text style={styles.text}>Custom track colors</Text>
      <Slider activeTrackColor="blue" inactiveTrackColor="gray" />

      <Text style={styles.text}>Customize the thumb</Text>
      <Slider thumbHeight={66} thumbColor="pink" />
    </ScrollView>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  contentContainer: {
    paddingVertical: 30,
  },
  slider: {
    width: '100%',
  },
  text: {
    fontSize: 15,
    paddingHorizontal: 16,
    paddingBottom: 4,
  },
});
