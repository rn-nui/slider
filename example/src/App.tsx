import { View, StyleSheet } from 'react-native';
import { SliderView } from '@rn-nui/slider';

export default function App() {
  return (
    <View style={styles.container}>
      <SliderView color="#00000000" style={styles.box} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: '100%',
  },
});
