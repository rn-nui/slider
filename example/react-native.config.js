const path = require('path');
const pkg = require('../package.json');

module.exports = {
  project: {
    ios: {
      automaticPodsInstallation: true,
    },
  },
  dependencies: {
    [pkg.name]: {
      root: path.join(__dirname, '..'),
      platforms: {
        // Codegen script incorrectly fails without this
        // So we explicitly specify the platforms with empty object
        ios: {},
        android: {
          componentDescriptors: ['NUISliderComponentDescriptor'],
          cmakeListsPath: '../../android/src/main/jni/CMakeLists.txt',
        },
      },
    },
  },
};
