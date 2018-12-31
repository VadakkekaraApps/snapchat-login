
# react-native-snapchat-login

## Getting started

`$ npm install react-native-snapchat-login --save`

### Mostly automatic installation

`$ react-native link react-native-snapchat-login`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-snapchat-login` and add `RNSnapchatLogin.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSnapchatLogin.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.jose2007kj.reactnative.RNSnapchatLoginPackage;` to the imports at the top of the file
  - Add `new RNSnapchatLoginPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-snapchat-login'
  	project(':react-native-snapchat-login').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-snapchat-login/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-snapchat-login')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNSnapchatLogin.sln` in `node_modules/react-native-snapchat-login/windows/RNSnapchatLogin.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Snapchat.Login.RNSnapchatLogin;` to the usings at the top of the file
  - Add `new RNSnapchatLoginPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNSnapchatLogin from 'react-native-snapchat-login';

// TODO: What to do with the module?
RNSnapchatLogin;
```
  