module.exports = {
  dependency: {
    platforms: {
      android: {
        componentDescriptors: ['NUISliderComponentDescriptor'],
        cmakeListsPath: '../android/src/main/jni/CMakeLists.txt',
      },
    },
  },
};
