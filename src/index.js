import {NativeModules, Platform} from 'react-native';

const CommonIntents  = NativeModules.RNIntents;


const noIOS = () => console.warn('Native Common intents cannot be used for iOS.');

const iosOS = {
    ImagePicker: noIOS,
    openWebPage: noIOS,
    openDateSettings: noIOS,
    openWifiSettings: noIOS,
    openBluetoothSettings: noIOS,
    openAirplaneModeSettings: noIOS,
    performWebSearch: noIOS
};

const Intents = Platform.OS === 'ios' ? iosOS : {

    ImagePicker : CommonIntents.ImagePick,
    openWeb: url => {
        if(url==null || url==""){
            throw new Error('Please enter right url');
        }
        CommonIntents.openWebPage(url);
    },
    openDateSettings : CommonIntents.openDateSettings,
    openWifiSettings : CommonIntents.openWifiSettings,
    openBluetoothSettings : CommonIntents.openBluetoothSettings,
    openAirplaneModeSettings : CommonIntents.openAirplaneModeSettings,
    performWebSearch : query => {
        if(query== ""){
            throw new Error("Please Enter Text to search");
        }
        CommonIntents.performWebSearch(query);
    }

}
export default Intents;
