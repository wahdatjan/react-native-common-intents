# react-native-common-intents
React Native Android Module to use to use Android's Common intents actions like for Image picker from Gallery, open date settings,open wifi settings , open Bluetooth settings, open airplanemode settings, open browser with input url or to make web search by entering text.


## How this Module is useful ?
lets say you want to pick image from gallery and want to upload it, you want user to open wifi settings , date settings etc directly from your app , you want to load a web url in browser or you want to make some web search all these things can be achieved with this library.

# Installation
 `npm install react-native-common-intents --save`  
       or  
  `yarn add react-native-common-intents`
    
#### Add it to your android project

Automatic link:  

    `react-native link react-native-common-intents`  
   
Manual link:

 - in `android/app/build.gradle` 
 
     ```
     - dependencies {  
           implementation "com.facebook.react:react-native:+"  // From node_modules  
         
           + implementation project(':react-native-common-intents')  // <------ add this line to your build.gradle file
       } 
       
       
       
 - in  `android/settings.gradle`  
  ```
  
  include ':app'
  // <------ add below two lines to your settings.gradle------>
 + include ':react-native-common-intents' // 
 + project(':react-native-common-intents').projectDir = new File(rootProject.projectDir, '../node_modules/react-native-common-intents/android')// 

```
 - in `MainApplication.java`
 
 ```
     @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
        +    new IntentModulePackage() // <------ add this line to your MainApplication class
      );
    }
 ```
 ## Permissions
 ###### Add this to your `AndroidManifest.xml`  
 ```
 ...
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />
  ...
  ```
 
 ## Import Library
 `import RNIntents from 'react-native-common-intents';`

 
 ## Example/ Image Picker
 
 RNIntents.ImagePicker Module has two arguments first one will give you the **url** of the  
 image that you will choose from gallery and 2nd argument will give you the **error** which may occur  
 ```
 RNIntents.ImagePicker(url => {
  console.log("image uri",url);
},(err) => {
  console.log("eeeoe",err);
});


```
