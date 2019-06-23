# react-native-common-intents
React Native Android Module's Android Common intents actions like for   
##### Image picker from Gallery  
##### Open date settings  
##### Open wifi settings    
##### Open Bluetooth settings  
##### Open airplanemode settings  
##### Open browser with input url   
##### Make web search by entering text  
##### Create alarm  
##### Create timer
##### dial phone number    


## How this Module is useful ?
lets say you want to pick image from gallery and want to upload it, you want user to open wifi settings , date settings etc directly from your app , you want to load a web url in browser or you want to make some web search all these things can be achieved with this library as shown below.



# Installation
 `npm install react-native-common-intents --save`  
       or  
  `yarn add react-native-common-intents`
    
#### Add it to your android project

Automatic link:  

    react-native link react-native-common-intents  
   
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
            new IntentModulePackage() // <------ add this line to your MainApplication class
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
    <uses-permission android:name="com.android.alarm.permission.SET_ALARM" />
  ...
  ```
 
 ##  Import Library

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

## Example/ open Web Page

```
RNIntents.openWeb("https://google.com");
```

## Example/ Perform Web Search

```
RNIntents.performWebSearch("Winter is coming");
```

## Example/ Open DateSettings

```
RNIntents.openDateSettings();
```

## Example/ Open WifiSettings

```
RNIntents.openWifiSettings();
```
## Example/ open AirplaneModeSettings

```
RNIntents.openAirplaneModeSettings();
```

## Example/ open BluetoothSettings

```
RNIntents.openBluetoothSettings();
```

## Example/ Dial Phone Number

```
RNIntents.dialNumber("9086090860");
```


## Example/ Start Timer
###### you can start a timer in background without opening app or with opening app based
on the boolean value passed as true or false

 It takes 3 parameters
 Message as string , seconds as int , boolean value

```
RNIntents.startTimer(String message, int seconds,boolean value) 
```


## Example/ Create alarm


### It takes 3 parameters
#### Message as string , hours as int , minutes as int
```
RNIntents.createAlarm(String message, int hour, int minutes);
```

## Example


```diff

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, Button} from 'react-native';
import RNIntents from 'react-native-common-intents';



export default class App extends Component {
  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>React-native-common-intents</Text>
        <Button  title =" pick image from gallery" onPress = {() => {


RNIntents.ImagePicker(url => {
  console.log("image uri",url);
},(err) => {
  console.log("error",err);
});

  }  


}  />
<Button title ="open Date settings" onPress = { () => {
  RNIntents.openDateSettings();
}}/>

<Button title ="open Wifi settings" onPress = { () => {
  RNIntents.openWifiSettings();
}}/>

<Button title ="open Airplanemode Settings " onPress = { () => {
  RNIntents.openAirplaneModeSettings();
  console.log("hehehehe")
}}/>

<Button title ="open Bluetooth Settings " onPress = { () => {
  RNIntents.openBluetoothSettings();
}}/>
<Button title ="open browser" onPress = { () => {
  RNIntents.openWeb("http://zigtor.com");
}}/>

<Button title ="Perform web search " onPress = { () => {
  RNIntents.performWebSearch("Winter is coming");
}}/>

      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});

```
